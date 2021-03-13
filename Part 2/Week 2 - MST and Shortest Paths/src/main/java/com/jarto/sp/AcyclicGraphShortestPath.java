package com.jarto.sp;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * Works only with DAGs
 */
public class AcyclicGraphShortestPath extends ShortestPath {
    private Set<Integer> visited;

    public AcyclicGraphShortestPath(EdgeWeightedDirectedGraph graph, int source) {
        super(graph, source);
    }

    @Override
    protected void calculateShortestPath(EdgeWeightedDirectedGraph graph, int source) {

        visited = new HashSet<>();
        visited.add(source);

        var order = topoSort(graph);

        for (int i : order) {
            relax(graph, i);
            visited.add(i);
        }
    }

    private int[] topoSort(EdgeWeightedDirectedGraph graph) {
        int n = graph.vertexCount();
        var processed = new boolean[n];
        Stack<Integer> order = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!processed[i])
                topoSort(graph, i, order, processed);
        }

        var result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = order.pop();
        }

        return result;
    }

    private void topoSort(EdgeWeightedDirectedGraph graph, int v, Stack<Integer> order, boolean[] processed) {
        processed[v] = true;

        for (var a : graph.adj(v)) {
            if (!processed[a.to()])
                topoSort(graph, a.to(), order, processed);
        }

        order.push(v);
    }

    private void relax(EdgeWeightedDirectedGraph graph, Integer u) {
        for (var a : graph.adj(u)) {
            var v = a.from();
            var w = a.to();
            if (!visited.contains(w)) {
                if (distTo[w] > distTo[v] + a.weight()) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + a.weight();
                }
            }
        }
    }
}
