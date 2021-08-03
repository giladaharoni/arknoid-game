/*
name: Gilad Aharoni
Id: 318781127
 */
package main.elements.properties;
import biuoop.DrawSurface;
import main.elements.Sprite;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * a class for sprite collection.
 */
public class SpriteCollection {
    private LinkedList<Sprite> spriteList;

    /**
     * Constructor for this class.
     */
    public SpriteCollection() {
        spriteList = new LinkedList<Sprite>();
    }

    /**
     * adding sprite to the collection.
     * @param s sprite object.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * remove a sprite from the collection.
     * @param s selected sprite.
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        Set<Sprite> temp = new HashSet<>(spriteList);
        for (Sprite element : temp) {
            element.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d drawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: spriteList) {
            sprite.drawOn(d);

        }

    }
}
