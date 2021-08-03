package main.elements.visual.elements.animation;

import biuoop.DrawSurface;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * a class for pause screen.
 */
public class PauseScreen implements Animation {

    /**
     * Constructor.
     */
    public PauseScreen() {
    }

    /**
     * draw the pause screen.
     * @param d draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue"
                , 32);
    }

    /**
     * get the stoppinf condition.
     * @return stop condition.
     */
    public boolean shouldStop() {
        return true;
    }
}
