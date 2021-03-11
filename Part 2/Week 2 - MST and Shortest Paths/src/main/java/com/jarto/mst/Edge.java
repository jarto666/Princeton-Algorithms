package com.jarto.mst;

public class Edge implements Comparable<Edge> {

    private double weight;
    private int v;
    private int w;

    public Edge(int v, int w, double weight) {
        this.weight = weight;
        this.v = v;
        this.w = w;
    }

    public int either() {
        return v;
    }

    public int other(int v) {
        return this.v == v ? this.w : this.v;
    }

    @Override
    public int compareTo(Edge that) {
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
