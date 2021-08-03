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
 * a level information for level 1.
 */
public class Level1 implements LevelInformation {
    private final double xSizeFrame = 800;
    private final double ySizeFrame = 600;
    private final int ballsNum;
    private final String nameLevel;
    private final Backgrounds background;

    /**
     * Constructor.
     */
    public Level1() {
        ballsNum = 1;
        nameLevel = "Direct Hit";
        background = new Backgrounds(1);
    }


    @Override
    public int numberOfBalls() {
        return ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity velocity = new Velocity(0, -1);
        List<Velocity> list = new LinkedList<>();
        list.add(velocity);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return (int) xSizeFrame / 16;
    }

    @Override
    public String levelName() {
        return nameLevel;
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        double yCenter = ySizeFrame / 4;
        double xCenter = xSizeFrame / 2;
        Block oneBlock = new Block(new Rectangle(xCenter - 12, yCenter - 12,
                xCenter + 12,
                yCenter + 12));
        oneBlock.setColor(Color.RED);
        List<Block> list = new LinkedList<>();
        list.add(oneBlock);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

}
