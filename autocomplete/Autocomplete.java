import java.util.Arrays;
import java.util.ArrayList;

public class Autocomplete {
    Term[] terms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new IllegalArgumentException("Cannot Be Null");
        }

        this.terms = terms;

        for (int i = 0; i < terms.length; i++) {
            // make sure no elem in term[] are null
            if (terms[i] == null) {
                throw new IllegalArgumentException("Cannot Have Null Terms");
            } else {
                // copy to instance var
                this.terms[i] = terms[i];
            }
        }

        // sort term[]
        Arrays.sort(this.terms);
    }

    // Returns all terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Cannot Have Null Prefix");
        }

        // empty returns terms
        if (prefix.length() == 0) {
            return this.terms;
        }

        // store matches
        ArrayList<Term> matches = new ArrayList<Term>();

        // create test Term obj
        Term test = new Term(prefix, 0);

        // first and last index of terms arr that start with given prefix
        int length = prefix.length();
        int first_in = BinarySearchDeluxe.firstIndexOf(terms, test, Term.byPrefixOrder(length));
        int last_in = BinarySearchDeluxe.lastIndexOf(terms, test, Term.byPrefixOrder(length));

        // no matches when first_int == -1 so returns a new empty array
        if (first_in == -1) {
            return new Term[0];
        }

        // fill matches with matches
        for (int i = first_in; i <= last_in; i++) {
            matches.add(terms[i]);
        }

        // sort by descending weight
        matches.sort(Term.byReverseWeightOrder());

        // converts to array with match size
        Term[] matches_arr = matches.toArray(new Term[matches.size()]);
        return matches_arr;

    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Cannot Have Null Prefix");
        }

        // empty returns terms
        if (prefix.length() == 0) {
            return terms.length;
        }

        Term test = new Term(prefix, 0);

        int length = prefix.length();

        int first_in = BinarySearchDeluxe.firstIndexOf(terms, test, Term.byPrefixOrder(length));
        int last_in = BinarySearchDeluxe.lastIndexOf(terms, test, Term.byPrefixOrder(length));

        // number of matches
        if (first_in == -1) {
            return 0; // no matches
        }

        int final_num = (last_in - first_in) + 1;
        return final_num;
    }

    // A sample client for unit testing
    public static void main(String[] args) {

        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        int i;
        for (i = 0; i < N; i++) {
            long weight = in.readLong(); // read the next weight
            in.readChar(); // scan past the tab
            String query = in.readLine(); // read the next query
            terms[i] = new Term(query, weight); // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}

// 👻