package com.jarto.mst;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrimLazyMSTTest {
    private List<String> expectedMst = Arrays.asList(
            "0 - 1",
            "0 - 7",
            "6 - 7",
            "5 - 6",
            "2 - 5",
            "2 - 8",
            "2 - 3",
            "3 - 4"
    );

    @Test
    void Should_FindMST_When_ConnectedGraph() {
        File file = new File("src/test/resources/weighted9.txt");
        var graph = new EdgeWeightedGraph(file);
        var mst = new PrimLazyMST(graph);

        var result = new ArrayList<String>();
        mst.edges().forEach(e ->
                result.add(String.format("%s - %s", e.either(), e.other(e.either()))));

        assertEquals(3.7, mst.weight(), 0.1);
        assertIterableEquals(expectedMst, result);
    }
}