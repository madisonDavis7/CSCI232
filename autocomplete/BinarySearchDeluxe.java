import java.util.Arrays;
import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in a[] that equals the search key, or -1
    // if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        // throw exception if any fields are null
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("Null Arguments Not Allowed");
        }

        int low = 0;
        int high = a.length - 1;

        int value = -1; // first occurrence of index (no match yet so can't be 0)

        while (low <= high) { // while still elements to search
            int mid = low + (high - low) / 2; // find middle index
            int compare = comparator.compare(a[mid], key);

            if (compare < 0) {
                low = mid + 1; // search right if the key is greater than the mid
            } else if (compare > 0) {
                high = mid - 1; // search left if key is less than mid
            } else {
                value = mid;
                high = mid - 1; // keep going left until first one found
            }

        }
        // -1 if no key found
        return value;

    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if
    // no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("Null Arguments Not Allowed");
        }

        int low = 0;
        int high = a.length - 1;
        int value = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int compare = comparator.compare(a[mid], key);

            if (compare < 0) {
                low = mid + 1;
            } else if (compare > 0) {
                high = mid - 1;
            } else {
                value = mid;
                // high = mid + 1
                low = mid + 1; // keep searching right half to find last
            }
        }

        return value;
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

        Term searchme = new Term("J", 0);
        int first = BinarySearchDeluxe.firstIndexOf(terms, searchme, Term.byPrefixOrder(1));
        int last = BinarySearchDeluxe.lastIndexOf(terms, searchme, Term.byPrefixOrder(1));
        StdOut.println("J: " + first + " to " + last);

        searchme = new Term("A", 0);
        first = BinarySearchDeluxe.firstIndexOf(terms, searchme, Term.byPrefixOrder(1));
        last = BinarySearchDeluxe.lastIndexOf(terms, searchme, Term.byPrefixOrder(1));
        StdOut.println("A: " + first + " to " + last);

        searchme = new Term("E", 0);
        first = BinarySearchDeluxe.firstIndexOf(terms, searchme, Term.byPrefixOrder(1));
        last = BinarySearchDeluxe.lastIndexOf(terms, searchme, Term.byPrefixOrder(1));
        StdOut.println("E: " + first + " to " + last);

        searchme = new Term("T", 0);
        first = BinarySearchDeluxe.firstIndexOf(terms, searchme, Term.byPrefixOrder(1));
        last = BinarySearchDeluxe.lastIndexOf(terms, searchme, Term.byPrefixOrder(1));
        StdOut.println("T: " + first + " to " + last);
    }
}

// 👻