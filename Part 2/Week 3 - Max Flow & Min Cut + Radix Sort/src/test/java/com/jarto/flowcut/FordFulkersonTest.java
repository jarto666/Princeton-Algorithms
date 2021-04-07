package com.jarto.flowcut;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FordFulkersonTest {

    @Test
    public void Should_T() {
        var network = new FlowNetwork(8);
        network.addEdge(new FlowEdge(0, 1, 10));
        network.addEdge(new FlowEdge(0, 2, 5));
        network.addEdge(new FlowEdge(0, 3, 15));
        network.addEdge(new FlowEdge(1, 2, 4));
        network.addEdge(new FlowEdge(1, 4, 9));
        network.addEdge(new FlowEdge(1, 5, 15));
        network.addEdge(new FlowEdge(2, 3, 4));
        network.addEdge(new FlowEdge(2, 5, 8));
        network.addEdge(new FlowEdge(3, 6, 16));
        network.addEdge(new FlowEdge(4, 5, 15));
        network.addEdge(new FlowEdge(4, 7, 10));
        network.addEdge(new FlowEdge(5, 6, 15));
        network.addEdge(new FlowEdge(5, 7, 10));
        network.addEdge(new FlowEdge(6, 2, 6));
        network.addEdge(new FlowEdge(6, 7, 10));

        var ff = new FordFulkerson(network, 0, 7);
        var maxflow = ff.value();
        assertEquals(28.0d, maxflow);
    }
}