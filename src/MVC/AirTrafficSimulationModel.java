package MVC;


import Simulation.Scheduler;

public class AirTrafficSimulationModel  {
    //private Graph<Station> SimulationGraph = new Graph<>();

    private int tripsAmount;
    private int stationsAmount;
    private float simulationTime;
    private float simulateHourEquivalentToMiliseconds;
    private int arcsPerStation;

    private Scheduler simulator;

    public AirTrafficSimulationModel(){

    }

    public AirTrafficSimulationModel(int pTripsAmount, int pStationsAmount, float pSimulationTime, int pArcsPerStation){
        this.tripsAmount = pTripsAmount;
        this.stationsAmount = pStationsAmount;
        this.simulationTime = pSimulationTime;
        this.arcsPerStation = pArcsPerStation;
    }

    public void createGraph(int pNodeQuantity, int pArcsQuantity){

    }

    public void defineSimulator(){
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

    public void setSimulator(String simulator) {
        //this.simulator = simulator;
    }
}
