import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private int x;
    private int y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {

        int s = 200;
        StdDraw.filledPolygon(
                new double[]{x - s, x - s, x + s, x + s},
                new double[]{y - s, y + s, y + s, y - s});
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }

    // string representation
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        int diffY = y - that.y;
        return diffY != 0 ? diffY : x - that.x;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        if (x == that.x && y == that.y) return Double.NEGATIVE_INFINITY;
        else if (x == that.x) return Double.POSITIVE_INFINITY;
        else if (y == that.y) return +0;
        else return (double)(that.y - y) / (that.x - x);
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return (o1, o2) -> {
            double o1Slope = slopeTo(o1);
            double o2Slope = slopeTo(o2);
            double diff = o1Slope - o2Slope;
            if (diff > 0) return 1;
            else if (diff < 0) return -1;
            else return 0;
        };
    }
}