package View;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AirTrafficSimulationView extends JFrame{

    private JLabel LB_typeOfSimulation = new JLabel("Elija el tipo de simulación a realizar");
    private JButton BTN_heuristic = new JButton("Heuristica");
    private JButton BTN_probabilistic = new JButton("Probabilistica");
    private JButton BTN_backtracking = new JButton("Backtracking");


    AirTrafficSimulationView(){

        JPanel simulationPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 200);


        simulationPanel.add(LB_typeOfSimulation);
        simulationPanel.add(BTN_heuristic);
        simulationPanel.add(BTN_probabilistic);
        simulationPanel.add(BTN_backtracking);

        this.add(simulationPanel);

    }

    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}
