package Simulation;

public class Trip {

    private DroneMeasure drone;
    private int dronesQuantity;
    private Station stationDestiny;
    private int time;

    public Trip(Station stationB, int cantDrones) {
        this.drone =  DroneMeasure.getInstance();
        this.stationDestiny = stationB;
        this.dronesQuantity = cantDrones;
    }

    public DroneMeasure getDrone() {
        return drone;
    }

    public Station getStationDestiny() {
        return stationDestiny;
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
