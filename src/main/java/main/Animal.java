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

import java.awt.*;
import java.util.Random;

/**
 * A simple class to encapsulate an animal object within the simulation
 */
public class Animal {

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
    private Integer energy;

    /**
     * Double value that holds the X location of the animal within space
     */
    private Double animalLocX;

    /**
     * Double value that holds the Y locations of the animal within space
     */
    private Double animalLocY;

    /**
     * Constructor for the Animal class that assigns the attributes of the animal
     * @param speedVal - the integer representation of the animals speed
     * @param reproductionRateVal - the double value representation of the animal's reproduction rate
     * @param animalLocXVal - the Double representation of the animal's location along the X axis
     * @param animalLocYVal - the Double representation of the animal's location along the Y axis
     */
    public Animal(Integer speedVal, Double reproductionRateVal, Double animalLocXVal, Double animalLocYVal) {
        this.speed = speedVal;
        this.energy = 1000;
        this.reproductionRate = reproductionRateVal;
        this.animalLocX = animalLocXVal;
        this.animalLocY = animalLocYVal;
    }

    /**
     * This method acts on an animal object and randomly adjust its locations values based on some random
     * number scaled by its overall speed value
     */
    public void Randmove() {
        Random rand = new Random();
        System.out.println(getAnimalLocX() + ", " + getAnimalLocY());
        int randomIntX = rand.nextInt(3) - 1;
        int randomIntY = rand.nextInt(3) - 1;
        animalLocX = getAnimalLocX() + (getSpeed()*randomIntX);
        animalLocY = getAnimalLocY() + (getSpeed()*randomIntY);
        System.out.println(randomIntX + ", " + randomIntY);
        System.out.println(getAnimalLocX() + ", " + getAnimalLocY());
        energy = getEnergy() - (Integer) ((getSpeed()*randomIntX) + (getSpeed()*randomIntY));
        System.out.println(getEnergy());
    }

    public void SnartMove() {
        //TODO: Define Smarter movement method based off of machine learning techniques
    }

    public Double getAnimalLocX() {
        return animalLocX;
    }

    public Double getAnimalLocY() {
        return animalLocY;
    }

    public Double getReproductionRate() {
        return reproductionRate;
    }

    public Integer getEnergy() {
        return energy;
    }

    public Integer getSpeed() {
        return speed;
    }

    public static void main(String[] args) {
        Animal fish = new Animal(2, 0.5, 50.0, 50.0);
        fish.Randmove();
        fish.Randmove();
        fish.Randmove();
        fish.Randmove();
    }
}