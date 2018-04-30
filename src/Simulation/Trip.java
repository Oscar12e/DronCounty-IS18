package Simulation;

public class Trip {

    private DroneMeasure drone;
    private int dronesQuantity;
    private Station stationA;
    private Station stationB;
    private int tripTime;
    private int startTime;

    public Trip(Station stationA, Station stationB, int cantDrones, int tripTime, int startTime) {
        this.drone =  DroneMeasure.getInstance();
        this.stationA = stationA;
        this.stationB = stationB;
        this.dronesQuantity = cantDrones;
        this.tripTime = tripTime;
        this.startTime = startTime;
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

    public int getTripTime() {
        return tripTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int calcularTiempo() {
        return 2;
    }
}
