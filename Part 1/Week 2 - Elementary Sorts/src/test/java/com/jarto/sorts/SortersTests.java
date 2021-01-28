package com.jarto.sorts;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.*;

import static org.junit.jupiter.api.Assertions.*;

class SortersTests {

    @Test
    public void SelectionSorter_SortsCorrectly() {
        Integer[] array = new Integer[]{5, 2, 6, 8, 3, 1, 7, 8};
        SelectionSorter.sort(array);

        assertArrayEquals(new Integer[]{1, 2, 3, 5, 6, 7, 8, 8}, array);
    }

    @Test
    public void InsertionSorter_SortsCorrectly() {
        Integer[] array = new Integer[]{5, 2, 6, 8, 3, 1, 7, 8};
        InsertionSorter.sort(array);

        assertArrayEquals(new Integer[]{1, 2, 3, 5, 6, 7, 8, 8}, array);
    }

    @Test
    public void ShellSorter_SortsCorrectly() {
        Integer[] array = new Integer[]{5, 2, 6, 8, 3, 1, 7, 8};
        ShellSorter.sort(array);

        assertArrayEquals(new Integer[]{1, 2, 3, 5, 6, 7, 8, 8}, array);
    }

    @Test
    public void PerfomanceTest_RandomArray_LowNumberOfItems() {

        var n = 500;
        out.printf("Sorting performance testing Low number of items: %s. Random array \n", n);

        var a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(10000);
        }

        MeasureSortsAndLog(a);
    }

    @Test
    public void PerfomanceTest_RandomArray_HighNumberOfItems() {

        var n = 50000;
        out.printf("Sorting performance testing High number of items: %s. Random array \n", n);

        var a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(10000);
        }

        MeasureSortsAndLog(a);
    }

    @Test
    public void PerfomanceTest_ArrayInDescendingOrder_LowNumberOfItems() {

        var n = 500;
        out.printf("Sorting performance testing Low number of items: %s. Array in Descending order \n", n);

        var a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = n-i;
        }

        MeasureSortsAndLog(a);
    }

    @Test
    public void PerfomanceTest_ArrayInDescendingOrder_HighNumberOfItems() {

        var n = 50000;
        out.printf("Sorting performance testing High number of items: %s. Array in Descending order \n", n);

        var a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = n-i;
        }

        MeasureSortsAndLog(a);
    }

    @Test
    public void PerfomanceTest_SortedArray_LowNumberOfItems() {

        var n = 500;
        out.printf("Sorting performance testing Low number of items: %s. Array in Sorted \n", n);

        var a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }

        MeasureSortsAndLog(a);
    }

    @Test
    public void PerfomanceTest_SortedArray_HighNumberOfItems() {

        var n = 50000;
        out.printf("Sorting performance testing High number of items: %s. Array in Sorted \n", n);

        var a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }

        MeasureSortsAndLog(a);
    }

    private void MeasureSortsAndLog(int[] array) {

        var insertionMillis = Measure(array, InsertionSorter::sort);
        var selectionMillis = Measure(array, SelectionSorter::sort);
        var shellMillis = Measure(array, ShellSorter::sort);

        // Log
        out.printf("Insertion Sort time: %s\n", insertionMillis);
        out.printf("Selection Sort time: %s\n", selectionMillis);
        out.printf("Shell Sort time: %s\n", shellMillis);
    }

    private Integer[] CloneAndBoxArray(int[] array) {
        return Arrays.stream(array.clone()).boxed().toArray(Integer[]::new);
    }

    private Double Measure(int[] a, SortAction sortAction) {

        var measuresCount = 2;
        var measures = new ArrayList<Long>();
        for (int i = 0; i < measuresCount; i++) {
            // Measure
            Instant starts = Instant.now();
            var boxedArray = CloneAndBoxArray(a);
            sortAction.sort(boxedArray);
            Instant ends = Instant.now();

            // Assert
            var expectedArray = boxedArray.clone();
            Arrays.sort(expectedArray);
            assertArrayEquals(expectedArray, boxedArray);

            measures.add(Duration.between(starts, ends).toMillis());
        }

        return measures.stream().mapToDouble(x -> x).average().getAsDouble();
    }

    interface SortAction {
        void sort(Integer[] a);
    }
}