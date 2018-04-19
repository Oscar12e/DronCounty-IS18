public class Arc {

    private int weight;
    private Node destiny;

    public Arc(int weight, Node destiny) {
        this.weight = weight;
        this.destiny = destiny;
    }

    public int getWeight() {
        return weight;
    }

    public Node getDestiny() {
        return destiny;
    }
}
