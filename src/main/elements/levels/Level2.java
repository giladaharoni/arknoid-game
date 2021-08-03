package main.elements.levels;

import main.elements.Sprite;
import main.elements.geometric.shapes.Rectangle;
import main.elements.properties.Velocity;
import main.elements.visual.elements.Backgrounds;
import main.elements.visual.elements.Block;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 *level 2 info provider.
 */
public class Level2 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            velocities.add(Velocity.fromAngleAndSpeed((90 + i * 18), 2.5));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 1;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Backgrounds(2);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<>();
        Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE,
                Color.YELLOW, Color.YELLOW, Color.GREEN, Color.GREEN,
                Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK,
                Color.CYAN, Color.CYAN};
        double ySmallCorner = 600 / 3 - 10;
        double xSmallCorner = 10;
        double width = 780 / 15;
        for (int i = 0; i < 15; i++) {
            Block block =
                    new Block(new Rectangle((xSmallCorner + i * width),
                    ySmallCorner, (xSmallCorner + (i + 1) * width),
                            ySmallCorner + 20));
            block.setColor(colors[i]);
            blocks.add(block);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
