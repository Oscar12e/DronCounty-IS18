package Simulation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/** StationsLogic
 * @atribute
 */
public class StationsLogic {
    private Hashtable<Character, Station> stationsToControl;

    private Hashtable <String, Hashtable < Character, Hashtable < Character, Integer > > > differenceOfTime;
    private Hashtable <String, Hashtable< String, ArrayList<Integer> >> travelingTimePerStation;
    private Hashtable <Character, ArrayList<Trip> > pendingTripsPerStation;
    private Hashtable <String, String> arrivalPortPerTrip;

    /**
     * @param pRouteId String witch the code 'Station of departure' plus 'Station of arrival'
     * @param pSecondDeparture Integer with the second select for the departure of the trip
     * This method makes an update based on the router indicated by pRouteId in time indicated by pSecondDeparture.
    */
    public void updateDepartureTime(String pRouteId, int pSecondDeparture){
        //Creates a hash with the conflicting trips of other stations, based on where is the trip starting and where is going
        Hashtable < Character, Hashtable < Character, Integer > > stationsToUpdate =  this.differenceOfTime.get(pRouteId);

        //Takes the idCode for every Station that cause conflicts
        for (char conflictingStationID : stationsToUpdate.keySet()){
            Station conflictingStation = stationsToControl.get(conflictingStationID); //Gets the station to use update its data

            Hashtable < Character, Integer > conflictingRoutes = stationsToUpdate.get(conflictingStationID); //Hash with all the routes that creates problems

            //List of time that has to be updated per stations destinies
            Hashtable <Character, ArrayList <Integer> > departureTimeToUpdate = conflictingStation.getDepartureTime();

            //Each destiny station of the conflicting station its checked
            for (char conflictingDestiny: conflictingRoutes.keySet()){
                //Gets the time of departure to the conflicting station
                List<Integer> conflictingDepartureTime = departureTimeToUpdate.get(conflictingDestiny);

                //Here we erase all the times that will definitely cause a collision
                if (arrivalPortPerTrip.get(pRouteId).compareTo(new StringBuilder().append(conflictingStationID).append(conflictingDestiny).toString()) == 0)
                    conflictingDepartureTime = eraseConflictingTimesOnLanding(conflictingDepartureTime, pSecondDeparture);
                else
                    conflictingDepartureTime = eraseConflictingTimesOnPassing(conflictingDepartureTime, pSecondDeparture);

                //Saves the departure time
                departureTimeToUpdate.put(conflictingDestiny, (ArrayList<Integer>) conflictingDepartureTime);
            }
        }
    }

    /**
     *
     * @param departureTimeList List of departure time from a station that may cause conflicting with te actual trip
     * @param pSec Second in which the trip is leaving its station.
     * @return List modified
     * This method makes one call to the Utilitarian method closestLowerNumber, for passingTime. So conflicting times can
     * be erase it. Then updates the rest.
     */
    private List<Integer> eraseConflictingTimesOnLanding(List<Integer> departureTimeList, int pSec) {
        int updateValue = pSec - Utilitarian.closestLowerNumber(departureTimeList, pSec + 29);

        int closestNextDeparture = Utilitarian.closestLowerNumber(departureTimeList, pSec + 29);

        if (Math.abs(pSec - closestNextDeparture) < 30 )
            departureTimeList.remove(closestNextDeparture);

        departureTimeList.remove(Utilitarian.closestLowerNumber(departureTimeList, pSec));

        if (updateValue < 30)
            for (int current = 0; current < departureTimeList.size(); current++){
                if (pSec <= departureTimeList.get(current))
                    departureTimeList.set(current, departureTimeList.get(current) + updateValue);
            }
        return departureTimeList;
    }

    /**
     *
     * @param departureTimeList List of departure time from a station that may cause conflicting with te actual trip
     * @param pSec Second in which the trip is leaving its station.
     * @return List modified
     * This method makes too calls to the Utilitarian method closestLowerNumber, arrivalTime and landingTime respectably
     */
    private List<Integer> eraseConflictingTimesOnPassing(List<Integer> departureTimeList, int pSec) {
        //Takes the closest number for the next
        int updateValue = pSec - Utilitarian.closestLowerNumber(departureTimeList, pSec + 29) + 1;
        int closestDeparture = Utilitarian.closestLowerNumber(departureTimeList, pSec);

        if (Math.abs(pSec - closestDeparture) < 30 )
            departureTimeList.remove(closestDeparture);

            departureTimeList.remove(Utilitarian.closestLowerNumber(departureTimeList, pSec));

        if (updateValue < 30)
            for (int current = 0; current < departureTimeList.size(); current++){
                if (pSec <= departureTimeList.get(current))
                    departureTimeList.set(current, departureTimeList.get(current) + updateValue);
            }
        return departureTimeList;
    }


}
