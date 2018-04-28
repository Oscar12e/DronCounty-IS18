package Simulation;

public class Arc {

    private int weight;
    private Node destiny;
    //private Highway highway;

    public Arc(int weight, Node destiny, Highway highway) {
        this.weight = weight;
        this.destiny = destiny;
        //this.highway = highway;
    }

    public int getWeight() {
        return weight;
    }

    public Node getDestiny() {
        return destiny;
    }

    /*public Highway getHighway() {
        return highway;
    }*/
}
