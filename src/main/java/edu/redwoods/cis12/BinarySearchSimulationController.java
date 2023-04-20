package edu.redwoods.cis12;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BinarySearchSimulationController extends SimulationController {

    @FXML
    private TextField searchForTextField;

    public void initialize() { }

    public TextField getSearchForTextField() {
        return searchForTextField;
    }
}