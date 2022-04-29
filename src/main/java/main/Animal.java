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
 * Class: Animal
 *
 * Description:
 *
 * ****************************************
 */
package main;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * A simple class to encapsulate an animal object within the simulation
 */
public class Animal implements  Runnable {

    /**
     * Integer representation of the speed of the animal
     */
    private Integer speed;

    /**
     * Double representing the rate of reprodution between two indivudals within the simulation
     */
    private Double reproductionRate;

    /**
     * Integer representation of the energy of the animal
     */
    private double energy;

    /**
     * Double value that holds the X location of the animal within space
     */
    private double animalLocX;

    /**
     * Double value that holds the Y locations of the animal within space
     */
    private double animalLocY;

    /**
     * Constructor for the Animal class that assigns the attributes of the animal
     * @param speedVal - the integer representation of the animals speed
     * @param reproductionRateVal - the double value representation of the animal's reproduction rate
     * @param animalLocXVal - the Double representation of the animal's location along the X axis
     * @param animalLocYVal - the Double representation of the animal's location along the Y axis
     */
    public Animal(Integer speedVal, Double reproductionRateVal, Double animalLocXVal, Double animalLocYVal) {
        this.speed = speedVal;
        this.energy = 3000;
        this.reproductionRate = reproductionRateVal;
        this.animalLocX = animalLocXVal;
        this.animalLocY = animalLocYVal;
    }

    /**
     * This method acts on an animal object and randomly adjust its locations values based on some random
     * number scaled by its overall speed value
     */
    public void randMove(Animal animal) {
        Random rand = new Random();
        System.out.println(animal.getAnimalLocX() + ", " + animal.getAnimalLocY());
        int randomIntX = rand.nextInt(3) - 1;
        int randomIntY = rand.nextInt(3) - 1;
        animal.animalLocX = animal.getAnimalLocX() + (animal.getSpeed()*randomIntX);
        animal.animalLocY = animal.getAnimalLocY() + (animal.getSpeed()*randomIntY);
        System.out.println(randomIntX + ", " + randomIntY);
        System.out.println(animal.getAnimalLocX() + ", " + animal.getAnimalLocY());
        animal.energy = animal.getEnergy() - (Integer) (Math.abs(animal.getSpeed()*randomIntX) + Math.abs(animal.getSpeed()*randomIntY));
        System.out.println(animal.getEnergy());
        if (animal.getEnergy() <= 0) {
            AnimalDies(animal);
        }
    }

    /**
     * This method takes the current animal and moves it in a random direction with the overall distance in that direction
     * being determined by the speed of the animal, the method then reduces the overall energy of the animal by the absolute value of the total movement
     * If the movement would bring the animal outside of the given bounds of the canvas, it simply will bounce of the walls in the opposite direction back onto the canvas
     * @param width - the maximum X coordinate of the canvas
     * @param height - the maximum Y coordinate of the canvas
     */
    public void Move(int width, int height) {
        Random rand = new Random();
        System.out.println(this.getAnimalLocX() + ", " + this.getAnimalLocY());
        double randomIntX = (double) rand.nextInt(3) - 1;
        double randomIntY = (double) rand.nextInt(3) - 1;
        if ((this.getAnimalLocX() + (randomIntX*speed)) < 0 || (this.getAnimalLocX() + (randomIntX*speed)) > width) {
            //System.out.println("runs");
            randomIntX = -1*randomIntX;
        }
        if ((this.getAnimalLocY() + (randomIntY*speed)) < 0 || (this.getAnimalLocY() + (randomIntY*speed)) > height) {
            //System.out.println("runs");
            randomIntY = -1*randomIntY;
        }
        animalLocX = animalLocX + randomIntX * speed;
        animalLocY = animalLocY + randomIntY * speed;
        this.energy = this.getEnergy() - (Math.abs(this.getSpeed()*randomIntX) + Math.abs(this.getSpeed()*randomIntY));
    }

    /**
     * Method that will use machine learning to develop optimal movement styles for the animal so that it survives the longest
     */
    public void SmartMove() {
        //TODO: Define Smarter movement method based off of machine learning techniques
    }

    /**
     * Method takes in an animal once it has run out of energy and nullifies its attributes so that it is no longer visible
     * @param animal
     * @return
     */
    public static Animal AnimalDies(Animal animal) {
        animal = null;
        return animal;
    }

    /**
     * Getter for the animal X location
     * @return the X location of the animal
     */
    public double getAnimalLocX() {
        return animalLocX;
    }

    /**
     * Getter for the animal Y location
     * @return the Y location of the animal
     */
    public double getAnimalLocY() {
        return animalLocY;
    }

    /**
     * Setter method for the X location of the animal
     * @param animalLocX a double representing the X location of the animal
     */
    public void setAnimalLocX(double animalLocX) {
        this.animalLocX = animalLocX;
    }

    /**
     * Setter method for the X location of the animal
     * @param animalLocY a double representing the X location of the animal
     */
    public void setAnimalLocY(double animalLocY) {
        this.animalLocY = animalLocY;
    }

    /**
     * Getter method for the reproduction rate of the animal
     * @return a double representing the reproduction rate of the animal
     */
    public Double getReproductionRate() {
        return reproductionRate;
    }

    /**
     * Getter method for the energy of the animal
     * @return double representing the total remaining energy of the animal
     */
    public double getEnergy() {
        return energy;
    }

    /**
     * Getter method for the speed of the animal
     * @return integer representation of the speed of the animal
     */
    public Integer getSpeed() {
        return speed;
    }


    GraphicsContext gc = World.gc;
    Canvas canvas = gc.getCanvas();

    /**
     * This method takes in the foodList object and creates an iterator for it. The method then iterates through all of the food items for
     * the given animal and checks to see if any of the are close to the animal location (within 20), if the animal is close to some food,
     * then it eats the food by gaining 1000 energy food, it calls the reproduce method with its current location, then removes the food item
     * from the iterator. This works to reproduce an animal everytime an animal eats
     */
    public void eat() {

        List<Food> foodList = WorldModel.getFoodList();
        Iterator<Food> iterator = foodList.iterator();

        while (iterator.hasNext()) {
            Food food = iterator.next();
            if (Math.abs(this.getAnimalLocY() - food.getFoodLocY()) < 20 && Math.abs(this.getAnimalLocX() - food.getFoodLocX()) < 20) {
                this.energy += 1000;
                World.reproduce(canvas, this.getAnimalLocX(), this.getAnimalLocY());
                iterator.remove();
            }
        }
        /*for (Food food : foodList) {
            if (Math.abs(this.getAnimalLocY() - food.getFoodLocY()) < 20 && Math.abs(this.getAnimalLocX() - food.getFoodLocX()) < 20) {
                this.energy += 1000;
                foodList.remove(food);
            }
        }*/
    }

    /**
     * Run method for an animal thread. It checks that thread continues to run so long as it has a positive energy value. Moving each and checking
     * whether it is able to eat after each movement. When it runs out of energy the given thread object is removed
     */
    @Override
    public synchronized void run() {
        //gc.fillOval(this.getAnimalLocX(), this.getAnimalLocY(), 30, 30);
        while (this.getEnergy() > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.Move((int) canvas.getWidth(), (int) canvas.getHeight());
            //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight() );
            //gc.fillOval(this.getAnimalLocX(), this.getAnimalLocY(), 30, 30);
            this.eat();


        }
        World.animals.remove(this);
    }
}