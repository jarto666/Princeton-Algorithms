package com.jarto.stacks;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListStackTest {
    @Test
    void Should_ReturnFirstAdded_When_NotEmpty() {
        var q = new LinkedListStack<Integer>();

        q.push(2);
        q.push(3);
        assertEquals(2, q.count());

        int val = q.pop();
        assertEquals(3, val);
    }

    @Test
    void Should_ReturnAll_When_NotEmpty() {
        var q = new LinkedListStack<Integer>();

        q.push(1);
        q.push(2);
        q.pop();
        q.push(3);
        q.push(4);
        q.pop();
        q.push(5);

        assertEquals(3, q.count());
        var actual = q.fetchAll();
        assertEquals(List.of(1, 3, 5), actual);
    }
}