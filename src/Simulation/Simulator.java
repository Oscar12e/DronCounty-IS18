package Simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Simulator<T> {

    private GraphLogic graphLogic;
    private Graph<T> graph;
    private Scheduler scheduler;
    private Timeline timeline;
    private ArrayList<OrderedPair> vertexPositions;

    public Simulator() {
    }

    public void generateNodes(int stationsQuantity, int dronesQuantity){
        this.graph = new Graph();
        this.graphLogic = new GraphLogic(this.graph);
        Random random = new Random(System.currentTimeMillis());
        int randomNumber;
        int proportion = dronesQuantity/stationsQuantity;
        char baseId = 'A';

        for(int i=0; i<stationsQuantity; i++){
            Station station = new Station(0,baseId);
            Node vertex = new Node(station);
            graphLogic.insertVertex(vertex);
            baseId++;
        }

        Station station;
        for(int i=0; i<=proportion; i++){
            randomNumber = random.nextInt(stationsQuantity);
            station = (Station) graph.getVertexList().get(randomNumber).getValue();
            station.setTotalDronesQuantity(station.getTotalDronesQuantity()+1);
        }

        int totalDrones = 0;
        for(int i=0; i<=stationsQuantity-1;i++){
            station = (Station) graph.getVertexList().get(i).getValue();
            station.setTotalDronesQuantity(station.getTotalDronesQuantity()*stationsQuantity);
            totalDrones+=station.getTotalDronesQuantity();
            station.setCurrentDronesQuantity(station.getTotalDronesQuantity());
            System.out.println(station.getIdStation() +": "+station.getTotalDronesQuantity());
        }

        if(totalDrones>dronesQuantity){
            station = (Station) graph.getVertexList().get(stationsQuantity-1).getValue();
            station.setTotalDronesQuantity(station.getTotalDronesQuantity()-(totalDrones-dronesQuantity));
            station.setCurrentDronesQuantity(station.getTotalDronesQuantity());
        }

        //Clone the content of graph.VertexList to each Node.availableNodes
        for (int i=0; i<stationsQuantity; i++){
            graph.getVertexList().get(i).setAvailableNodes(new ArrayList<>(graph.getVertexList()));
            graph.getVertexList().get(i).getAvailableNodes().remove(i);
        }
/*
        for(int i=0; i<stationsQuantity-1; i++){
            percentage = random.nextInt(100);
            dronesThisStation = dronesQuantity*percentage/100;
            System.out.println(dronesThisStation);
            dronesQuantity = dronesQuantity - dronesThisStation;
            Station station = new Station(dronesThisStation,i);
            Node vertex = new Node(station);
            graphLogic.insertVertex(vertex);
        }
        Station station = new Station(dronesQuantity,stationsQuantity);
        System.out.println(dronesQuantity);
        Node vertex = new Node(station);
        graphLogic.insertVertex(vertex);*/
    }

    public boolean generateArcs(int arcsPerStation){
        if(arcsPerStation>=graph.getVertexList().size())
            arcsPerStation=graph.getVertexList().size()-1;
        if (graph.getVertexList().size()<arcsPerStation)
            return false;
        Station station;
        for(int i = 0; i<graph.getVertexList().size(); i++){
            station = (Station) graph.getVertexList().get(i).getValue();
            Node sourceStation = graphLogic.searchVertex(station.getIdStation());
            Random random = new Random(System.currentTimeMillis());
            int randomId;
            int arcsLeft = 0;
            ArrayList<Node> closerNodes = null;
            for(int j=0; j<arcsPerStation; j++) {
                //System.out.println(j);
                ArrayList<Node> voidNodes = graphLogic.searchVoidNodes(station.getIdStation());
                if (voidNodes.size()>0) {
                    //System.out.println(voidNodes.size());
                    randomId = random.nextInt(voidNodes.size());
                    Node chosenStation = voidNodes.get(randomId);
                    int weight = calculateTwoNodeDistance(chosenStation, sourceStation);
                    graphLogic.insertArc(sourceStation, chosenStation, weight);
                    //System.out.println(chosenStation.getArcs());
                }
                else {
                    //Entre estaciones mas cercanas
                    arcsLeft = arcsPerStation-j;
                    closerNodes = getCloserNodes(sourceStation);
                    break;
                }
            }
            if(arcsLeft != 0 && closerNodes.size()!=0) {
                for (int j = 0; j < arcsLeft; j++) {
                    //System.out.println(j);
                    int weight = calculateTwoNodeDistance(sourceStation, closerNodes.get(0));
                    graphLogic.insertArc(sourceStation, closerNodes.remove(0), weight);
                }
            }
        }
        return true;
    }

    public void generateMap(int mapSizeX, int mapSizeY){
        int frameWidth = mapSizeX/6;
        int frameHeight = mapSizeY/5;
        int x = 0;
        int y = 0;
        ArrayList<OrderedPair> possiblePositions = new ArrayList<>();
        //generate grid of possible positions
        for (int i=0; i<6; i++){
            x+=frameWidth;
            for (int j=0; j<5; j++){
                y+=frameHeight;
                OrderedPair newPair = new OrderedPair(x,y);
                //System.out.println(newPair.getX()+" y:"+newPair.getY());
                possiblePositions.add(newPair);
            }
            y=0;
        }
        //generate random position based on previous generated grid
        Random random = new Random(System.currentTimeMillis());
        int randomNumber;
        int randomX;
        int minX;
        int randomY;
        int minY;
        for (int i=0; i<graph.getVertexList().size(); i++){
            randomNumber = random.nextInt(possiblePositions.size());
            minX = possiblePositions.get(randomNumber).getX()-frameWidth;
            randomX = random.nextInt((possiblePositions.get(randomNumber).getX()-minX)+1)+minX;

            minY = possiblePositions.get(randomNumber).getY()-frameHeight;
            randomY = random.nextInt((possiblePositions.get(randomNumber).getY()-minY)+1)+minY;

            OrderedPair orderedPair = new OrderedPair(randomX,randomY);
            graph.getVertexList().get(i).setOrderedPair(orderedPair);
            System.out.println("x:"+graph.getVertexList().get(i).getOrderedPair().getX()+" y:"+graph.getVertexList().get(i).getOrderedPair().getY());
            possiblePositions.remove(randomNumber);
        }
    }

    public int calculateTwoNodeDistance(Node<T> node1, Node<T> node2){
        //Node node1 = graphLogic.searchVertex(key1);
        //Node node2 = graphLogic.searchVertex(key2);

        int result = (int) Math.hypot(node1.getOrderedPair().getX()-node2.getOrderedPair().getX(), node1.getOrderedPair().getY()-node2.getOrderedPair().getY());
        return result;
    }

    public ArrayList<Node<T>> getCloserNodes(Node<T> source){
        ArrayList<Node<T>> closerNodes = new ArrayList<>();
        if(source.getAvailableNodes().size() == 0){
            return closerNodes;
        }
        for (int i=0; i<source.getAvailableNodes().size(); i++){
            if(closerNodes==null)
                closerNodes.add(source.getAvailableNodes().get(i));
            else if(calculateTwoNodeDistance(source, source.getAvailableNodes().get(i)) < calculateTwoNodeDistance(source, source.getAvailableNodes().get(i))){
                closerNodes.add(i-1, source.getAvailableNodes().get(i));
            }
            else
                closerNodes.add(source.getAvailableNodes().get(i));
        }
        return closerNodes;
    }

    public void printInfo(){
        Station station;
        for (int i=0; i<graph.getVertexList().size(); i++){
            station = (Station) graph.getVertexList().get(i).getValue();
            System.out.println("Station: "+ station.getIdStation()+"    Arcs: ");
            for (int j=0; j<graph.getVertexList().get(i).getArcs().size(); j++){
                Station s = (Station) graph.getVertexList().get(i).getArcs().get(j).getDestiny().getValue();
                System.out.println(graph.getVertexList().get(i).getArcs().get(j).getWeight() + " to station: "+ s.getIdStation());
            }
        }
    }

}
