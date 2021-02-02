package com.jarto.pq;

public class HeapSort {

    public static void sort(Comparable[] a) {
        int count = a.length;
        for (int i = count / 2 - 1; i >= 0; i--)
            sink(a, i, count);

        for (int i = count - 1; i >= 0; i--) {
            swap(a, 0, i);
            sink(a, 0, i);
        }
    }

    private static void sink(Comparable[] a, int i, int n) {
        int largest = i;
        int l = left(i);
        int r = right(i);

        if (l < n && isGreater(a, l, largest))
            largest = l;

        if (r < n && isGreater(a, r, largest))
            largest = r;

        if (largest != i) {
            swap(a, i, largest);
            sink(a, largest, n);
        }
    }

    private static boolean isGreater(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) > 0;
    }

    private static int left(int i) {
        return 2 * i + 1;
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
