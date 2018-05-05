package Simulation;

import java.util.*;

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
            Arc newArc = new Arc(weight, destiny); //corregir highway con funcion de buscar
            source.getArcs().add(newArc);
            destiny.setVoid(false);
            source.getAvailableNodes().remove(destiny); //ok?
            //System.out.println(source.getAvailableNodes());
            return true;
        }
        return false;
    }

    public Hashtable<Node<T>, Integer> dijkstraShorterRoute(Node<T> source){
        Hashtable<Node<T>, Integer> shortestPaths = new Hashtable<>();
        ArrayList<Node<T>> nodesToProccess = new ArrayList<>();
        source.setMinDistance(0);
        shortestPaths.put(source,0);

        nodesToProccess.add(source);
        while (nodesToProccess.size() > 0){
            Node<T> node = nodesToProccess.get(0);
            nodesToProccess.remove(node);
            node.setVisited(true);
            ArrayList<Arc> neighbors = node.getArcs();

            for (int i=0; i<neighbors.size(); i++){
                Node<T> actual = neighbors.get(i).getDestiny();
                int actualDistance = neighbors.get(i).getWeight();

                if(!shortestPaths.containsKey(actual)/*actual.getMinDistance()==Integer.MAX_VALUE*/)
                    shortestPaths.put(actual, actualDistance+shortestPaths.get(node));
                    //actual.setMinDistance(actual.getMinDistance()+node.getMinDistance());
                else if(shortestPaths.get(actual) > shortestPaths.get(node)+neighbors.get(i).getWeight()/*actual.getMinDistance() > node.getMinDistance() + neighbors.get(i).getWeight()*/)
                    shortestPaths.put(actual, shortestPaths.get(node) + actualDistance);
                    //actual.setMinDistance(actual.getMinDistance() + node.getMinDistance() + neighbors.get(i).getWeight());
                if (actual.isVisited() == false)
                    nodesToProccess.add(actual);
            }
            nodesToProccess.remove(node);
        }
        System.out.println(shortestPaths.toString());
        source.setShortestPaths(shortestPaths);
        return shortestPaths;
    }

    public Node<T> getShorterPath(ArrayList<Node<T>> fringe){
        Node<T> comparable;
        if(fringe.size()==1)
            return fringe.get(0);
        else {
            comparable = fringe.get(0);
            for (int i = 1; i < fringe.size(); i++) {
                if (comparable.getMinDistance() > fringe.get(i).getMinDistance())
                    comparable = fringe.get(i);
            }
        }
            return comparable;
    }

    public ArrayList searchVoidNodes(char key){
        ArrayList<Node> voidNodes = new ArrayList<>();
        for(int i=0; i<graph.getVertexList().size(); i++){
            if(graph.getVertexList().get(i).isVoid() == true && searchVertex(key) != graph.getVertexList().get(i)){
                voidNodes.add(graph.getVertexList().get(i));
            }
        }
        return voidNodes;
    }

    public ArrayList checkIfConnectedNode(Node source){
        return null;
    }

}
