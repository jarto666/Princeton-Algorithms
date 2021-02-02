package com.jarto.pq;

import com.jarto.pq.interfaces.MaxPriorityQueue;

class MaxOrderedArrayPQTest extends PQTestBase<MaxPriorityQueue<Integer>> {

    @Override
    protected MaxPriorityQueue createInstance() {
        return new MaxOrderedArrayPQ();
    }
}