import java.util.ArrayList;

public class Graph<T extends Comparable<T>>{
    private ArrayList<Node> vertexList;

    public ArrayList<Node> getVertexList() {
        return vertexList;
    }


    public Graph(){
        vertexList = new ArrayList<Node>();

        int i = 0;
    }
}
