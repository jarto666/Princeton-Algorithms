package com.jarto.stacks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {

    @Test
    void Should_ReturnFirstAdded_When_CapacityIsEnough() {
        var q = new ArrayQueue<Integer>();

        q.enqueue(2);
        q.enqueue(3);
        assertEquals(2, q.count());

        int val = q.dequeue();
        assertEquals(2, val);
    }

    @Test
    void Should_ResizeArrayUpCorrectly_When_Overflow() {
        var q = new ArrayQueue<Integer>(2);

        q.enqueue(2);
        q.enqueue(3);
        q.dequeue();
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);

        assertEquals(4, q.count());
        Object[] actual = q.fetchAll();
        assertArrayEquals(new Integer[] {3, 4, 5, 6}, actual);
    }

    @Test
    void Should_ResizeArrayDownCorrectly_When_LessThanQuarter() {
        var q = new ArrayQueue<Integer>(2);

        for (int i = 0; i < 16; i++) {
            q.enqueue(i);
        }
        for (int i = 0; i < 13; i++) {
            q.dequeue();
        }

        assertEquals(3, q.count());
        Object[] actual = q.fetchAll();
        assertArrayEquals(new Integer[] {13, 14, 15}, actual);
    }
}