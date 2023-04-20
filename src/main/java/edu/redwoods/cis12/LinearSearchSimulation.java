package edu.redwoods.cis12;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.util.concurrent.TimeUnit;

public class LinearSearchSimulation extends AlgorithmSimulation {
    public LinearSearchSimulation(AlgSimulatorController asc) {
        super("Linear Search", "linearSearchControls.fxml", asc);
    }

    private void algorithmStep(int searchFor) {
        try {
            for(Node n : gridPane.getChildren()) {
                Button b = (Button)n;
                int v = Integer.parseInt(b.getText());
                if( v == searchFor ) {
                    b.setStyle("-fx-background-color: #FF0000");
                    return;
                }
                b.setStyle("-fx-background-color: #666666");
                TimeUnit.SECONDS.sleep(1);
            }
        } catch(NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Invalid Search");
            a.setContentText("Please enter an integer to search for in the \"Search For\" text-field.");
            a.show();
        } catch(InterruptedException ie) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Interrupted");
            a.setContentText("Please enter an integer to search for in the \"Search For\" text-field.");
            a.show();
        }
    }

    @Override
    public void simulate() {
        try {
            int searchFor = Integer.parseInt(((LinearSearchSimulationController)this.getSc()).getSearchForTextField().getText());
            new Thread(() -> {
                algorithmStep(searchFor); // -1 because size is > last index
            }).start();
        } catch(NumberFormatException|NullPointerException n) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Invalid Search");
            a.setContentText("Please enter an integer to search for in the \"Search For\" text-field.");
            a.show();
        }
    }
}