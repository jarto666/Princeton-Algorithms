package com.jarto.graphs.undirected;

import java.util.Stack;

public class DfsIterativePathAnalyzer implements PathAnalyzer {
    boolean[] visited;
    int[] edgeTo;
    int source;

    public DfsIterativePathAnalyzer(Graph g, int s) {
        visited = new boolean[g.getVertexCount()];
        edgeTo = new int[g.getVertexCount()];
        source = s;
        dfs(g, s);
    }

    private void dfs(Graph g, int s) {

        Stack<Integer> st = new Stack<>();
        st.push(s);
        while (!st.isEmpty()) {
            int cur = st.pop();
            visited[cur] = true;
            for (int a : g.adj(cur)) {
                if (visited[a]) continue;
                st.push(a);
                edgeTo[a] = cur;
            }
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
