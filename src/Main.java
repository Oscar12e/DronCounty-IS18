import Simulation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    public static void main(String[] args){
        Simulator s = new Simulator();
        s.generateNodes(5,1000);
        s.generateMap(500,500);
        s.generateArcs(5);
        s.printInfo();

/*
        System.out.println("Iniciando aplicación");
        launch(args);
        ArrayList<Integer> listaSimple = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(2,55);
        }};

        ArrayList<Integer> listaSimple2 = new ArrayList<Integer>(){{
            add(3);
            add(4);
            add(5);
        }};


        //listaSimple.removeAll(listaSimple2);

        System.out.println(listaSimple);*/
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



