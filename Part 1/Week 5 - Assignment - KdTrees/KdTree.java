import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {

    private Node root;
    private int count = 0;
    private final int XMIN = 0;
    private final int XMAX = 1;
    private final int YMIN = 0;
    private final int YMAX = 1;

    // construct an empty set of points
    public KdTree() {
        root = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return count;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {

        if (p == null)
            throw new IllegalArgumentException();

        if (root == null) {
            root = new Node(p, false);
            count++;
            return;
        }

        if (contains(p))
            return;

        Node cur = root;
        count++;
        while (true) {
            double nextCoord = cur.isHorizontal ? p.y() : p.x();
            double curCoord = cur.isHorizontal ? cur.point.y() : cur.point.x();

            if (nextCoord < curCoord) {
                if (cur.left == null) {
                    cur.left = new Node(p, !cur.isHorizontal);
                    return;
                } else {
                    cur = cur.left;
                }
            } else {
                if (cur.right == null) {
                    cur.right = new Node(p, !cur.isHorizontal);
                    return;
                } else {
                    cur = cur.right;
                }
            }
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        if (root == null) return false;

        Node cur = root;
        while (true) {

            if (cur.point.equals(p)) return true;

            double nextCoord = cur.isHorizontal ? p.y() : p.x();
            double curCoord = cur.isHorizontal ? cur.point.y() : cur.point.x();

            if (nextCoord < curCoord) {
                if (cur.left == null) return false;
                else cur = cur.left;
            } else {
                if (cur.right == null) return false;
                else cur = cur.right;
            }
        }
    }

    // draw all points to standard draw
    public void draw() {
        draw(root, 0, 1, 0, 1);
    }

    private void draw(Node n, double minX, double maxX, double minY, double maxY) {
        if (n == null) return;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(n.point.x(), n.point.y());

        if (n.isHorizontal) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(minX, n.point.y(), maxX, n.point.y());
            draw(n.left, minX, maxX, minY, n.point.y());
            draw(n.right, minX, maxX, n.point.y(), maxY);
        } else {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.point.x(), minY, n.point.x(), maxY);
            draw(n.left, minX, n.point.x(), minY, maxY);
            draw(n.right, n.point.x(), maxX, minY, maxY);
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();

        ArrayList<Point2D> pointsInside = new ArrayList<>();

        range(rect, root, pointsInside);

        return pointsInside;
    }

    private void range(RectHV rect, Node node, ArrayList<Point2D> pointsInside) {

        if (node == null)
            return;

        if (rect.contains(node.point))
            pointsInside.add(node.point);

        double rectMin;
        double rectMax;
        double pointCoord;

        if (node.isHorizontal) {
            rectMin = rect.ymin();
            rectMax = rect.ymax();
            pointCoord = node.point.y();
        } else {
            rectMin = rect.xmin();
            rectMax = rect.xmax();
            pointCoord = node.point.x();
        }

        if (rectMin <= pointCoord)
            range(rect, node.left, pointsInside);
        if (rectMax >= pointCoord)
            range(rect, node.right, pointsInside);
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        if (isEmpty())
            return null;

        Point2D nearest = null;

        nearest = nearest(root, p, nearest, new RectHV(XMIN, YMIN, XMAX, YMAX));

        return nearest;
    }

    private Point2D nearest(Node node, Point2D searchPoint, Point2D minPoint, RectHV rect) {

        if (node == null) return minPoint;

        if (minPoint == null)
            minPoint = node.point;

        double minToSearchDist = minPoint.distanceTo(searchPoint);
        double rectToSearchDist = rect.distanceTo(searchPoint);
        if (rectToSearchDist < minToSearchDist) {
            double nodeToSearchDist = node.point.distanceTo(searchPoint);
            if (nodeToSearchDist < minToSearchDist) {
                minPoint = node.point;
            }

            if (goLeft(node, searchPoint)) {
                RectHV rr = getRect(node.point, rect, node.isHorizontal, false);
                minPoint = nearest(node.right, searchPoint, minPoint, rr);
                RectHV lr = getRect(node.point, rect, node.isHorizontal, true);
                minPoint = nearest(node.left, searchPoint, minPoint, lr);
            } else {
                RectHV lr = getRect(node.point, rect, node.isHorizontal, true);
                minPoint = nearest(node.left, searchPoint, minPoint, lr);
                RectHV rr = getRect(node.point, rect, node.isHorizontal, false);
                minPoint = nearest(node.right, searchPoint, minPoint, rr);
            }
        }

        return minPoint;
    }

    private boolean goLeft(Node node, Point2D searchPoint) {
        return (node.isHorizontal && node.point.y() < searchPoint.y())
                || (!node.isHorizontal && node.point.x() < searchPoint.x());
    }

    private RectHV getRect(
            Point2D point, RectHV origRect,
            boolean isHorizontal, boolean toLeft) {
        if (isHorizontal) {
            if (toLeft) {
                return new RectHV(origRect.xmin(), origRect.ymin(), origRect.xmax(), point.y());
            } else {
                return new RectHV(origRect.xmin(), point.y(), origRect.xmax(), origRect.ymax());
            }
        } else {
            if (toLeft) {
                return new RectHV(origRect.xmin(), origRect.ymin(), point.x(), origRect.ymax());
            } else {
                return new RectHV(point.x(), origRect.ymin(), origRect.xmax(), origRect.ymax());
            }
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        KdTree t = new KdTree();
        t.insert(new Point2D(0.7, 0.2));
        t.insert(new Point2D(0.5, 0.4));
        t.insert(new Point2D(0.2, 0.3));
        t.insert(new Point2D(0.4, 0.7));
        t.insert(new Point2D(0.9, 0.6));

        t.nearest(new Point2D(0.84, 0.39));
    }

    private static class Node {
        public Point2D point;
        public Node left;
        public Node right;
        public boolean isHorizontal;

        public Node(Point2D point, boolean isHorizontal) {
            this.point = point;
            this.isHorizontal = isHorizontal;
        }
    }
}