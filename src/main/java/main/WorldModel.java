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
     * Generate an animal at a random location with a set speed and reprodution rate
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
     * This method intakes two doubles representing the coordinates of the animal and creates a new animal at those coordinates
     * @param x - double representing the X location of the animal
     * @param y - double representing the Y location of the animal
     */
    public void addNewAnimal(double x, double y) {
        Animal animal = new Animal(1, .5, x, y);

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
}

