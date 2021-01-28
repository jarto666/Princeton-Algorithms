package com.jarto.sorts;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {
    @Test
    public void Should_Sort_When_HugeArray() {
        int n = 5000000;
        var a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(10000);
        }
        var expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        QuickSort.sort(a);

        assertArrayEquals(expected, a);
    }
}