package Simulation;

public class Trip {

    private DroneMeasure drone;
    private int dronesQuantity;
    private Station stationB;
    private int time;

    public Trip(Station stationB, int cantDrones) {
        this.drone =  DroneMeasure.getInstance();
        this.stationB = stationB;
        this.dronesQuantity = cantDrones;
    }

    public DroneMeasure getDrone() {
        return drone;
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
