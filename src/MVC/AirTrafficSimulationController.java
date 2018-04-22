package MVC;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;


public class AirTrafficSimulationController  implements Initializable {
    @FXML private  ToggleGroup algorithmSelection;
    @FXML private Spinner<Integer> SB_stationQuantity;
    @FXML private Spinner<Integer> SB_tripsQuantity;
    @FXML private Spinner<Integer> SB_heightMeasure;
    @FXML private Spinner<Integer> SB_widthMeasure;
    @FXML private Spinner<Integer> SB_arcsQuantity;
    @FXML private Spinner<Integer> SB_hourEquivalent;

    @FXML private RadioButton RB_Backtracking;
    @FXML private RadioButton RB_Probabilistic;
    @FXML private RadioButton RB_Heuristic;

    //private AirTrafficSimulationModel theModel = new AirTrafficSimulationModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Inicia");

    }


    public int getSB_stationQuantity() {
        return SB_stationQuantity.getValue();
    }

    public int getSB_tripsQuantity() {
        return SB_tripsQuantity.getValue();
    }

    public int getSB_heightMeasure() {
        return SB_heightMeasure.getValue();
    }

    public int getSB_widthMeasure() {
        return SB_widthMeasure.getValue();
    }

    public int getSB_arcsQuantity() {
        return SB_arcsQuantity.getValue();
    }

    public int getSB_hourEquivalent() {
        return SB_hourEquivalent.getValue();
    }

    public void getAlgorithmSelection(){
        RadioButton selectedButton = (RadioButton) algorithmSelection.getSelectedToggle();

        System.out.println(selectedButton.getText());
        System.out.println("Presionado");
    }
}

