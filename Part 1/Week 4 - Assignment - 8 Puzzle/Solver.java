import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class Solver {

    private boolean isSolvable = false;
    private final LinkedList<Board> solution;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException();

        MinPQ<Node> pq = new MinPQ<>();

        solution = new LinkedList<>();
        pq.insert(new Node(initial, null));
        pq.insert(new Node(initial.twin(), null));

        while (!pq.min().board.isGoal()) {
            Node currentNode = pq.delMin();
            for (Board b : currentNode.board.neighbors()) {
                if (currentNode.prevNode == null || !currentNode.prevNode.board.equals(b))
                    pq.insert(new Node(b, currentNode));
            }
        }

        // Build Solution steps
        Node cur = pq.min();
        while (cur != null) {
            solution.addFirst(cur.board);
            cur = cur.prevNode;
        }

        // Is Solvable
        if (solution.peekFirst().equals(initial)) {
            isSolvable = true;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return isSolvable ? solution.size() - 1 : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return isSolvable ? solution : null;
    }

    private class Node implements Comparable<Node> {
        Board board;
        Node prevNode;
        int manhattan;
        int moves;

        public Node(Board board, Node prev) {
            this.board = board;
            this.prevNode = prev;
            this.manhattan = board.manhattan();
            this.moves = prev == null ? 0 : prev.moves + 1;
        }

        @Override
        public int compareTo(Node node) {
            int diff = (this.manhattan + this.moves) - (node.manhattan + node.moves);
            if (diff != 0)
                return diff;
            else
                return this.manhattan - node.manhattan;
        }
    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In("puzzle04.txt");
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
