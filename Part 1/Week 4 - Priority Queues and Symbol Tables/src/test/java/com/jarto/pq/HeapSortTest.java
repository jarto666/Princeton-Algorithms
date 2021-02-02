package com.jarto.pq;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

    @Test
    public void Should_SortInDescendingOrder() {
        Integer[] a = new Integer[]{1, 9, 2, 8, 3, 7, 4, 6, 5, 10};
        Integer[] expected = a.clone();

        Arrays.sort(expected);
        HeapSort.sort(a);

        assertArrayEquals(expected, a);
    }
}