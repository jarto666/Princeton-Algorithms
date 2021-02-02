package com.jarto.pq;

import java.util.NoSuchElementException;

public class MaxHeapPQ<Key extends Comparable<Key>> extends MaxPriorityQueueBase<Key> {

    public MaxHeapPQ() {
        items = (Key[]) new Comparable[4];
    }

    @Override
    public Key delMax() {
        if (count == 0)
            throw new NoSuchElementException();

        if (count < items.length / 4)
            resize(items.length / 2);

        var max = items[0];
        swap(0, --count);
        items[count] = null;
        sink(0);

        return max;
    }

    @Override
    public void insert(Key key) {
        if (count == items.length)
            resize(items.length * 2);

        items[count] = key;
        swim(count++);
    }

    private void swim(int i) {
        var p = parent(i);

        if (i == p || isGreater(p, i))
            return;

        swap(i, p);
        swim(p);
    }

    private void sink(int i) {

        int largest = i;
        int l = left(i);
        int r = right(i);

        if (l < count && isGreater(l, largest))
            largest = l;

        if (r < count && isGreater(r, largest))
            largest = r;

        if (largest != i) {
            swap(i, largest);
            sink(largest);
        }
    }

    private boolean isGreater(int i, int j) {
        return items[i].compareTo(items[j]) > 0;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }
}
