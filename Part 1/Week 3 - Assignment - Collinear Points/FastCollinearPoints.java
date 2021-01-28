import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {

    private List<LineSegment> segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        if (points == null) throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            for (int j = 0; j < i; j++) {
                if (points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException();
            }
        }

        segments = new ArrayList<>();
        LinkedList<Point> collinearPoints = new LinkedList<>();

        int n = points.length;
        Point[] copyPoints = Arrays.copyOf(points, points.length);
        for (Point originalPoint : points) {
            Arrays.sort(copyPoints);
            Arrays.sort(copyPoints, originalPoint.slopeOrder());

            for (int i = 0; i < n; i++) {
                collinearPoints.add(copyPoints[i]);

                // Add while slope is the same, so we could find the furthest points ont he same line
                // i < n - 1 is used so that when we check current and next, next wouldn't be null
                while (i < n - 1 && originalPoint.slopeTo(copyPoints[i]) == originalPoint.slopeTo(copyPoints[i + 1])) {
                    collinearPoints.add(copyPoints[++i]);
                }

                // check for number of required point on the same line and eliminating duplicate values
                if (collinearPoints.size() >= 3 && originalPoint.compareTo(collinearPoints.peekFirst()) < 0) {
                    segments.add(new LineSegment(originalPoint, collinearPoints.peekLast()));
                }

                collinearPoints.clear();
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In("input8.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}