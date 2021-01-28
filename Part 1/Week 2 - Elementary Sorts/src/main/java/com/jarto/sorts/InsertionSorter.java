package com.jarto.sorts;

/**
 * Insertion sort
 * For ALREADY SORTED array
 * N-1 compares; 0 swaps
 * For SORTED DESCENDING array
 * N^2 compares; N^2 swaps
 */
public class InsertionSorter {
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j].compareTo(a[j-1]) < 0) {
                    swap(a, j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable el = a[i];
        a[i] = a[j];
        a[j] = el;
    }
}
