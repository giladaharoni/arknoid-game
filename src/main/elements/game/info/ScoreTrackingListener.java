package main.elements.game.info;

import main.elements.properties.Counter;
import main.elements.properties.HitListener;
import main.elements.visual.elements.Ball;
import main.elements.visual.elements.Block;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * a listener that update counting the score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * a constructor.
     * @param scoreCounter counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit block
     * @param hitter   ball
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
