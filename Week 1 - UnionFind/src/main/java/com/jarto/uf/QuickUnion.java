package com.jarto.uf;

import java.util.HashSet;

/**
 * Data structure
 * - Integer array id[] of length N
 * - Interpretation: id[i] is parent of i
 * - Root of i is id[id[id[...id[i]...]]]
 * - Example:
 * --- indexes: 0 1 2 3 4 5 6 7 8 9
 * --- values:  0 1 9 4 9 6 6 7 8 9
 * --- connected components are the ones with same values:
 * --- {0}, {1}, {2, 3, 4, 9}, {5, 6}, {7}, {8}
 * - Number of array accesses (worst case):
 * --- Initialize: N
 * --- Union: N
 * --- Find: N
 */
public class QuickUnion implements UnionFind {

    private int[] ids;

    public QuickUnion(int N) {
        ids = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        var rootP = root(p);
        var rootQ = root(q);
        ids[rootP] = rootQ;
    }

    @Override
    public boolean connected(int p, int q) {
        var rootP = root(p);
        var rootQ = root(q);
        return rootP == rootQ;
    }

    @Override
    public int find(int p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int count() {
        var set = new HashSet<Integer>();
        for (int i = 0; i < ids.length; i++) {
            set.add(root(i));
        }
        return set.size();
    }

    private int root(int p) {
        while(p != ids[p]) p = ids[p];
        return p;
    }
}
