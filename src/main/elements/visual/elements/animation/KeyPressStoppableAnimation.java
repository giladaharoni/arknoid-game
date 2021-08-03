package main.elements.visual.elements.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * a decorator class for key press animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed;
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private AnimationRunner runner;

    /**
     * Constructor.
     * @param sensor a keyboard sensor.
     * @param key the key that start the animation.
     * @param animation the decorated animation.
     * @param runner the animation runner to run on it, and stop another
     *               running animations.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation,
                                      AnimationRunner runner) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.runner = runner;
    }

    /**
     * this method run the key press Stop-able animation.
     */
    public void run() {
        if (key == "m") {
            runner.run(this);
        }
        if (sensor.isPressed(key)) {
            runner.run(this);
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        isAlreadyPressed = sensor.isPressed(KeyboardSensor.SPACE_KEY);
        return isAlreadyPressed;
    }

}
