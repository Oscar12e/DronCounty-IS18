import Simulation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;


public class Main extends Application {

    public static void main(String[] args){
        System.out.println("Iniciando aplicación");
        launch(args);
        Trip trip;
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



