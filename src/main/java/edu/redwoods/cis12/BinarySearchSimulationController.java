package edu.redwoods.cis12;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class BinarySearchSimulationController {
    private BinarySearchSimulation bss;

    @FXML
    private GridPane controlsGridPane;

    @FXML
    private TextField arraySizeTextField;

    @FXML
    private ColorPicker colorColorPicker;

    @FXML
    private Button createBoardButton;

    @FXML
    private Button simulateButton;

    @FXML
    private TextField searchForTextField;

    public void initialize() { }

    @FXML
    void createBoardButtonPressed(ActionEvent event) {
        try {
            this.bss.createBoard(
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
    private void simulateButtonPressed(ActionEvent ignoredEvent) {
        this.bss.simulate();
    }

    public void setBss(BinarySearchSimulation bss) {
        this.bss = bss;
    }

    public TextField getSearchForTextField() {
        return searchForTextField;
    }
}
