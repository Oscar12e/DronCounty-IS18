import java.util.ArrayList;

public class Node<T> {
    private T value;
    private ArrayList<Node<T>> arcs;
    private ArrayList<Node<T>> next;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ArrayList<Node<T>> getArcs() {
        return arcs;
    }

    public void setArcs(ArrayList<Node<T>> arcs) {
        this.arcs = arcs;
    }

    public ArrayList<Node<T>> getNext() {
        return next;
    }

    public void setNext(ArrayList<Node<T>> next) {
        this.next = next;
    }

    public Node(T pValor){
        this.value = pValor;
    }

}
