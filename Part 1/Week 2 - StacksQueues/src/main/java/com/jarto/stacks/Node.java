package com.jarto.stacks;

public class Node<T> {

    public Node(T value) {
        this.value = value;
    }

    public Node next;
    public T value;
}
