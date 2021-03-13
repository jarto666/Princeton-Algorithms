package com.jarto.sp;

import com.jarto.mst.EdgeWeightedGraph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * The Dijkstra algorithm of finding shortest paths to all vertices from source.
 * Works only with non-negative weights
 */
public class DijkstraShortestPath extends ShortestPath {

    private PriorityQueue<Integer> pq;
    private Set<Integer> visited;

    public DijkstraShortestPath(EdgeWeightedDirectedGraph graph, int source) {
        super(graph, source);
    }

    @Override
    protected void calculateShortestPath(EdgeWeightedDirectedGraph graph, int source) {
        int n = graph.vertexCount();

        pq = new PriorityQueue<>((a, b) -> {
            if (distTo[a] > distTo[b])
                return 1;
            if (distTo[a] < distTo[b])
                return -1;

            return 0;
        });
        pq.add(source);

        visited = new HashSet<>();
        visited.add(source);

        while(visited.size() != n) {
            var u = pq.remove();

            visited.add(u);

            relax(graph, u);
        }
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

            pq.add(w);
        }
    }
}
