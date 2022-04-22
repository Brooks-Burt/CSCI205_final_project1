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
import main.model.ParticleSystemModel;

public class World {

    /**
     * A reference to the model this controller must work with
     */
    private WorldModel theModel;

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
    void initialize() {
        assert btnGenerate != null : "fx:id=\"btnGenerate\" was not injected: check your FXML file 'world.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'world.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'world.fxml'.";

        this.gc = canvas.getGraphicsContext2D();

    }

    public List<Animal> animals = new ArrayList<>();
    public List<Thread> threads = new ArrayList<>();
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
            System.out.println("Press Button");

            /**
            for (int i = 0; i < 3; i++) {
                Animal animal = this.theModel.generateAnimal((int) this.canvas.getWidth(), (int)this.canvas.getHeight());
                Thread thread = new Thread(animal);
                thread.start();
            }
            **/
            Animal animal = this.theModel.generateAnimal((int) this.canvas.getWidth(), (int)this.canvas.getHeight());
            animals.add(animal);
            Thread myThread = new Thread(animal);
            threads.add(myThread);

            //myThread.start();
        });

        this.btnStart.setOnAction(event -> {
            Runnable theUpdater = new UpdateWorld();
            Thread update = new Thread(theUpdater);

            for (Thread thread : threads){
                thread.start();
            }
            update.start();

//            gc.fillOval(animals.get(0).getAnimalLocX(), animals.get(0).getAnimalLocY(), 20, 20);
//            while (true){
//                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//                for (Animal animal : animals){
//                    gc.fillOval(animal.getAnimalLocX(), animal.getAnimalLocY(), 20, 20);
//                }


            });


    }

    /*private class WorldThread implements Runnable {
        @Override
        public void run() {
            try {
                Animal threadAnimal = theModel.generateAnimal((int) canvas.getWidth(), (int) canvas.getHeight());
                System.out.println(threadAnimal.getEnergy());
                //while (threadAnimal.getEnergy() > 0) {
                    gc.fillOval(threadAnimal.getAnimalLocX(), threadAnimal.getAnimalLocY(), 30, 30);
                    threadAnimal.Move();
                    //System.out.println(threadAnimal.getEnergy());
                    Thread.sleep(50);
                    //gc.fillOval(threadAnimal.getAnimalLocX(), threadAnimal.getAnimalLocY(), 30, 30);
                //}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/

    private class UpdateWorld implements Runnable{

        private void UpdatePositions(List<Animal> animals) throws InterruptedException {
            while (true) {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                for (Animal animal : animals) {
                    gc.fillOval(animal.getAnimalLocX(), animal.getAnimalLocY(), 20, 20);
                    //Thread.sleep(25);

                }
                //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                Thread.sleep(10);
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




