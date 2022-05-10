package main;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A simple class to test the functionality of the WorldModel methods
 */
class WorldModelTest {

    /**
     * The World instance to get the canvas to access the WorldModel methods
     */
    public World theWorld = new World();

    /**
     * WorldModel object to get all of the arrays storing predators
     */
    public WorldModel theModel = new WorldModel();

    /**
     * A simple method to test the functionality of the reproduce method
     */
    @Test
    void reproduce() {
        WorldModel.reproduce(theWorld.getCanvas(), 20.0, 20.0);
        Food food = new Food(21.0, 15.0);
        WorldModel.getFoodList().add(food);
        assertEquals(WorldModel.getFoodList().size(), 1);
        WorldModel.animals.get(0).eat();
        assertEquals(WorldModel.animals, 1);
        assertEquals(WorldModel.animals.get(0).energy, 4000);
    }

    /**
     * A simple method to test the functionality of the reproducePredator method
     */
    @Test
    void reproducePredator() {
        WorldModel.reproducePredator(theWorld.getCanvas(), 20.0, 20.0);
        Animal animal = new Animal(5, 0.5, 21.0, 15.0);
        WorldModel.animals.add(animal);
        assertEquals(WorldModel.animals.size(), 2);
        WorldModel.animals.get(0).eat();
        assertEquals(WorldModel.animals, 1);
        assertEquals(WorldModel.animals.get(0).energy, 4000);
    }

    /**
     * A simple method to test the functionality of the setFoodList method
     */
    @Test
    void setFoodList() {
        Food food = new Food( 15.0, 20.0);
        WorldModel.getFoodList().add(food);
        assertEquals(WorldModel.getFoodList().size(), 1);
        List<Food> foodList1 = new ArrayList<>();
        theModel.setFoodList(foodList1);
        assertEquals(WorldModel.getFoodList().size(), 0);
    }

    /**
     * A simple method to test the functionality of the removeFood method
     */
    @Test
    void removeFood() {
        Food food = new Food( 15.0, 20.0);
        WorldModel.getFoodList().add(food);
        assertEquals(WorldModel.getFoodList().size(), 1);
        WorldModel.removeFood(food);
        assertEquals(WorldModel.getFoodList().size(), 0);
    }

    /**
     * A simple method to test the functionality of the generateAnimal method
     */
    @Test
    void generateAnimal() {
        Animal animal = WorldModel.generateAnimal(200, 300);
        assertEquals(animal.getClass(), Animal.class);
    }

    /**
     * A simple class to test the functionality of the generateAnimal method
     */
    @Test
    void generateFood() {
        theModel.generateFood(0, 100, 200);
        assertEquals(WorldModel.getFoodList().size(), 0);
        theModel.generateFood(10, 100, 200);
        assertEquals(WorldModel.getFoodList().size(), 10);
    }

    /**
     * A simple class to test the functionality of the generatePredator method
     */
    @Test
    void generatePredator() {
        Predator predator = WorldModel.generatePredator(200, 300);
        assertEquals(predator.getClass(), Predator.class);
    }
}