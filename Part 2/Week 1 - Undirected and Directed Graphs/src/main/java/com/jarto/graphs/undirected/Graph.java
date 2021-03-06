package com.jarto.graphs.undirected;

import com.jarto.graphs.GraphBase;

import java.io.File;

public class Graph extends GraphBase {

    public Graph(File file) {
        super(file);
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        edgeCount++;
    }

    public static void main(String[] args) {

    }
}
