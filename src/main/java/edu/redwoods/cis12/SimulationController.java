package edu.redwoods.cis12;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public abstract class SimulationController {

    protected AlgorithmSimulation as;
    @FXML
    protected TextField arraySizeTextField;
    @FXML
    protected ColorPicker colorColorPicker;

    public abstract void initialize();
    @FXML
    protected void createBoardButtonPressed(ActionEvent event) {
        try {
            this.as.createBoard(
                    Integer.parseInt(
                            this.arraySizeTextField.getText()), this.colorColorPicker.getValue());
        } catch (NumberFormatException nfe) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Invalid Input");
            a.setContentText("Please set an \"Array Size\"");
            a.show();
        }
    }

    @FXML
    protected void simulateButtonPressed(ActionEvent ignoredEvent) {
        this.as.simulate();
    }

    protected void setSimulation(AlgorithmSimulation as) {
        this.as = as;
    }

    protected AlgorithmSimulation getSimulation() {
        return this.as;
    }
}
