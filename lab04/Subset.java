public class Subset {

    public static void main(String[] args) {

        // converts command line arg to an int and assign to k
        int k = Integer.parseInt(args[0]); // put on line with program
        RandomizedBag<String> bag = new RandomizedBag<String>();

        // fill the bag with words from StdIn; example of doing this in e.g. LinkedQueue

        StdOut.println("Please enter the elements you would like to add ('quit' to exit)");

        // get user input until "quit"
        while (!StdIn.isEmpty()) { // reads until no more input
            String input = StdIn.readString();
            if (input.equals("quit")) {
                StdOut.println();
                StdOut.println("Exiting loop");
                StdOut.println("-----------------------------------");
                break; // exit loop when user inputs quit
            }
            bag.put(input);
        }

        // prints out k random items
        StdOut.println("The " + k + " random elements taken from the bag are: ");
        StdOut.println();
        for (int i = 0; i < k; i++) {
            if (!bag.isEmpty()) {
                String elem = bag.get();
                StdOut.println(elem);
            } else {
                StdOut.println("The bag is empty, nothing to take out");
                break;
            }
        }
        StdOut.println();

        // pull k things from the bag, if possible.

    }

}
// 👻