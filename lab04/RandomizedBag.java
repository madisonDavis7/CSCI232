
/******************************************************************************
 *  Madison Davis
 *  10/3/24
/******************************************************************************
 *  Compilation:  javac RandomizedBag.java
 *  Execution:    java RandomizedBag < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  
 *  Stack implementation with a resizing array.
 *
 *  % java ResizingArrayStack < tobe.txt
 *  to be not that or be (2 left on stack)
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedBag<Item> implements Iterable<Item> {
    private Item[] a; // array of items
    private int n; // number of elements on stack
    Random rng;

    /**
     * Initializes an empty stack.
     */
    public RandomizedBag() {
        a = (Item[]) new Object[2]; // ignore warning
        n = 0;
        rng = new Random();
    }

    /**
     * Is this stack empty?
     */
    public boolean isEmpty() {
        // check if n is 0 (means no items have been added)
        if (n == 0) {
            // StdOut.print("The bag is empty");
            return true;
        }
        return false;
    }

    /**
     * Returns the number of items in the stack.
     */
    public int size() {
        int size = n;
        // System.out.print("The size of the bag is: " + size);
        return size;
    }

    /*
     * Resize the underlying array holding the elements
     */
    private void resize(int capacity) {
        // haven't we done this somewhere before? (in some other lab)
        Item[] temp = (Item[]) new Object[capacity]; // create temp array

        // copy original (a) to temp
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    /**
     * Adds the item to this bag (which is an array).
     */
    public void put(Item item) {
        if (n == a.length) { // if adding an item will be over capacity so double
            int new_len = 2 * a.length;
            resize(new_len);
        }
        a[n] = item; // add item to index n
        n++; // increment to reflect new size
    }

    /**
     * Removes and returns a random item from the bag
     */
    public Item get() {

        Random rand = new Random();
        int rand_int = rand.nextInt(n); // random int from 0- (n-1)

        // remove elem from bag and decrement n
        Item item = a[rand_int];
        a[rand_int] = a[n - 1];
        a[n - 1] = null; // no loitering
        n--;

        // resize if removing the item made the array 1/4 size full
        if (n == a.length / 4) {
            int new_len = a.length / 2;
            resize(new_len);
        }
        return item;
    }

    /**
     * Returns an iterator to this bag that iterates through the items in random
     * order.
     */
    public Iterator<Item> iterator() {
        return new RandomizedBagIterator();
    }

    // an iterator; ours doesn't implement remove() since it's optional
    private class RandomizedBagIterator implements Iterator<Item> {
        private int i; // iterator for itArr array
        private Item itArr[];

        public RandomizedBagIterator() {
            /*
             * do the work here to support
             * (i) multiple independent iterators (i.e. each one initializes its own itArr);
             * - in this constructor, this can take time linear in the size of the bag
             * (ii) constant time next() and hasnext() calls.
             */

            i = 0; // initializes index iterator for itArr
            // create a new array of n elem of original by copying over
            itArr = (Item[]) new Object[n];
            for (int b = 0; b < n; b++) {
                itArr[b] = a[b];
            }

            // shuffle the new array
            for (int b = 0; b < n; b++) {
                int max = b + 1;
                int rand_index = rng.nextInt(max); // up to b
                Item temp = itArr[b];
                itArr[b] = itArr[rand_index];
                itArr[rand_index] = temp;
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            // i is iterator and n is num of elements
            if (i < n) {
                return true;
            }
            return false; // i = n there is no next element
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException(); // no further element
            // Item item = null;

            Item next_elem = itArr[i++]; // next element in the array
            return next_elem;
        }
    }

    /**
     * Unit tests the RandomizeBag data type.
     */
    public static void main(String[] args) {
        RandomizedBag<String> bag = new RandomizedBag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                bag.put(item);
            else if (!bag.isEmpty())
                StdOut.print(bag.get() + " ");
        }
        System.out.println("(" + bag.size() + " left on bag)");

        Iterator<String> itr1 = bag.iterator();
        if (!bag.isEmpty())
            bag.get(); // test removal of one
        Iterator<String> itr2 = bag.iterator();

        System.out.println("Here's what was left before removing one (in random order):");
        while (itr1.hasNext()) {
            String s = itr1.next();
            StdOut.println(s + " ");
        }
        StdOut.println("");

        System.out.println("Here's what was left after removing one (in random order):");
        while (itr2.hasNext()) {
            String s = itr2.next();
            StdOut.println(s + " ");
        }
        StdOut.println("");

        StdOut.println("I sure hope the second one is missing one entry, and in a different order.");

    }

}

// 👻
