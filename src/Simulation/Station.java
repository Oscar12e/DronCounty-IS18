package Simulation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Station implements Comparable<Station> {

    private int totalDronesQuantity;
    private int quantityOfDronesReceived;
    private char idStation;
    //private List<Character> destinyStations;//necesario?
    private ArrayList<Trip> trips;

    //private Hashtable<Integer, ArrayList<Integer>> paths;

    private ArrayList<Character> destinyStations; //method done
    private Hashtable<Character, String > paths; //method done
    private Hashtable <Integer, ArrayList <Trip> > schedule;
    private Hashtable <Character, Float> timeDistance; //method done
    private Hashtable <Character, ArrayList<Trip> > tripsToSchedule;

    public Station(int quantityDronesTotal, char idStation) {
        this.totalDronesQuantity = quantityDronesTotal;
        this.quantityOfDronesReceived = 0;
        this.idStation = idStation;
        this.trips = new ArrayList<>();
        this.paths = new Hashtable<>();
        this.destinyStations = new ArrayList<>();
        this.tripsToSchedule = new Hashtable<>();
    }

    /*public Station(int ask, char pIdStation) {
        this.idStation = pIdStation;
        this.paths = new Hashtable<>();
    }*/

    public Station(char pIdStation, Hashtable<Character, String > pPaths, Hashtable <Character, Float> pTimeDistance) {
        this.idStation = pIdStation;
        this.paths = pPaths;
        this.timeDistance = pTimeDistance;
    }

    public Station(){

    }

    /**
     * In worse case scenario, all the trips would be to the mos far station.
     * Takes the regular distance to get there add the time to deploy (30s), then multiplies by the quantity of trips.
     * @return The time taht would take sending all the trips to this station.
     */
    public int getWorseCaseTime(){
        int higherTripDuration = 0;

        for (char destinyId: this.timeDistance.keySet()){
            if (this.timeDistance.get(destinyId) > higherTripDuration)
                higherTripDuration = Math.round( (totalDronesQuantity/Trip.getMaxDronesPerTrip()) * (this.timeDistance.get(destinyId) + 30) + 0.4f );
        }

        return higherTripDuration;
    }

    public int getTotalDronesQuantity() {
        return totalDronesQuantity;
    }

    public void setTotalDronesQuantity(int totalDronesQuantity) {
        this.totalDronesQuantity = totalDronesQuantity;
    }

    public int getCurrentDronesQuantity() {
        return quantityOfDronesReceived;
    }

    public void setCurrentDronesQuantity(int currentDronesQuantity) {
        this.quantityOfDronesReceived = currentDronesQuantity;
    }

    public char getIdStation() {
        return idStation;
    }

    public void setIdStation(char idStation) {
        this.idStation = idStation;
    }

    public List<Character> getDestinyStations() {
        return destinyStations;
    }

    public void setDestinyStations(ArrayList<Character> destinyStations) {
        this.destinyStations = destinyStations;
    }

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }

    public Hashtable<Character, String> getPaths() {
        return paths;
    }

    public void setPaths(Hashtable<Character, String > paths) {
        this.paths = paths;
    }

    public Hashtable<Integer, ArrayList<Trip>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Hashtable<Integer, ArrayList<Trip>> schedule) {
        this.schedule = schedule;
    }


    public Hashtable<Character, Float> getTimeDistance() {
        return timeDistance;
    }

    public void setTimeDistance(Hashtable<Character, Float> timeDistance) {
        this.timeDistance = timeDistance;
    }


    public Hashtable<Character, ArrayList<Trip>> getTripsToSchedule() {
        return tripsToSchedule;
    }

    public void setTripsToSchedule(Hashtable<Character, ArrayList<Trip>> tripsToSchedule) {
        this.tripsToSchedule = tripsToSchedule;
    }

    public void getTripsToScheduleAsList(){
        List<String> t = new ArrayList<String>();

        for (Character destinyID: tripsToSchedule.keySet()){
            tripsToSchedule.get(destinyID).size();
        }
    }

    @Override
    public int compareTo(Station otherObject) {
        int difference = this.idStation - otherObject.getIdStation();
        if (difference == 0)
            return 0;
        else if (difference > 0)
            return 1;
        else
            return -1;
    }
}
