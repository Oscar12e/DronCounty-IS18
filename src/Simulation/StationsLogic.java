package Simulation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/** StationsLogic
 * @atribute
 */
public class StationsLogic {
    private Hashtable<Character, Station> stationsToControl;
    private List<Station> temporaryStationsList; //Se va a modificar, eso de fijo. Profe si lee esto en la revisión del sábado ponganos 0 de una

    private Hashtable <String, Hashtable < Character, Hashtable < Character, Integer > > > departureTimeDifferenceNeed;
    private Hashtable <String, Hashtable< String, ArrayList<Integer> >> travelingTimePerStation; //Not so useful, now that i think about it

    private Hashtable <Character, Hashtable< Character, List <Integer> >> availableDepartureTimes; //Only need it to make calculationsavaibleDepartureTimes



    /**
     *
     * @param pDepartureStation: The station form which the trip its going to sail (i know sailing is for boats)
     * @param pDestinyStation: The station from which the trip is going to
     * @param pSecondDeparture: The second the trip is leaving
     */
    public void sendTrips(char pDepartureStation, char pDestinyStation, int pSecondDeparture){
        Station currentStation  = stationsToControl.get(pDepartureStation);

        Hashtable <Integer, ArrayList <Trip> > currentSchedule = currentStation.getSchedule();

        Trip sendingTrip = currentStation.getTripsToSchedule().get(pDestinyStation).remove(0);

        if (currentSchedule.containsKey(pSecondDeparture)){
            ArrayList<Trip> temporaryTripList = currentSchedule.get(pSecondDeparture);
            temporaryTripList.add(0, sendingTrip);
            currentSchedule.replace(pSecondDeparture, temporaryTripList);
        } else {
            ArrayList<Trip> temporaryTripList = new ArrayList<Trip>(){{add(sendingTrip);}};
            currentSchedule.put(pSecondDeparture, temporaryTripList);
        }
        //Updates the desparture times for all stations
        updateDepartureTime(pDepartureStation, pDestinyStation, pSecondDeparture);
    }


    public void cancelTrip(char pDepartureStation, char pDestinyStation, int pSecondDeparture){
        Station currentStation  = stationsToControl.get(pDepartureStation);

        Hashtable <Integer, ArrayList <Trip> > currentSchedule = currentStation.getSchedule();

        Trip cancelledTrip = currentSchedule.get(pSecondDeparture).remove(0);
        currentStation.getTripsToSchedule().get(pDestinyStation).add(cancelledTrip);

        //Corrects the update here, method is need
    }

    /**
     * @param pDepartureStation Char witch the id ot the 'Station of departure'
     * @param pDestinyStation Char with the id of the 'Station of arrival'
     * @param pSecondDeparture Integer with the second select for the departure of the trip
     * This method makes an update based on the router indicated by pRouteId in time indicated by pSecondDeparture.
    */
    private void updateDepartureTime(char pDepartureStation, char pDestinyStation, int pSecondDeparture){
        //Creates a hash with the conflicting trips of other stations, based on where is the trip starting and where is going
        Hashtable < Character, Hashtable < Character, Integer > > stationsToUpdate =  this.departureTimeDifferenceNeed.get(new StringBuilder().append(pDepartureStation).append(pDestinyStation).toString());

        //Takes the idCode for every Station with trips that cause conflicts, so it can use it to take data from the conflicting Station
        for (char conflictingStationID : stationsToUpdate.keySet()){

            //Gets a hash with 'K': Destiny V: 'ArrayList of departure times'
            Hashtable <Character, List <Integer> > departureTimesToUpdate = availableDepartureTimes.get(conflictingStationID); //stationsToControl.get(conflictingStationID).getAvaibleDepartureTimes();

            //Gets a hash with 'K': Destiny V: 'update to be make'
            //V: I's an integer, it can be positive or negative
            Hashtable <Character, Integer> timeUpdatePerDestiny = stationsToUpdate.get(conflictingStationID);

            //Each destiny of the conflicting station is checked
            for (char conflictingDestiny: timeUpdatePerDestiny.keySet()){
                int conflictTime = timeUpdatePerDestiny.get(conflictingDestiny) + pSecondDeparture;
                //All departure between this last values will definitely cause a collision

                List<Integer> departureTimes = departureTimesToUpdate.get(conflictingDestiny);


                //We use two cycles cos it gives a better worst case scenario
                int current = 0;
                //Checks all times till finding a time that cause problems
                while (current < departureTimes.size()) {
                    if (conflictTime <= departureTimes.get(current) + 29)
                        break;
                    current++;
                }

                //Gets the value that has to be add to make sure no collision will happen
                int update = conflictTime - departureTimes.get(current) + 1;
                while (current < departureTimes.size()) {
                    departureTimes.set(current, departureTimes.get(current) + update);
                    current++;
                }

                //Saves the new list
                departureTimesToUpdate.replace(conflictingDestiny, departureTimes);
            }
            availableDepartureTimes.replace(conflictingStationID, departureTimesToUpdate);
        }

    }


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

    public List<String> getTripsPerStation(){
        return new ArrayList<String>();
    }


    public Hashtable<Character, Hashtable<Character, List<Integer>>> getAvailableDepartureTimes() {
        return availableDepartureTimes;
    }

    public void setAvaibleDepartureTimes(Hashtable<Character, Hashtable<Character, List<Integer>>> avaibleDepartureTimes) {
        this.availableDepartureTimes = avaibleDepartureTimes;
    }

}
