package com.jarto.sp;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BellmanFordShortestPathTest {
    @Test
    void Should_FindSP_When_DirectedConnectedGraph() {
        File file = new File("src/test/resources/wdirected8.txt");
        var graph = new EdgeWeightedDirectedGraph(file);
        var sp = new BellmanFordShortestPath(graph, 0);

        var path = sp.pathTo(6);
        for (var p : path)
            System.out.println(p);
        assertIterableEquals(Arrays.asList(6, 2, 5, 4, 0), path);
        assertEquals(25, sp.distTo(6), 0.1);
    }
}