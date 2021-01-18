package com.jarto.stacks;

public interface Queue<T> {

    void enqueue(T value);

    T dequeue();

    int count();

    T[] fetchAll();
}
