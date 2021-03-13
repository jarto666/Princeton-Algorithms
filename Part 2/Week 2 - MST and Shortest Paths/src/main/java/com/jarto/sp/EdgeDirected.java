package com.jarto.sp;

import com.jarto.mst.Edge;

public class EdgeDirected implements Comparable<EdgeDirected> {

    private double weight;
    private int from;
    private int to;

    public EdgeDirected(int from, int to, double weight) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    @Override
    public int compareTo(EdgeDirected that) {
        if (this.weight > that.weight)
            return 1;
        else if (this.weight < that.weight)
            return -1;

        return 0;
    }

    public double weight() {
        return weight;
    }
}
