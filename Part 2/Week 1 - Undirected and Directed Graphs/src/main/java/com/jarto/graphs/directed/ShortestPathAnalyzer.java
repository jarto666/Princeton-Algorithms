package com.jarto.graphs.directed;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShortestPathAnalyzer {

    private int source;
    private Digraph g;

    private int[] edgeTo;

    public ShortestPathAnalyzer(Digraph g, int s) {
        this.g = g;
        this.source = s;

        edgeTo = new int[g.getVertexCount()];
        Arrays.fill(edgeTo, -1);

        edgeTo[source] = source;
        Queue<Integer> q = new LinkedList<>();
        q.offer(source);
        while (!q.isEmpty()) {
            var cur = q.remove();

            for (int a : g.adj(cur)) {
                if (edgeTo[a] == -1){
                    q.offer(a);
                    edgeTo[a] = cur;
                }
            }
        }
    }

    public Iterable<Integer> shortestPathTo(int v) {
        if (!hasPathTo(v)) return null;

        var st = new Stack<Integer>();
        for (int x = v; x != source; x = edgeTo[x])
            st.push(x);
        st.push(source);
        return st;
    }

    private boolean hasPathTo(int v) {
        return edgeTo[v] != -1;
    }
}
