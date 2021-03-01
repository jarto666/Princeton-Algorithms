package com.jarto.graphs.undirected;

import java.util.Arrays;

public class ConnectedComponents {

    private int[] id;
    private int count;

    // find connected components in G
    public ConnectedComponents(Graph g) {
        id = new int[g.getVertexCount()];
        Arrays.fill(id, -1);
        for(int i = 0; i < g.getVertexCount(); i++) {
            if (id[i] != -1) continue;
            dfs(g, i, count++);
        }
    }

    private void dfs(Graph g, int s, int ccId) {
        id[s] = ccId;
        for (int a : g.adj(s)) {
            if (id[a] != -1) continue;
            dfs(g, a, ccId);
        }
    }

    // are v and w connected?
    boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    // number of connected components
    int count() {
        return count;
    }

    // component identifier for v
    int id(int v) {
        return id[v];
    }
}
