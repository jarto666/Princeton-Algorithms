package com.jarto.uf;

import java.util.HashSet;

/**
 * Same Quick-Union Algorithm, but
 * optimized with Weighting and Path Compression
 * - Weighted QU means that merging trees always track their size
 *   in an additional array. When merging roots,
 *   the root with smaller tree
 *   becomes parent of the root with bigger tree
 * - Path Compression
 *   - Two-pass implementation: add second loop to root() to set the id[]
 *   of each examined node to the root. - I used this one
 *   - Simpler one-pass variant: Make every other node in path point to its
 *   grandparent (thereby halving path length).
 */
public class QuickUnionOptimized implements UnionFind {

    private int[] ids;
    private int[] sizes;

    public QuickUnionOptimized(int N) {
        sizes = new int[N];
        ids = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        var rootP = root(p);
        var rootQ = root(q);
        int node;
        int mainRoot;
        // Weighted union
        if (sizes[rootP] < sizes[rootQ]) {
            ids[rootP] = rootQ;
            sizes[rootQ] += sizes[rootP];
            node = p;
            mainRoot = rootQ;
        } else {
            ids[rootQ] = rootP;
            sizes[rootP] += sizes[rootQ];
            node = q;
            mainRoot = rootP;
        }
        // Path compression
        while (node != ids[node]) {
            ids[node] = mainRoot;
            node = ids[node];
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    @Override
    public int count() {
        var set = new HashSet<Integer>();
        for (int i = 0; i < ids.length; i++) {
            set.add(root(i));
        }
        return set.size();
    }

    public int root(int p) {
        while(p != ids[p]) p = ids[p];
        return p;
    }
}
