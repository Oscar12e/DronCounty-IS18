import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    public static void main(String[] args){
        System.out.println("Hola mundo");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MVC/AirTrafficSimulationView.fxml"));
        primaryStage.setTitle("Hello app");
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
