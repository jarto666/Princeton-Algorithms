package com.jarto.pq;

import com.jarto.pq.interfaces.MaxPriorityQueue;

import java.util.Arrays;

public abstract class MaxPriorityQueueBase<Key extends Comparable<Key>> implements MaxPriorityQueue<Key> {
    Key[] items;
    int count = 0;

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    void resize(int newCapacity) {
        items = Arrays.copyOf(items, newCapacity);
    }

    void swap(int i, int j) {
        Key t = items[i];
        items[i] = items[j];
        items[j] = t;
    }
}
