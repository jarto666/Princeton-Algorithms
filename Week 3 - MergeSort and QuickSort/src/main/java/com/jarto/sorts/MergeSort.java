package com.jarto.sorts;

import java.util.Arrays;

public class MergeSort {

    public static final int CUTOFF = 7;

    public static void sort(Comparable[] a) {
        Comparable[] aux = Arrays.copyOf(a, a.length);

        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int left, int right) {

        // Improvement: Little arrays we can sort using insertion
        if (right - left < CUTOFF) {
            insertionSort(a, left, right);
        }

        if (right <= left) return;
        int mid = left + (right - left) / 2;
        sort(a, aux, left, mid);
        sort(a, aux, mid + 1, right);
        // Improvement: If greatest element of left array is less than lowest element of right - return;
        if (a[mid].compareTo(a[mid + 1]) < 0) return;
        merge(a, aux, left, mid, right);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int left, int mid, int right) {

        for (int i = left; i <= right; i++)
            aux[i] = a[i];

        int i = left;
        int j = mid + 1;
        for (int h = left; h <= right; h++) {
            if (i > mid) a[h] = aux[j++];
            else if (j > right) a[h] = aux[i++];
            else if (aux[i].compareTo(aux[j]) <= 0) a[h] = aux[i++];
            else a[h] = aux[j++];
        }
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

    private static void swap(Comparable[] a, int i, int j) {
        Comparable el = a[i];
        a[i] = a[j];
        a[j] = el;
    }
}
