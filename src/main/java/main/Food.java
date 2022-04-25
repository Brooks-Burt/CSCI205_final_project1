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


public class Food {

    /**
     * Double value that holds the X location of the food within space
     */
    private double foodLocX;

    /**
     * Double value that holds the Y locations of the food within space
     */
    private double foodLocY;


    public Food(double foodLocX, double foodLocY) {
        this.foodLocX = foodLocX;
        this.foodLocY = foodLocY;
    }

    public double getFoodLocX() {
        return foodLocX;
    }

    public double getFoodLocY() {
        return foodLocY;
    }

}