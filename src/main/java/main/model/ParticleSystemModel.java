/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Liam Stott
 * Section: 10am
 * Date: 4/13/2022
 * Time: 5:43 PM
 *
 * Project: csci205_final_project
 * Package: main.model
 * Class: ParticleSystemModel
 *
 * Description:
 *
 *
 *****************************************/
package main.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class ParticleSystemModel {

    /**
     * Default number of particles for each emitter if not specified
     */
    public static final int DEF_NUM_PARTICLES = 1;

    /**
     * A shared random number generator
     */
    private final Random rng = new Random();

    /**
     * A list of all the emitters
     */
    private List<Emitter> listOfEmitters;

    /**
     * Create a new particle system model
     */
    public ParticleSystemModel() {
        this.listOfEmitters = new ArrayList<>();
    }

    public Stream<Emitter> emitterStream() {
        return this.listOfEmitters.stream();
    }

    /**
     * Add new emitter that will generate specified amount of particles
     * @param x x coordinate of where emitters will start from
     * @param y y coordinate of where emitters will start from
     * @param numParticles the number of particles the emitter will generate
     */
    public void addNewEmitter(int x, int y, int numParticles){
        Emitter emitter = new Emitter(numParticles, x, y);
        this.listOfEmitters.add(emitter);
    }

    /**
     * Generate an emitter at a random location
     * @param maxWidth - maximum x-coordinate to allow
     * @param maxHeight - maximum y-coordinate to allow
     * @param numParticles - the number of particles the emitter will generate
     */
    public void generateRandomEmitter(int maxWidth, int maxHeight, int numParticles) {
        int x = rng.nextInt(maxWidth);
        int y = rng.nextInt(maxHeight);
        this.addNewEmitter(x, y, numParticles);

    }

    /**
     * Generate random emitter with default number of particles
     * @param maxWidth - maximum x-coordinate of emitter
     * @param maxHeight - maximum y-corodinate of emitter
     */
    public void generateRandomEmitter(int maxWidth, int maxHeight){
        this.generateRandomEmitter(maxWidth, maxHeight, DEF_NUM_PARTICLES);
    }

    /**
     * Start the timeline system for each emitter in our system
     */
    public void play() {
        emitterStream().forEach(emitter -> emitter.play());

    }

    /**
     * Stop the animation for each emitter
     */
    public void stopAndReset() {
        emitterStream().forEach(emitter -> emitter.stopAndReset());
    }







}