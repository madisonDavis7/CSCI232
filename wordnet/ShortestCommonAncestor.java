import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestCommonAncestor {
    private final Digraph G;
    private final int root;

    // constructor takes a rooted DAG as argument
    public ShortestCommonAncestor(Digraph G) {
        this.G = G;
        this.root = finding_root();
    }

    private int finding_root() {
        for (int i = 0; i < G.V(); i++) {
            if (G.indegree(i) == 0) {
                return i;
            }
        }
        return -1;
    }

    // length of shortest ancestral path between v and w
    public int length(int v, int w) {
        // errors is out of bounds
        if (v < 0 || v >= G.V() || w < 0 || w >= G.V()) {
            throw new IndexOutOfBoundsException();
        }
        // runs bfs from each vertex and find shortest
        int[] distToV = bfs(v);
        int[] distToW = bfs(w);

        // initially set so nothing is larger
        int shortest_len = Integer.MAX_VALUE;

        for (int i = 0; i < G.V(); i++) {
            // sees if they are reachable (-1 if not)
            if ((distToV[i] != -1) && (distToW[i] != -1)) {
                int path_len = distToV[i] + distToW[i];
                // set to shortest
                if (path_len < shortest_len) {
                    shortest_len = path_len;
                }
            }
        }
        if (shortest_len == Integer.MAX_VALUE) {
            return -1;
        } else {
            return shortest_len;
        }
    }

    // helper function for bfs from source s
    private int[] bfs(int s) {

        // initialize so all nodes unreachable to start and dist to s = 0
        int[] distTo = new int[G.V()]; // same num of vertices
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = -1;
        }
        distTo[s] = 0;

        // create a queue and add s
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        while (!queue.isEmpty()) {
            // vertex is first elem from queue FIFO
            int v = queue.remove();

            // iterate over adjacent vertices
            for (int w : G.adj(v)) {
                if (distTo[w] == -1) { // if not visitied add dist
                    distTo[w] = distTo[v] + 1;
                    queue.add(w); // go to next
                }
            }
        }
        return distTo;

    }

    // a shortest common ancestor of vertices v and w
    public int ancestor(int v, int w) {
        int[] distToV = bfs(v);
        int[] distToW = bfs(w);

        int shortest_len = Integer.MAX_VALUE;
        int common_ancestor = -1; // initial as none

        for (int i = 0; i < G.V(); i++) {
            if ((distToV[i] != -1) && (distToW[i] != -1)) {
                int total_dist = distToV[i] + distToW[i];
                if (total_dist < shortest_len) {
                    shortest_len = total_dist;
                    common_ancestor = i; // common_ancestor is index
                }
            }
        }
        // or -1 if no common ancestor is found
        return common_ancestor;
    }

    // length of shortest ancestral path of vertex subsets A and B
    public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        // Output shortest length of all pairs

        int shortest_len = Integer.MAX_VALUE;

        // iterate through each part of each subset to find all pairs and compare
        for (int a : subsetA) {
            for (int b : subsetB) {
                int path_len = length(a, b);
                // set to shortest if true
                if ((path_len != -1) && (path_len < shortest_len)) {
                    shortest_len = path_len; // new shortest
                }
            }
        }

        if (shortest_len == Integer.MAX_VALUE) {
            return -1;
        } else {
            return shortest_len;
        }

    }

    // a shortest common ancestor of vertex subsets A and B
    public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        // Output shortest common ancestor of all pairs

        int shortest_len = Integer.MAX_VALUE;
        int shortest_ancestor = -1;

        // iterate over each subset and run methods on vertext pairs
        for (int a : subsetA) {
            for (int b : subsetB) {
                int common_ancestor = ancestor(a, b);
                int path_len = length(a, b);

                if ((path_len != -1) && (path_len < shortest_len)) {
                    shortest_len = path_len;
                    shortest_ancestor = common_ancestor;
                }
            }
        }
        // shortest common ancestor or -1 if none
        return shortest_ancestor;

    }

    // do unit testing of this class
    public static void main(String[] args) {

        // Build unit tests
        if (args.length < 1) {
            manualUnitTest();
        } else {
            In in = new In(args[0]);
            Digraph G = new Digraph(in);
            ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
            while (!StdIn.isEmpty()) {
                int v = StdIn.readInt();
                int w = StdIn.readInt();
                int length = sca.length(v, w);
                int ancestor = sca.ancestor(v, w);
                StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
            }
        }
    }

    // Unit test made by me
    public static void manualUnitTest() {
        // Basic tree test
        int numVertices = 6;// or whatever
        Digraph d1 = new Digraph(numVertices);
        d1.addEdge(1, 0); // add a bunch of these, to form some tree-like shape, e.g.:
        /*
         * 0
         * / \
         * 1 2
         * / \ / \
         * 3 4 5
         */

        ShortestCommonAncestor sca = new ShortestCommonAncestor(d1);
        int w = -1; // fixme
        int x = -1; // fixme
        int y = -1; // fixme
        int z = -1; // fixme

        StdOut.println("Testing Case: 1");
        StdOut.println("length: " + sca.length(x, y));
        StdOut.println("ancestor: " + sca.ancestor(x, y));

        // testing sets with some iterable type
        // ({1,2},{3,4})
        Bag<Integer> b1 = new Bag<Integer>();
        Bag<Integer> b2 = new Bag<Integer>();

        b1.add(x);
        b1.add(y);
        b2.add(w);
        b2.add(z);

        StdOut.println("Testing Case: 2");
        StdOut.println("length: " + sca.length(b1, b2));
        StdOut.println("ancestor: " + sca.ancestor(b1, b2));
    }
}