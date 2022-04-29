/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Connor Vucovich
 * Section: 01 - 10a
 * Date: 4/13/22
 * Time: 10:23 AM
 *
 * Project: csci205_final_project
 * Package: main
 * Class: Simulator
 *
 * Description:
 *
 * ****************************************
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A simple class to encapsulate the running of the simulation on the GUI
 */
public class Simulator extends Application {
    private World theWorld;
    private WorldModel theWorldModel;

    /**
     * The main class simply launches the given World model
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method takes in a stage and starts it upon the beginning of the simulation allowing it to run
     * @param primaryStage - the stage object that is set to be run
     * @throws IOException - an exception for if the stage is not a valid stage object
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.theWorldModel = new WorldModel();

        // Load in the FXML file. Obtain the root node of the scene graph
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/world.fxml"));
        Parent root = loader.load();
        this.theWorld = loader.getController();
        this.theWorld.setModel(theWorldModel);

        // Set up our stage
        primaryStage.setTitle("Animal Simulator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }}