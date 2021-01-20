package com.jarto.sorts;

/**
 * Selection sort
 * O(N^2) compares
 * O(N) swaps
 */
public class SelectionSorter {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[min]) < 0) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable el = a[i];
        a[i] = a[j];
        a[j] = el;
    }
}
