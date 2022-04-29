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

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import main.model.ParticleSystemModel;

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

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGenerate;

    @FXML
    private Button btnStart;

    @FXML
    public Canvas canvas;

    @FXML
    private Slider slider;

    @FXML
    private Button resetBtn;


    @FXML
    void initialize() {
        assert btnGenerate != null : "fx:id=\"btnGenerate\" was not injected: check your FXML file 'world.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'world.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'world.fxml'.";
        assert resetBtn != null : "fx:id=\"resetBtn\" was not injected: check your FXML file 'world.fxml'.";
        assert slider != null : "fx:id=\"slider\" was not injected: check your FXML file 'world.fxml'.";

        this.gc = canvas.getGraphicsContext2D();

    }

    public static List<Animal> animals = new ArrayList<>();
    public static List<Thread> threads = new ArrayList<>();
    private Runnable UpdateWorld;

    private void UpdatePositions(List<Animal> animals) {

        for (Animal animal : animals){

            gc.fillOval(animal.getAnimalLocX(), animal.getAnimalLocY(), 30, 30);

        }
        System.out.println("got here!");

    }

    public void setModel(WorldModel theModel) {
        this.theModel = theModel;

        this.btnGenerate.setOnAction(event -> {
            int numGen = (int) this.slider.getValue();
            System.out.println(numGen);
            for(int x=0; x < numGen; x++) {
                Animal animal = this.theModel.generateAnimal((int) this.canvas.getWidth(), (int)this.canvas.getHeight());
                animals.add(animal);
                Thread myThread = new Thread(animal);
                threads.add(myThread);
                this.theModel.generateFood(15, (int)this.canvas.getWidth(), (int)this.canvas.getHeight());
            }
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

    public static void reproduce(Canvas canvas, Double animalLocX, Double animalLocY) {
        Animal animal = theModel.generateAnimal((int) canvas.getWidth(), (int) canvas.getHeight());
        animals.add(animal);
        animal.setAnimalLocX(animalLocX+20);
        animal.setAnimalLocY(animalLocY+20);
        Thread myThread = new Thread(animal);
        threads.add(myThread);
        myThread.start();
    }


    private class UpdateWorld implements Runnable{

        private void UpdatePositions(List<Animal> animals) throws InterruptedException {
            //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            while (animals.size() > 0) {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                /*for (Animal animal : animals){
                    if (animal.getEnergy() <= 0){
                        //Animal animal1 = animal;
                        //Animal animal2 = animal;
                        //animals.add(animal1);
                        //animals.add(animal2);
                        animals.remove(animal);
                    }
                }*/
                gc.setFill(Color.GREEN);
                for (Food food : theModel.getFoodList()) {

                    gc.fillRect(food.getFoodLocX(), food.getFoodLocY(), 5, 5);
                }
                gc.setFill(Color.BLACK);

                for (Animal animal : animals) {
                    gc.fillOval(animal.getAnimalLocX(), animal.getAnimalLocY(), 20, 20);
                    //Thread.sleep(25);

                }
                //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                Thread.sleep(10);
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            }
        }


        @Override
        public synchronized void run() {
            try {
                UpdatePositions(animals);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("here");

        }
    }
}




