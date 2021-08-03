package main.elements.levels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import main.elements.GameLevel;
import main.elements.properties.Counter;
import main.elements.visual.elements.animation.AnimationRunner;
import main.elements.visual.elements.animation.EndScreen;
import main.elements.visual.elements.animation.KeyPressStoppableAnimation;

import java.util.List;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * object to control the game flow between levels and scenes.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private DrawSurface drawSurface;
    private Counter score;
    private KeyPressStoppableAnimation endGame;

    /**
     * Constructor.
     * @param ar animation runner.
     * @param ks sensor.
     * @param drawSurface drawSurface.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, DrawSurface drawSurface) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.drawSurface = drawSurface;
        this.score = new Counter();
    }

    /**
     * run the given levels.
     * @param levels list.
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter remainingBalls = new Counter();
        Counter remainingBlocks = new Counter();
        endGame = new  KeyPressStoppableAnimation(keyboardSensor, "m",
                new EndScreen(remainingBalls, remainingBlocks, score),
                animationRunner);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, animationRunner, score,
                    keyboardSensor);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            if (level.shouldStop()) {
                remainingBalls = level.getRemainingBalls();
                remainingBlocks = level.getRemainingBlocks();
                if (remainingBalls.getValue() == 0) {
                    break;
                }
            }
        }
        endGame.run();
    }
}
