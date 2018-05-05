package Simulation;

import java.util.List;
import java.util.Random;

public class ProbabilisticScheduler extends Scheduler {

    public ProbabilisticScheduler(StationsLogic pLogic) {
        super(pLogic);
    }

    @Override
    public boolean runSimulation(){
        return false;
    }

    private boolean scheduleStation(List<String> tripsPerStation){
        Random random = new Random(System.currentTimeMillis());
        int randomIndex;

        while(tripsPerStation.size()!=0){
            randomIndex = random.nextInt(tripsPerStation.size());

            String tripCode = tripsPerStation.remove(randomIndex);
            List<Integer> timesAvailable = logic.getAvailableDepartureTimes().get(tripCode.charAt(0)).get(tripCode.charAt(1));
            randomIndex = random.nextInt(timesAvailable.size());
            int departureTime = timesAvailable.get(randomIndex);
            logic.sendTrips(tripsPerStation.get(randomIndex).charAt(0), tripsPerStation.get(randomIndex).charAt(tripsPerStation.get(randomIndex).length()-1), departureTime);
        }
        return true;
    }

}
