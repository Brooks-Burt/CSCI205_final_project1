/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Liam Stott
 * Section: 10am
 * Date: 4/19/2022
 * Time: 6:46 PM
 *
 * Project: csci205_final_project
 * Package: main.ball
 * Class: Ball
 *
 * Description:
 *
 *
 *****************************************/
package main.ball;

public class myBall {
    int x;
    int y;
    int velX;
    int velY;


    public myBall(int x, int y, int velX, int velY) {
        this.x = (int )(Math.random() * 560);
        this.y = (int )(Math.random() * 360);
        this.velX = velX;
        this.velY = velY;
    }

    public void move() {
        x += velX;
        y += velY;
    }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void reverseX() {
            velX = -velX;
        }

        public void reverseY() {
            velY = -velY;
        }

    }