package Simulation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/** StationsLogic
 * @atribute
 */
public class StationsLogic {
    private Hashtable<Character, Station> stationsToControl;

    private Hashtable <String, Hashtable < Character, Hashtable < Character, Integer > > > departureTimeDifferenceNeed;
    private Hashtable <String, Hashtable< String, ArrayList<Integer> >> travelingTimePerStation; //Not so useful, now that i think about it

    private Hashtable <Character, Hashtable< Character, List <Integer> >> avaibleDepartureTimes; //Only need it to make calculos


    /**
     * @param pDepartureStation Char witch the id ot the 'Station of departure'
     * @param pDestinyStation Char with the id of the 'Station of arrival'
     * @param pSecondDeparture Integer with the second select for the departure of the trip
     * This method makes an update based on the router indicated by pRouteId in time indicated by pSecondDeparture.
    */
    public void updateDepartureTime(char pDepartureStation, char pDestinyStation, int pSecondDeparture){
        //Creates a hash with the conflicting trips of other stations, based on where is the trip starting and where is going
        Hashtable < Character, Hashtable < Character, Integer > > stationsToUpdate =  this.departureTimeDifferenceNeed.get(new StringBuilder().append(pDepartureStation).append(pDestinyStation).toString());

        //Takes the idCode for every Station with trips that cause conflicts
        for (char conflictingStationID : stationsToUpdate.keySet()){
            //Gets a hash with 'K': Destiny V: 'ArrayList of departure times'

            Hashtable <Character, List <Integer> > departureTimesToUpdate = avaibleDepartureTimes.get(conflictingStationID); //stationsToControl.get(conflictingStationID).getAvaibleDepartureTimes();
            //Gets a hash with 'K': Destiny V: 'update to be make'
            Hashtable <Character, Integer> timeUpdatePerDestiny = stationsToUpdate.get(conflictingStationID);

            //Each destiny of the conflicting station its checked
            for (char conflictingDestiny: timeUpdatePerDestiny.keySet()){
                int conflictTimeBegin = timeUpdatePerDestiny.get(conflictingDestiny) + pSecondDeparture;
                //All departure between this last values will definitely cause a collision

                List<Integer> departureTimes = departureTimesToUpdate.get(conflictingDestiny);
                int current = 0;

                //Checks all times till finding a time that cause problems
                while (current < departureTimes.size()) {
                    if (conflictTimeBegin <= departureTimes.get(current) + 29)
                        break;
                    current++;
                }

                //Gets the value that has to be add to make sure no collision will happen
                int update = conflictTimeBegin - departureTimes.get(current) + 1;
                while (current < departureTimes.size()) {
                    departureTimes.set(current, departureTimes.get(current) + update);
                    current++;
                }

                //Saves the new list
                departureTimesToUpdate.replace(conflictingDestiny, departureTimes);
            }
        }
    }

    /*
    public void test (){
        for (char currentStationID: stationsToControl.keySet()){
            Station currentStation = stationsToControl.get(currentStationID);

            Hashtable<Character, String> currentPaths = currentStation.getPaths();

            currentStation.getTimeDistance();

        }
    }
    */

    public Hashtable<Character, Station> getStationsToControl() {
        return stationsToControl;
    }

    public void setStationsToControl(Hashtable<Character, Station> stationsToControl) {
        this.stationsToControl = stationsToControl;
    }

    public Hashtable<String, Hashtable<Character, Hashtable<Character, Integer>>> getDepartureTimeDifferenceNeed() {
        return departureTimeDifferenceNeed;
    }

    public void setDepartureTimeDifferenceNeed(Hashtable<String, Hashtable<Character, Hashtable<Character, Integer>>> departureTimeDifferenceNeed) {
        this.departureTimeDifferenceNeed = departureTimeDifferenceNeed;
    }

    public Hashtable<String, Hashtable<String, ArrayList<Integer>>> getTravelingTimePerStation() {
        return travelingTimePerStation;
    }

    public void setTravelingTimePerStation(Hashtable<String, Hashtable<String, ArrayList<Integer>>> travelingTimePerStation) {
        this.travelingTimePerStation = travelingTimePerStation;
    }
}
