/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Liam Stott
 * Section: 10am
 * Date: 4/19/2022
 * Time: 6:43 PM
 *
 * Project: csci205_final_project
 * Package: main.ball
 * Class: Start
 *
 * Description:
 *
 *
 *****************************************/
package main.ball;

import javax.swing.*;

public class Start {
    public static void main(String args[]) {
        JFrame f = new JFrame();
        Gui gui = new Gui();
        gui.addBalls();
        f.add(gui);

        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Moving Ball");
        f.setVisible(true);
    }
}