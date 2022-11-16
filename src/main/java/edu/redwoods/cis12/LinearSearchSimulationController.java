package edu.redwoods.cis12;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LinearSearchSimulationController {
    private LinearSearchSimulation lss;

    @FXML
    private TextField arraySizeTextField;

    @FXML
    private ColorPicker colorColorPicker;

    @FXML
    private GridPane controlsGridPane;

    @FXML
    private Button createBoardButton;

    @FXML
    private TextField searchForTextField;

    @FXML
    private Button simulateButton;

    @FXML
    void createBoardButtonPressed(ActionEvent event) {
        try {
            this.lss.createBoard(
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
    void simulateButtonPressed(ActionEvent event) {
        this.lss.simulate();
    }

    public void setLss(LinearSearchSimulation lss) {
        this.lss = lss;
    }

    public TextField getSearchForTextField() {
        return searchForTextField;
    }
}
