package MVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirTrafficSimulationController {
    private AirTrafficSimulationView theView;
    private AirTrafficSimulationModel theModel;

    public AirTrafficSimulationController(AirTrafficSimulationView pTheView, AirTrafficSimulationModel pTheModel){
        this.theView = pTheView;
        this.theModel = pTheModel;

    }
}

class AirTrafficSimulationListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
