package main.elements.visual.elements.animation;

import biuoop.DrawSurface;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * Interface for animation object. should include the sprites and the logic
 * of the screen.
 */
public interface Animation {
    /**
     * draw the sprites in the given frame.
     * @param d draw Surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Stopping condition for the animation.
     * @return boolean condition.
     */
    boolean shouldStop();
}
