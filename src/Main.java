import Simulation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;


public class Main extends Application {

    public static void main(String[] args){
        Simulator s = new Simulator();
        s.generateNodes(5,1000);
        //s.generateArcs(2);
        s.generateMap(150,150);

        System.out.println(3%9);

        System.out.println("Pruebas de Óscar");
        StationsLogic sl = new StationsLogic();

        List<String> names = Arrays.asList("a", "b", "c", "#", "$");


        Hashtable <Character, String > pA = new Hashtable <Character, String>() {{
            put('b',  "#b");
            put('c',  "#bc");
            put('#',  "#");
            put('&',  "#b$");
        }};

        Hashtable <Character, String > pB = new Hashtable <Character, String>() {{
            put('a',  "$#a");
            put('c',  "c");
            put('#',  "$#");
            put('&',  "$");
        }};

        Hashtable <Character, String > pC = new Hashtable <Character, String>() {{
            put('a',  "b$#a");
            put('b',  "b");
            put('#',  "b$#");
            put('&',  "b$");
        }};

        Hashtable <Character, String > pG = new Hashtable <Character, String>() {{
            put('a',  "a");
            put('b',  "b");
            put('c',  "bc");
            put('&',  "b$");
        }};

        Hashtable <Character, String > pD = new Hashtable <Character, String>() {{
            put('a',  "#a");
            put('b',  "b");
            put('c',  "bc");
            put('#',  "#");
        }};


        Hashtable <Character, Integer> dA = new Hashtable <Character, Integer>() {{
            put('a', 30);
            put('b', 36);
            put('c', 39);
            put('#', 31);
            put('$', 40);
        }};

        Hashtable <Character, Integer> dB = new Hashtable <Character, Integer>() {{
            put('a', 38);
            put('b', 30);
            put('c', 33);
            put('#', 37);
            put('$', 34);
        }};

        Hashtable <Character, Integer> dC = new Hashtable <Character, Integer>() {{
            put('a', 41);
            put('b', 33);
            put('c', 30);
            put('#', 40);
            put('$', 37);
        }};

        Hashtable <Character, Integer> dG = new Hashtable <Character, Integer>() {{
            put('a', 31);
            put('b', 35);
            put('c', 37);
            put('#', 30);
            put('$', 39);
        }};

        Hashtable <Character, Integer> dD = new Hashtable <Character, Integer>() {{
            put('a', 34);
            put('b', 34);
            put('c', 37);
            put('#', 33);
            put('$', 30);
        }};

        Hashtable <Character, Station>  stationsHash = new Hashtable <Character, Station>(){{
            put('a', new Station('a', pA, dA));
            put('b', new Station('b', pB, dB));
            put('c', new Station('c', pC, dC));
            put('#', new Station('#', pG, dG));
            put('$', new Station('$', pD, dD));
        }};


        sl.setStationsToControl(stationsHash);
        sl.setTravelingBetweenStations();
        sl.setRoutesUsedByTravels();
        System.out.println("Ahora viene lo bueno");
        sl.setDepartureTimeDifferenceNeed2();
        System.out.println("Ahora viene lo bueno2");
        sl.updateDepartureTime('a', 'b', 0);
        System.out.println("Ahora viene lo bueno3");
        return;

        //launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getResource("MVC/AirTrafficSimulationView.fxml");
        System.out.println(resource);
        Parent root = FXMLLoader.load(getClass().getResource("MVC/AirTrafficSimulationView.fxml"));
        primaryStage.setTitle("Hello It's me");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        /*
        Scene s = new Scene(layout, 200,200);
        primaryStage.setScene(s);
        primaryStage.show();
        */
    }
}



