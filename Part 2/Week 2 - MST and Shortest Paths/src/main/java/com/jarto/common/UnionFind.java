package com.jarto.common;

public class UnionFind {

    private int[] ids;
    private int[] sizes;

    public UnionFind(int n) {
        ids = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB)
            return;

        if (sizes[rootA] > sizes[rootB]) {
            ids[rootB] = rootA;
            sizes[rootA] += sizes[rootB];
        } else {
            ids[rootA] = rootB;
            sizes[rootB] += sizes[rootA];
        }
    }

    public int find(int i) {
        while (ids[i] != i)
            i = ids[i];

        return i;
    }

    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }
}
