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
 * Class: Predator
 *
 * Description:
 *
 * ****************************************
 */
package main;

import java.util.Iterator;
import java.util.List;

/**
 * A simple class to encapsulate a predator object
 */
public class Predator extends Animal{
    /**
     * Constructor method for the preedator class. It takes in all of the attribtutes and calls a super method from the
     * animal constructor
     * @param speedVal integer representation of the predator's speed
     * @param reproductionRateVal - double representation of the animals chance of reproducing
     * @param animalLocXVal -double representation of the animals X location
     * @param animalLocYVal - double representation of the animals Y location
     */
    public Predator(Integer speedVal, Double reproductionRateVal, Double animalLocXVal, Double animalLocYVal) {
        super(speedVal, reproductionRateVal, animalLocXVal, animalLocYVal);
    }

    /**
     * Method takes in the given predator and iterates through all of the possible animals in the list and then checks to see if
     * they are close to the predator, if they are the predator gains energy, reproduces, and then removes the animal from the list
     */
    @Override
    public void eat(){
        List<Animal> animalList = World.getAnimals();
        Iterator<Animal> iterator = animalList.iterator();

        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            if (Math.abs(this.getAnimalLocY() - animal.getAnimalLocY()) < 20 && Math.abs(this.getAnimalLocX() - animal.getAnimalLocX()) < 20) {
                this.energy += 1000;
                WorldModel.reproducePredator(canvas, this.getAnimalLocX(), this.getAnimalLocY());
                iterator.remove();
            }
        }

    }

    /**
     * The run method runs as long as the energy is greater than 0, moving the animal and calling the eat method after every eat, then
     * when the predator runs out of energy is removed from the list
     */
    @Override
    public synchronized void run() {
        while (this.getEnergy() > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.Move((int) canvas.getWidth(), (int) canvas.getHeight());
            this.eat();


        }
        World.predators.remove(this);
    }
}