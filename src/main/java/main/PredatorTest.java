package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A simple class to test the methods in Predator (all other methods tested in @AnimalTest
 */
class PredatorTest {

    /**
     * The World instance to get the canvas to access the WorldModel methods
     */
    public World theWorld = new World();

    /**
     * WorldModel object to get all of the arrays storing predators
     */
    public WorldModel theModel = new WorldModel();

    /**
     * A simple method to test the predator eat method
     */
    @Test
    void eat() {
        WorldModel.reproducePredator(theWorld.getCanvas(), 20.0, 20.0);
        Animal animal = new Animal(5, 0.5, 21.0, 15.0);
        WorldModel.animals.add(animal);
        assertEquals(WorldModel.animals.size(), 2);
        WorldModel.animals.get(0).eat();
        assertEquals(WorldModel.animals, 1);
        assertEquals(WorldModel.animals.get(0).energy, 4000);

    }
}