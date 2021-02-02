package com.jarto.pq;

import java.util.NoSuchElementException;

public class MaxOrderedArrayPQ<Key extends Comparable<Key>> extends MaxPriorityQueueBase<Key> {

    public MaxOrderedArrayPQ() {
        items = (Key[]) new Comparable[4];
    }

    public Key delMax() {
        if (count == 0)
            throw new NoSuchElementException();

        if (count < items.length / 4)
            resize(items.length / 2);

        return items[--count];
    }

    public void insert(Key key) {
        if (count == items.length)
            resize(items.length * 2);

        int i = 0;
        for (i = 0; i < count; i++) {
            if (items[i].compareTo(key) > 0) {
                for (int j = count; j > i; j--) {
                    items[j] = items[j - 1];
                }
                break;
            }
        }
        count++;
        items[i] = key;
    }
}
