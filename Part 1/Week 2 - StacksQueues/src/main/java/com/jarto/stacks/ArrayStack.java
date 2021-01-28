package com.jarto.stacks;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class ArrayStack<T> implements Stack<T> {

    private T[] items;
    private int count = 0;

    public ArrayStack() {
        this.items = (T[])new Object[4];
    }

    @Override
    public void push(T value) {
        if (count == items.length)
            resize(items.length*2);

        items[count++] = value;
    }

    @Override
    public T pop() {
        if (count < items.length / 4)
            resize(items.length/2);

        return items[--count];
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public List<T> fetchAll() {
        return Arrays.asList(items).subList(0, count);
    }

    private void resize(int newCapacity) {
        items = Arrays.copyOf(items, newCapacity);
    }
}
