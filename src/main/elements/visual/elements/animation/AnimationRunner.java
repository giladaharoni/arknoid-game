package main.elements.visual.elements.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * an animation machine to run the given animation.
 */
public class AnimationRunner {
    private final GUI gui;


    /**
     * Constructor.
     * @param gui1 gui object.
     */
    public AnimationRunner(GUI gui1) {
        gui = gui1;
    }

    /**
     * run the animation ny the given animation object.
     * @param animation animation object.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }
}
