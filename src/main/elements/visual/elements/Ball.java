//name: Gilad Aharoni.
//Id: 318781127.
package main.elements.visual.elements;
import biuoop.DrawSurface;
import main.elements.Collidable;
import main.elements.GameLevel;
import main.elements.Sprite;
import main.elements.geometric.shapes.Line;
import main.elements.geometric.shapes.Point;
import main.elements.properties.CollisionInfo;
import main.elements.properties.GameEnvironment;
import main.elements.properties.Velocity;
import java.awt.Color;

/**
 * this class defines the Ball object.
 */
public class Ball implements Sprite {
    //fields
    private Point point;
    private double radius = 0;
    private java.awt.Color color = Color.BLACK;
    private Velocity velocity;
    private GameEnvironment board;


    /**
     * constructor of a ball object.
     * @param center the centre of the ball by Point object.
     * @param r the radius
     * @param color color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        double x = center.getX();
        double y = center.getY();
        point = new Point(x, y);
        radius = r;
        this.color = color;
    }

    /**
     * constructor of a ball object.
     * @param x x value of the centre coordinate
     * @param y y value of the centre coordinate
     * @param r the radius
     * @param color color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        point = new Point(x, y);
        radius = r;
        this.color = color;
    }

    // accessors

    /**
     * get the center of the ball.
     * @return center.
     */
    public Point getPoint() {
        return point;
    }

    /**
     * get the x value of the centre.
     * @return x value.
     */
    public int getX() {
        return (int) point.getX();
    }

    /**
     * get the y value of the centre.
     * @return y value.
     */
    public int getY() {
        return (int) point.getY();
    }

    /**
     * get the size of the ball (radius).
     * @return radius value.
     */
    public int getSize() {
        return (int) radius * 2;
    }

    /**
     * get the color of the ball.
     * @return tone.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface the DrawSurface object.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) point.getX(), (int) point.getY(), (int) radius);
        surface.setColor(Color.BLACK);

        surface.drawCircle((int) point.getX(), (int) point.getY(), (int) radius);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * set the velocity of the ball from a ready one.
     * @param v velocity.
     */
    public void setVelocity(Velocity v) {
        velocity = v;
    }

    /**
     * set the velocity of the ball from vectors.
     * @param dx x vector
     * @param dy y vector
     */
    public void setVelocity(double dx, double dy) {
        velocity = new Velocity(dx, dy);
    }

    /**
     * setting the game environment for this ball.
     * @param environment GameEnvironment object
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.board = environment;
    }

    /**
     * get the ball velocity.
     * @return velocity.
     */
    public Velocity getVelocity() {
        return velocity;
    }


    /**
     * apply the velocity on the ball once.
     */
    public void moveOneStep() {
        double fixVY = 0;
        double fixVX = 0;
        if (velocity.getXDirection() < 0) {
            fixVX = point.getX() + velocity.getXDirection() - radius;
        }
        if (velocity.getXDirection() > 0) {
            fixVX = point.getX() + velocity.getXDirection() + radius;
        }
        if (velocity.getYDirection() < 0) {
            fixVY = point.getY() + velocity.getYDirection() - radius;
        }
        if (velocity.getYDirection() > 0) {
            fixVY = point.getY() + velocity.getYDirection() + radius;
        }
        Line theNextStep = new Line(point, new Point(fixVX, fixVY));
        if (board.getClosestCollision(theNextStep) != null) {
            CollisionInfo collisionInfo = board.getClosestCollision(theNextStep);
            Point collisionPoint = collisionInfo.collisionPoint();
            Collidable object = collisionInfo.collisionObject();
            //set the point
            Point imagePoint = velocity.applyToPoint(point);
            velocity = object.hit(this, collisionPoint, velocity);
        }
        point = velocity.applyToPoint(point);

    }

    /**
     * add the ball to the game.
     * @param gameLevel to add the ball.
     */
    public void addToGame(GameLevel gameLevel) {
        this.setGameEnvironment(gameLevel.getEnvironment());
        gameLevel.addSprite(this);
    }

    /**
     * remove this ball from the given game.
     * @param g given game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        board = null;
    }
}
