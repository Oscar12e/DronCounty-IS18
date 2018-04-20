package Simulation;

import java.util.ArrayList;

public class Node<T> {
    private T value;
    private ArrayList<Node<T>> edges;
    private ArrayList<Node<T>> next;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ArrayList<Node<T>> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Node<T>> edges) {
        this.edges = edges;
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
