package Simulation;

public class Trip {

    private Drone drone;
    private int dronesQuantity;
    private Station destinyStation;
    private int time;

    public Trip(Drone drone, Station stationA, int cantDrones) {
        this.drone = drone;
        this.destinyStation = stationA;
        this.dronesQuantity = cantDrones;
    }

    public Drone getDrone() {
        return drone;
    }

    public Station getStationA() {
        return destinyStation;
    }


    public int getDronesQuantity() {
        return dronesQuantity;
    }

    public int getTime() {
        return time;
    }

    public int calcularTiempo() {
        return 2;
    }
}
