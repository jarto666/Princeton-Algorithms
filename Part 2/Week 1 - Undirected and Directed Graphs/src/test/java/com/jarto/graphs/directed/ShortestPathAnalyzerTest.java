package com.jarto.graphs.directed;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Stack;

import static com.jarto.graphs.helpers.StackHelpers.toArray;
import static org.junit.jupiter.api.Assertions.*;

class ShortestPathAnalyzerTest {

    private static Digraph digraph;
    private static ShortestPathAnalyzer analyzer;

    @BeforeAll
    static void setup() {
        File file = new File("src/test/resources/digraph13.txt");
        digraph = new Digraph(file);
        analyzer = new ShortestPathAnalyzer(digraph, 0);
    }

    @Test
    void Should_ReturnNull_When_NoPath() {
        var path = analyzer.shortestPathTo(12);
        assertNull(path);
    }

    @Test
    void Should_ReturnShortestPath_When_HasPath() {
        Stack<Integer> path = (Stack<Integer>) analyzer.shortestPathTo(2);
        assertArrayEquals(new int[]{0, 5, 4, 2}, toArray(path));
    }
}