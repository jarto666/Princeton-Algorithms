package com.jarto.graphs.directed;

import com.jarto.graphs.GraphBase;

import java.io.File;

public class Digraph extends GraphBase {

    public Digraph(int V) {
        super(V);
    }

    public Digraph(File file) {
        super(file);
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        edgeCount++;
    }

    public Digraph reversed() {
        var reversed = new Digraph(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            var adj = adj(i);
            for (int a : adj) {
                reversed.addEdge(a, i);
            }
        }

        return reversed;
    }

    public static void main(String[] args) {

    }
}