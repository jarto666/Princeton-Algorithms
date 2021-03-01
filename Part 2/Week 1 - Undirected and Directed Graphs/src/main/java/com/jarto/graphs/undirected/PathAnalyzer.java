package com.jarto.graphs.undirected;

public interface PathAnalyzer {
    boolean hasPathTo(int v);

    Iterable<Integer> pathTo(int v);
}
