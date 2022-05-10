package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A simple class to test the Food class methods
 */
class FoodTest {

    /**
     * A simple class to test the getFoodLocX method
     */
    @Test
    void getFoodLocX() {
        Food food = new Food(20.0, 15.0);
        assertEquals(20.0, food.getFoodLocX());
    }

    /**
     * A simple class to test the getFoodLocY method
     */
    @Test
    void getFoodLocY() {
        Food food = new Food(20.0, 15.0);
        assertEquals(15.0, food.getFoodLocY());
    }
}