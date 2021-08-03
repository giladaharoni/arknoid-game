package main.elements.properties.remover;

import main.elements.GameLevel;
import main.elements.properties.HitListener;
import main.elements.properties.Counter;
import main.elements.visual.elements.Ball;
import main.elements.visual.elements.Block;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * the ball remover class. removes a ball when it hits a defined block.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor for the ball remover.
     * @param gameLevel the game.
     * @param remainingBalls a counter.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
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
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}
