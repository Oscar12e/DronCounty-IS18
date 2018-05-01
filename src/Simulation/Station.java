package Simulation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Station implements Comparable<Station> {

    private int totalDronesQuantity;
    private int currentDronesQuantity;
    private char idStation;

    private List<Character> destinyStations;

    private Hashtable<Integer, ArrayList<Integer>> paths;
    private Hashtable <Integer, ArrayList <Trip> > schedule;
    private Hashtable <Character, ArrayList <Integer> > departureTime;


    public Station(int quantityDronesTotal, int idStation, Hashtable<Integer,Integer> pDronesLeft) {
        this.totalDronesQuantity = quantityDronesTotal;
        this.currentDronesQuantity = quantityDronesTotal;
    }

    public Station(int ask, char pIdStation) {
        this.idStation = pIdStation;
        this.paths = new Hashtable<>();
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

    public Hashtable<Integer, ArrayList<Integer>> getPaths() {
        return paths;
    }

    public void setPaths(Hashtable<Integer, ArrayList<Integer>> paths) {
        this.paths = paths;
    }

    public Hashtable<Integer, ArrayList<Trip>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Hashtable<Integer, ArrayList<Trip>> schedule) {
        this.schedule = schedule;
    }

    public Hashtable<Character, ArrayList<Integer>> getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Hashtable<Character, ArrayList<Integer>> departureTime) {
        this.departureTime = departureTime;
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
