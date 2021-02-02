package com.jarto.pq;

import java.util.NoSuchElementException;

public class MaxUnorderedArrayPQ<Key extends Comparable<Key>> extends MaxPriorityQueueBase<Key> {

    public MaxUnorderedArrayPQ() {
        items = (Key[]) new Comparable[4];
    }

    public Key delMax() {
        if (count == 0)
            throw new NoSuchElementException();

        if (count < items.length / 4)
            resize(items.length / 2);

        int max = 0;
        for (int i = 1; i < count; i++) {
            if (items[i].compareTo(items[max]) > 0)
                max = i;
        }

        swap(max, --count);
        return items[count];
    }

    public void insert(Key key) {
        if (count == items.length)
            resize(items.length * 2);

        items[count++] = key;
    }
}
