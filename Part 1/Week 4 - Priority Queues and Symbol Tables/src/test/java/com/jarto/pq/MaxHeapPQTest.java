package com.jarto.pq;

import com.jarto.pq.interfaces.MaxPriorityQueue;

class MaxHeapPQTest extends PQTestBase<MaxPriorityQueue<Integer>> {

    @Override
    protected MaxPriorityQueue createInstance() {
        return new MaxHeapPQ();
    }
}