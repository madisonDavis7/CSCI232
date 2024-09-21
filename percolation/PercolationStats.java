
/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Last modified:     October 16, 1842
 **************************************************************************** */
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    int num_trials;
    double[] trial_data;
    int N;
    int T;

    public PercolationStats(int N, int T) {
        // perform T independent experiments on an N-by-N grid
        this.N = N;
        this.T = T;
        // checks if board size is valid
        boolean board_too_small = N <= 0;

        // checks if trial size is valid
        boolean trials_too_small = T <= 0;

        if (board_too_small || trials_too_small) {
            throw new IllegalArgumentException("Board Size or Number of Trials Too Small");
        }

        this.trial_data = new double[this.T];

        // i is the trials (goes from 0 to T-1)
        for (int i = 0; i < this.T; i++) {
            Percolation p = new Percolation(this.N);

            // runs until connected top and bottom (randomly opens sites until it
            // percolates)
            while (!p.percolates()) {
                // randomly opens a spot at (row, col)
                int row = StdRandom.uniform(0, N); // this.N
                int col = StdRandom.uniform(0, N); // this.N
                p.open(row, col);

            }

            // number of sites that had to be open in order for it to percolate
            double num_open = p.numberOfOpenSites();
            // ratio of open to total (how much of board was opened)
            double total_sites = this.N * this.N;
            double opened = num_open / total_sites;

            // open sites needed is stored in an array at index correspodning to which
            // trial/i it was
            this.trial_data[i] = opened;
        }
    }

    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(this.trial_data);

    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return StdStats.stddev(this.trial_data);

    }

    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        // mu is mean, sigma is stddev
        double low = this.mean() - ((1.96 * this.stddev()) / (Math.sqrt(this.T)));
        return low;
    }

    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        double high = this.mean() + ((1.96 * this.stddev()) / (Math.sqrt(this.T)));
        return high;
    }

    // unit tests with stopwatch
    public static void unit_test(int N, int T) {

        // new Stopwatch object to get time
        Stopwatch s_watch = new Stopwatch();

        PercolationStats ps = new PercolationStats(N, T);

        double time = s_watch.elapsedTime();

        System.out.println("Test with N: " + N + " and T: " + T);
        System.out.println("mean(): " + ps.mean());
        System.out.println("stddev(): " + ps.stddev());
        System.out.println("confidenceLow(): " + ps.confidenceLow());
        System.out.println("confidenceHigh(): " + ps.confidenceHigh());
        System.out.println("time elapsed in seconds: " + time);
        System.out.println();
    }

    // board size is doubled each time (num_d is number of times board is doubled)
    public static void double_timeN(int N, int T, int num_d) {
        int new_N = N; // starting value for N

        // runs num_d times
        for (int i = 1; i <= num_d; i++) {
            unit_test(new_N, T); // runs test with new time
            new_N = new_N * 2; // doubles board each
        }
    }

    // number of trials is doubled
    public static void double_timeT(int N, int T, int num_d) {
        int new_T = T; // starting value for T (num of trials)

        // runs num_d times
        for (int i = 1; i <= num_d; i++) {
            unit_test(N, new_T);
            new_T = new_T * 2; // doubles trial size
        }
    }

    public static void main(String[] args) {
        // Not required for the API, but useful to test if
        // your code is doing reasonable things
        int N = 400;
        int T = 500;
        int num_d = 4;

        // elapsed time prints as 0.0 every time
        // unit_test(N, T);
        // double_timeN(N, T, num_d); // Trials (T) constant
        double_timeT(N, T, num_d); // Board (N) constant

    }
}