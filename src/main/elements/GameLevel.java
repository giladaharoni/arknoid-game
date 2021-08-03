/*
name: Gilad Aharoni
Id: 318781127
 */
package main.elements;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import main.elements.game.info.ScoreTrackingListener;
import main.elements.geometric.shapes.Rectangle;
import main.elements.levels.LevelInformation;
import main.elements.properties.Counter;
import main.elements.properties.GameEnvironment;
import main.elements.properties.HitListener;
import main.elements.properties.SpriteCollection;
import main.elements.properties.remover.BallRemover;
import main.elements.properties.remover.BlockRemover;
import main.elements.visual.elements.Ball;
import main.elements.visual.elements.Block;
import main.elements.visual.elements.Paddle;
import main.elements.visual.elements.ScoreIndicator;
import main.elements.visual.elements.animation.Animation;
import main.elements.visual.elements.animation.AnimationRunner;
import main.elements.visual.elements.animation.KeyPressStoppableAnimation;
import main.elements.visual.elements.animation.PauseScreen;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;


/**
 * this class defines the initialization and the running of the game.
 */
public class GameLevel implements Animation {
    private final double xSizeFrame = 800;
    private final double ySizeFrame = 600;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Ball[] balls;
    private Paddle paddle;
    private List<HitListener> listeners;
    private ScoreIndicator scoreIndicator;
    private boolean running;
    private AnimationRunner runner;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter currentScore;
    private LevelInformation information;
    private KeyboardSensor sensor;
    private KeyPressStoppableAnimation stoppableAnimation;

    /**
     * Constructor.
     * @param information information level.
     * @param runner animation runner.
     * @param currentScore score.
     * @param sensor sensor.
     */
    public GameLevel(LevelInformation information, AnimationRunner runner,
                     Counter currentScore, KeyboardSensor sensor) {
        this.runner = runner;
        this.information = information;
        this.currentScore = currentScore;
        this.sensor = sensor;
        stoppableAnimation = new KeyPressStoppableAnimation(sensor, "p",
                new PauseScreen(), runner);

    }

    /**
     * adding collision object to the collidables collection.
     * @param c Collidable object.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * removing object from the collision environment.
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * adding sprite to the sprites collection.
     * @param s sprite object.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * removing object from the sprites collection.
     * @param s Sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * creating borders made of blocks.
     * @param xSize of the frame.
     * @param ySize of the frame.
     */
    private void createBorders(double xSize, double ySize) {
        Block up = new Block(new Rectangle(0, ySize / 20 - 1, xSize,
                ySize / 20));
        Block left = new Block(new Rectangle(0, 10, 10, ySize));
        Block right = new Block(new Rectangle(xSize - 10, 10, xSize, ySize));
        Block death = new Block(new Rectangle(0, ySize + 40, xSize,
                ySize + 50));
        up.setColor(Color.BLACK);
        left.setColor(Color.BLACK);
        right .setColor(Color.BLACK);
        death.setColor(Color.BLACK);
        addCollidable(up);
        addCollidable(left);
        addCollidable(death);
        addCollidable(right);
        addSprite((Sprite) left);
        addSprite((Sprite) right);
        addSprite((Sprite) death);
        addSprite((Sprite) up);
        death.addHitListener(listeners.get(1));

    }

    /**
     * creating the terraced tiles made of blocks.
     * @param xSize of the frame.
     * @param ySize of the frame.
     */
    private void createTiles(double xSize, double ySize) {
        for (Block block: information.blocks()) {
            addSprite(block);
            addCollidable(block);
            block.addHitListener(listeners.get(0));
            block.addHitListener(listeners.get(2));
        }
    }

    /**
     * create the counters for the game.
     */
    private void createCounters() {
        remainingBlocks = new Counter();
        remainingBlocks.increase(information.numberOfBlocksToRemove());
        remainingBalls = new Counter();
        remainingBalls.increase(information.numberOfBalls());
    }

    /**
     * create the listeners for the game.
     */
    private void createListeners() {
        listeners = new LinkedList<>();
        HitListener blockRemover = new BlockRemover(this, remainingBlocks);
        listeners.add(blockRemover);
        HitListener ballRemover = new BallRemover(this, remainingBalls);
        listeners.add(ballRemover);
        HitListener scoreTracking = new ScoreTrackingListener(currentScore);
        listeners.add(scoreTracking);
    }

    /**
     * creating three balls.
     * @param xSize the width of frame
     * @param ySize the height of the frame.
     */
    private void createBalls(double xSize, double ySize) {
        double yThird = ySize * 5 / 6;
        double xQuarter = xSize / 2;
        balls = new Ball[information.numberOfBalls()];
        for (int i = 0; i < information.numberOfBalls(); i++) {
            balls[i] = new Ball(xQuarter, yThird, 10, Color.white);
            balls[i].setVelocity(information.initialBallVelocities().get(i));
            balls[i].addToGame(this);
        }
    }

    /**
     * create the score visual element.
     */
    private void createScore() {
        scoreIndicator = new ScoreIndicator(currentScore, (int) xSizeFrame,
                (int) ySizeFrame, information.levelName());
        sprites.addSprite(scoreIndicator);
    }

    /**
     * create the paddle by level information.
     */
    private void createPaddle() {
        paddle = new Paddle((int) xSizeFrame, (int) ySizeFrame, sensor,
                information.paddleSpeed(), information.paddleWidth());
        paddle.addToGame(this);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        createCounters();
        createListeners();
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
        addSprite(information.getBackground());
        createBorders(xSizeFrame, ySizeFrame);
        createBalls(xSizeFrame, ySizeFrame);
        createPaddle();
        createScore();
        createTiles(xSizeFrame, ySizeFrame);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.running =
                (remainingBalls.getValue() != 0) && (remainingBlocks.getValue() != 0);
        this.runner.run(this);
    }

    /**
     * get the game environment.
     * @return environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * get the sprites collection.
     * @return sprites
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        stoppableAnimation.run();
        this.sprites.notifyAllTimePassed();

    }

    @Override
    public boolean shouldStop() {
        return ((remainingBalls.getValue() == 0) || (remainingBlocks.getValue() == 0));
    }

    /**
     * accessor to the remaining balls counter.
     * @return counter.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * accessor to the remaining blocks counter.
     * @return counter.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }
}
