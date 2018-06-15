package Simulation;

import java.util.List;

public class BacktrackingScheduler extends Scheduler {

    public BacktrackingScheduler(StationsLogic pLogic) {
        super(pLogic);
    }

    @Override
    public boolean runSimulation() {
        //scheduleStation()
        //scheduleStation(, 0);
        return false;
    }

    /**
     *
     * @param tripsPerStations List of stations trips, using the code 'departure station id' + 'destiny station id', we will use another structure later
     * @param current index of the current trip that is being tested
     * @return
     */
    private boolean scheduleStation(List<String> tripsPerStations, int current){
        if (current == tripsPerStations.size())
            return true;

        String tripCode = tripsPerStations.get(current); //2
        Station currentStation = logic.getStationsToControl().get(tripCode.charAt(0)); //4
        //All trips are send
        List<Integer> timesAvailed =  logic.getAvailableDepartureTimes().get(tripCode.charAt(0)).get(tripCode.charAt(1)); //5

        for (int currentTime = 0; currentTime < timesAvailed.size(); currentTime++){ //n      +3

            //Si no se esta tardando más tiempo del debido

            if (logic.sendTrips(currentStation.getIdStation(), tripCode.charAt(1), timesAvailed.get(current))) // 30 * n
                // Ya que no puede haber choques
                if (scheduleStation(tripsPerStations, current++)) //n
                    return true;

            logic.cancelTrip(currentStation.getIdStation(), tripCode.charAt(1), timesAvailed.get(current)); //n
        }

        return false;
    }   //O(n^3)




}
