package com.jarto.mst;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class MST {

    protected double weight;
    protected Queue<Edge> edges;

    public MST(EdgeWeightedGraph graph) {
        edges = new LinkedList<>();
        calculateMst(graph);
    }

    public Iterable<Edge> edges() {
        return edges;
    }

    public double weight() {
        return weight;
    }

    protected abstract void calculateMst(EdgeWeightedGraph graph);
}
