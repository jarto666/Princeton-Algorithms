package com.jarto.uf;

public interface UnionFind {
    /**
     * Add connection between p and q
     */
    void union(int p, int q);

    /**
     * Checks whether p and q are connected
     */
    boolean connected(int p, int q);

    /**
     * Number of components
     */
    int count();
}
