import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Term implements Comparable<Term> {

    // match Term data type below
    String query;
    Long weight; // upper case is wrapper classs

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        // make sure query and weight are valid
        if (query == null) {
            throw new IllegalArgumentException("Please enter a query");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must be a positive integer");
        }

        // assign values to instance variables
        this.query = query;
        this.weight = weight;
    }

    public static Comparator<Term> byReverseWeightOrder() {
        return new Comparator<Term>() {
            public int compare(Term v, Term w) {
                return -Double.compare(v.weight, w.weight); // - reverse ascending order

                // w > v -> return 1 (puts w first)
                // w < v --> returns -1 (puts v first)
                // w == v --> return 0
            }
        };
    }

    // Compares the two terms in lexicographic order but using only the first r
    // characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        return new Comparator<Term>() {
            public int compare(Term v, Term w) {
                String prefixV;
                String prefixW;

                if (r < 0) {
                    throw new IllegalArgumentException("Must Be A Positive Integer Greater Than Zero");
                }

                if (v.query.length() <= r) {
                    prefixV = v.query; // if r < query tske whole string as the prefix
                } else {
                    prefixV = v.query.substring(0, r); // takes query from index 0 to r for prefix
                }

                if (w.query.length() <= r) {
                    prefixW = w.query;
                } else {
                    prefixW = w.query.substring(0, r);
                }

                // compares the two prefixes using compareTo() because strings
                int result = prefixV.compareTo(prefixW);
                return result;

                // v first -> returns negative (v < w)
                // w first --> positive value (v > w)
                // equal -> returns 0
            }
        };
    }

    // Compares the two terms in lexicographic order by query
    // use compareTo() with this.query (current Term object) and that.query
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        String weight_str = weight.toString(); // comvert from Long to String
        return weight_str + "     " + query;
    }

    // unit testing (you should have some Unit Testing here to confirm that your
    // methods work); for example...
    public static void main(String[] args) {
        Term[] terms = new Term[5];
        terms[0] = new Term("Trevor", 45);
        terms[1] = new Term("Kathy", 43);
        terms[2] = new Term("Ellie", 11);
        terms[3] = new Term("Allen", 9);
        terms[4] = new Term("Eva", 1);

        Arrays.sort(terms);
        for (Term t : terms) {
            StdOut.println(t);
        }

        StdOut.println("");
        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (Term t : terms) {
            StdOut.println(t);
        }

        StdOut.println("");
        Arrays.sort(terms, Term.byPrefixOrder(1));
        for (Term t : terms) {
            StdOut.println(t);
        }
    }
}

// 👻