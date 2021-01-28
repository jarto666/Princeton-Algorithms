/******************************************************************************
 *  Name:              Ilya Livshits
 *  Coursera User ID:
 *  Last modified:     16/1/2021
 *****************************************************************************/

/**
 * Percolation. Given a composite systems comprised of randomly distributed
 * insulating and metallic materials: what fraction of the materials
 * need to be metallic so that the composite system is an electrical conductor?
 * Given a porous landscape with water on the surface (or oil below),
 * under what conditions will the water be able to drain through
 * to the bottom (or the oil to gush through to the surface)?
 * Scientists have defined an abstract process known as percolation to model such situations.
 *
 * TASK AND EXPLANATION: https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final byte CLOSED = 0;
    private static final byte OPENED = 1;
    private static final byte CONNECTED_TO_TOP = 2;
    private static final byte CONNECTED_TO_BOTTOM = 4;

    private final int gridSize;
    private int numberOfOpenSites = 0;
    private byte[] sites;
    private boolean doesPercolate = false;
    private WeightedQuickUnionUF unionFind;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();

        gridSize = n;
        sites = new byte[n * n];
        unionFind = new WeightedQuickUnionUF(n * n);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateCoordinate(row, col);

        if (isOpen(row, col)) {
            return;
        }

        // open
        numberOfOpenSites++;
        int ufIndex = getUfIndex(row, col);
        if (gridSize == 1) {
            doesPercolate = true;
            sites[ufIndex] = OPENED | CONNECTED_TO_BOTTOM | CONNECTED_TO_TOP;
            return;
        }

        sites[ufIndex] = OPENED;
        int rootPrev = unionFind.find(ufIndex);
        if (row == 1)
            sites[rootPrev] |= CONNECTED_TO_TOP;
        else if (row == gridSize)
            sites[rootPrev] |= CONNECTED_TO_BOTTOM;

        byte[] states = new byte[5];

        // top
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            int rootAdj = unionFind.find(getUfIndex(row - 1, col));
            unionFind.union(ufIndex, ufIndex - gridSize);
            states[0] = sites[rootAdj];
        }
        // bottom
        if (row + 1 <= gridSize && isOpen(row + 1, col)) {
            int rootAdj = unionFind.find(getUfIndex(row + 1, col));
            unionFind.union(ufIndex, ufIndex + gridSize);
            states[1] = sites[rootAdj];
        }
        // left
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            int rootAdj = unionFind.find(getUfIndex(row, col - 1));
            unionFind.union(ufIndex, ufIndex - 1);
            states[2] = sites[rootAdj];
        }
        // right
        if (col + 1 <= gridSize && isOpen(row, col + 1)) {
            int rootAdj = unionFind.find(getUfIndex(row, col + 1));
            unionFind.union(ufIndex, ufIndex + 1);
            states[3] = sites[rootAdj];
        }

        states[4] = sites[rootPrev];
        int rootNew = unionFind.find(ufIndex);
        for (byte state : states) {
            sites[rootNew] |= state;
        }
        byte siteState = sites[rootNew];
        if ((siteState & CONNECTED_TO_BOTTOM) == CONNECTED_TO_BOTTOM
                && (siteState & CONNECTED_TO_TOP) == CONNECTED_TO_TOP)
            doesPercolate = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateCoordinate(row, col);
        int ufIndex = getUfIndex(row, col);
        return sites[ufIndex] != CLOSED;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateCoordinate(row, col);
        int ufIndex = getUfIndex(row, col);
        return (sites[unionFind.find(ufIndex)] & CONNECTED_TO_TOP) == CONNECTED_TO_TOP;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return doesPercolate;
    }

    private boolean numberInBounds(int x, int min, int max) {
        return x >= min && x <= max;
    }

    private void validateCoordinate(int row, int col) {
        if (!numberInBounds(row, 1, gridSize)
                || !numberInBounds(col, 1, gridSize))
            throw new IllegalArgumentException();
    }

    private int getUfIndex(int row, int col) {
        return (row - 1) * gridSize + (col - 1);
    }
}
