//Gilad Aharoni
//318781127
package main.elements;
import biuoop.DrawSurface;

/**
 * this interface describe a visible object.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d selected screen object.
     */
    void drawOn(DrawSurface d);


    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
