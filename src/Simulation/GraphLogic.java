package Simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class GraphLogic<T> {
    private Graph<T> graph;

    public GraphLogic(Graph<T> graph) {
        this.graph = graph;
    }

    public Graph<T> getGraph() {
        return graph;
    }

    public boolean insertVertex(Node<T> value) {
            Station station = (Station) value.getValue();
            if (searchVertex(station.getIdStation()) == null) {
            Node newNode = value;
            graph.getVertexList().add(newNode);
            return true;
        }
        return false;
    }

    public Node searchVertex(char id){
        for(int i=0; i<graph.getVertexList().size(); i++){
            Station station = (Station) graph.getVertexList().get(i).getValue();
            if (station.getIdStation() == id)
            return graph.getVertexList().get(i);
        }
        return null;
    }

    public boolean insertArc(Node source, Node destiny, int weight){
        //Node sourceVertex = searchVertex(source);
        //Node destinyVertex = searchVertex(destiny);

        if(source != null && destiny != null){
            Arc newArc = new Arc(weight, destiny, null); //corregir highway con funcion de buscar
            source.getArcs().add(newArc);
            return true;
        }
        return false;
    }

    public LinkedList<Node> dijkstra(Node origin){
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
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
        return origin.getPath();
    }

    public ArrayList searchVoidNodes(char key){
        ArrayList<Node> voidNodes = new ArrayList<>();
        for(int i=0; i<graph.getVertexList().size(); i++){
            if(graph.getVertexList().get(i).isVoid() == false && searchVertex(key) != graph.getVertexList().get(i)){
                voidNodes.add(graph.getVertexList().get(i));
            }
        }
        return voidNodes;
    }

    public ArrayList searchClosestNodes(Node source, int quantity){
        ArrayList<Node> closestNodes = new ArrayList<>();
        for(int i=0; i<quantity; i++){
            //???????????
        }
        return closestNodes;
    }

    public void cleanPaths(){
        for (int i = 0; i<graph.getVertexList().size(); i++){
            graph.getVertexList().get(i).setPath(null);
        }
    }

    public void cleanMinDistance(){
        for (int i = 0; i<graph.getVertexList().size(); i++){
            graph.getVertexList().get(i).setMinDistance(Integer.MAX_VALUE);
        }
    }

    //calcular distancia
    //Math.hypot(x1 -x2, y1 -y2)
}
