/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Liam Stott
 * Section: 10am
 * Date: 4/13/2022
 * Time: 12:10 PM
 *
 * Project: csci205_final_project
 * Package: main.model
 * Class: Emitter
 *
 * Description:
 *
 *
 *****************************************/
package main.model;

import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Emitter {

    /**
     * Maximum velocity allowed in pixels per second
     */
    private static final double MAX_VELOCITY_PER_SEC = 100.0;

    /**
     * Maximum duration in seconds
     */
    private static final double MAX_DURATION = 5.00;

    //private static final double MAX_DURATION = Duration.INDEFINITE.toMillis();

    /**
     * Particles will be emitted in a random direction from the source
     */
    private static Random rng = new Random();

    /**
     * Create a list of particles that will be created
     */
    private List<Particle> listOfParticles;

    /**
     * Create the number of particles that will be emitted
     */
    private int numParticles;

    /**
     * Position of the source of the emitter
     */
    private double x, y;

    /**
     * Create the emitter which will hold a list of a number of particles and a starting position
     * for where those particles will move from
     * @param numParticles the number of particles that are emitted
     * @param x the starting x position of the particles
     * @param y the starting y position of the particles
     */
    public Emitter(int numParticles, double x, double y) {
        this.numParticles = numParticles;
        this.x = x;
        this.y = y;
        this.listOfParticles = new ArrayList<>();
        this.initParticles();
    }

    /**
     * Initialize the set of particles that will be emitted from this emitter
     */
    private void initParticles() {
        while (listOfParticles.size() < this.numParticles){ //while the list of particles < current particles emitted
            double durationInSec = rng.nextDouble() * MAX_DURATION; // duration is 5 * random number
            //double durationInSec = Duration.INDEFINITE.toMillis();
            double xDeltaPerSec = rng.nextDouble() * MAX_VELOCITY_PER_SEC + -(MAX_VELOCITY_PER_SEC / 2); // direction in x direction rng * 100 - 50
            double yDeltaPerSec = rng.nextDouble() * MAX_VELOCITY_PER_SEC + -(MAX_VELOCITY_PER_SEC / 2); // direction in y direction rng * 100 - 50
            Particle p = new Particle(this.x, this.y, durationInSec, xDeltaPerSec, yDeltaPerSec, Color.AQUA); //construct particle
            listOfParticles.add(p); //add particle to list
        }
    }

    /**
     * Return a stream object representing a stream of particles
     * @return stream of particles
     */
    public Stream<Particle> particleStream() {return this.listOfParticles.stream();}

    /**
     * Go through each particle and start the timeline
     */
    public void play() {
        particleStream().forEach(Particle::play );
    }

    /**
     * Go through each particle to stop and reset the timeline
     */
    public void stopAndReset() {
        particleStream().forEach(Particle::stopAndReset);
    }



}