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

import java.util.List;
import java.util.Random;

/**
 * A simple class to encapsulate an animal object within the simulation
 */
public class Animal implements  Runnable {

    /**
     * A reference to the model this controller must work with
     */
    private WorldModel theModel;

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

    public void SmartMove() {
        //TODO: Define Smarter movement method based off of machine learning techniques
    }

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

    public Double getReproductionRate() {
        return reproductionRate;
    }

    public double getEnergy() {
        return energy;
    }

    public Integer getSpeed() {
        return speed;
    }

    public static void main(String[] args) {
        Animal fish = new Animal(2, 0.5, 50.0, 50.0);
//        randMove(fish);
//        randMove(fish);
//        randMove(fish);
//        randMove(fish);
        AnimalDies(fish);
    }
    GraphicsContext gc = World.gc;
    Canvas canvas = gc.getCanvas();

    public List<Food> eat(List<Food> foodList) {
        for (Food food : foodList) {
            if (((this.getAnimalLocX() + 10) > food.getFoodLocX()) &&  ((this.getAnimalLocX() - 10) < food.getFoodLocX()) && ((this.getAnimalLocX() + 10) > food.getFoodLocY()) &&  ((this.getAnimalLocX() - 10) < food.getFoodLocY())) {
                this.energy += 1000;
                foodList.remove(food);
            }
        }
        return foodList;
    }

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
            this.eat(this.theModel.getFoodList());
            //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight() );
            //gc.fillOval(this.getAnimalLocX(), this.getAnimalLocY(), 30, 30);


        }
    }
}