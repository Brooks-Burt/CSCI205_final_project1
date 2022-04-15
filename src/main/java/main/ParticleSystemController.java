/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Brooks Burt
 * Section: 10am
 * Date: 4/13/22
 * Time: 11:33 PM
 *
 * Project: csci205_final_project
 * Package: main
 * Class: ParticleSystemController
 *
 * Description:
 *
 * ****************************************
 */
package main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import main.model.ParticleSystemModel;

public class ParticleSystemController {

    /**
     * A reference to the model this controller must work with
     */
    private ParticleSystemModel theModel;

    /**
     * The Graphics Context of the canvas
     */
    private GraphicsContext gc;

    /** Our AnimationTimer object taht will keep the canvas updating */
    private AnimationTimer animationTimer;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGenerate;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnStop;

    @FXML
    private Canvas canvas;

    @FXML
    private CheckBox checkBoxContinuous;

    @FXML
    void initialize() {
        assert btnGenerate != null : "fx:id=\"btnGenerate\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert btnStop != null : "fx:id=\"btnStop\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'particlesim.fxml'.";
        assert checkBoxContinuous != null : "fx:id=\"checkBoxContinuous\" was not injected: check your FXML file 'particlesim.fxml'.";

        // We're going to need to access the graphics context of canvas. a lot.
        this.gc = canvas.getGraphicsContext2D();
    }



    /**
     * Let's set our model instance that this conroller is going to be referring to,
     * and we'll set up our event handlers and bindings here too.
     *
     * @param theModel a reference to the {@link main.model.ParticleSystemModel} we're
     *                 working with
     */

    public void setModel(ParticleSystemModel theModel) {
        this.theModel = theModel;

        // Generate a new random emitter
        this.btnGenerate.setOnAction(event -> {
            this.theModel.generateRandomEmitter((int)this.canvas.getWidth(),(int)this.canvas.getHeight());
        });

        // Set the start button handler
        this.btnStart.setOnAction(event -> {

            //If we already have an animation timer, then just stop timeline and reset
            if (this.animationTimer != null) {
                this.theModel.stopAndReset();
            }
            else {
                // We don't have timer, create it
                this.animationTimer = createAnimationTimer();
            }

            // Update the timeline to start playing
            this.theModel.play();


            // Start animationTimer
            this.animationTimer.start();

            this.btnStop.setOnAction(a -> this.theModel.emitterStream().forEach(e -> e.particleStream()
                    .forEach(p -> {
                                p.pause();
                            }
                    )
            ));
        });
    }


    /**
     * Construct an instance of an {@link AnimationTimer} that will update every particle from
     * every emitter
     *
     * @return an AnimationTimer
     */

    private AnimationTimer createAnimationTimer() {

        return (new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = ParticleSystemController.this.gc;
                ParticleSystemModel theModel = ParticleSystemController.this.theModel;

                // Clear the background every update
                gc.setFill(Color.BLACK);
                gc.fillRect(0,0,
                        ParticleSystemController.this.canvas.getWidth(),
                        ParticleSystemController.this.canvas.getHeight());

                // Now, go through every particle of every emitter and draw an updated oval based
                // on its current position
                theModel.emitterStream()
                        .forEach(e -> e.particleStream()
                                .forEach(p -> {
                                            gc.setFill(p.getColor());
                                            gc.fillOval(p.getX(), p.getY(), 10, 10);
                                        }
                                )
                        );


            }
        });

    }


}
