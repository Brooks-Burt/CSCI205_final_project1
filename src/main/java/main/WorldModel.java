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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A simple class to encapsulate the objects contained in the simulation and the methods needed to run them
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

    /**
     * A list of food to be generated
     */
    private static List<Food> foodList = new ArrayList<>();

    /**
     * Getter method for the food in the list
     * @return the list object of every food item
     */
    public static List<Food> getFoodList() {
        return Collections.synchronizedList(foodList);
    }

    /**
     * Setter method for the foodlist object
     * @param foodList - the new food list that we want to set
     */
    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    /**
     * Method that removes a food item from the food list if it is contained in the list
     * @param food the food object to be removed from the list
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
     * Method to create a new animal at a given location
      * @param x - the x location of the animal
     * @param y - the y location of the animal
     */
    public void addNewAnimal(double x, double y) {
        Animal animal = new Animal(1, .5, x, y);

    }

    /**
     * Method to generate num amount of food at random locations across the canvas
     * @param num - the total amount of food to be create
     * @param maxWidth - the maximum X coordinate for the food
     * @param maxHeight - the maximum Y coordinate for the food
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
     * Method to generate a predator on the canvas
     * @param maxWidth - the maximum X coordinate that the predator can have
     * @param maxHeight - the maximum Y coordinate that the predator can have
     * @return the predator object
     */
    public static Predator generatePredator(int maxWidth, int maxHeight) {
        System.out.println("Generate Predator");
        double x = (double) rng.nextInt(maxWidth);
        double y = (double) rng.nextInt(maxHeight);
        Predator predator = new Predator(5, .5, x, y);
        return predator;
    }


}

