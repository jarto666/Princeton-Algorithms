package com.jarto.sorts;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SortHelpers {

    // Fisher–Yates shuffle
    public static void shuffleArray(Comparable[] a)
    {
        int n = a.length;
        Random rnd = ThreadLocalRandom.current();
        rnd.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + rnd.nextInt(n - i);
            swap(a, i, change);
        }
    }

    public static void insertionSort(Comparable[] a) {
        insertionSort(a, 0, a.length-1);
    }

    public static void insertionSort(Comparable[] a, int left, int right) {
        for (int i = left; i <= right; i++) {
            for (int j = i; j > left; j--) {
                if (a[j].compareTo(a[j - 1]) < 0) {
                    swap(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static void swap(Comparable[] a, int i, int j) {
        Comparable el = a[i];
        a[i] = a[j];
        a[j] = el;
    }
}
