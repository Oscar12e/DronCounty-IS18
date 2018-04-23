package Simulation;

import java.util.ArrayList;
import java.util.Hashtable;

public class Station implements Comparable<Station> {

    private int totalDronesQuantity;
    private int currentDronesQuantity;
    private int idStation;
    private Hashtable<Integer, ArrayList<Integer>> paths;
    private Hashtable<Integer,Integer> dronesLeft;

    public Station(int quantityDronesTotal, int idStation, Hashtable<Integer,Integer> pDronesLeft) {
        this.totalDronesQuantity = quantityDronesTotal;
        this.currentDronesQuantity = quantityDronesTotal;
        this.idStation = idStation;
        this.dronesLeft = pDronesLeft;
    }

    public Station(int idStation) {
        this.idStation = idStation;
        this.paths = new Hashtable<>();
    }

    public int getTotalDronesQuantity() {
        return totalDronesQuantity;
    }

    public int getCurrentDronesQuantity() {
        return currentDronesQuantity;
    }

    public int getIdStation() {
        return idStation;
    }

    public Hashtable<Integer, ArrayList<Integer>> getPaths() {
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
