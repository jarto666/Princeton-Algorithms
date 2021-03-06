package com.jarto.graphs.directed;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.jarto.graphs.helpers.StackHelpers.toArray;

import static org.junit.jupiter.api.Assertions.*;

class TopoSortTest {

    @Test
    void Should_ReturnInSortedOrder_When_AcyclicGraph() {
        var topoSort = topoSort("digraph7_acyclic.txt");
        var sorted = toArray(topoSort.order());

        assertArrayEquals(new int[] {3, 6, 0, 1, 4, 5, 2}, sorted);
    }

    private TopoSort topoSort(String fileName) {
        File file = new File("src/test/resources/" + fileName);
        var digraph = new Digraph(file);
        return new TopoSort(digraph);
    }
}