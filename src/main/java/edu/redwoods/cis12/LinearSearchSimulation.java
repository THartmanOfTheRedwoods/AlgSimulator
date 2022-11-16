package edu.redwoods.cis12;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LinearSearchSimulation extends AlgorithmSimulation {
    private LinearSearchSimulationController lssController;
    private GridPane gridPane;
    public LinearSearchSimulation(AlgSimulatorController asc) {
        super("Linear Search", asc);
    }

    @Override
    public void loadControls() {
        try {
            //controlsGridPane = FXMLLoader.load(getClass().getResource("binarySearchControls.fxml"));
            FXMLLoader loader = new FXMLLoader();
            Pane controlsGridPane = loader.load(getClass().getResourceAsStream("linearSearchControls.fxml"));
            this.lssController = loader.getController();
            this.lssController.setLss(this);
            this.getAsc().setControls(controlsGridPane);
        } catch (IOException | NullPointerException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("File Not Found");
            a.setContentText(e.getMessage());
            a.show();
        }
    }

    @Override
    public void simulate() {
        try {
            int searchFor = Integer.parseInt(lssController.getSearchForTextField().getText());
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

    public void createBoard(int size, Color color) {
        //Creating a Grid Pane
        gridPane = new GridPane();
        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.setMaxHeight(Double.MAX_VALUE);
        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);
        //Arranging all the nodes in the grid
        int columns = (int)Math.sqrt(size);

        Random ran = new Random();

        for(int i=0, c=0, r=0; i<size; i++, c = (c+1) % columns, r = (i-c)/columns) {
            //Button b = new Button(String.valueOf(i));
            Button b = new Button(
                    String.valueOf(ran.nextInt(100) + 1));
            b.setMaxWidth(Double.MAX_VALUE);
            b.setMaxHeight(Double.MAX_VALUE);
            Font font = Font.font("Courier New", FontWeight.BOLD, 36);
            b.setFont(font);
            b.setStyle(String.format("-fx-background-color: %s",
                    String.format( "#%02X%02X%02X",
                            (int)( color.getRed() * 255 ),
                            (int)( color.getGreen() * 255 ),
                            (int)( color.getBlue() * 255 ) )));
            gridPane.add(b, c, r);
        }
        this.getAsc().setSimulationArea(gridPane);
    }
}
