package com.jarto.graphs.undirected;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BfsPathAnalyzer implements PathAnalyzer {

    boolean[] visited;
    int[] edgeTo;
    int source;

    public BfsPathAnalyzer(Graph g, int s) {
        visited = new boolean[g.getVertexCount()];
        edgeTo = new int[g.getVertexCount()];
        source = s;
        bfs(g, s);
    }

    private void bfs(Graph g, int s) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(s);
        while(!q.isEmpty()) {
            int cur = q.remove();
            visited[cur] = true;
            for (int a : g.adj(cur)) {
                if (visited[a]) continue;
                q.offer(a);
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
