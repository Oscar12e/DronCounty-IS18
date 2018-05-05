package Simulation;

//patron strategy
public abstract class Scheduler {
    StationsLogic logic;

    public Scheduler(StationsLogic pLogic) {
        this.logic = pLogic;
    }



    public abstract boolean runSimulation(); //TO-DO: aclarar los parametros que van aqu

}
