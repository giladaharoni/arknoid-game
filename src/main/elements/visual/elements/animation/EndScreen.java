package main.elements.visual.elements.animation;

import biuoop.DrawSurface;
import main.elements.properties.Counter;

import java.awt.Color;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * End screen animation.
 */
public class EndScreen implements Animation {
    private Counter remainingBalls;
    private Counter remainingBlocks;
    private Counter score;

    /**
     * Constructor.
     * @param remainingBalls counter 1.
     * @param remainingBlocks counter 2.
     * @param score the score.
     *
     */
    public EndScreen(Counter remainingBalls, Counter remainingBlocks,
                     Counter score) {
        this.remainingBalls = remainingBalls;
        this.remainingBlocks = remainingBlocks;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        if (remainingBalls.getValue() == 0) {
            d.drawText(d.getHeight() / 2, d.getWidth() / 2,
                    "Game Over. Your score is " + score.getValue(),
                    20);
        } else {
            d.drawText(d.getHeight() / 2, d.getWidth() / 2, "You Win! Your "
                    + "score is " + score.getValue(), 20);
        }
    }

    @Override
    public boolean shouldStop() {
        return true;
    }

}
