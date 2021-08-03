/*
 * Gilad Aharoni.
 * Id: 318781127
 */
package main.elements.properties;
import java.util.HashSet;
import java.util.Set;

import main.elements.geometric.shapes.Line;
import main.elements.Collidable;
import main.elements.geometric.shapes.Point;
import main.elements.visual.elements.Paddle;

/**
 * this class holds all the Collidable objects in the game.
 */
public class GameEnvironment {
    private Set<Collidable> collidableSet = new HashSet<>();

    /**
     * add the given collidable to the environment.
     * @param c given collidable
     */
    public void addCollidable(Collidable c) {
        collidableSet.add(c);
    }

    /**
     * remove a collidable object from the environment.
     * @param c collidable.
     */
    public void removeCollidable(Collidable c) {
        collidableSet.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory line.
     * @return collision information.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closePoint = null;
        Collidable closeObject = null;
        for (Collidable object:collidableSet) {
            //first: the object need to be short that the line.
            if (object.getCollisionRectangle().intersectionPoints(trajectory) != null) {
                Point suspected =
                        object.getCollisionRectangle().intersectionPoints(trajectory).get(0);
                if (closeObject == null && closePoint == null) {
                    closeObject = object;
                    closePoint = suspected;
                } else {
                    if (trajectory.start().distance(closePoint) > trajectory.start().distance(suspected)) {
                        closeObject = object;
                        closePoint = suspected;
                    }
                }
            }
        }
        if (closePoint == null) {
            return null;
        }
        return new CollisionInfo(closeObject, closePoint);
    }

    /**
     * grab the paddle from the environment in case it exists.
     * @return Paddle object.
     */
    public Paddle findPaddle() {
        if (collidableSet.isEmpty()) {
            return null;
        }
        for (Collidable object:collidableSet) {
            if (object.type() == "Paddle") {
                return (Paddle) object;
            }
        }
        return null;
    }



}
