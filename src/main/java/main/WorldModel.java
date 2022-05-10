/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Brooks Burt
 * Section: 10am
 * Date: 4/15/22
 * Time: 10:46 AM
 *
 * Project: csci205_final_project
 * Package: main
 * Class: WorldModel
 *
 * Description:
 *
 * ****************************************
 */

package main;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 * A simple class that handles all of the constructors of objects within the simulation and generates them for use in the simulation
 */

public class WorldModel {

    /**
     * Default number of animals
     */
    public static final int DEF_NUM_ANIMALS = 1;

    /**
     * A shared random number generator
     */
    private static final Random rng = new Random();
    public static List<Animal> animals = new ArrayList<>();

    /**
     * A list of food to be generated
     */
    private static List<Food> foodList = new ArrayList<>();

    /**
     * Getter method for the foodList collection
     * @return a list object of all of the food items
     */
    public static List<Food> getFoodList() {
        return Collections.synchronizedList(foodList);
    }

    /**
     * Static method that takes in the canvas, and the animal's location and generates a new animal, with a new location
     * that is 20 distance away from the old animal, then adds the animal to the array and creates a new thread
     * object of it before starting that thread
     * @param canvas the canvas object of the model
     * @param animalLocX - the X location of the animal
     * @param animalLocY - the Y location of the animal
     */
    public static void reproduce(Canvas canvas, Double animalLocX, Double animalLocY) {
        Animal animal = generateAnimal((int) canvas.getWidth(), (int) canvas.getHeight());
        animals.add(animal);
        animal.setAnimalLocX(animalLocX+20);
        animal.setAnimalLocY(animalLocY+20);
        Thread myThread = new Thread(animal);
        World.threads.add(myThread);
        myThread.start();
    }

    /**
     * Static method that takes in the canvas, and the predator's location and generates a new predator, with a new location
     * that is 20 distance away from the old predator, then adds the predator to the array and creates a new thread
     * object of it before starting that thread
     * @param canvas
     * @param animalLocX
     * @param animalLocY
     */
    public static void reproducePredator(Canvas canvas, Double animalLocX, Double animalLocY) {
        Predator predator = generatePredator((int) canvas.getWidth(), (int) canvas.getHeight());
        World.predators.add(predator);
        predator.setAnimalLocX(animalLocX+20);
        predator.setAnimalLocY(animalLocY+20);
        Thread myThread = new Thread(predator);
        World.threads.add(myThread);
        myThread.start();
    }

    /**
     * Setter method for the foodlist object
     * @param foodList - a List of food objects to become the new food list collection
     */
    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    /**
     * This method takes in a food object and checks whether it is contained in the foodlist collection and removes it if it is contained
     * @param food - the food object to be removed from the list
     */
    public static void removeFood(Food food){
        if (foodList.contains(food)){
            foodList.remove(food);
        }
    }

    /**
     * Generate an animal at a random location
     *
     * @param maxWidth  - maximum x-coordinate to allow
     * @param maxHeight - maximum y-coordinate to allow
     */
    public static Animal generateAnimal(int maxWidth, int maxHeight) {
        System.out.println("Generate Animal");
        double x = (double) rng.nextInt(maxWidth);
        double y = (double) rng.nextInt(maxHeight);
        Animal animal = new Animal(5, .5, x, y);
        return animal;
    }



    /**
     * This method takes a number of food items to be generates as well as the size of the canvas onto which they will appear and
     * then populates the board with that number of food objects adding them and their locations into the foodList as they are added
     * @param num - integer representation of the total amount of food to be added
     * @param maxWidth - the integer representation of the maximum X value of the canvas
     * @param maxHeight - the integer representation of the maximum Y value of the canvas
     */
    public void generateFood(int num, int maxWidth, int maxHeight) {
        Random rand = new Random();

        for (int x = 0; x < num; x++) {
            int randomIntX = rand.nextInt(maxWidth);
            int randomIntY = rand.nextInt(maxHeight);
            Food newFood = new Food(randomIntX, randomIntY);
            getFoodList().add(newFood);
        }
    }

    /**
     * Method takes in the maximum coordinates for the predator object and creates a new predator object and returns
     * it
     * @param maxWidth - the maximum X coordinate
     * @param maxHeight - the maximum Y coordinate
     * @return the predator object that was created
     */
    public static Predator generatePredator(int maxWidth, int maxHeight) {
        System.out.println("Generate Predator");
        double x = (double) rng.nextInt(maxWidth);
        double y = (double) rng.nextInt(maxHeight);
        Predator predator = new Predator(5, .5, x, y);
        return predator;
    }


}

