import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;

public class Board {

    private final int[][] tiles;
    private final int size;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        size = tiles.length;

        this.tiles = new int[size][];
        for (int i = 0; i < size; i++) {
            this.tiles[i] = new int[size];
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(tiles[i][j]);
                if (j < size - 1)
                    sb.append(" ");

            }
            sb.append("\n");
        }

        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return size;
    }

    // number of tiles out of place
    public int hamming() {
        int n = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int val = tiles[i][j];
                if (val != 0 && val != i * size + j + 1)
                    n++;
            }
        }
        return n;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int n = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int val = tiles[i][j];
                if (val != 0) {
                    int expectedX = (val - 1) / size;
                    int expectedY = (val - 1) % size;
                    n += Math.abs(i - expectedX) + Math.abs(j - expectedY);
                }
            }
        }
        return n;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                int val = tiles[i][j];
                if ((val == 0 && i != size - 1 && j != size - 1)
                        || (val != 0 && val != i * size + j + 1))
                    return false;
            }

        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {

        if (this == y) return true;
        if (y == null || y.getClass() != this.getClass()) return false;

        Board board = (Board) y;

        if (this.dimension() != board.dimension())
            return false;

        return Arrays.deepEquals(tiles, board.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int eX = 0;
        int eY = 0;
        boolean eFound = false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] == 0) {
                    eX = i;
                    eY = j;
                    eFound = true;
                    break;
                }
            }
            if (eFound)
                break;
        }

        LinkedList<Board> boards = new LinkedList<>();

        // left
        if (eX != 0) {
            boards.add(new Board(swap(eX, eY, eX - 1, eY)));
        }
        // up
        if (eY != 0) {
            boards.add(new Board(swap(eX, eY, eX, eY - 1)));
        }
        // right
        if (eX != size - 1) {
            boards.add(new Board(swap(eX, eY, eX + 1, eY)));
        }
        // down
        if (eY != size - 1) {
            boards.add(new Board(swap(eX, eY, eX, eY + 1)));
        }

        return boards;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return tiles[0][0] != 0 && tiles[0][1] != 0
                ? new Board(swap(0, 0, 0, 1))
                : new Board(swap(1, 0, 1, 1));
    }

    private int[][] swap(int x1, int y1, int x2, int y2) {
        int[][] newTiles = copy();

        int t = newTiles[x1][y1];
        newTiles[x1][y1] = newTiles[x2][y2];
        newTiles[x2][y2] = t;

        return newTiles;
    }

    private int[][] copy() {
        int[][] copy = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                copy[i][j] = tiles[i][j];
            }
        }
        return copy;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] a = {
            {8, 1, 3},
            {4, 0, 2},
            {7, 6, 5}
        };

        Board b = new Board(a);

        StdOut.println(b.toString());
        StdOut.println("Is Goal: " + b.isGoal());
        StdOut.println("Hamming: " + b.hamming());
        StdOut.println("Manhattan: " + b.manhattan());

        StdOut.println(b.twin().toString());
    }

}