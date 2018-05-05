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

        String tripCode = tripsPerStations.get(current);
        Station currentStation = logic.getStationsToControl().get(tripCode.charAt(0));
        //All trips se envian (yes, spanglish)
        List<Integer> timesAvailed =  logic.getAvailableDepartureTimes().get(tripCode.charAt(0)).get(tripCode.charAt(1));

        for (int currentTime = 0; currentTime < timesAvailed.size(); currentTime++){

            //Si no se esta tardando más tiempo del debido

            if (logic.sendTrips(currentStation.getIdStation(), tripCode.charAt(1), timesAvailed.get(current))) //Unica valiación que nos viene a la mente
                // Ya que no puede haber choques
                if (scheduleStation(tripsPerStations, current++))
                    return true;

            logic.cancelTrip(currentStation.getIdStation(), tripCode.charAt(1), timesAvailed.get(current));
        }

        return false;
    }




}
