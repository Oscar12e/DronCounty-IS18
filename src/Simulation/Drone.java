package Simulation;

public class Drone {

    private int LENGHT;
    private int WIDTH;
    private int HIGH;
    private int SPEED;

    public Drone() {
        this.LENGHT = 2;
        this.WIDTH = 2;
        this.HIGH = 3;
        this.SPEED= 120;
    }

    public int getLENGHT() {
        return LENGHT;
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
