package com.jarto.graphs.directed;

import java.util.Stack;

public class TopoSort {
    private boolean[] visited;
    private Stack<Integer> topologicalOrder;

    public TopoSort(Digraph G)
    {
//        var ca = new CyclesAnalyzer(G);
//        if (ca.cycle() != null){
//            throw new IllegalArgumentException("Graph cannot contain cycles");
//        }

        topologicalOrder = new Stack<Integer>();
        visited = new boolean[G.getVertexCount()];
        for (int v = 0; v < G.getVertexCount(); v++)
            if (!visited[v]) dfs(G, v);
    }

    public Stack<Integer> order() {
        return topologicalOrder;
    }

    private void dfs(Digraph G, int v) {
        visited[v] = true;

        for(int a : G.adj(v)) {
            if (!visited[a])
                dfs(G, a);
        }

        topologicalOrder.push(v);
    }
}
