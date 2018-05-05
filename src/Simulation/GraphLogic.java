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

    public ArrayList<ArrayList<Node<T>>> dijkstraShorterRoute(Node<T> source){
        source.setShortestPaths(null);
        cleanVisitedNodes();
        Hashtable<Node<T>, Integer> shortestPaths = new Hashtable<>();
        ArrayList<Node<T>> nodesToProcess = new ArrayList<>();
        Station station;
        shortestPaths.put(source,0);
        nodesToProcess.add(source);

        //ArrayList<ArrayList<Node<T>>> paths = new ArrayList<>(new ArrayList<>());
        while (nodesToProcess.size() > 0){
            Node<T> node = nodesToProcess.get(0);
            nodesToProcess.remove(node);
            node.setVisited(true);
            ArrayList<Arc> neighbors = node.getArcs();

            for (int i=0; i<neighbors.size(); i++){
                Node<T> actual = neighbors.get(i).getDestiny();
                int actualDistance = neighbors.get(i).getWeight();

                if(!shortestPaths.containsKey(actual)) {
                    shortestPaths.put(actual, actualDistance + shortestPaths.get(node));
                    actual.setPrevious(node);
                }
                else if(shortestPaths.get(actual) > shortestPaths.get(node)+actualDistance) {
                    shortestPaths.put(actual, actualDistance + shortestPaths.get(node));
                    actual.setPrevious(node);
                }

                if (actual.isVisited() == false)
                    nodesToProcess.add(actual);
            }

            nodesToProcess.remove(node);
        }
        /*for (int i=0; i<graph.getVertexList().size();i++) {
            if(graph.getVertexList().get(i).getPrevious() == null)
                System.out.println("SOY NULL");
            else{
                station = (Station) graph.getVertexList().get(i).getValue();
                System.out.println("current" +station.getIdStation());
                station = (Station) graph.getVertexList().get(i).getPrevious().getValue();
                System.out.println(station.getIdStation());}
        }*/

        Stack<Node<T>> stack = new Stack<>();
        ArrayList<ArrayList<Node<T>>> paths = new ArrayList<>(new ArrayList<>());
        for (int i=0; i<graph.getVertexList().size(); i++){
            ArrayList<Node<T>> path = new ArrayList<>();
            if(graph.getVertexList().get(i) == source)
                continue;
            else{
                Node<T> actual = graph.getVertexList().get(i);
                while(actual!=null){
                    if(stack.contains(actual)) {
                        //stack.pop();
                        break;
                    }
                    else {
                        stack.push(actual);
                        station = (Station) actual.getValue();
                        //System.out.println(station.getIdStation());
                        if(actual == source)
                            break;
                        else
                            actual = actual.getPrevious();
                    }
                }
            }
            while(!stack.isEmpty()){
                path.add(stack.pop());
            }
            paths.add(path);
        }
        //System.out.println(graph.getVertexList());
        //System.out.println(paths);
        source.setShortestPaths(shortestPaths);
        return paths;
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

    public void cleanVisitedNodes(){
        for(int i=0; i<graph.getVertexList().size(); i++){
            graph.getVertexList().get(i).setVisited(false);
        }
    }

}
