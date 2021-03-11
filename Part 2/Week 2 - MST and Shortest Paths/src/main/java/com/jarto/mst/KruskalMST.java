package com.jarto.mst;

import com.jarto.common.UnionFind;

import java.util.PriorityQueue;

public class KruskalMST extends MST {

    public KruskalMST(EdgeWeightedGraph graph) {
        super(graph);
    }

    @Override
    protected void calculateMst(EdgeWeightedGraph graph) {
        var uf = new UnionFind(graph.vertexCount());
        var pq = new PriorityQueue<Edge>();

        for (var edge : graph.edges()) {
            pq.add(edge);
        }

        while (!pq.isEmpty()) {
            var edge = pq.remove();
            int v = edge.either();
            int w = edge.other(v);

            if (uf.isConnected(v, w))
                continue;

            uf.union(v, w);
            edges.add(edge);
            weight += edge.weight();
        }
    }
}
