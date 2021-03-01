package com.jarto.graphs.undirected;

import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Graph {

    private int vertexCount;
    private int edgeCount = 0;
    private final LinkedList<Integer>[] adj;

    public Graph(int V) {
        vertexCount = V;
        adj = (LinkedList<Integer>[])new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public Graph(File file) {
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
//            var c = connection;
            addEdge(c[0], c[1]);
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        edgeCount++;
    }

    Iterable<Integer> adj(int v) {
        return adj[v];
    }

    int getVertexCount() {
        return vertexCount;
    }

    int getEdgeCount() {
        return edgeCount;
    }

    public static void main(String[] args) {

    }
}
