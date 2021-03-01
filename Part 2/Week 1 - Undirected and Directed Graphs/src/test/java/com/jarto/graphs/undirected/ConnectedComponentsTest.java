package com.jarto.graphs.undirected;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ConnectedComponentsTest {

    protected static Graph graph;

    @BeforeAll
    static void init() {
        File file = new File("src/test/resources/museum_edges.txt");
        graph = new Graph(file);
    }

    @Test
    void Should_KeepCountOfCCs() {
        var cc = new ConnectedComponents(graph);
        assertEquals(2, cc.count());
    }

    @Test
    void Should_CheckConnected_When_SameCC() {
        var cc = new ConnectedComponents(graph);
        assertTrue(cc.connected(6, 9));
    }

    @Test
    void Should_CheckUnconnected_When_DifferentCC() {
        var cc = new ConnectedComponents(graph);
        assertFalse(cc.connected(6, 11));
    }
}