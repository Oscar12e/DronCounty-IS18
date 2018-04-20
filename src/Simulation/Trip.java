package Simulation;

public class Trip {

    private DroneMeasure drone;
    private int dronesQuantity;
    private Station stationA;
    private Station stationB;
    private int time;

    public Trip(DroneMeasure drone, Station stationA, Station stationB, int cantDrones) {
        this.drone = drone;
        this.stationA = stationA;
        this.stationB = stationB;
        this.dronesQuantity = cantDrones;
    }

    public DroneMeasure getDrone() {
        return drone;
    }

    public Station getStationA() {
        return stationA;
    }

    public Station getStationB() {
        return stationB;
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
