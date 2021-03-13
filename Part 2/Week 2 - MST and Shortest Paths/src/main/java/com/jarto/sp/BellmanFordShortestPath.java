package com.jarto.sp;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Works with graphs with no negative cycles
 * Can detect negative cycles
 */
public class BellmanFordShortestPath extends ShortestPath {

    public BellmanFordShortestPath(EdgeWeightedDirectedGraph graph, int source) {
        super(graph, source);
    }

    @Override
    protected void calculateShortestPath(EdgeWeightedDirectedGraph graph, int source) {
        int n = graph.vertexCount();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                relax(graph, j);
            }
        }
    }

    private void relax(EdgeWeightedDirectedGraph graph, Integer u) {
        for (var a : graph.adj(u)) {
            var v = a.from();
            var w = a.to();
            if (distTo[w] > distTo[v] + a.weight()) {
                edgeTo[w] = v;
                distTo[w] = distTo[v] + a.weight();
            }
        }
    }
}
