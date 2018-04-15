public class Trip {

    private Drone drone;
    private int dronesQuantity;
    private Station stationA;
    private Station stationB;
    private int time;

    public Trip(Drone drone, Station stationA, Station stationB, int cantDrones) {
        this.drone = drone;
        this.stationA = stationA;
        this.stationB = stationB;
        this.dronesQuantity = cantDrones;
    }

    public Drone getDrone() {
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
