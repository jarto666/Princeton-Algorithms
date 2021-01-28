package com.jarto.sorts;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class QuickSelectTest {

    @Test
    public void Should_Sort_When_HugeArray() {
        int n = 500;
        var a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(10, 500);
        }

        var sorted = Arrays.copyOf(a, a.length);
        Arrays.sort(sorted);

        var smallestKthElement = (Integer)QuickSelect.select(a, 100);

        assertEquals(sorted[100], smallestKthElement);
    }
}