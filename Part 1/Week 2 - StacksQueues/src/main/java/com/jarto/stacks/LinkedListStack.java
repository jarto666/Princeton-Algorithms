package com.jarto.stacks;

import java.util.ArrayList;
import java.util.List;

public class LinkedListStack<T> implements Stack<T> {

    private Node<T> head;
    private int count = 0;

    @Override
    public void push(T value) {
        var newNode = new Node<T>(value);
        newNode.next = head;
        head = newNode;
        count++;
    }

    @Override
    public T pop() {
        var val = head.value;
        head = head.next;
        count--;
        return val;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public List<T> fetchAll() {
        var items = new ArrayList<T>(count);
        var cur = head;
        for (int i = 0; i < count; i++) {
            items.add(0, cur.value);
            cur = cur.next;
        }
        return items;
    }
}
