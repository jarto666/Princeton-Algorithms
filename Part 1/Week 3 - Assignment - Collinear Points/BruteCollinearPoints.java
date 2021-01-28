import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {

    private List<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        if (points == null) throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            for (int j = 0; j < i; j++) {
                if (points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException();
            }
        }

        segments = new ArrayList<>();

        int n = points.length;
        Point[] copyPoints = Arrays.copyOf(points, points.length);

        Arrays.sort(copyPoints);

        for (int p = 0; p < n - 3; p++)
            for (int q = p + 1; q < n - 2; q++)
                for (int r = q + 1; r < n - 1; r++)
                    for (int s = r + 1; s < n; s++) {
                        double sq = copyPoints[p].slopeTo(copyPoints[q]);
                        double sr = copyPoints[p].slopeTo(copyPoints[r]);
                        double ss = copyPoints[p].slopeTo(copyPoints[s]);
                        if (sq == sr && sq == ss) {
                            segments.add(new LineSegment(copyPoints[p], copyPoints[s]));
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
        In in = new In("input6.txt");
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}