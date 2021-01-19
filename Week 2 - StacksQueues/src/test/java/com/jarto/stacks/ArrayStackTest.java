package com.jarto.stacks;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {
    @Test
    void Should_ReturnLastAdded_When_CapacityIsEnough() {
        var q = new ArrayStack<Integer>();

        q.push(2);
        q.push(3);
        assertEquals(2, q.count());

        int val = q.pop();
        assertEquals(3, val);
    }

    @Test
    void Should_ResizeArrayUpCorrectly_When_Overflow() {
        var q = new ArrayStack<Integer>();

        q.push(2);
        q.push(3);
        q.pop();
        q.push(4);
        q.push(5);
        q.push(6);

        assertEquals(4, q.count());
        var actual = q.fetchAll();
        assertEquals(List.of(2, 4, 5, 6), actual);
    }

    @Test
    void Should_ResizeArrayDownCorrectly_When_LessThanQuarter() {
        var q = new ArrayStack<Integer>();

        for (int i = 0; i < 16; i++) {
            q.push(i);
        }
        for (int i = 0; i < 13; i++) {
            q.pop();
        }

        assertEquals(3, q.count());
        var actual = q.fetchAll();
        assertEquals(List.of(0, 1, 2), actual);
    }
}