package edu.redwoods.cis12;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.util.Random;

public abstract class AlgorithmSimulation {
    private final String title;
    private SimulationController sc;
    private AlgSimulatorController asc;
    protected GridPane gridPane;

    protected String simResource;

    public AlgorithmSimulation(String title, String simResource, AlgSimulatorController asc) {
        this.title = title;
        this.simResource = simResource;
        this.asc = asc;
    }
    public AlgorithmSimulation(String title) {
        this.title = title;
    }
    public void loadControls() {
        try {
            //controlsGridPane = FXMLLoader.load(getClass().getResource("binarySearchControls.fxml"));
            FXMLLoader loader = new FXMLLoader();
            Pane controlsGridPane = loader.load(getClass().getResourceAsStream(this.simResource));
            this.setSc(loader.getController());
            this.getSc().setSimulation(this);
            this.getAsc().setControls(controlsGridPane);
        } catch (IOException | NullPointerException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("File Not Found");
            a.setContentText(e.getMessage());
            a.show();
        }
    }
    public abstract void simulate();

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() { return this.getTitle();}

    public AlgSimulatorController getAsc() {
        return asc;
    }

    public void setAsc(AlgSimulatorController asc) {
        this.asc = asc;
    }

    public SimulationController getSc() {
        return sc;
    }

    public void setSc(SimulationController sc) {
        this.sc = sc;
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
