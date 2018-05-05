package Simulation;

import java.util.ArrayList;

public class Graph<T>{
    private ArrayList<Node<T>> vertexList;

    public ArrayList<Node<T>> getVertexList() {
        return vertexList;
    }

    public Graph(){
        vertexList = new ArrayList<>();
    }

}
