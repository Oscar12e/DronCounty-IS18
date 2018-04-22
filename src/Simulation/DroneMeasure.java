package Simulation;

public class DroneMeasure {

    private static final int LENGTH = 2;
    private static final int WIDTH = 2;
    private static final int HIGH = 3;
    private static final int SPEED = 120;

    private static DroneMeasure ourInstance = new DroneMeasure();

    public static DroneMeasure getInstance() {
        return ourInstance;
    }



    public int getLENGTH() {
        return LENGTH;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHIGH() {
        return HIGH;
    }

    public int getSPEED() {
        return SPEED;
    }
}
