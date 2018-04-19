import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph<T extends Comparable<T>>{
    private ArrayList<Node> vertexList;

    public ArrayList<Node> getVertexList() {
        return vertexList;
    }

    public Graph(){
        vertexList = new ArrayList<Node>();
    }

    public boolean insertVertex(int value) {
        if (searchVertex(value) == null) {
            Node newNode = new Node(new Station(value));
            vertexList.add(newNode);
            return true;
        }
        return false;
    }

    public Node searchVertex(int value){
        for(int i=0; i<vertexList.size(); i++){
            if ((Integer) vertexList.get(i).getValue() == value) //???
                return vertexList.get(i);
        }
        return null;
    }

    public boolean insertArc(int source, int destiny, int weight){
        Node sourceVertex = searchVertex(source);
        Node destinyVertex = searchVertex(destiny);
        if(sourceVertex != null && destinyVertex != null){
            Arc newArc = new Arc(weight, destinyVertex);
            sourceVertex.getArcs().add(newArc);
            return true;
        }
        return false;
    }

    public void cleanVisitedVertex(){
        for (int i=0; i<vertexList.size(); i++){
            vertexList.get(i).setVisited(false);
        }
    }

    public void dijkstra(Node origin, Node destiny){
        PriorityQueue<Node>  queue = new PriorityQueue<Node>();
        origin.setMinDistance(0);
        queue.add(origin);

        while(!queue.isEmpty()){
            Node nodeQueued = queue.poll();

            for(int i = 0; i<nodeQueued.getArcs().size(); i++){
                Arc neighbour = (Arc) nodeQueued.getArcs().get(i);
                int newDist = nodeQueued.getMinDistance()+neighbour.getWeight();

                if(neighbour.getDestiny().getMinDistance()>newDist){
                    queue.remove(neighbour.getDestiny());
                    neighbour.getDestiny().setMinDistance(newDist);

                    neighbour.getDestiny().setPath(new LinkedList<Node>(nodeQueued.getPath()));
                    neighbour.getDestiny().getPath().add(nodeQueued);

                    queue.add(neighbour.getDestiny());
                }
            }
        }
    }

}
