package Simulation;

import java.util.ArrayList;
import java.util.Hashtable;

public class Station implements Comparable<Station> {

    private int totalDronesQuantity;
    private int currentDronesQuantity;
    private char idStation;
    private Hashtable<Integer,Integer> paths; // highways?
    private Hashtable<Integer,Integer> dronesLeft;
    //private ArrayList<Highway> highways;

    public Station(int quantityDronesTotal, char idStation) {
        this.totalDronesQuantity = quantityDronesTotal;
        this.idStation = idStation;
    }

    /*public Station(int idStation) {
        this.idStation = idStation;
        this.paths = new Hashtable<>();
    }*/

    public int getTotalDronesQuantity() {
        return totalDronesQuantity;
    }

    public void setTotalDronesQuantity(int totalDronesQuantity) {
        this.totalDronesQuantity = totalDronesQuantity;
    }

    public void setCurrentDronesQuantity(int currentDronesQuantity) {
        this.currentDronesQuantity = currentDronesQuantity;
    }

    public int getCurrentDronesQuantity() {
        return currentDronesQuantity;
    }

    public char getIdStation() {
        return idStation;
    }

    public Hashtable<Integer, Integer> getPaths() {
        return paths;
    }

    public Hashtable<Integer, Integer> getDronesLeft() {
        return dronesLeft;
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
