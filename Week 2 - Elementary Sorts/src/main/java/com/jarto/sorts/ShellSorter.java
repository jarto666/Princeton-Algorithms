package com.jarto.sorts;

/**
 * Shell sort
 * Worst-case compares (O(N^(3/2)))
 * Fast unless array is huge
 */
public class ShellSorter {
    public static void sort(Comparable[] a) {
        int h = 1;
        while (h < a.length/3) h = 3 * h + 1;

        while (h > 0) {
            for(int i = h; i < a.length; i++) {
                for (int j = i; j >= h && a[j].compareTo(a[j-h]) < 0; j-=h) {
                    swap(a, j, j-h);
                }
            }

            h /= 3;
        }
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable el = a[i];
        a[i] = a[j];
        a[j] = el;
    }
}