/*
name: Gilad Aharoni
Id: 318781127
 */
package main.elements.visual.elements;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import main.elements.Collidable;
import main.elements.GameLevel;
import main.elements.Sprite;
import main.elements.geometric.shapes.Point;
import main.elements.geometric.shapes.Rectangle;
import main.elements.properties.Velocity;

import java.awt.Color;

/**
 * this class defines the Paddle object.
 */
public class Paddle implements Collidable, Sprite {
    private Rectangle shape;
    private biuoop.KeyboardSensor keyboard;
    private int velocity;
    private int xWidth;

    /**
     * move the paddle to the left.
     */
    public void moveLeft() {
        moveSides(velocity);
    }

    /**
     * move the paddle to the right.
     */
    public void moveRight() {
        moveSides(-velocity);
    }

    /**
     * Constructor for the paddle.
     * @param xWidth size of x screen.
     * @param yHeight size of y screen.
     * @param keyboard sensor.
     * @param velocity speed.
     * @param width of the paddle.
     */
    public Paddle(int xWidth, int yHeight, KeyboardSensor keyboard,
                  int velocity, int width) {
        this.velocity = velocity;
        shape = new Rectangle((double) xWidth / 2 - width / 2, yHeight - 40,
                (double) xWidth / 2 + width / 2, yHeight - 10);
        this.keyboard = keyboard;
        this.xWidth = xWidth;
    }

    /**
     * Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return shape;
    }

    /**
     *
     * @return keyboard.
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a
     * given velocity.
     *
     * @param hitter the ball that hit.
     * @param collisionPoint  where the collision ocours.
     * @param currentVelocity the current velocity of the collided object.
     * @return the new velocity expected after the hit (based on the force
     * the object inflicted on us).
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double speed = currentVelocity.getSpeedFromAngle();
        if (collisionPoint.getY() == shape.getUpperLeft().getY()) {
            double xLeft = shape.getUpperLeft().getX();
            if (collisionPoint.getX() > xLeft + 0.8 * shape.getWidth()) {
                return Velocity.fromAngleAndSpeed(330, -speed);
            }
            if (collisionPoint.getX() < xLeft + 0.2 * shape.getWidth()) {
                return Velocity.fromAngleAndSpeed(30, -speed);
            }
            if (collisionPoint.getX() > xLeft + 0.6 * shape.getWidth()) {
                return Velocity.fromAngleAndSpeed(300, -speed);
            }
            if (collisionPoint.getX() < xLeft + 0.4 * shape.getWidth()) {
                return Velocity.fromAngleAndSpeed(60, -speed);
            }
            return Velocity.fromAngleAndSpeed(0, -speed);

        }
        return new Velocity(-currentVelocity.getXDirection(),
                currentVelocity.getYDirection());
    }

    /**
     * return the type of the object.
     *
     * @return string with type of the object.
     */
    @Override
    public String type() {
        return "Paddle";
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d selected screen object.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x1 = (int) shape.getUpperLeft().getX();
        int y1 = (int) shape.getUpperLeft().getY();
        int width = (int) shape.getWidth();
        int height = (int) shape.getHeight();
        d.setColor(Color.CYAN);
        d.fillRectangle(x1, y1, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x1, y1, width, height);
    }

    /**
     * the basic method for vertical move of the point.
     * @param unit kind of velocity in x axis.
     */
    private void moveSides(int unit) {
        double x1 = shape.getUpperLeft().getX();
        double y1 = shape.getUpperLeft().getY();
        double height = shape.getHeight();
        double width = shape.getWidth();
        if (xWidth < (x1 + width + unit) || (x1 + unit) < 0) {
            return;
        }
        shape = new Rectangle(new Point(x1 + unit, y1), width, height);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveSides(-velocity);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveSides(velocity);
        }
    }

    /**
     * add the paddle to the game.
     * @param gameLevel the game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }
}
