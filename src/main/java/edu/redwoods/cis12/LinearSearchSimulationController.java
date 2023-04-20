package edu.redwoods.cis12;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LinearSearchSimulationController extends SimulationController {

    @FXML
    private TextField searchForTextField;

    @Override
    public void initialize() {

    }

    public TextField getSearchForTextField() {
        return searchForTextField;
    }
}