package com.jarto.sorts;

import static com.jarto.sorts.SortHelpers.*;

public class QuickSort {

    public static final int CUTOFF = 7;

    public static void sort(Comparable[] a) {
        shuffleArray(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {

        if (hi <= lo + CUTOFF - 1) {
            insertionSort(a, lo, hi);
            return;
        }

        medianOf3(a, lo, hi);

        if (hi <= lo) return;
        int k = partition(a, lo, hi);
        sort(a, lo, k - 1);
        sort(a, k + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while (true) {

            while (a[++i].compareTo(a[lo]) < 0)
                if (i == hi) break;

            while (a[lo].compareTo(a[--j]) < 0)
                if (j == lo) break;

            if (i >= j) break;
            swap(a, i, j);
        }

        swap(a, lo, j);
        return j;
    }

    private static void medianOf3(Comparable[] a, int lo, int hi) {
        int center = (lo + hi)/2;

        if (a[lo].compareTo(a[center]) > 0)
            swap(a, lo, center);

        if (a[lo].compareTo(a[hi]) > 0)
            swap(a, lo, hi);

        if (a[center].compareTo(a[hi]) > 0)
            swap(a, center, hi);

        swap(a, lo, center);
    }
}
