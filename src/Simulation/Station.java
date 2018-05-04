package Simulation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Station implements Comparable<Station> {

    private int totalDronesQuantity;
    private int currentDronesQuantity;
    private char idStation;

    private List<Character> destinyStations;
    private Hashtable<Character, String > paths;
    private Hashtable <Integer, ArrayList <Trip> > schedule;
    private Hashtable <Character, Integer> timeDistance;
    private Hashtable <Character, ArrayList<Trip> > tripsToSchedule;

    public Station(int quantityDronesTotal, int idStation, Hashtable<Integer,Integer> pDronesLeft) {
        this.totalDronesQuantity = quantityDronesTotal;
        this.currentDronesQuantity = quantityDronesTotal;
    }

    public Station(int ask, char pIdStation) {
        this.idStation = pIdStation;
        this.paths = new Hashtable<>();
    }

    public Station(char pIdStation, Hashtable<Character, String > pPaths, Hashtable <Character, Integer> pTimeDistance) {
        this.idStation = pIdStation;
        this.paths = pPaths;
        this.timeDistance = pTimeDistance;
    }

    public Station(){

    }

    public int getTotalDronesQuantity() {
        return totalDronesQuantity;
    }

    public void setTotalDronesQuantity(int totalDronesQuantity) {
        this.totalDronesQuantity = totalDronesQuantity;
    }

    public int getCurrentDronesQuantity() {
        return currentDronesQuantity;
    }

    public void setCurrentDronesQuantity(int currentDronesQuantity) {
        this.currentDronesQuantity = currentDronesQuantity;
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

    public void setDestinyStations(List<Character> destinyStations) {
        this.destinyStations = destinyStations;
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




    public Hashtable<Character, Integer> getTimeDistance() {
        return timeDistance;
    }

    public void setTimeDistance(Hashtable<Character, Integer> timeDistance) {
        this.timeDistance = timeDistance;
    }


    public Hashtable<Character, ArrayList<Trip>> getTripsToSchedule() {
        return tripsToSchedule;
    }

    public void setTripsToSchedule(Hashtable<Character, ArrayList<Trip>> tripsToSchedule) {
        this.tripsToSchedule = tripsToSchedule;
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
