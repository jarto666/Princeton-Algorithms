package com.jarto.graphs.undirected;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class PathAnalyzerTestBase<T extends PathAnalyzer> {
    protected T analyzer;

    protected abstract T createInstance();

    protected static Graph graph;

    @BeforeAll
    static void init() {
        File file = new File("src/test/resources/museum_edges.txt");
        graph = new Graph(file);
    }

    @BeforeEach
    public void setUp() {
        analyzer = createInstance();
    }

    @Test
    void Should_HavePath_When_PathExists() {

        assertTrue(analyzer.hasPathTo(9));
    }

    @Test
    void Should_NotHavePath_When_PathNotExists() {
        assertFalse(analyzer.hasPathTo(17));
    }

    @Test
    void Should_ReturnNull_When_PathNotExists() {
        var path = analyzer.pathTo(17);
        assertNull(path);
    }

    protected static ArrayList<Integer> toArrayList(Iterable<Integer> iter) {
        var list = new ArrayList<Integer>();
        for (Integer item : iter) {
            list.add(item);
        }
        Collections.reverse(list);
        return list;
    }
}
