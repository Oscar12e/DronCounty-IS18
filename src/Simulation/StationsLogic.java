package Simulation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


/** StationsLogic
 * @atribute
 */
public class StationsLogic {
    private int worstTimeAccepted;
    private List<String> travelingBetweenStations; //Like AB AC AD AE BE BC

    private Hashtable <Character, Station> stationsToControl;
    private Hashtable <String, Hashtable < String, Integer  > > departureTimeDifferenceNeed;
    private Hashtable <Character, Hashtable< Character, List <Integer> >> availableDepartureTimes; //Only need it to make calculationsavaibleDepartureTimes
    private Hashtable <String, List<String> > routesUsedByTravels;

    public void preProcessStations( Hashtable <Character, Station> pStationsToControl ) {
        this.stationsToControl = pStationsToControl;
        this.availableDepartureTimes = new Hashtable <>();
        this.stationsToControl = new Hashtable<>();
        this.travelingBetweenStations = new ArrayList<>();
        this.departureTimeDifferenceNeed = new Hashtable<>();
        this.routesUsedByTravels = new  Hashtable <>();

        setRoutesUsedByTravels();
        setAvaibleDepartureTimes();

        setTravelingBetweenStations();

        setDepartureTimeDifferenceNeed();
        setWorstTimeAccepted();


    }

    /**
     * @param pDepartureStation: The station form which the trip its going to sail (i know sailing is for boats)
     * @param pDestinyStation: The station from which the trip is going to
     * @param pSecondDeparture: The second the trip is leaving
     */
    public boolean sendTrips(char pDepartureStation, char pDestinyStation, int pSecondDeparture){ //O(C)
        Station currentStation  = stationsToControl.get(pDepartureStation); // 3

        Hashtable <Integer, ArrayList <Trip> > currentSchedule = currentStation.getSchedule(); //2

        currentStation.getTripsToSchedule().get(pDestinyStation);
        Trip sendingTrip = currentStation.getTripsToSchedule().get(pDestinyStation).remove(0);

        //Se elimina de los tiempos disponbles
        availableDepartureTimes.get(pDepartureStation).get(pDestinyStation).remove(pSecondDeparture);

        if (currentSchedule.containsKey(pSecondDeparture)){
            ArrayList<Trip> temporaryTripList = currentSchedule.get(pSecondDeparture);
            temporaryTripList.add(0, sendingTrip);
            currentSchedule.replace(pSecondDeparture, temporaryTripList);
        } else {
            ArrayList<Trip> temporaryTripList = new ArrayList<Trip>(){{add(sendingTrip);}};
            currentSchedule.put(pSecondDeparture, temporaryTripList);
        }
        //Updates the desparture times for all stations
        return updateDepartureTime(pDepartureStation, pDestinyStation, pSecondDeparture, true);
    }


    public void cancelTrip(char pDepartureStation, char pDestinyStation, int pSecondDeparture){
        Station currentStation  = stationsToControl.get(pDepartureStation);

        Hashtable <Integer, ArrayList <Trip> > currentSchedule = currentStation.getSchedule();

        Trip cancelledTrip = currentSchedule.get(pSecondDeparture).remove(0);
        currentStation.getTripsToSchedule().get(pDestinyStation).add(cancelledTrip);

        List<Integer> departureTimes = availableDepartureTimes.get(pDepartureStation).get(pDestinyStation);

        int timeIndex = 0;

        while (timeIndex < departureTimes.size()){ //n
            if (departureTimes.get(timeIndex) > pSecondDeparture)
                departureTimes.add(timeIndex, pSecondDeparture);
            break;
        }

        updateDepartureTime(pDepartureStation, pDestinyStation, pSecondDeparture, false);;
    }

    /**
     * @param pDepartureStation Char witch the id ot the 'Station of departure'
     * @param pDestinyStation Char with the id of the 'Station of arrival'
     * @param pSecondDeparture Integer with the second select for the departure of the trip
     * This method makes an update based on the router indicated by pRouteId in time indicated by pSecondDeparture.
    */
    public boolean updateDepartureTime(char pDepartureStation, char pDestinyStation, int pSecondDeparture, boolean pUpdateSending){
        //Creates a hash with the conflicting trips of other stations, based on where is the trip starting and where is going
        Hashtable < String, Integer  > stationsToUpdate =  this.departureTimeDifferenceNeed.get(new StringBuilder().append(pDepartureStation).append(pDestinyStation).toString());

        //Takes the idCode for every Station with trips that cause conflicts, so it can use it to take data from the conflicting Station
        for (String conflictingStationRoute : stationsToUpdate.keySet()){ //n = 30 (peor de los casos es 30, no mas, ya que hay 30 estaciones conflictivas)
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
                while (current < departureTimes.size()) { //n
                    if (departureTimes.get(current) -29 <= conflictTime && conflictTime  <= departureTimes.get(current) + 29)
                        break;
                    current++;
                }

                //Gets the value that has to be add to make sure no collision will happen
                int update = conflictTime - departureTimes.get(current) + 1;
                if (pUpdateSending == false)
                    update = -1 * update;
                while (current < departureTimes.size()) {//n
                    departureTimes.set(current, departureTimes.get(current) + update);
                    current++;
                }



                //Saves the new list
                departureTimesToUpdate.replace(conflictingDestiny, departureTimes);

            availableDepartureTimes.replace(conflictingStationID, departureTimesToUpdate);
        }

        return true;

    }

    public void setTravelingBetweenStations(){
        ArrayList<Character> stationsIDs = new ArrayList<Character>();
        for (char currentStationId: stationsToControl.keySet())
            stationsIDs.add(currentStationId);

        for (int currentDepartureStation = 0; currentDepartureStation < stationsIDs.size(); currentDepartureStation++) {
            for (int currentDestinyStation = 0; currentDestinyStation < stationsIDs.size(); currentDestinyStation++){
                if (currentDepartureStation == currentDestinyStation)
                    continue;
                this.travelingBetweenStations.add(new StringBuilder().append(stationsIDs.get(currentDepartureStation)).append(stationsIDs.get(currentDestinyStation)).toString());
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


                if (routesUsedByTravels.containsKey(String.valueOf(code))) {
                    List<String> savedList = routesUsedByTravels.get(String.valueOf(code));
                    savedList.add(tripCode);
                    routesUsedByTravels.replace(String.valueOf(code), savedList);
                } else {
                    routesUsedByTravels.put(String.valueOf(code), new ArrayList<String>() {{add(tripCode);}});
                }
                code[0] = route.charAt(index);
            }
        }

        /*
        System.out.println(":)");
        for (String k: routesUsedByTravels.keySet()){
            System.out.println(k);
            for (String i: routesUsedByTravels.get(k))
                System.out.println("\t" + i);
            System.out.println("\n");
        }*/

    }

    public void setDepartureTimeDifferenceNeed(){
        //Salida+Destino -> Salida -> Destino -> Tiempo
        //private Hashtable <String, Hashtable < Character, Hashtable < Character, Integer > > > departureTimeDifferenceNeed;
        this.departureTimeDifferenceNeed = new  Hashtable <String, Hashtable < String, Integer > >();

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
                    else if (!this.departureTimeDifferenceNeed.containsKey(currentDeparturePath))
                        this.departureTimeDifferenceNeed.put(currentDeparturePath, new Hashtable < String, Integer >());

                    updateDepartureTimeDifferenceNeed(currentDeparturePath, pathsThatUseCurrentArc.get(currentCompare));
                }
            }

            }

            /*
            for (String i: departureTimeDifferenceNeed.keySet()){
                System.out.println(i);
                for (String j: departureTimeDifferenceNeed.get(i).keySet()){
                    System.out.println("\t" + j + ": " +  departureTimeDifferenceNeed.get(i).get(j));
                }
            }

            System.out.println("\n");
            */
        }



    public void updateDepartureTimeDifferenceNeed(String fromStation, String toStation){
        Hashtable<String, Integer> fromStationDifference =  this.departureTimeDifferenceNeed.get(fromStation);
        float exactDifference = this.stationsToControl.get(fromStation.charAt(0)).getTimeDistance().get(toStation.charAt(1)) - this.stationsToControl.get(toStation.charAt(0)).getTimeDistance().get(toStation.charAt(1));
        int worseCase = Math.round(exactDifference + 0.4f);
        fromStationDifference.put(toStation, worseCase);
        this.departureTimeDifferenceNeed.replace(fromStation, fromStationDifference);
    }


    public Hashtable<Character, Station> getStationsToControl() {
        return stationsToControl;
    }

    public void setStationsToControl(Hashtable<Character, Station> stationsToControl) {
        this.stationsToControl = stationsToControl;

    }

    public List<String> getTripsPerStation(){
        return new ArrayList<String>();
    }


    public Hashtable<Character, Hashtable<Character, List<Integer>>> getAvailableDepartureTimes() {
        return availableDepartureTimes;
    }

    public void setAvaibleDepartureTimes() {
        for (char id: stationsToControl.keySet()){

            availableDepartureTimes.put(id, new Hashtable<Character, List<Integer>>() {{
                for (char stationId : stationsToControl.keySet()) {
                    ArrayList<Integer> departureTimeForStation = new ArrayList<Integer>() {{
                        for (int departureTime = 0; departureTime < worstTimeAccepted; departureTime+=30)
                            add(departureTime);
                    }};
                    put(stationId, departureTimeForStation);
                }
            }
        } );
        }
    }

    public int getWorstTimeAccepted() {
        return worstTimeAccepted;
    }

    public void setWorstTimeAccepted() {
        this.worstTimeAccepted = 0;

        for (char stationID: this.stationsToControl.keySet()){
            this.worstTimeAccepted += this.stationsToControl.get(stationID).getWorseCaseTime();
        }

    }
}
