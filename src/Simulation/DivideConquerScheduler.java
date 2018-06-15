package Simulation;

import java.util.List;

public class DivideConquerScheduler extends Scheduler {

    public DivideConquerScheduler(StationsLogic pLogic) {
        super(pLogic);
    }

    @Override
    public boolean runSimulation() {
        return false;
    }


    private boolean scheduleStation(List<String> tripsPerStations){
        if(tripsPerStations.size()>1){
            scheduleStation(tripsPerStations.subList(0, tripsPerStations.size()/2));
            scheduleStation(tripsPerStations.subList(tripsPerStations.size()/2, tripsPerStations.size()));
        }
        else{
            String tripCode = tripsPerStations.get(0); //2
            Station currentStation = logic.getStationsToControl().get(tripCode.charAt(0)); //4
            //All trips are send
            List<Integer> timesAvailed =  logic.getAvailableDepartureTimes().get(tripCode.charAt(0)).get(tripCode.charAt(1)); //5

                logic.sendTrips(currentStation.getIdStation(), tripCode.charAt(1), timesAvailed.get(0)); // 30 * n

        }

        return true; // TO-DO:seudocodigo paso a paso de cada algoritmo
    }

} //O((log2(n))*n)
