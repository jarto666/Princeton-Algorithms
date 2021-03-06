import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class SAP {

    private final Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) {
            throw new IllegalArgumentException();
        }
        this.graph = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        var bfsV = new BreadthFirstDirectedPaths(graph, v);
        var bfsW = new BreadthFirstDirectedPaths(graph, w);

        var commonAncestor = ancestor(v, w);
        if (commonAncestor == -1)
            return -1;

        return bfsV.distTo(commonAncestor) + bfsW.distTo(commonAncestor);
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        var bfsV = new BreadthFirstDirectedPaths(graph, v);
        var bfsW = new BreadthFirstDirectedPaths(graph, w);

        int shortestPath = Integer.MAX_VALUE;
        int shortestAncestor = -1;
        for (int i = 0; i < graph.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int dist = bfsV.distTo(i) + bfsW.distTo(i);
                if (dist < shortestPath) {
                    shortestPath = dist;
                    shortestAncestor = i;
                }
            }
        }

        return shortestAncestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertexSet(v);
        validateVertexSet(w);

        if (!v.iterator().hasNext() || !w.iterator().hasNext()){
            return -1;
        }

        var bfsV = new BreadthFirstDirectedPaths(graph, v);
        var bfsW = new BreadthFirstDirectedPaths(graph, w);

        var commonAncestor = ancestor(v, w);
        if (commonAncestor == -1)
            return -1;

        return bfsV.distTo(commonAncestor) + bfsW.distTo(commonAncestor);
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertexSet(v);
        validateVertexSet(w);

        if (!v.iterator().hasNext() || !w.iterator().hasNext()){
            return -1;
        }

        var bfsV = new BreadthFirstDirectedPaths(graph, v);
        var bfsW = new BreadthFirstDirectedPaths(graph, w);

        int shortestPath = Integer.MAX_VALUE;
        int shortestAncestor = -1;
        for (int i = 0; i < graph.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int dist = bfsV.distTo(i) + bfsW.distTo(i);
                if (dist < shortestPath) {
                    shortestPath = dist;
                    shortestAncestor = i;
                }
            }
        }

        return shortestAncestor;
    }

    private void validateVertexSet(Iterable<Integer> vset) {
        if (vset == null)
            throw new IllegalArgumentException();

        for (Integer v : vset) {
            validateVertex(v);
        }
    }

    private void validateVertex(Integer v) {
        if (v == null || v >= graph.V() || v < 0) {
            throw new IllegalArgumentException();
        }
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In("digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        sap.length(Arrays.asList(0,3,8,9), null);
//        while (!StdIn.isEmpty()) {
//            int v = StdIn.readInt();
//            int w = StdIn.readInt();
//            int length = sap.length(v, w);
//            int ancestor = sap.ancestor(v, w);
//            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//        }
    }
}