package com.jarto.mst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class EdgeWeightedGraph {

    private int edgeCount;
    private int vertexCount;
    private List<Edge>[] edges;

    public EdgeWeightedGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        edges = (LinkedList<Edge>[])new LinkedList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            edges[i] = new LinkedList<>();
        }
    }

    public EdgeWeightedGraph(File file) {
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String vxCountString = sc.nextLine();
        vertexCount = parseInt(vxCountString);
        edges = new LinkedList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            edges[i] = new LinkedList<>();
        }

        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            var c = data.split(" ");
            addEdge(new Edge(parseInt(c[0]), parseInt(c[1]), parseDouble(c[2])));
        }
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        edges[v].add(e);
        edges[w].add(e);
        edgeCount++;
    }

    public Iterable<Edge> adj(int v) {
        return edges[v];
    }

    public Iterable<Edge> edges() {
        return Arrays.stream(edges).flatMap(List::stream).collect(Collectors.toList());
    }

    public int vertexCount() {
        return vertexCount;
    }

    public int edgeCount() {
        return edgeCount;
    }
}
