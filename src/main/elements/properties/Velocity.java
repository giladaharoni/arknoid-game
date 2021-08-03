//name: Gilad Aharoni.
//Id: 318781127.

package main.elements.properties;
import main.elements.geometric.shapes.Point;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double xDirection = 0;
    private double yDirection = 0;

    /**
     * Constructor.
     * @param dx how many units it moves on x axis.
     * @param dy how many units it moves on y axis.
     */
    public Velocity(double dx, double dy) {
        xDirection = dx;
        yDirection = dy;
    }

    /**
     * accessor to xDirection.
     * @return xDirection
     */
    public double getXDirection() {
        return xDirection;
    }

    /**
     * accessor to yDirection.
     * @return yDirection
     */
    public double getYDirection() {
        return yDirection;
    }

    /**
     * this function is a constructor of velocity by an angle and a speed.
     * calculate the dx,dy velocity.
     * @param angle an angle in degrees.
     * @param speed amount of units it changes.
     * @return Velocity(dx, dy)
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin((angle) / 180 * Math.PI);
        double dy = speed * Math.cos((angle) / 180 * Math.PI);
        return new Velocity(dx, dy);
    }

    /**
     * calculate units for speed depend the angle velocity.
     * @return speed.
     */
    public double getSpeedFromAngle() {
        double speed = Math.sqrt(Math.pow(xDirection, 2) + Math.pow(yDirection,
                2));
        return speed;
    }

    /**
     * Take a point with position (x,y) and return a new point with position
     * (x+dx, y+dy).
     * @param p the point to apply velocity
     * @return a new point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + xDirection,  p.getY() + yDirection);
    }

    @Override
    public String toString() {
        return "Velocity{" + "xDirection=" + xDirection
                + ", yDirection=" + yDirection + '}';
    }

}