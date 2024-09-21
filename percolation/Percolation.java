
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Arrays;

public class Percolation {

    // private static string[][] board = new string[N][N];
    private final boolean[][] board;
    private final int n;
    private WeightedQuickUnionUF uf;
    // private QuickFindUF uf;
    private final int topVirtual;
    private final int bottomVirtual;
    private int num_open;

    public Percolation(int N) {

        if (N <= 0) {
            throw new IllegalArgumentException("No negative numbers for grid size");
        }

        n = N;
        // create N-by-N grid, with all sites initially blocked
        board = new boolean[n][n];

        uf = new WeightedQuickUnionUF(n * n + 2); // includes two virtual sites
        // uf = new QuickFindUF(n * n + 2);

        topVirtual = n * n; // virtual top site
        bottomVirtual = n * n + 1; // virtual bottom site
        num_open = 0;

        // connects virtual top site to all sites in top row
        for (int col = 0; col < n; col++) {
            uf.union(topVirtual, getIndex(0, col));
        }

        // connects virtual bottom to all sites in bottom row
        for (int col = 0; col < n; col++) {
            uf.union(bottomVirtual, getIndex(n - 1, col));
        }

    }

    // converts (row, col) to 1D indec in UF array
    private int getIndex(int row, int col) {
        return row * n + col;
    }

    public void print_board() {
        System.out.println(Arrays.deepToString(board));
    }

    // check neighbors to see if open (4 surrounding) helper function
    // check if off board/ out of index
    private void check_neighbors(int row, int col) {
        // cheack surrounding
        int current = getIndex(row, col);

        // make int[][] of 4 directions
        // top, right, bottom, left (cw)
        int[][] choices = {
                { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }
        };
        // loop through directions and create new intRow/intCol
        int newRow;
        int newCol;

        // moving if into for loop
        for (int[] elem : choices) {
            newRow = row + elem[0];
            newCol = col + elem[1];

            // int neighbor = getIndex(newRow, newCol);
            // connect old to new with UF (root)
            // check if its within bounds && isOpen
            if ((0 <= newRow && newRow < n) && (0 <= newCol && newCol < n) && (isOpen(newRow, newCol))) {
                int neighbor = getIndex(newRow, newCol);
                uf.union(current, neighbor);
            }
        }
        // uf.union(current, getIndex(new row, new col) )

    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        // if invalid row or colum throw error but dont do anything
        // board[row][col] = true;
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException("Index Out Of Bounds :(");
        }

        if (!isOpen(row, col)) {
            board[row][col] = true;
            check_neighbors(row, col);
            num_open++;
        }
    }

    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        // use check_neighbors function to see if neighbors are connected/ open
        if ((0 <= row && row < n) && (0 <= col && col < n)) {
            return board[row][col];
        }
        return false; // not open
    }

    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        // checks is there is path back to top
        // compare top node
        // run uf on top mode and cmopare result to uf of current node return true
        // extra top = N * N (same for bottom)
        // len of 1D is N * N + 2
        // find takes index of array 1D

        int top = n * n;
        int current = getIndex(row, col);
        if (uf.find(top) == uf.find(current)) {
            return true;
        }

        return false;
    }

    public int numberOfOpenSites() {
        // number of open sites
        num_open = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (isOpen(row, col)) {
                    num_open += 1;
                }
            }
        }
        return num_open;
    }

    public boolean percolates() {
        // does the system percolate?
        // compare find of top and bottom

        int top = n * n;
        int bottom = n * n + 1;
        if (((uf.find(top))) == (uf.find(bottom))) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // unit testing (suggested)
        // test running time for quickfind and weightedquickfind
    }

}
// 🎃
