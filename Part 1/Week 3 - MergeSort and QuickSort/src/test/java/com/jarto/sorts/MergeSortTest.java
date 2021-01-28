package com.jarto.sorts;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    public void Should_Sort_When_UnevenNumberOfItems() {
        var a =         new Integer[]{6, 3, 8, 9, 1, 2, 4, 7, 5};
        var expected =  new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        MergeSort.sort(a);

        assertArrayEquals(expected, a);
    }

    @Test
    public void Should_Sort_When_EvenNumberOfItems() {
        var a =         new Integer[]{6, 3, 8, 1, 2, 4, 7, 5};
        var expected =  new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        MergeSort.sort(a);

        assertArrayEquals(expected, a);
    }

    @Test
    public void Should_Sort_When_HugeArray() {
        int n = 5000000;
        var a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(10000);
        }
        var expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        MergeSort.sort(a);

        assertArrayEquals(expected, a);
    }
}