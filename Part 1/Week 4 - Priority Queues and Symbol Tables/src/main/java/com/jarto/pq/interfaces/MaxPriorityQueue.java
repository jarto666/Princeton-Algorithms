package com.jarto.pq.interfaces;

public interface MaxPriorityQueue<Key extends Comparable<Key>> extends PriorityQueue<Key> {
    Key delMax();
}
