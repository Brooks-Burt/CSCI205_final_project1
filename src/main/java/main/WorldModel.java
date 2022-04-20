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

import java.util.Random;

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
     * Generate an animal at a random location
     * @param maxWidth - maximum x-coordinate to allow
     * @param maxHeight - maximum y-coordinate to allow
     */
    public static Animal generateAnimal(int maxWidth, int maxHeight) {
        System.out.println("Generate Animal");
        double x = (double) rng.nextInt(maxWidth);
        double y = (double) rng.nextInt(maxHeight);
        Animal animal = new Animal(20, .5, x,y);
        return animal;
    }

    public void addNewAnimal( double x, double y) {
        Animal animal = new Animal(1, .5, x,y);

    }


    /**
    public void run(Animal animal) throws InterruptedException {
            animal.Move();
            System.out.println(animal.getAnimalLocX() + ", " + animal.getAnimalLocY());
            Thread.sleep(50);
    }
    */

    //Consider moving this method straight intothe World class so that it can be mapped inside of the run method
    //erasing the prior issues scene in the code


}