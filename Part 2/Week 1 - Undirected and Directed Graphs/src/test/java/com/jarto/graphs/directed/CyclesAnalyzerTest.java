package com.jarto.graphs.directed;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Stack;

import static com.jarto.graphs.helpers.StackHelpers.toArray;
import static org.junit.jupiter.api.Assertions.*;

class CyclesAnalyzerTest {

    @Test
    void Should_ReturnCyclePath_When_HasCycle() {
        var analyzer = analyze("digraph6_cyclic.txt");
        Stack<Integer> cycle = (Stack<Integer>) analyzer.cycle();
        assertArrayEquals(new int[]{1, 2, 4, 3}, toArray(cycle));
    }

    @Test
    void Should_ReturnNull_When_NoCycle() {
        var analyzer = analyze("digraph6_acyclic.txt");
        Stack<Integer> cycle = (Stack<Integer>) analyzer.cycle();
        assertNull(cycle);
    }

    private CyclesAnalyzer analyze(String fileName) {
        File file = new File("src/test/resources/" + fileName);
        var digraph = new Digraph(file);
        return new CyclesAnalyzer(digraph);
    }
}