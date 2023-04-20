package edu.redwoods.cis12;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class BinarySearchSimulation extends AlgorithmSimulation {
    public BinarySearchSimulation(AlgSimulatorController asc) {
        super("Binary Search", "binarySearchControls.fxml", asc);
    }

    private void algorithmStep(int searchFor, int start, int end) {
        try {
            if(end < start) {
                return;
            }
            int middle = (start + end)/2;  // Integer division is OK

            Button b = (Button)gridPane.getChildren().get(middle);
            b.setStyle("-fx-background-color: #00AA00");
            TimeUnit.SECONDS.sleep(1);
            int value = Integer.parseInt(b.getText());

            if(value == searchFor) {
                b.setStyle("-fx-background-color: #FF0000");
                return;
            }
            if(value < searchFor) {
                // Make all squares from search and below disabled.
                for(int i=start; i<=middle; i++) {
                    b = (Button)gridPane.getChildren().get(i);
                    b.setStyle("-fx-background-color: #666666");
                }
                start = middle + 1;
            } else {
                // Make all squares from search and above disabled.
                for(int i=middle; i<=end; i++) {
                    b = (Button)gridPane.getChildren().get(i);
                    b.setStyle("-fx-background-color: #666666");
                }
                end = middle - 1;
            }
            TimeUnit.SECONDS.sleep(3);
            algorithmStep(searchFor, start, end);
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

    private void sortGrid() {
        // Generates a sorted list, sorting on button text value, but is not a deep copy
        ObservableList<Node> sortedList = gridPane.getChildren().sorted(
                Comparator.comparingInt(o -> Integer.parseInt(((Button) o).getText())));
        // Create a new list to make a deep copy so clear doesn't garbage collect GridPane Nodes
        ObservableList<Node> deepCopy = FXCollections.observableArrayList();
        deepCopy.addAll(sortedList);

        // Clear previous list of Nodes, so we don't get duplicate add errors.
        gridPane.getChildren().clear();

        // Apparently I have to loop over gridPane and re-Add the sorted Nodes instead of using getChildren().addAll()
        // to get the nodes to show up in the right spots. I'm probably missing something here.
        int size = deepCopy.size();
        int columns = (int)Math.sqrt(size);
        for(int i=0, c=0, r=0; i<size; i++, c = (c+1) % columns, r = (i-c)/columns) {
            gridPane.add(deepCopy.get(i), c, r);
        }
    }

    @Override
    public void simulate() {
        try {
            sortGrid();

            int searchFor = Integer.parseInt(
                    ((BinarySearchSimulationController)this.getSc()).getSearchForTextField().getText());
            new Thread(() -> {
                algorithmStep(searchFor, 0, gridPane.getChildren().size() - 1); // size is > last index, so -1
            }).start();
        } catch(NumberFormatException|NullPointerException n) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Invalid Search");
            a.setContentText("Please enter an integer to search for in the \"Search For\" text-field.");
            a.show();
        }
    }
}