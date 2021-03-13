package com.jarto.sp;

import com.jarto.mst.Edge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class EdgeWeightedDirectedGraph {

    private int edgeCount;
    private int vertexCount;
    private List<EdgeDirected>[] edges;

    public EdgeWeightedDirectedGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        edges = (LinkedList<EdgeDirected>[])new LinkedList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            edges[i] = new LinkedList<>();
        }
    }

    public EdgeWeightedDirectedGraph(File file) {
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
            String[] c = data.split(" ");
            addEdge(new EdgeDirected(parseInt(c[0]), parseInt(c[1]), parseDouble(c[2])));
        }
    }

    public void addEdge(EdgeDirected e) {
        int v = e.from();
        int w = e.to();
        edges[v].add(e);
        edgeCount++;
    }

    public Iterable<EdgeDirected> adj(int v) {
        return edges[v];
    }

    public Iterable<EdgeDirected> edges() {
        return Arrays.stream(edges).flatMap(List::stream).collect(Collectors.toList());
    }

    public int vertexCount() {
        return vertexCount;
    }

    public int edgeCount() {
        return edgeCount;
    }
}
