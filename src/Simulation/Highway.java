package Simulation;

public class Highway {
    private static final int HEIGHT = 1000;
    private static int width;

    private static Highway ourInstance = new Highway(width);

    public static Highway getInstance() {
        return ourInstance;
    }

    private Highway(int width) {
        this.width = width;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWidth() {
        return width;
    }

}
