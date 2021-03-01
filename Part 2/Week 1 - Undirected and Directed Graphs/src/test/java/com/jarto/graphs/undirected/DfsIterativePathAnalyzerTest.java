package com.jarto.graphs.undirected;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DfsIterativePathAnalyzerTest extends PathAnalyzerTestBase {

    @Override
    protected PathAnalyzer createInstance() {
        return new DfsIterativePathAnalyzer(graph, 0);
    }

    @Test
    void Should_ReturnPath_When_PathExists() {
        var path = analyzer.pathTo(9);
        var pathList = toArrayList(path);
        assertEquals(Arrays.asList(0, 6, 12, 13, 7, 8, 14, 15, 9), pathList);
    }
}