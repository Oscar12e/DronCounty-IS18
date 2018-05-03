package Simulation;

import java.util.ArrayList;
import java.util.LinkedList;

public class Node<T> {
    private T value;
    private ArrayList<Arc> arcs;
    private int minDistance;
    private LinkedList<Node> path;
    private boolean isVoid;
    private ArrayList<Node<T>> availableNodes;
    //For UI
    private OrderedPair orderedPair;

    public Node(T value){
        this.value = value;
        this.arcs = new ArrayList<>();
        this.minDistance = Integer.MAX_VALUE;
        this.isVoid = true;
    }

    public T getValue() {
        return value;
    }

    public ArrayList<Arc> getArcs() {
        return arcs;
    }

    public int getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(int minDistance) {
        this.minDistance = minDistance;
    }

    public LinkedList<Node> getPath() {
        return path;
    }

    public void setPath(LinkedList<Node> path) {
        this.path = path;
    }

    public boolean isVoid() {
        return isVoid;
    }

    public void setVoid(boolean aVoid) {
        isVoid = aVoid;
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
        int difference = station1.getIdStation() - otherObject.getIdStation();
        if (difference == 0)
            return 0;
        else if (difference > 0)
            return 1;
        else
            return -1;
    }*/

}
