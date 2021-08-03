package main.elements.properties.remover;

import main.elements.GameLevel;
import main.elements.properties.HitListener;
import main.elements.visual.elements.Block;
import main.elements.properties.Counter;
import main.elements.visual.elements.Ball;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * a listener that cause removing blocks.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * a constructor to the block remover.
     * @param gameLevel the game.
     * @param removedBlocks a counter.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        remainingBlocks = removedBlocks;
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
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);

    }

    @Override
    public String toString() {
        return "BlockRemover{" + "gameLevel=" + gameLevel + ", remainingBlocks="
                + remainingBlocks + '}';
    }
}
