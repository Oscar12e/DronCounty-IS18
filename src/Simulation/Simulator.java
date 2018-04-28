package Simulation;

import java.util.ArrayList;
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
            System.out.println(station.getTotalDronesQuantity());
        }

        if(totalDrones>dronesQuantity){
            station = (Station) graph.getVertexList().get(stationsQuantity-1).getValue();
            station.setTotalDronesQuantity(station.getTotalDronesQuantity()-(totalDrones-dronesQuantity));
            station.setCurrentDronesQuantity(station.getTotalDronesQuantity());
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
        if (graph.getVertexList().size()<arcsPerStation)
            return false;
        Station station;
        for(int i = 0; i<graph.getVertexList().size(); i++){
            station = (Station) graph.getVertexList().get(i).getValue();
            Node sourceStation = graphLogic.searchVertex(station.getIdStation());
            Random random = new Random(System.currentTimeMillis());
            int randomId;
            for(int j=0; j<arcsPerStation; j++) {
                ArrayList<Node> voidNodes = graphLogic.searchVoidNodes(station.getIdStation());
                if (voidNodes != null) {
                    randomId = random.nextInt(voidNodes.size());
                    int randomWeight = random.nextInt(1000);
                    Node chosenStation = voidNodes.get(randomId);
                    graphLogic.insertArc(sourceStation, chosenStation, randomWeight);
                    //System.out.println(chosenStation.getArcs());
                }
                else {
                    //Entre estaciones mas cercanas
                }
            }
        }
        return true;
    }

    public ArrayList generateMap(int mapSizeX, int mapSizeY){
        int frameWidth = mapSizeX/30;
        int frameHeight = mapSizeY/30;
        int x = 0;
        int y = 0;
        ArrayList<OrderedPair> possiblePositions = new ArrayList<>();
        //generate grid of possible positions
        for (int i=0; i<30; i++){
            x+=frameWidth;
            for (int j=0; j<30; j++){
                y+=frameHeight;
                OrderedPair newPair = new OrderedPair(x,y);
                possiblePositions.add(newPair);
            }
        }
        //generate random position based on previous generated grid
        Random random = new Random(System.currentTimeMillis());
        int randomNumber;
        for (int i=0; i<graph.getVertexList().size(); i++){
            randomNumber = random.nextInt(possiblePositions.size());
            graph.getVertexList().get(i).setOrderedPair(possiblePositions.get(randomNumber));
            possiblePositions.remove(randomNumber);
        }
        return null;
    }


}
