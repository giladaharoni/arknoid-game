//name: Gilad Aharoni.
//Id: 318781127.
package main.elements.geometric.shapes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * this helping class defines a rectangle object.
 */
public class Rectangle {
    //fields
    private Point upperLeft = null;
    private Point bottomRight = null;
    private Point upperRight = null;
    private Point bottomLeft = null;

    //constructors

    /**
     * a constructor to rectangle by points.
     * @param topLeft1 the top left corner.
     * @param bottomRight1 the bottom right corner.
     */
    public Rectangle(Point topLeft1, Point bottomRight1) {
        upperLeft = topLeft1;
        bottomRight = bottomRight1;
        upperRight = new Point(bottomRight.getX(), upperLeft.getY());
        bottomLeft = new Point(upperLeft.getX(), bottomRight.getY());
    }

    /**
     * a constructor to rectangle by x,y values.
     * @param x1 the top left x value corner.
     * @param y1 the top left y value corner.
     * @param x2 the bottom right x value corner.
     * @param y2 the bottom right y value corner.
     */
    public Rectangle(double x1, double y1, double x2, double y2) {
        upperLeft = new Point(x1, y1);
        bottomRight = new Point(x2, y2);
        upperRight = new Point(bottomRight.getX(), upperLeft.getY());
        bottomLeft = new Point(upperLeft.getX(), bottomRight.getY());
    }

    /**
     * a constructor to rectangle by upper left point, width and height.
     * @param upperLeft point.
     * @param width of the rectangle.
     * @param height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        bottomRight = new Point(upperLeft.getX() + width,
                upperLeft.getY() + height);
        upperRight = new Point(bottomRight.getX(), upperLeft.getY());
        bottomLeft = new Point(upperLeft.getX(), bottomRight.getY());
    }

    /**
     *creating intersection points list of a line and rectangle.
     * @param line to intersect with
     * @return intersection points list or null in case of no intersection.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<Point>();
        Point inter1 = new Line(upperLeft, upperRight).intersectionWith(line);
        Point inter2 = new Line(upperRight, bottomRight).intersectionWith(line);
        Point inter3 = new Line(bottomRight, bottomLeft).intersectionWith(line);
        Point inter4 = new Line(bottomLeft, upperLeft).intersectionWith(line);
        boolean aPointHasBeenAdded = false;
        if (inter1 != null) {
            list.add(inter1);
            aPointHasBeenAdded = true;
        }
        if (inter2 != null) {
            if (!list.contains(inter2)) {
                list.add(inter2);
                aPointHasBeenAdded = true;
            }
        }
        if (inter3 != null) {
            if (!list.contains(inter3)) {
                list.add(inter3);
                aPointHasBeenAdded = true;
            }
        }
        if (inter4 != null) {
            if (!list.contains(inter4)) {
                list.add(inter4);
                aPointHasBeenAdded = true;
            }
        }
        if (list.size() == 2) {
            if (line.start().distance(list.get(0)) > line.start().distance(list.get(1))) {
                Collections.reverse(list);
            }
        }
        if (!aPointHasBeenAdded) {
            return null;
        }
        return list;
    }

    //accessors

    /**
     * accessor to bottomRight.
     * @return point bottomRight.
     */
    public Point getBottomRight() {
        return bottomRight;
    }

    /**
     * accessor to topLeft.
     * @return point topLeft.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * calculating the height.
     * @return double height.
     */
    public double getHeight() {
        return bottomRight.getY() - upperLeft.getY();
    }

    /**
     * calculating the width.
     * @return double width.
     */
    public double getWidth() {
        return bottomRight.getX() - upperLeft.getX();
    }

    /**
     * tell us where a point of a collision is on the rectangle.
     * @param hit collision point.
     * @return with the direction.
     */
    public String whereThePointHit(Point hit) {
        if (hit.getY() == upperLeft.getY() && hit.getX() <= upperRight.getX()
                && hit.getX() >= upperLeft.getX()) {
            return "up";
        }
        if (hit.getY() == bottomLeft.getY() && hit.getX() <= upperRight.getX()
                && hit.getX() >= upperLeft.getX()) {
            return "down";
        }
        if (hit.getX() == upperLeft.getX() && hit.getY() <= bottomLeft.getY()
                && hit.getY() >= upperLeft.getY()) {
            return "left";
        }
        if (hit.getX() == upperRight.getX() && hit.getY() <= bottomRight.getY()
                && hit.getY() >= upperRight.getY()) {
            return "right";
        }
        return null;
    }

    @Override
    public String toString() {
        return "Rectangle{" + "upperLeft=" + upperLeft + ", bottomRight="
                + bottomRight + ", upperRight=" + upperRight + ", bottomLeft="
                + bottomLeft + '}';
    }
}
