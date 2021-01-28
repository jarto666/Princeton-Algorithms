package com.jarto.uf;


import javax.naming.OperationNotSupportedException;

/**
 * Data structure
 * - Integer array id[] of length N
 * - Interpretation: p and q are connected if they have the same id
 * - Example:
 * --- indexes: 0 1 2 3 4 5 6
 * --- values:  0 1 1 3 4 4 4
 * --- connected components are the ones with same values:
 * --- {0}, {1, 2}, {3}, {4, 5, 6}
 * - Number of array accesses:
 * --- Initialize: N
 * --- Union: N
 * --- Find: 1
 */
public class QuickFind implements UnionFind {

    private int[] ids;

    public QuickFind(int N) {
        ids = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        var pid = ids[p];
        var qid = ids[q];
        for (int i = 0; i < ids.length; i++) {
            if (pid == ids[i])
                ids[i] = qid;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return ids[p] == ids[q];
    }

    @Override
    public int count() {
        return countDistinct(ids);
    }

    private static int countDistinct(int[] arr)
    {
        int res = 1;

        for (int i = 1; i < arr.length; i++)
        {
            int j = 0;
            for (j = 0; j < i; j++)
                if (arr[i] == arr[j])
                    break;

            if (i == j)
                res++;
        }
        return res;
    }
}
