/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Brooks Burt
 * Section: 10am
 * Date: 4/13/22
 * Time: 11:32 PM
 *
 * Project: csci205_final_project
 * Package: main
 * Class: ParticleSystemMain
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
import main.model.ParticleSystemModel;

import java.io.IOException;

public class ParticleSystemMain extends Application {
    private ParticleSystemController theController;
    private ParticleSystemModel theModel;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        // Load in the FXML file. Obtain the root node of the scene graph
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/particlesim.fxml"));
        Parent root = loader.load();
        this.theController = loader.getController();
        this.theController.setModel(theModel);

        // Set up the stage
        primaryStage.setTitle("Particle Simulator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}