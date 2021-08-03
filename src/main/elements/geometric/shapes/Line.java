//name: Gilad Aharoni.
//Id: 318781127.
package main.elements.geometric.shapes;
import java.util.List;

/**
 * this class Line defines the object Line.
 */
public class Line {
    private Point first = new Point(0, 0);
    private Point last = new Point(0, 0);
    private boolean yIncline;
    private double incline;
    private double height;

    /**
     * creates the line from two points.
     * @param start the first point of the line
     * @param end the second point of the line
     */
    public Line(Point start, Point end) {
        first = start;
        last = end;
        yIncline = this.parallelToY();
        incline = this.inclineCalc();
        height = first.getY() - (incline * first.getX());

    }

    /**
     * creates a new line from 2 Coordinates.
     * @param x1 x of the first coordinate
     * @param y1 y of the first coordinate
     * @param x2 x of the second coordinate
     * @param y2 y of the second coordinate
     */
    public Line(double x1, double y1, double x2, double y2) {
        first = new Point(x1, y1);
        last = new Point(x2, y2);
        yIncline = this.parallelToY();
        incline = this.inclineCalc();
        height = first.getY() - (incline * first.getX());
    }

    /**
     * check if is the function parallel to y.
     * @return boolean true or false.
     */
    private boolean parallelToY() {
        return this.first.getX() == this.last.getX();
    }

    /**
     * calculating the incline of the line.
     * @return 0 or double incline.
     */
    private double inclineCalc() {
        if (yIncline) {
            return 0;
        } else {
            return (this.first.getY() - this.last.getY())
                    / (this.first.getX() - this.last.getX());
        }
    }

    /**
     * Return the length of the line.
     * @return double distance.
     */
    public double length() {
        return first.distance(last);
    }

    /**
     * Returns the middle point of the line.
     * @return point of middle.
     */
    public Point middle() {
        double xCenter = (first.getX() + last.getX()) / 2.0;
        double yCenter = (first.getY() + last.getY()) / 2.0;
        return new Point(xCenter, yCenter);
    }

    /**
     * Returns the start point of the line.
     * @return Point
     */
    public Point start() {
        return first;
    }

    /**
     * Returns the end point of the line.
     * @return Point
     */
    public Point end() {
        return last;
    }

    /**
     * an helping method for checking if a given value is in the range of the
     * two sides.
     * @param checked the checked value
     * @param sideA first side of the range
     * @param sideB second side of the range
     * @return true or false
     */
    private boolean isInRange(double checked, double sideA, double sideB) {
        double minimum = Math.min(sideA, sideB);
        double maximum = Math.max(sideA, sideB);
        return checked <= maximum && checked >= minimum;
    }

    /**
     * calculating the intersection of two lines, assuming there's no zero
     * values at all. and checkd if the intersection point is in the given
     * ranges.
     * @param other the second line for intersection.
     * @return true in case the intersected point is in ranges.
     */
    private boolean classicIntersect(Line other) {
        //calculating the intersect
        double x =
                (this.height - other.height) / (other.incline - this.incline);
        double y = this.incline * x + this.height;
        return this.isInRange(x, this.first.getX(), this.last.getX())
                && other.isInRange(x, other.first.getX(), other.last.getX())
                && this.isInRange(y, this.first.getY(), this.last.getY())
                && other.isInRange(y, other.first.getY(), other.last.getY());
    }

    /**
     * checks shared points of the two lines.
     * @param other another line.
     * @return true in case there are shared points of the two lines.
     */
    private boolean sharePoint(Line other) {
        return this.first.equals(other.first) || this.last.equals(other.first)
                || this.first.equals(other.last)
                || this.last.equals(other.last);
    }

    /**
     * when their both 2 y parallels it checks for intersection.
     * @param other another line.
     * @return true in case there is an intersection.
     */
    private boolean bothYParallels(Line other) {
        if (this.first.getX() == other.first.getX()) {
            return this.isInRange(this.first.getY(), other.first.getY(),
                    other.last.getY()) || this.isInRange(this.last.getY(),
                     other.first.getY(), other.last.getY());
        }
        return false;
    }

    /**
     * when 1 is the y parallel it checks for intersection.
     * @param parallel the parallel line.
     * @param other another line.
     * @return true in case there is an intersection.
     */
    private boolean oneYParallel(Line parallel, Line other) {
        double maxPointY = Math.max(parallel.first.getY(),
                parallel.last.getY());
        double minPointY = Math.min(parallel.first.getY(),
                parallel.last.getY());
        double yIntersection =
                other.incline * parallel.first.getX() + other.height;
        if (other.incline == 0) {
            double xInter = parallel.first.getX();
            return other.height <= maxPointY && other.height >= minPointY
                    && isInRange(xInter, other.first.getX(), other.last.getX());
        } else {
            return yIntersection <= maxPointY && yIntersection >= minPointY;
        }
    }

    /**
     * when their both 2 x parallels it checks for intersection.
     * @param other another line.
     * @return true in case there is an intersection.
     */
    private boolean bothXParallels(Line other) {
        if (this.first.getY() != other.first.getY()) {
            return false;
        }
        double xLeftThis = Math.min(this.first.getX(), this.last.getX());
        double xRightThis = Math.max(this.first.getX(), this.last.getX());
        double xLeftOther = Math.min(other.first.getX(), other.last.getX());
        double xRightOther = Math.max(other.first.getX(), other.last.getX());
        return this.isInRange(xLeftThis, xLeftOther, xRightOther)
                || this.isInRange(xRightThis, xLeftOther, xRightOther);
    }

    //when there 1 x parallel
    /**
     * when 1 is the x parallel it checks for intersection.
     * @param parallel the parallel line.
     * @param other another line.
     * @return true in case there is an intersection.
     */
    private boolean singleXParallel(Line parallel, Line other) {
        double yParallel = parallel.first.getY();
        double xIntersection = (yParallel - other.height) / other.incline;
        return (this.isInRange(xIntersection, other.first.getX(),
                other.last.getX()) && this.isInRange(xIntersection
                , parallel.first.getX(), parallel.last.getX()))
                 && (this.isInRange(yParallel, other.first.getY(),
                other.last.getY()) && this.isInRange(yParallel
                , parallel.first.getY(), parallel.last.getY()));
    }

    /**
     * the main function for checking intersection point in the given lines.
     * @param other another line.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //check if they are the same line or have a sharePoint.
        if (this.equals(other) || this.sharePoint(other)) {
            return true;
        }
        //check if both are y parallels.
        if (this.yIncline && other.yIncline) {
            return this.bothYParallels(other);
        }
        //check if only one is y parallels.
        if (this.yIncline) {
            return this.oneYParallel(this, other);
        }
        if (other.yIncline) {
            return this.oneYParallel(other, this);
        }
        //check if both are x parallels.
        if (this.incline == 0 && other.incline == 0) {
            return this.bothXParallels(other);
        }
        //check if only one is x parallels.
        if (this.incline == 0) {
            return this.singleXParallel(this, other);
        }
        if (other.incline == 0) {
            return this.singleXParallel(other, this);
        }
        //check the basic case - there's no zero values inclines.
        return this.classicIntersect(other);
    }

    /**
     * check if the given point is on the line.
     * @param point given point.
     * @return true in case it is on the line.
     */
    public boolean isOnLine(Point point) {
        boolean x = isInRange(point.getX(), first.getX(), last.getX());
        boolean y = isInRange(point.getY(), first.getY(), last.getY());
        return x && y;
    }

    /**
     * find the intersection point od two lines.
     * @param other another line.
     * @return the intersection point if the lines intersect, and null
     * otherwise.
     */
    public Point intersectionWith(Line other) {
        //check for existence of intersection point.
        if (!this.isIntersecting(other)) {
            return null;
        }
        //check for shared point.
        if (this.sharePoint(other)) {
            if (this.first.equals(other.first) || this.first.equals(other.last)) {
                if (other.isOnLine(this.last)) {
                    return null;
                }
                return this.first;
            }
            if (this.last.equals(other.first) || this.last.equals(other.last)) {
                return this.last;
            }
        }
        //check for convergence on y parallels lines.
        if (this.yIncline && other.yIncline && this.bothYParallels(other)) {
            return null;
        }

        if (this.yIncline && this.oneYParallel(this, other)) {
            double x = this.first.getX();
            double y = other.incline * x + other.height;
            if (this.isOnLine(new Point(x, y)) && other.isOnLine(new Point(x,
                    y))) {
                return new Point(x, y);
            } else {
                return null;
            }

        }
        //other is y incline and we have intersection.
        if (other.yIncline && this.oneYParallel(other, this)) {
            double x = other.first.getX();
            double y = this.incline * x + this.height;
            if (isInRange(x, start().getX(), end().getX()) || isInRange(y,
                    start().getY(), end().getY())) {
                return new Point(x, y);
            }
            return null;
        }
        if (this.incline == 0 && other.incline == 0 && this.bothXParallels(other)) {
            return null;
        }
        if (this.incline == 0 && this.singleXParallel(this, other)) {
            double yParallel = this.first.getY();
            double xIntersection = (yParallel - other.height) / other.incline;
            return new Point(xIntersection, yParallel);
        }
        if (other.incline == 0 && this.singleXParallel(other, this)) {
            double yParallel = other.first.getY();
            double xIntersection = (yParallel - this.height) / this.incline;
            return new Point(xIntersection, yParallel);
        }
        double x =
                (other.height - this.height) / (this.incline - other.incline);
        return new Point(x, this.incline * x + this.height);
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     * @param other another line.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return ((this.first.equals(other.first) && this.last.equals(other.last))
                || (this.first.equals(other.last))
                                && (this.last.equals(other.first)));

    }

    /**
     *finding the closest intersection point to the start point and a rectangle.
     * @param rect rectangle for intersection.
     * @return the closest intersection point of a line and a rectangle.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        if (points == null) {
            return null;
        }
        Point minimalDistance = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            if (minimalDistance.distance(first) > points.get(i).distance(first)) {
                minimalDistance = points.get(i);
            }
        }
        return minimalDistance;
    }

    @Override
    public String toString() {
        return "Line{" + "first=" + first + ", last=" + last + ", yIncline="
                + yIncline + ", incline=" + incline + ", height=" + height + '}';
    }
}
