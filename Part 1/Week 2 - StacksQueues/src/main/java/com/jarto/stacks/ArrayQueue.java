package com.jarto.stacks;

import java.util.Iterator;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class ArrayQueue<T> implements Queue<T> {

    private T[] items;
    private int count = 0;
    private int first = 0;

    public ArrayQueue() {
        this.items = (T[]) new Object[2];
    }

    public ArrayQueue(int capacity) {
        this.items = (T[]) new Object[capacity];
    }

    @Override
    public void enqueue(T value) {
        if (count == items.length)
            resize(items.length * 2);

        var i = (first+count)%items.length;
        count++;
        items[i] = value;
    }

    @Override
    public T dequeue() {
        if (items.length > 1 && count <= items.length / 4)
            resize(items.length / 2);

        var value = items[first];
        first = (first + 1) % items.length;
        count--;
        return value;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public T[] fetchAll() {
        T[] allItems = (T[])new Object[count];
        for (int i = 0; i < count; i++) {
            var index = (i + first) % items.length;
            allItems[i] = items[index];
        }
        return allItems;
    }

    private void resize(int newCapacity) {
        var newItems = (T[]) new Object[newCapacity];

        for (int i = 0; i < count; i++) {
            var index = (i + first) % items.length;
            newItems[i] = items[index];
        }

        first = 0;
        items = newItems;
    }
}
