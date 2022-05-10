/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Connor Vucovich
 * Section: 01 - 10a
 * Date: 4/13/22
 * Time: 10:21 AM
 *
 * Project: csci205_final_project
 * Package: main
 * Class: World
 *
 * Description:
 *
 * ****************************************
 */
package main;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

/**
 * A simple class to manage the GUI and its interactions with the various objects of the simulation
 */
public class World {

    /**
     * A reference to the model this controller must work with
     */
    private static WorldModel theModel;

    /**
     * The Graphics Context of the canvas
     */
    public static GraphicsContext gc;

    /**
     * Resource bundle object
     */
    @FXML
    private ResourceBundle resources;

    /**
     * The XML url object
     */
    @FXML
    private URL location;

    /**
     * The button encapsulation
     */
    @FXML
    private Button btnGenerate;

    /**
     * The start button encapsulation
     */
    @FXML
    private Button btnStart;

    /**
     * The canvas encapsulation
     */
    @FXML
    private Canvas canvas;

    /**
     * The predator slider counter
     */
    @FXML
    private Slider sliderPred;

    /**
     * The prey slider counter
     */
    @FXML
    private Slider sliderPrey;

    /**
     * This method initializes the GUI with all of the button ands slider across the canvas
     */
    @FXML
    void initialize() {
        assert btnGenerate != null : "fx:id=\"btnGenerate\" was not injected: check your FXML file 'world.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'world.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'world.fxml'.";
        assert sliderPred != null : "fx:id=\"sliderPred\" was not injected: check your FXML file 'world.fxml'.";
        assert sliderPrey != null : "fx:id=\"sliderPrey\" was not injected: check your FXML file 'world.fxml'.";

        this.gc = canvas.getGraphicsContext2D();

    }

    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Static getter method for the animal list
     * @return the animal list
     */
    public static List<Animal> getAnimals() {
        return WorldModel.animals;
    }


    /**
     * A list o threads storing the animals and predators
     */
    public static List<Thread> threads = new ArrayList<>();

    /**
     * A list used to store all of the predator objects
     */
    public static List<Predator> predators = new ArrayList<>();

    /**
     * Setter method for the model. Uses the two button objects to take in their number counters and gneerate animals
     * based on their settings
     * @param theModel takes in the world model object
     */
    public void setModel(WorldModel theModel) {
        this.theModel = theModel;

        this.btnGenerate.setOnAction(event -> {
            int numGenPrey = (int) this.sliderPrey.getValue();
            for(int x=0; x < numGenPrey; x++) {
                Animal animal = this.theModel.generateAnimal((int) this.canvas.getWidth(), (int)this.canvas.getHeight());
                WorldModel.animals.add(animal);
                Thread myThread = new Thread(animal);
                threads.add(myThread);
                this.theModel.generateFood(15, (int)this.canvas.getWidth(), (int)this.canvas.getHeight());

            }

            int numGenPred = (int) this.sliderPred.getValue();

            for(int x=0; x < numGenPred; x++) {
                Predator predator = this.theModel.generatePredator((int) this.canvas.getWidth(), (int) this.canvas.getHeight());
                predators.add(predator);
                Thread predatorThread = new Thread(predator);
                threads.add(predatorThread);

            }


            //btnGenerate.setVisible(false); //Makes the generate not visible anymore after it has been clicked
        });

        this.btnStart.setOnAction(event -> {

            Runnable theUpdater = new UpdateWorld();
            Thread update = new Thread(theUpdater);

            for (Thread thread : threads){
                thread.start();
            }

            update.start();
            });

    }


    /**
     * A simple class to encapsulate the updating of the objects on the GUI
     */
    private class UpdateWorld implements Runnable{

        /**
         * World model object
         */
        private WorldModel theModel;

        /**
         * Method to take in the postitions of every animal, predator, and food object to put their positions onto the GUI
         * @throws InterruptedException
         */
        private void UpdatePositions() throws InterruptedException {
            while (WorldModel.animals.size() > 0 || predators.size() > 0) {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFill(Color.GREEN);
                for (Food food : theModel.getFoodList()) {

                    gc.fillRect(food.getFoodLocX(), food.getFoodLocY(), 5, 5);
                }
                gc.setFill(Color.BLACK);

                for (Animal animal : WorldModel.animals) {
                    gc.fillOval(animal.getAnimalLocX(), animal.getAnimalLocY(), 20, 20);
                }

                gc.setFill(Color.RED);
                for (Predator predator : predators){
                    gc.fillOval(predator.getAnimalLocX(), predator.getAnimalLocY(), 30, 30);
                }
                Thread.sleep(10);
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); //clears at the end


            }
        }


        /**
         * Run object for the thread updater that continually runs the updatePositions method if at all possible
         */
        @Override
        public synchronized void run() {
            try {
                UpdatePositions();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("here");

        }
    }
}




