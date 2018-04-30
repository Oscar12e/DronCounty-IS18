package Simulation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class StationsLogic {
    private Hashtable<Character, Station> stationsToControl;

    private Hashtable <String, Hashtable < Character, Hashtable < Character, Integer > > > differenceOfTime;
    private Hashtable <String, Hashtable< String, ArrayList<Integer> >> travelingTimePerStation;
    private Hashtable <Character, ArrayList<Trip> > pendingTripsPerStation;

    //Muy pediente de cambios - Óscar a cambio de arreglar el problema que causo Ok :(
    /**
     * @param pRouteId String witch the code 'Station of departure' plus 'Station of arrival'
     * @param pSecondDeparture Integer with the second select for the departure of the trip
     * This method makes an update based on the router indicated by pRouteId in time indicated by pSecondDeparture.
    */
    public void updateDepartureTime(String pRouteId, int pSecondDeparture){
        //Creates a hash with station
        Hashtable < Character, Hashtable < Character, Integer > > stationsToUpdate =  this.differenceOfTime.get(pRouteId);

        //Takes the idCode for every Station
        for (char conflictingStationID : stationsToUpdate.keySet()){
            //Gets the station to use update its data
            Station conflictingStation = stationsToControl.get(conflictingStationID);

            //Hash with all the routes that creates problems
            Hashtable < Character, Integer > conflictingRoutes = stationsToUpdate.get(conflictingStationID);

            //List of time that has to be updated
            Hashtable <Character, ArrayList <Integer> > departureTimeToUpdate = conflictingStation.getDepartureTime();

            //Each destiny station its checked
            for (char conflictingDestiny: conflictingRoutes.keySet()){
                //Gets the time of departure to the conflicting station
                List<Integer> conflictingDepartureTime = departureTimeToUpdate.get(conflictingDestiny);

                //Here we erase all the times that will definitely cause a collision
                ereseaseConflictingTimes(conflictingDepartureTime, pSecondDeparture);

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
     */
    public List<Integer> ereseaseConflictingTimes(List<Integer> departureTimeList, int pSec) {

        departureTimeList.remove(Utilitarian.closestLowerNumber(departureTimeList, pSec));
        departureTimeList.remove(Utilitarian.closestLowerNumber(departureTimeList, pSec + 29));

        int updateValue = pSec - Utilitarian.closestLowerNumber(departureTimeList, pSec);

        if (updateValue < 30)
            for (int current = 0; current < departureTimeList.size(); current++){
                if (departureTimeList.get(current) > pSec )
                    departureTimeList.set(current, departureTimeList.get(current) + updateValue);
            }
        return departureTimeList;
    }




}
