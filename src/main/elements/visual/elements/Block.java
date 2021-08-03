/*
name: Gilad Aharoni
Id: 318781127
 */
package main.elements.visual.elements;
import biuoop.DrawSurface;
import main.elements.Collidable;
import main.elements.GameLevel;
import main.elements.Sprite;
import main.elements.geometric.shapes.Point;
import main.elements.geometric.shapes.Rectangle;
import main.elements.properties.HitListener;
import main.elements.properties.HitNotifier;
import main.elements.properties.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class defining the block object.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private Color color = Color.BLUE;
    private List<HitListener> hitListeners;

    /**
     * the main Constructor of a Block.
     * @param shape the rectangle shape of the block.
     */
    public Block(Rectangle shape) {
        this.shape = shape;
        hitListeners = new ArrayList<>();
    }

    /**
     *
     * @return shape
     */
    public Rectangle getShape() {
        return shape;
    }

    /**
     * accessor to listeners list.
     * @return listeners list.
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * set the color of the block.
     * @param tone of the block.
     */
    public void setColor(Color tone) {
        this.color = tone;
    }

    /**
     * Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return getShape();
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a
     * given velocity.
     *
     * @param collisionPoint  where the collision occurs.
     * @param currentVelocity the current velocity of the collided object.
     * @return the new velocity expected after the hit (based on the force
     * the object inflicted on us).
     */
    public Velocity hitAssist(Point collisionPoint, Velocity currentVelocity) {
        //considering the case when the collision occur in the corner of the
        // rectangle.
        if (collisionPoint == shape.getUpperLeft()
                || collisionPoint == shape.getBottomRight()
                || collisionPoint.equals(new Point(shape.getUpperLeft().getX(), shape.getBottomRight().getY()))
                || collisionPoint.equals(new Point(shape.getBottomRight().getX(), shape.getUpperLeft().getY()))) {
            return new Velocity((-1) * currentVelocity.getXDirection(),
                    (-1) * currentVelocity.getYDirection());
        }
        if (collisionPoint.getX() == shape.getUpperLeft().getX()
                || collisionPoint.getX() == shape.getBottomRight().getX()) {
            return new Velocity((-1) * currentVelocity.getXDirection(),
                    (1) * currentVelocity.getYDirection());
        }
        if (collisionPoint.getY() == shape.getUpperLeft().getY()
                || collisionPoint.getY() == shape.getBottomRight().getY()) {
            return new Velocity((1) * currentVelocity.getXDirection(),
                    (-1) * currentVelocity.getYDirection());
        }
        return null;
    }

    /**
     * return the type of the object.
     *
     * @return string with type of the object.
     */
    @Override
    public String type() {
        return "Block";
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
        int x2 = (int) shape.getWidth();
        int y2 = (int) shape.getHeight();
        d.setColor(Color.BLACK);
        d.fillRectangle(x1, y1, x2, y2);
        d.setColor(color);
        d.fillRectangle(x1 + 1, y1 + 1, x2 - 2, y2 - 2);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        //empty line. the block is static object.
    }

    /**
     * remove the block from visual and physical environment.
     * @param gameLevel full environment.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);

    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl listener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);

    }

    /**
     * notify the hitting.
     * @param hitter ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            if (hl == null) {
                continue;
            }
            hl.hitEvent(this, hitter);
        }
    }


    /**
     *Notify the object that we collided with it at collisionPoint with a
     * given velocity.
     * @param hitter the ball.
     * @param collisionPoint where the collision ocours.
     * @param currentVelocity the current velocity of the collided object.
     * @return the velocity by the assist method.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        this.notifyHit(hitter);
        return hitAssist(collisionPoint, currentVelocity);
    }

    @Override
    public String toString() {
        return "Block{" + "shape=" + shape + ", color=" + color + ", hitListeners="
                + hitListeners + '}';
    }
}
