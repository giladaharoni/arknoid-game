package main.elements.visual.elements;

import biuoop.DrawSurface;
import main.elements.Sprite;
import main.elements.properties.Counter;

import java.awt.Color;

/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * the scoreIndicator shows the current score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    private final int xDimension;
    private final int yDimension;
    private final String name;

    /**
     * Constructor to to the scoreIndicator.
     * @param counter for presenting updated value.
     * @param xDim the x dimension of the screen.
     * @param yDim the y dimension of the screen.
     * @param name the name of the level.
     */
    public ScoreIndicator(Counter counter, int xDim, int yDim, String name) {
        currentScore = counter;
        xDimension = xDim;
        yDimension = yDim;
        this.name = name;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d selected screen object.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int score = currentScore.getValue();
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, xDimension, yDimension / 20);
        d.setColor(Color.BLACK);
        //d.drawRectangle(0, 0, xDimension, yDimension / 20);
        d.drawText(xDimension / 2 - 50, yDimension / 21, "Score:" + score,
                20);
        d.drawText(xDimension / 2 + 150, yDimension / 21, "Level Name:" + name,
                20);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
