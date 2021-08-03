package main.elements.properties;

import main.elements.visual.elements.Ball;
import main.elements.visual.elements.Block;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * a listener that identify a hit in the game.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit block
     * @param hitter ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
