/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Liam Stott
 * Section: 10am
 * Date: 4/13/2022
 * Time: 10:30 AM
 *
 * Project: csci205_final_project
 * Package: main.model
 * Class: Particle
 *
 * Description:
 *
 *
 *****************************************/
package main.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Particle {

    //Current x coordinate of the particle
    private final DoubleProperty x;

    //Current y coordinate of the particle
    private final DoubleProperty y;

    //The color of the particle
    private final Color color;

    //The timeline object that Javafx provides to help auto update the particle
    private Timeline timeline;


    /**
     * Initialize a new particle
     *
     * @param startX starting x coordinate of the particle
     * @param startY starting y coordinate of the particle
     * @param duration the number of seconds the particle should exist for
     * @param deltaXPerSec the change in x coordinate per second
     * @param deltaYPerSec the change in y coordinate per second
     * @param color the color of the particle
     */
    public Particle(double startX, double startY, double duration, double deltaXPerSec, double deltaYPerSec, Color color) {
        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();
        this.color = color;
        this.initTimeline(startX, startY, duration, deltaXPerSec, deltaYPerSec);

    }

    /**
     * This initializes a new timeline to allow particles to move over time
     *
     * @param startX the starting x coordinate of the particle
     * @param startY the starting y coordinate of the particle
     * @param duration the number of seconds that the particle should stay alive
     * @param xDeltaPerSec the change in x coordinate per second
     * @param yDeltaPerSec the change in y coordinate per second
     */
    private void initTimeline(double startX, double startY, double duration,
                              double xDeltaPerSec, double yDeltaPerSec){
        this.timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(x, startX),
                        new KeyValue(y, startY)),
                new KeyFrame(Duration.seconds(duration),
                        new KeyValue(x, startX + (xDeltaPerSec * duration)),
                        new KeyValue(y, startY + (yDeltaPerSec * duration)))

        );
    }

    /**
     * Start the timeline animation
     */
    public void play() { this.timeline.play();}

    /**
     * Pause the timeline animation
     */
    public void pause() {this.timeline.pause();}

    /**
     * Stop and reset the timeline back to the beginning
     */
    public void stopAndReset() { this.timeline.stop();}

    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public Timeline getTimeline() {
        return timeline;
    }
}