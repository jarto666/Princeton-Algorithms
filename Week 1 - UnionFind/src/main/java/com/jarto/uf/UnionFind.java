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
     * Component identifier for p (0 to N-1)
     */
    int find(int p);

    /**
     * Number of components
     */
    int count();
}
