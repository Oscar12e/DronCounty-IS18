package Simulation;

public class Trip {
    static private int maxDronesPerTrip;
    static private final int HEIGHT = 1000; //meters - Deberia ir en cosntants


    private static DroneMeasure drone = DroneMeasure.getInstance();
    private int dronesQuantity;
    private Station destinyStation;



    public Trip(Station destinyStation, int cantDrones) {
        this.destinyStation = destinyStation;
        this.dronesQuantity = cantDrones;
    }

    public DroneMeasure getDrone() {
        return drone;
    }

    public Station getDestinyStation() {
        return destinyStation;
    }

    public int getDronesQuantity() {
        return dronesQuantity;
    }

    public static int getMaxDronesPerTrip() {
        return maxDronesPerTrip;
    }

    public static void setMaxDronesPerTrip(int pWidth){ //Width siempre sera mayor o igual al ancho de dos drones
       maxDronesPerTrip = ((HEIGHT*pWidth)/(drone.getHIGH()*drone.getWIDTH()))/2;//(pWidth/2 * HEIGHT) / (drone.getWIDTH()); 	//Ancho divido entre dos (variable) por altura (constante)
    }

}
