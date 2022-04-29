/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Brooks Burt
 * Section: 10am
 * Date: 4/22/22
 * Time: 10:41 AM
 *
 * Project: csci205_final_project
 * Package: main
 * Class: Food
 *
 * Description:
 *
 * ****************************************
 */

package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple class that encapsulates a food object
 */

public class Food {

    /**
     * Double value that holds the X location of the food within space
     */
    private double foodLocX;

    /**
     * Double value that holds the Y locations of the food within space
     */
    private double foodLocY;


    /**
     * Constructor method for the food object that records its XY location on the canvas
     * @param foodLocX - the double representation of the X location
     * @param foodLocY - the double representation of the Y location
     */
    public Food(double foodLocX, double foodLocY) {
        this.foodLocX = foodLocX;
        this.foodLocY = foodLocY;
    }

    /**
     * Getter method for the food object X lcoation
     * @return double representation of the X location
     */
    public double getFoodLocX() {
        return foodLocX;
    }

    /**
     * Getter method for the food object Y location
     * @return double representation of the Y location
     */
    public double getFoodLocY() {
        return foodLocY;
    }

}