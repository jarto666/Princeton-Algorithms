package com.jarto.stacks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListQueueTest {

    @Test
    void Should_ReturnFirstAdded_When_NotEmpty() {
        var q = new LinkedListQueue<Integer>();

        q.enqueue(2);
        q.enqueue(3);
        assertEquals(2, q.count());

        int val = q.dequeue();
        assertEquals(2, val);
    }

    @Test
    void Should_ReturnAll_When_NotEmpty() {
        var q = new LinkedListQueue<Integer>();

        q.enqueue(1);
        q.enqueue(2);
        q.dequeue();
        q.enqueue(3);
        q.enqueue(4);
        q.dequeue();
        q.enqueue(5);

        assertEquals(3, q.count());
        Object[] actual = q.fetchAll();
        assertArrayEquals(new Integer[] {3, 4, 5}, actual);
    }
}