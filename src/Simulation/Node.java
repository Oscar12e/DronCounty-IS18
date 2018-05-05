package Simulation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class Node<T>{
    private T value;
    private ArrayList<Arc> arcs;
    private boolean isVoid;
    private ArrayList<Node<T>> availableNodes;
    private boolean visited;
    private Hashtable<Node<T>, Integer> shortestPaths;

    private Node<T> previous = null;
    //For UI
    private OrderedPair orderedPair;

    public Node(T value){
        this.value = value;
        this.arcs = new ArrayList<>();
        this.isVoid = true;
        this.visited = false;
    }

    public T getValue() {
        return value;
    }

    public ArrayList<Arc> getArcs() {
        return arcs;
    }

    public boolean isVoid() {
        return isVoid;
    }

    public void setVoid(boolean aVoid) {
        isVoid = aVoid;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public OrderedPair getOrderedPair() {
        return orderedPair;
    }

    public void setOrderedPair(OrderedPair orderedPair) {
        this.orderedPair = orderedPair;
    }

    public ArrayList<Node<T>> getAvailableNodes() {
        return availableNodes;
    }

    public void setAvailableNodes(ArrayList<Node<T>> availableNodes) {
        this.availableNodes = availableNodes;
    }

    public Hashtable<Node<T>, Integer> getShortestPaths() {
        return shortestPaths;
    }

    public void setShortestPaths(Hashtable<Node<T>, Integer> shortestPaths) {
        this.shortestPaths = shortestPaths;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public boolean isAdjacentNode(Station value){
        for(int i=0; i<arcs.size(); i++){
            if(arcs.get(i).getDestiny().getValue() == value){//Como getIdStation
                return true;
            }
        }
        return false;
    }

/*
    @Override
    public int compareTo(Node otherObject) {
        Station station1 = (Station) this.value;
        Station station2 = (Station) otherObject.getValue();

        int difference = station1.getIdStation() - station2.getIdStation();
        if (difference == 0)
            return 0;
        else if (difference > 0)
            return 1;
        else
            return -1;
    }*/

}
