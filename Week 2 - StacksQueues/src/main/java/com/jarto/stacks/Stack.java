package com.jarto.stacks;

public interface Stack<T> {

    void push(T value);

    T pop();

    int count();
}
