package com.jarto.graphs.directed;

public class StronglyConnectedComponents {

    private boolean[] visited;
    private int[] ccIds; // ConnectedComponent ID
    private int count = 0; // ConnectedComponent count

    public StronglyConnectedComponents(Digraph digraph) {
        int vCount = digraph.getVertexCount();

        visited = new boolean[vCount];
        ccIds = new int[vCount];

        var topoSort = new TopoSort(digraph.reversed());
        var order = topoSort.order();

        while(!order.isEmpty()) {
            int v = order.pop();
            if (!visited[v]) {
                dfs(digraph, v);
                count++;
            }
        }
    }

    public boolean areConnected(int v, int w) {
        return ccIds[v] == ccIds[w];
    }

    public int ccId(int v) {
        return ccIds[v];
    }

    public int count() {
        return count;
    }

    private void dfs(Digraph g, int v) {
        visited[v] = true;
        ccIds[v] = count;
        for (int a : g.adj(v))
            if (!visited[a])
                dfs(g, a);
    }
}
