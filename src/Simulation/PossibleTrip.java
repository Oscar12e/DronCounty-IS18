package Simulation;

public class PossibleTrip {
    private int startTime;
    private int finishTime;
    private int destinyStationId;

    public PossibleTrip(int startTime, int finishTime, int destinyStationId) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.destinyStationId = destinyStationId;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }
}
