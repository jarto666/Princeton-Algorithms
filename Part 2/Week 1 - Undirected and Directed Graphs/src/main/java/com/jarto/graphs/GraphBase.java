package com.jarto.graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public abstract class GraphBase {

    protected int vertexCount;
    protected int edgeCount = 0;
    protected final LinkedList<Integer>[] adj;

    public GraphBase(int V) {
        vertexCount = V;
        adj = (LinkedList<Integer>[])new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public GraphBase(File file) {
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String vxCountString = sc.nextLine();
        vertexCount = Integer.parseInt(vxCountString);
        adj = new LinkedList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            adj[i] = new LinkedList<>();
        }

        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            var c = Arrays.stream(data.split(" ")).mapToInt(Integer::parseInt).toArray();
            addEdge(c[0], c[1]);
        }
    }

    public abstract void addEdge(int v, int w);

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }
}
