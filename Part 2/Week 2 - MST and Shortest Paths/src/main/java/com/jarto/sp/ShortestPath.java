package com.jarto.sp;

import com.jarto.mst.Edge;
import com.jarto.mst.EdgeWeightedGraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public abstract class ShortestPath {

    protected double[] distTo;
    protected int[] edgeTo;

    public ShortestPath(EdgeWeightedDirectedGraph graph, int source) {
        int n = graph.vertexCount();

        distTo = new double[n];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[source] = 0;

        edgeTo = new int[n];
        Arrays.fill(edgeTo, -1);
        edgeTo[source] = source;

        calculateShortestPath(graph, source);
    }

    public Stack<Integer> pathTo(int v)
    {
        if (edgeTo[v] == -1)
            return null;

        Stack<Integer> path = new Stack<>();
        path.push(v);
        int e = edgeTo[v];
        for (; e != edgeTo[e]; e = edgeTo[e])
            path.push(e);
        path.push(e);
        return path;
    }

    public double distTo(int v) {
        return distTo[v];
    }

    protected abstract void calculateShortestPath(EdgeWeightedDirectedGraph graph, int source);
}
