package Simulation;

public class Trip {
    static private int maxDronesPerTrip;
    static private final int HEIGHT = 1000; //meters - Deberia ir en cosntants


    private static DroneMeasure drone;
    private int dronesQuantity;
    private Station destinyStationID;


    public Trip(Station stationA, Station stationB, int cantDrones, int tripTime, int startTime) {
        this.drone =  DroneMeasure.getInstance();
        this.destinyStationID = stationB;
        this.dronesQuantity = cantDrones;
    }

    public DroneMeasure getDrone() {
        return drone;
    }

    public Station getStationB() {
        return destinyStationID;
    }

    public int getDronesQuantity() {
        return dronesQuantity;
    }


    public static void setMaxDronesPerTrip(int pWidth){ //Width siempre sera mayor o igual al ancho de dos drones
        maxDronesPerTrip = (pWidth/2 * HEIGHT) / (drone.getWIDTH()); 	//Ancho divido entre dos (variable) por altura (constante)
    }

}
