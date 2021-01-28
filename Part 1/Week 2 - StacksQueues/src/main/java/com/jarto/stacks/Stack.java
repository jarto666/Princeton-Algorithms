package com.jarto.stacks;

import java.util.List;

public interface Stack<T> {

    void push(T value);

    T pop();

    int count();

    List<T> fetchAll();
}
