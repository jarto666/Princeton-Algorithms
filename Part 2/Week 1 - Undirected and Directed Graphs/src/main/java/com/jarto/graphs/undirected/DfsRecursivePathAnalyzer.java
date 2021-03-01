package com.jarto.graphs.undirected;

import java.util.Stack;

public class DfsRecursivePathAnalyzer implements PathAnalyzer {

    boolean[] visited;
    int[] edgeTo;
    int source;

    public DfsRecursivePathAnalyzer(Graph g, int s) {
        visited = new boolean[g.getVertexCount()];
        edgeTo = new int[g.getVertexCount()];
        source = s;

        dfs(g, s);
    }

    private void dfs(Graph g, int s) {
        visited[s] = true;
        for (int a : g.adj(s)) {
            if (visited[a]) continue;
            dfs(g, a);
            edgeTo[a] = s;
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return visited[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;

        var st = new Stack<Integer>();
        for (int x = v; x != source; x = edgeTo[x])
            st.push(x);
        st.push(source);
        return st;
    }
}
