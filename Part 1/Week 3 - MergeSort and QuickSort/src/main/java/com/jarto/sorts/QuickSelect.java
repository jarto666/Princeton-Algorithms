package com.jarto.sorts;

import static com.jarto.sorts.SortHelpers.shuffleArray;
import static com.jarto.sorts.SortHelpers.swap;

/**
 * Goal. Given an array of N items, find a kth smallest item.
 * Ex. Min (k = 0), max (k = N - 1), median (k = N/ 2).
 */
public class QuickSelect {
    public static Comparable select(Comparable[] a, int k)
    {
        shuffleArray(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo)
        {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[k];
        }
        return a[k];
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
}
