package com.jarto.graphs.directed;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CyclesAnalyzer {

    private boolean[] visited;
    private int[] edgeTo;               // edgeTo[v] = previous vertex on path to v
    private boolean[] recStack;         // recStack[v] = recursion Stack
    private Stack<Integer> cycle;       // directed cycle (or null if no such cycle)

    public CyclesAnalyzer(Digraph g) {

        int vCount = g.getVertexCount();
        visited = new boolean[vCount];
        recStack = new boolean[vCount];
        edgeTo = new int[vCount];

        for (int i = 0; i < g.getVertexCount(); i++) {

            if (cycle != null)
                return;

            if (!visited[i])
                hasCycleDfs(g, i);
        }
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    private void hasCycleDfs(Digraph g, int v) {

        recStack[v] = true;
        visited[v] = true;
        for (int a : g.adj(v)) {
            if (cycle != null) return;

            if (!visited[a]) {
                edgeTo[a] = v;
                hasCycleDfs(g, a);
            } else if (recStack[a]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != a; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(a);
            }
        }

        recStack[v] = false;
    }
}
