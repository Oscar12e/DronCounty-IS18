package MVC;


import Simulation.*;
import com.sun.prism.image.Coords;

public class AirTrafficSimulationModel  {
    //private Graph<Station> SimulationGraph = new Graph<>();

    private int tripsAmount;
    private int stationsAmount;
    private float simulationTime;
    private float simulateHourEquivalentToMiliseconds;
    private int arcsPerStation;

    private DroneController simulator;
    private Graph stationMap;

    public AirTrafficSimulationModel(){
    }

    public void createGraph(int pNodeQuantity, int pArcsQuantity){
        this.stationMap = null;
    }

    public void setTripsAmount(int tripsAmount) {
        this.tripsAmount = tripsAmount;
    }

    public void setStationsAmount(int stationsAmount) {
        this.stationsAmount = stationsAmount;
    }

    public void setSimulationTime(float simulationTime) {
        this.simulationTime = simulationTime;
    }

    public void setSimulateHourEquivalentToMiliseconds(float simulateHourEquivalentToMiliseconds) {
        this.simulateHourEquivalentToMiliseconds = simulateHourEquivalentToMiliseconds;
    }

    public void setArcsPerStation(int arcsPerStation) {
        this.arcsPerStation = arcsPerStation;
    }

    public void setSimulator(String simulatorType) {
        switch (simulatorType) {
            case "Probabilistico":
                this.simulator = new ProbabilisticSimulator();
                break;
            case "Backtracking":
                this.simulator = new BacktrackingSimulation();
                break;
            default:
                this.simulator = new DivideConquerSimulator();
                break;
        }
    }
}
