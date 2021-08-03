package main.elements.levels;

import main.elements.Sprite;
import main.elements.properties.Velocity;
import main.elements.visual.elements.Block;

import java.util.List;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * this interface include all the information to generate a level.
 */
public interface LevelInformation {
    /**
     * number of balls in the level.
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return  numberOfBalls()
     */
    List<Velocity> initialBallVelocities();

    /**
     * speed of the paddle.
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * the width of the paddle.
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return string.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return background object.
     */
    Sprite getBackground();


    /**
     * The Blocks that make up this level, each block contains its size,
     * color and location.
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return blocks.size().
     */
    int numberOfBlocksToRemove();
}
