package edu.redwoods.cis12;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AlgSimulatorController {

    @FXML
    private ListView<AlgorithmSimulation> algorithmList;

    @FXML
    private AnchorPane controlsAnchorPane;

    @FXML
    private Pane simulationAreaPane;

    private final ObservableList<AlgorithmSimulation> algs = FXCollections.observableArrayList();

    public void initialize() {
        // This is where we add all of our algorithms!
        // TODO: Make algorithm loads dynamic, or read from a folder/config file.
        algs.add(new LinearSearchSimulation(this));
        algs.add(new BinarySearchSimulation(this));
        algorithmList.setItems(algs);

        algorithmList.getSelectionModel().selectedItemProperty()
                .addListener(
                        (ov, oldAlg, newAlg) -> newAlg.loadControls()
                );
    }

    public void setControls(Node element) {
        this.controlsAnchorPane.getChildren().clear();
        this.controlsAnchorPane.getChildren().add(element);
    }
    public void setSimulationArea(Pane simulation) {
        this.simulationAreaPane.getChildren().clear();
        this.simulationAreaPane.getChildren().add(simulation);
    }

}