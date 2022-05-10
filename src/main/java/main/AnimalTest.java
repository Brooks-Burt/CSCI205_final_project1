package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A simple class to test the functionality of the Animal Class methods
 */
class AnimalTest {

    /**
     * The World instance to get the canvas to access the WorldModel methods
     */
    public World theWorld = new World();

    /**
     * WorldModel object to get all of the arrays storing predators
     */
    public WorldModel theModel = new WorldModel();

    /**
     * A simple test for the random move method
     */
    @Test
    void randMove() {
        Animal animal = new Animal(5, 0.5, 50.0, 50.0);
        animal.randMove(animal);
        if (animal.getAnimalLocX() == 50.0 && animal.getAnimalLocY() == 50.0) {
            assertEquals(animal.getEnergy(), 3000);
            animal.randMove(animal);
            assertEquals(animal.getEnergy(), 3000 -  (Math.abs(50.0 - animal.getAnimalLocX())) + (Math.abs(50.0 - animal.getAnimalLocY())));
        } else {
            assertEquals(animal.getEnergy(), 3000 -  (Math.abs(50.0 - animal.getAnimalLocX())) + (Math.abs(50.0 - animal.getAnimalLocY())));
        }
    }

    /**
     * A simple method to test the Move method for the Animal Class
     */
    @Test
    void move() {
        Animal animal = new Animal(5, 0.5, 50.0, 50.0);
        animal.Move(200, 300);
        if (animal.getAnimalLocX() == 50.0 && animal.getAnimalLocY() == 50.0) {
            assertEquals(animal.getEnergy(), 3000);
            animal.Move(200, 300);
            assertEquals(animal.getEnergy(), 3000 -  (Math.abs(50.0 - animal.getAnimalLocX())) + (Math.abs(50.0 - animal.getAnimalLocY())));
        } else {
            assertEquals(animal.getEnergy(), 3000 -  (Math.abs(50.0 - animal.getAnimalLocX())) + (Math.abs(50.0 - animal.getAnimalLocY())));
        }
    }

    /**
     * A simple method to test the animalDies method
     */
    @Test
    void animalDies() {
        Animal animal = new Animal(5, 0.5, 50.0, 50.0);
        Animal animal2 = new Animal(5, 0.5, 50.0, 50.0);
        assertEquals(animal, animal2);
        animal = Animal.AnimalDies(animal);
        assertEquals(animal, null);
    }

    /**
     * A simple method to test the setter method for the animal Y location
     */
    @Test
    void setAnimalLocX() {
        Animal animal = new Animal(5, 0.5, 40.0, 50.0);
        assertEquals(animal.getAnimalLocX(), 40.0);
        animal.setAnimalLocX(100.0);
        assertEquals(animal.getAnimalLocX(), 100.0);
    }

    /**
     * A simple method to test the setter method for the animal Y location
     */
    @Test
    void setAnimalLocY() {
        Animal animal = new Animal(5, 0.5, 40.0, 50.0);
        assertEquals(animal.getAnimalLocY(), 50.0);
        animal.setAnimalLocY(100.0);
        assertEquals(animal.getAnimalLocY(), 100.0);
    }

    /**
     * A simple method to test the functionality of the getReproductionRate method
     */
    @Test
    void getReproductionRate() {
        Animal animal = new Animal(5, 0.5, 40.0, 50.0);
        assertEquals(animal.getReproductionRate(), 0.5);
    }

    /**
     * A simple method to test the functionality of the getSpeed method
     */
    @Test
    void getSpeed() {
        Animal animal = new Animal(5, 0.5, 40.0, 50.0);
        assertEquals(animal.getSpeed(), 5);
    }

    /**
     * A simple method to test the functionality of the eat method for the animal class
     */
    @Test
    void eat() {
        WorldModel.reproduce(theWorld.getCanvas(), 20.0, 20.0);
        Food food = new Food(21.0, 15.0);
        WorldModel.getFoodList().add(food);
        assertEquals(WorldModel.getFoodList().size(), 1);
        WorldModel.animals.get(0).eat();
        assertEquals(WorldModel.animals, 1);
        assertEquals(WorldModel.animals.get(0).energy, 4000);
    }
}