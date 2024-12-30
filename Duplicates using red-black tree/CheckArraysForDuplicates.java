import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StopwatchCPU;
import edu.princeton.cs.algs4.StdOut;

public class CheckArraysForDuplicates {

    public static void main(String[] args) {

        /*
         * ===================================
         * No need to touch the next batch of code - it should just work.
         * Your changes will start after the StopwatchCPU object is created
         * ===================================
         */

        if (args.length < 1) {
            StdOut.println("Usage: java CheckArraysForDuplicates filename");
            System.exit(1);
        }
        String filename = args[0];

        In in = new In(filename);

        int k = in.readInt(); // how many arrays
        int[] sizes = new int[k];
        int[][] vals = new int[k][];

        /* Read in all numbers into a set of arrays */
        for (int i = 0; i < k; i++) {
            int n = in.readInt();
            sizes[i] = n;
            vals[i] = new int[n];
        }
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < sizes[i]; j++) {
                vals[i][j] = in.readInt();
            }
        }

        /*
         * ===================================
         * Now it's your turn: how to use a RedBlack tree (of size k) to solve this?
         */
        boolean duplicate = false; // start as false = no duplicate
        StopwatchCPU sw = new StopwatchCPU();

        RedBlackBST<Integer, Integer> rbt = new RedBlackBST<Integer, Integer>();

        // go through each array
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < sizes[i]; j++) {
                int value = vals[i][j];

                // see if elem in tree
                if (rbt.contains(value)) {
                    duplicate = true;
                    break; // break when duplicate found
                }

                // if no duplicate put into tree
                rbt.put(value, i); // key = value, value = array index
            }

            // break when duplicate true
            if (duplicate) {
                break;
            }
        }

        double elapsed = sw.elapsedTime();

        StdOut.println("The arrays do " + (duplicate ? "" : "not ") + "contain a duplicate");
        StdOut.println("elapsed time: " + elapsed + " seconds");

    }
}

// 👻
