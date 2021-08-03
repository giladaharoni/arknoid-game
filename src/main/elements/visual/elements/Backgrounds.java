package main.elements.visual.elements;

import biuoop.DrawSurface;
import main.elements.Sprite;
import main.elements.geometric.shapes.Point;

import java.awt.Color;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * this class create the background object, which draw on the surface.
 */
public class Backgrounds implements Sprite {
    private final int level;
    private final int xDimension;
    private final int yDimension;

    /**
     * Constructor.
     * @param level number of level.
     */
    public Backgrounds(int level) {
        xDimension = 800;
        yDimension = 600;
        this.level = level;
    }

    /**
     * level 4- in style of rain.
     * @param d drawSurface.
     */
    private void level4(DrawSurface d) {
        d.setColor(new Color(0,  166, 255));
        d.fillRectangle(0, 0, xDimension, yDimension);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                cloud(d, x * 200 + 50, y * 200);
            }
        }
    }

    /**
     * level 2- in style of strange sun.
     * @param d drawSurface.
     */
    private void level2(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, xDimension, yDimension);
        int yCenter = yDimension / 5;
        int xCenter = xDimension / 5;
        int radius = yCenter / 3;
        int lowBorder = yDimension / 3;
        d.setColor(Color.ORANGE);
        for (int i = 0; i < 100; i++) {
            d.drawLine(xCenter, yCenter, i * xDimension / 100, lowBorder);
        }
        Color[] colors = {new Color(255, 255, 0), new Color(241, 219, 117),
                new Color(150, 150, 0)};
        for (int i = 2; i > -1; i--) {
            d.setColor(colors[i]);
            d.fillCircle(xCenter, yCenter, radius + i * 10);
        }
    }

    /**
     * level 3- in style of city.
     * @param d drawSurface.
     */
    private void level3(DrawSurface d) {
        d.setColor(new Color(94,  144,  0));
        d.fillRectangle(0, 0, xDimension, yDimension);
        d.setColor(Color.BLACK);
        d.fillRectangle(50, 350, 200, 250);
        d.setColor(Color.GRAY);
        d.fillRectangle(120, 280, 60, 70);
        d.fillRectangle(140, 180, 20, 100);
        d.setColor(new Color(255, 253, 196));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(75 + i * 30, 365 + j * 50, 25, 40);
            }
        }
        d.setColor(Color.YELLOW);
        d.drawCircle(150, 180, 75);
        d.drawCircle(150, 180, 50);
        d.drawCircle(150, 180, 25);
    }

    /**
     * an fbi style background.
     * @param d draw Surface.
     */
    private void level1(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, xDimension, yDimension);
        int yCenter = yDimension / 4;
        int xCenter = xDimension / 2;
        int radius = yCenter / 5;
        d.setColor(Color.BLUE);
        for (int i = 1; i <= 3; i++) {
            d.drawCircle(xCenter, yCenter, radius * i);
        }
        d.drawLine(xCenter, yCenter - 4 * radius, xCenter,
                yCenter + 4 * radius);
        d.drawLine(xCenter - 4 * radius, yCenter, xCenter + 4 * radius,
                yCenter);
    }

    /**
     * drawing cloud unit for level 4.
     * @param d drawSurface.
     * @param x right corner x.
     * @param y right corner y.
     */
    private void cloud(DrawSurface d, int x, int y) {
        Point big = new Point(x + 25, y + 15);
        Point mid = new Point(x + 45, y + 25);
        Point small = new Point(x + 10, y + 35);
        for (int i = 0; i < 10; i++) {
            d.setColor(new Color(106, 152, 205));
            d.drawLine(x + 10 + i * 6, y + 25, x + 18 + i * 6, y + 100);
        }
        d.setColor(Color.WHITE);
        d.fillCircle((int) big.getX(), (int) big.getY(), 20);
        d.fillCircle((int) mid.getX(), (int) mid.getY(), 25);
        d.fillCircle((int) small.getX(), (int) small.getY(), 15);
    }


    /**
     * draw the sprite to the screen.
     *
     * @param d selected screen object.
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (level == 1) {
            level1(d);
            return;
        }
        if (level == 2) {
            level2(d);
            return;
        }
        if (level == 3) {
            level3(d);
            return;
        }
        if (level == 4) {
            level4(d);
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
