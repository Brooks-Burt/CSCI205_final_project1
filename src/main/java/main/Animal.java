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
    double energy;



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
     * @param animal - the animal object onto which the method acts upon
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
     * The movement method that takes in the height and width of the canvas and then moves the given animal in a random
     * direction scaled by the speed of the animal. If the movement would bring the animal outside of bounds then it
     * simply bounces off the wall back onto the canvas. The energy is then adjusted by the absolute value of the movement
     * @param width - the width of the canvas
     * @param height - the height of the canvas
     */
    public void Move(int width, int height) {
        Random rand = new Random();
        //System.out.println(this.getAnimalLocX() + ", " + this.getAnimalLocY());
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
     * A future method to use machine learning techniques to develop a smarter way for animals to move to survive longer
     */
    public void SmartMove() {
        //TODO: Define Smarter movement method based off of machine learning techniques
    }

    /**
     * Method to nullify an animal when it dies
     * @param animal the dead animal
     * @return the animal that died
     */
    public static Animal AnimalDies(Animal animal) {
        animal = null;
        return animal;
    }

    public double getAnimalLocX() {
        return animalLocX;
    }

    public double getAnimalLocY() {
        return animalLocY;
    }

    public void setAnimalLocX(double animalLocX) {
        this.animalLocX = animalLocX;
    }

    public void setAnimalLocY(double animalLocY) {
        this.animalLocY = animalLocY;
    }

    public Double getReproductionRate() {
        return reproductionRate;
    }

    public double getEnergy() {
        return energy;
    }

    public Integer getSpeed() {
        return speed;
    }


    GraphicsContext gc = World.gc;
    Canvas canvas = gc.getCanvas();

    /**
     * The eat method creates an iterator for the food list for the given animal and then runs through the food checking
     * if qny food item is within 20 of the animal's location. If so, then the animal gains the energy, the food is remvoed,
     * and the animal reproduces
     */
    public void eat() {

        List<Food> foodList = WorldModel.getFoodList();
        Iterator<Food> iterator = foodList.iterator();

        while (iterator.hasNext()) {
            Food food = iterator.next();
            if (Math.abs(this.getAnimalLocY() - food.getFoodLocY()) < 20 && Math.abs(this.getAnimalLocX() - food.getFoodLocX()) < 20) {
                this.energy += 1000;
                WorldModel.reproduce(canvas, this.getAnimalLocX(), this.getAnimalLocY());
                iterator.remove();
            }
        }
    }

    /**
     * Runnable method for every animal thread object. The method checks that the animal's energy is greater than 0, then it
     * continually moves the animal, calling the eat method, until the animal runs out of energy at which point it is
     * removed from the animal collection
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
            this.eat();


        }
        WorldModel.animals.remove(this);
    }
}