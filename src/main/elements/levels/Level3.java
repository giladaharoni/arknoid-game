package main.elements.levels;

import main.elements.Sprite;
import main.elements.geometric.shapes.Point;
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
 * info for level 3.
 */
public class Level3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            velocities.add(Velocity.fromAngleAndSpeed((-45 + 90 * i) + 180, 3));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Backgrounds(3);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<>();
        //color collection for every row.
        Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN,
                Color.ORANGE,
                Color.RED, Color.MAGENTA};
        //this nested loop creates the terraced tiles.
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < i + 5; j++) {
                Block block =
                        new Block(new Rectangle(new Point(740 - 50 * j,
                                300 - 20 * i), 50, 20));

                block.setColor(colors[i - 1]);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
