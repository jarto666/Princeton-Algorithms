package com.jarto.graphs.directed;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class StronglyConnectedComponentsTest {

    @Test
    void Should_ComputeCCs_When_SCCsExist() {
        var analyzer = analyze("digraph13.txt");
        assertEquals(5, analyzer.count());
        assertConnectedComponent(new int[] {1}, 0, analyzer);
        assertConnectedComponent(new int[] {2,3,4,5,0}, 1, analyzer);
        assertConnectedComponent(new int[] {10,12,9,11}, 2, analyzer);
        assertConnectedComponent(new int[] {8,6}, 3, analyzer);
        assertConnectedComponent(new int[] {7}, 4, analyzer);
    }

    @Test
    void Should_HaveVertexCountCCs_When_Acyclic() {
        var analyzer = analyze("digraph7_acyclic.txt");
        assertEquals(7, analyzer.count());
    }

    private void assertConnectedComponent(int[] set, int ccId, StronglyConnectedComponents scc) {
        for (int i = 0; i < set.length; i++) {
            for (int j = i+1; j < set.length; j++) {
                assertTrue(scc.areConnected(set[i], set[j]));
            }
            assertEquals(ccId, scc.ccId(set[i]));
        }
    }

    private StronglyConnectedComponents analyze(String fileName) {
        File file = new File("src/test/resources/" + fileName);
        var digraph = new Digraph(file);
        return new StronglyConnectedComponents(digraph);
    }
}