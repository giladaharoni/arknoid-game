//name: Gilad Aharoni.
//Id: 318781127.
package main.elements.geometric.shapes;
/**
 * this class defines the Point object.
 */
public class Point {
    //fields
    private double myX = 0;
    private double myY = 0;

    /**
     * constructor of Point object.
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public Point(double x, double y) {
        myX = x;
        myY = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     * @param other another point.
     * @return double calculated distance.
     */
    public double distance(Point other) {
        double xDistance = other.getX() - this.getX();
        double yDistance = other.getY() - this.getY();
        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     * @param other another point.
     * @return is equals boolean.
     */
    public boolean equals(Point other) {
        if (other != null) {
            return other.getY() == this.myY && other.getX() == this.myX;
        }
        return false;
    }

    /**
     * Return the x value of this point.
     * @return my_x
     */
    public double getX() {
        return myX;
    }

    /**
     * Return y value of this point.
     * @return my_y
     */
    public double getY() {
        return myY;
    }

    /**
     * setting the x value of the point.
     * @param x x value.
     */
    public void setX(double x) {
        myX = x;
    }

    /**
     * setting the y value of the point.
     * @param y y value.
     */
    public void setY(double y) {
        myY = y;
    }

    @Override
    public String toString() {
        return "Point{" + "myX=" + myX + ", myY=" + myY + '}';
    }
}