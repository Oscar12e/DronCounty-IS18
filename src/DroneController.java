//patron strategy
public abstract class DroneController {

    private int tripsAmount;
    private int stationsAmount;
    private float simulationTime;
    private float simulateHourEquivalentToMiliseconds;
    private int arcsPerStation;

    public DroneController(int tripsAmount, int stationsAmount, float simulationTime, int arcsPerStation) {
        this.tripsAmount = tripsAmount;
        this.stationsAmount = stationsAmount;
        this.simulationTime = simulationTime;
        this.arcsPerStation = arcsPerStation;
        //calculo de simulateHourEquivalentToMiliseconds
    }

    public DroneController() {
    }

    public int getTripsAmount() {
        return tripsAmount;
    }

    public int getStationsAmount() {
        return stationsAmount;
    }

    public float getSimulationTime() {
        return simulationTime;
    }

    public float getSimulateHourEquivalentToMiliseconds() {
        return simulateHourEquivalentToMiliseconds;
    }

    public int getArcsPerStation() {
        return arcsPerStation;
    }

    public boolean runSimulation(){
        return false;
    }
}
