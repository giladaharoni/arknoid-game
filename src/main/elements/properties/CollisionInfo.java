/*
name: Gilad Aharoni
Id: 318781127
 */
package main.elements.properties;
import main.elements.Collidable;
import main.elements.geometric.shapes.Point;
/*
name: Gilad Aharoni
Id: 318781127
 */
/**
 * this class defines an information for collision event.
 */
public class CollisionInfo {
    private final Collidable object1;
    private final Point collisionPoint;

    /**
     * a Constructor.
     * @param object1 the collided object.
     * @param collisionPoint coordinate of the collision.
     */
    public CollisionInfo(Collidable object1, Point collisionPoint) {
        this.object1 = object1;
        this.collisionPoint = collisionPoint;
    }

    /**
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return object1;
    }

    @Override
    public String toString() {
        return "CollisionInfo{" + "object1=" + object1 + ", collisionPoint="
                + collisionPoint + '}';
    }
}
