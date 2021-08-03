package main.elements.levels;

import main.elements.Sprite;
import main.elements.geometric.shapes.Point;
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
 * info for level 4.
 */
public class Level4 implements LevelInformation {
    private final double ySize = 600;
    private final double xSize = 800;

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            velocities.add(Velocity.fromAngleAndSpeed((-45 + 30 * i) + 180, 3));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 50;
    }

    @Override
    public String levelName() {
        return "Final four";
    }


    @Override
    public Sprite getBackground() {
        return new Backgrounds(4);
    }


    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<>();
        //color collection for every row.
        Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN,
                Color.ORANGE,
                Color.RED, Color.MAGENTA};
        //this nested loop creates the terraced tiles.
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 14; j++) {
                Block block =
                        new Block(new main.elements.geometric.shapes.Rectangle(new Point((double) (xSize - 10 - j * 60),
                                (double) (ySize / 2 - i * 20)), 60, 20));

                block.setColor(colors[i - 1]);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
