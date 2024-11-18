import java.io.IOException;

public class Outcast {
    private WordNet wordnet1;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        // initialize
        wordnet1 = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int outcast_id = -1;
        int max_dist = -1;

        // iterate over eachnoun and find sum of dist
        for (int i = 0; i < nouns.length; i++) {
            int total_dist = 0;

            // sum dist to other nouns
            for (int j = 0; j < nouns.length; j++) {
                if (i != j) { // dont find for itself
                    total_dist += wordnet1.distance(nouns[i], nouns[j]);
                }
            }
            // update outcast if curr noun has largest dist
            if (total_dist > max_dist) {
                max_dist = total_dist;
                outcast_id = i; // new outcast
            }
        }

        return nouns[outcast_id];
    }

    // Unit Test client
    public static void main(String[] args) throws IOException { // throw because WordNet throws
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}