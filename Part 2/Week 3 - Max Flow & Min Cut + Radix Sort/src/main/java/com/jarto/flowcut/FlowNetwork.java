package com.jarto.flowcut;

import java.util.LinkedList;

public class FlowNetwork {

    private final int V;
    private LinkedList<FlowEdge>[] adj;

    public FlowNetwork(int V) {
        this.V = V;
        adj = (LinkedList<FlowEdge>[]) new LinkedList[V];
        for (int v = 0; v < V; v++)
            adj[v] = new LinkedList<FlowEdge>();
    }

    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        adj[w].add(e);
    }

    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    public int getVertexCount() {
        return V;
    }
}