package Simulation;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;


/** StationsLogic
 * @atribute
 */
public class StationsLogic {
    private Hashtable<Character, Station> stationsToControl;
    private List<Station> temporaryStationsList; //Se va a modificar, eso de fijo. Profe si lee esto en la revisión del sábado ponganos 0 de una
    private List<String> travelingBetweenStations = new ArrayList<String>(); //Like AB AC AD AE BE BC

    private Hashtable <String, Hashtable < String, Integer  > > departureTimeDifferenceNeed2 = new Hashtable <String, Hashtable < String, Integer  > >();
    private Hashtable <String, Hashtable< String, ArrayList<Integer> >> travelingTimePerStation; //Not so useful, now that i think about it
    private Hashtable <Character, Hashtable< Character, List <Integer> >> availableDepartureTimes = new Hashtable <Character, Hashtable< Character, List <Integer> >>(); //Only need it to make calculationsavaibleDepartureTimes
    private Hashtable <String, List<String> > routesUsedByTravels = new  Hashtable <String, List<String> >();


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
    public void updateDepartureTime(char pDepartureStation, char pDestinyStation, int pSecondDeparture){
        //Creates a hash with the conflicting trips of other stations, based on where is the trip starting and where is going
        Hashtable < String, Integer  > stationsToUpdate =  this.departureTimeDifferenceNeed2.get(new StringBuilder().append(pDepartureStation).append(pDestinyStation).toString());

        //Takes the idCode for every Station with trips that cause conflicts, so it can use it to take data from the conflicting Station
        for (String conflictingStationRoute : stationsToUpdate.keySet()){
            char conflictingStationID = conflictingStationRoute.charAt(0);
            char conflictingDestiny = conflictingStationRoute.charAt(1);
            //Gets a hash with 'K': Destiny V: 'ArrayList of departure times'
            Hashtable <Character, List <Integer> > departureTimesToUpdate = availableDepartureTimes.get(conflictingStationID); //stationsToControl.get(conflictingStationID).getAvaibleDepartureTimes();

            //Gets a hash with 'K': Destiny V: 'update to be make'
            //V: I's an integer, it can be positive or negative
            //Hashtable <Character, Integer> timeUpdatePerDestiny = stationsToUpdate.get(conflictingStationRoute);

            //Each destiny of the conflicting station is checked
            //for (char conflictingDestiny: timeUpdatePerDestiny.keySet()){
                int conflictTime = stationsToUpdate.get(conflictingStationRoute) + pSecondDeparture;
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

            availableDepartureTimes.replace(conflictingStationID, departureTimesToUpdate);
        }

    }

    public void setTravelingBetweenStations(){
        for (char currentStationId: stationsToControl.keySet()){
            Station currentStation = stationsToControl.get(currentStationId);


            for (int i = 0; i< 5; i++){
                this.travelingBetweenStations.add(new StringBuilder().append(currentStationId).append("abc$#".charAt(i)).toString());
            }

        }
    }

    public void setRoutesUsedByTravels(){
        for (String tripCode: this.travelingBetweenStations){
            Station currentStation = stationsToControl.get(tripCode.charAt(0));
            String route = currentStation.getPaths().get(tripCode.charAt(1));

            if (route == null)
                continue;

            char code[] = {tripCode.charAt(0),' '};
            //Code From -> To
            for (int index = 0; index < route.length(); index++){
                code[1] = route.charAt(index);
                System.out.println(route);

                if (routesUsedByTravels.containsKey(String.valueOf(code))) {
                    //Change me
                    List<String> savedList = routesUsedByTravels.get(String.valueOf(code));
                    savedList.add(tripCode);
                    routesUsedByTravels.replace(String.valueOf(code), savedList);
                } else {
                    routesUsedByTravels.put(String.valueOf(code), new ArrayList<String>() {{add(tripCode);}});
                }
                code[0] = route.charAt(index);
            }
        }

        System.out.println(":)");
        for (String k: routesUsedByTravels.keySet()){
            System.out.println(k);
            for (String i: routesUsedByTravels.get(k))
                System.out.println("\t" + i);
            System.out.println("\n");
        }
    }

    public void setDepartureTimeDifferenceNeed2(){
        //Salida+Destino -> Salida -> Destino -> Tiempo
        //private Hashtable <String, Hashtable < Character, Hashtable < Character, Integer > > > departureTimeDifferenceNeed;
        this.departureTimeDifferenceNeed2 = new  Hashtable <String, Hashtable < String, Integer > >();

        Station stationA, stationB;
        //Arc between stations
        for (String currentArc: routesUsedByTravels.keySet()){
            System.out.println(currentArc);

            List<String> pathsThatUseCurrentArc = routesUsedByTravels.get(currentArc);

            //For each path that its held in here
            for (int currentPathRoute = 0; currentPathRoute < pathsThatUseCurrentArc.size(); currentPathRoute++){
                String currentDeparturePath = pathsThatUseCurrentArc.get(currentPathRoute);

                for (int currentCompare = 0; currentCompare < pathsThatUseCurrentArc.size(); currentCompare++){
                    if (currentPathRoute == currentCompare)
                        continue;
                    else if (!this.departureTimeDifferenceNeed2.containsKey(currentDeparturePath))
                        this.departureTimeDifferenceNeed2.put(currentDeparturePath, new Hashtable < String, Integer >());

                    test2_aux(currentDeparturePath, pathsThatUseCurrentArc.get(currentCompare));
                }
            }

            }

            for (String i: departureTimeDifferenceNeed2.keySet()){
                System.out.println(i);
                for (String j: departureTimeDifferenceNeed2.get(i).keySet()){
                    System.out.println("\t" + j + ": " +  departureTimeDifferenceNeed2.get(i).get(j));
                }
            }

            System.out.println("\n");
        }



    public void test2_aux(String fromStation, String toStation){
        Hashtable<String, Integer> conflitctosA =  this.departureTimeDifferenceNeed2.get(fromStation);
        int diferencia = this.stationsToControl.get(fromStation.charAt(0)).getTimeDistance().get(toStation.charAt(1)) - this.stationsToControl.get(toStation.charAt(0)).getTimeDistance().get(toStation.charAt(1));
        conflitctosA.put(toStation, diferencia);
        this.departureTimeDifferenceNeed2.replace(fromStation, conflitctosA);
    }


    public Hashtable<Character, Station> getStationsToControl() {
        return stationsToControl;
    }

    public void setStationsToControl(Hashtable<Character, Station> stationsToControl) {
        this.stationsToControl = stationsToControl;

        /* delete it, it has to be in other function that works with stationToControl and timeMax*/
        for (char id: stationsToControl.keySet()){
            availableDepartureTimes.put(id, new Hashtable<Character, List<Integer>>(){{
                    for (char id2 : stationsToControl.keySet()) {
                        ArrayList<Integer> l2 = new ArrayList<Integer>() {{
                            add(0);
                            add(30);
                            add(60);
                            add(90);
                            add(120);
                        }};
                        put(id2, l2);
                    }
                }});
        }

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
