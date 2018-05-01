package MVC;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;


public class AirTrafficSimulationController  implements Initializable {
    @FXML private Button BTN_startSimulation;
    @FXML private ToggleGroup algorithmSelection;
    @FXML private Spinner<Integer> SB_stationQuantity;
    @FXML private Spinner<Integer> SB_tripsQuantity;
    @FXML private Spinner<Integer> SB_widthMeasure;
    @FXML private Spinner<Integer> SB_arcsQuantity;
    @FXML private Spinner<Integer> SB_hourEquivalent;

    @FXML private RadioButton RB_Backtracking;
    @FXML private RadioButton RB_Probabilistic;
    @FXML private RadioButton RB_Heuristic;

    private AirTrafficSimulationModel theModel = new AirTrafficSimulationModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Inicia");

    }



    public void startSimulation(){

    }

    public void setSimulationParameters(){
        theModel.setArcsPerStation(SB_arcsQuantity.getValue());
        theModel.setSimulateHourEquivalentToMiliseconds(SB_hourEquivalent.getValue());
        theModel.setStationsAmount(SB_stationQuantity.getValue());
        theModel.setTripsAmount(SB_tripsQuantity.getValue());
        theModel.createGraph(SB_stationQuantity.getValue(), SB_arcsQuantity.getValue());

        RadioButton selectedButton = (RadioButton) algorithmSelection.getSelectedToggle();
        theModel.setSimulator(selectedButton.getText());

        BTN_startSimulation.setDisable(false);
    }


}

