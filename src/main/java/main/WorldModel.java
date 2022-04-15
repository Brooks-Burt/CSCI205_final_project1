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

import main.model.Emitter;
import main.Animal;
import java.util.List;
import java.util.Random;

public class WorldModel {

    /**
     * Default number of animals
     */
    public static final int DEF_NUM_ANIMALS = 1;

    /**
     * A shared random number generator
     */
    private final Random rng = new Random();



    /**
     * Generate an animal at a random location
     * @param maxWidth - maximum x-coordinate to allow
     * @param maxHeight - maximum y-coordinate to allow
     */

    public void generateAnimal(int maxWidth, int maxHeight) {
        int x = rng.nextInt(maxWidth);
        int y = rng.nextInt(maxHeight);

    }

    public void addNewAnimal() {
        Animal animal = new Animal(4, .5, 0.0,0.0);

        while (animal.getEnergy() != 0) {
            animal.randMove(animal);

        }
    }


}