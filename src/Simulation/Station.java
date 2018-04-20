package Simulation;

import java.util.Hashtable;

public class Station {

    private int totalDronesQuantity;
    private int currentDronesQuantity;
    private int idStation;
    private Hashtable<Integer,Integer> paths;
    private Hashtable<Integer,Integer> dronesLeft;

    public Station(int quantityDronesTotal, int currentDronesQuantity, int idStation) {
        this.totalDronesQuantity = quantityDronesTotal;
        this.currentDronesQuantity = currentDronesQuantity;
        this.idStation = idStation;
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

    public Hashtable<Integer, Integer> getPaths() {
        return paths;
    }

    public Hashtable<Integer, Integer> getDronesLeft() {
        return dronesLeft;
    }
}
