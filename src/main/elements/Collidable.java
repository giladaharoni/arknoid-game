//Name: Gilad Aharoni.
//318781127
package main.elements;
import main.elements.geometric.shapes.Point;
import main.elements.geometric.shapes.Rectangle;
import main.elements.properties.Velocity;
import main.elements.visual.elements.Ball;

/**
 * this interface describe an object that can collides.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a
     * given velocity.
     * @param hitter the ball.
     * @param collisionPoint where the collision ocours.
     * @param currentVelocity the current velocity of the collided object.
     * @return the new velocity expected after the hit (based on the force
     * the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * return the type of the object.
     * @return string with type of the object.
     */
    String type();
}
