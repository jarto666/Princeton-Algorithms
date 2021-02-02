package com.jarto.pq.interfaces;

public interface PriorityQueue<Key extends Comparable<Key>> {
    void insert(Key key);

    boolean isEmpty();

    int size();
}
