package com.jarto.stacks;

// LIFO
@SuppressWarnings("unchecked")
public class LinkedListQueue<T> implements Queue<T> {

    private Node<T> first = null;
    private Node<T> last = null;
    private int count = 0;

    @Override
    public void enqueue(T value) {
        //if ()
        var newNode = new Node(value);
        var oldLast = last;
        last = newNode;
        if (count == 0)
            first = last;
        else
            oldLast.next = last;
        count++;
    }

    @Override
    public T dequeue() {
        count--;
        T value = first.value;
        first = first.next;
        return value;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public T[] fetchAll() {
        var items = (T[])new Object[count];
        var cur = first;
        for (int i = 0; i < count; i++) {
            items[i] = cur.value;
            cur = cur.next;
        }
        return items;
    }
}
