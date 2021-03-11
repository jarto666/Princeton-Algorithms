package com.jarto.mst;

import java.util.PriorityQueue;

public class PrimLazyMST extends MST {

    private boolean[] marked;

    public PrimLazyMST(EdgeWeightedGraph graph) {
        super(graph);
    }

    @Override
    protected void calculateMst(EdgeWeightedGraph graph) {
        int n = graph.vertexCount();
        marked = new boolean[n];
        var pq = new PriorityQueue<Edge>();

        visit(graph, pq, 0);
        while (!pq.isEmpty() && edges.size() <= n) {
            var minEdge = pq.remove();
            int v = minEdge.either();
            int w = minEdge.other(v);
            if (marked[v] && marked[w])
                continue;

            edges.offer(minEdge);
            weight += minEdge.weight();
            if (!marked[v]) visit(graph, pq, v);
            if (!marked[w]) visit(graph, pq, w);
        }

    }

    private void visit(EdgeWeightedGraph graph, PriorityQueue<Edge> pq, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            int w = e.other(v);
            if (!marked[w]) {
                pq.add(e);
            }
        }
    }
}
