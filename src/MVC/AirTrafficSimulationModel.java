package MVC;


import Simulation.DroneController;
import Simulation.Graph;
import Simulation.Station;
import com.sun.prism.image.Coords;

public class AirTrafficSimulationModel  {
    //private Graph<Station> SimulationGraph = new Graph<>();

    protected int tripsAmount;
    protected int stationsAmount;
    protected float simulationTime;
    protected float simulateHourEquivalentToMiliseconds;
    protected int arcsPerStation;

    private DroneController simulator;

    public AirTrafficSimulationModel(int pTripsAmount, int pStationsAmount, float pSimulationTime, int pArcsPerStation){
        this.tripsAmount = tripsAmount;
        this.stationsAmount = stationsAmount;
        this.simulationTime = simulationTime;
        this.arcsPerStation = arcsPerStation;
    }

    public void createGraph(int pNodeQuantity, int pArcsQuantity){

    }

    public void defineSimulator(){
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



}
