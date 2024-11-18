import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordNet {
    private final Map<String, Set<String>> nounToSynsets; // store nouns and synsets
    private final HashMap<Integer, String[]> synsetsMap; // store synset IDs and nouns
    private final Digraph hypernymsGraph;
    private Set<String> all_nouns; // unique nouns

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) throws IOException /* "throw" required for FileReader */ {
        // create a new synsetsMap to store synIDs
        synsetsMap = new HashMap<>();
        all_nouns = new HashSet<>();
        nounToSynsets = new HashMap<>();

        // Read in all synsets (and do something with them)
        BufferedReader input = new BufferedReader(new FileReader(synsets));
        String line = input.readLine();
        while (line != null) {
            String parts[] = line.split(",");
            int synId = Integer.parseInt(parts[0]);
            String synStr = parts[1];
            String[] synset = synStr.split(" ");
            // notice: the definitions are in parts[2]; we're ignoring those

            // store synID + words to go in map
            synsetsMap.put(synId, synset);

            // add each word in synset to map
            for (String noun : synset) {
                all_nouns.add(noun);
                if (!nounToSynsets.containsKey(noun)) {
                    nounToSynsets.put(noun, new HashSet<>());
                }
                nounToSynsets.get(noun).add(String.valueOf(synId)); // converts to str before adding to set
            }

            // Read next line from file and ..
            line = input.readLine();
        }
        input.close();

        // initialize graph with vertices = to num of synsets
        hypernymsGraph = new Digraph(synsetsMap.size()); // same num of synsets

        // read in all hypernyms and build edges in Digraph
        input = new BufferedReader(new FileReader(hypernyms));
        line = input.readLine();
        while (line != null) { // as long as more to read
            String part[] = line.split(",");
            // part[0] holds primary identifier
            int synId = Integer.parseInt(part[0]);

            // connect synId to all its hypernyms
            for (int i = 1; i < part.length; i++) {
                int hypernymId = Integer.parseInt(part[i]); // converts hypernym identifier to int and stores it in
                                                            // hypernymId
                hypernymsGraph.addEdge(synId, hypernymId); // build in addEdge
            }

            line = input.readLine();
        }
        input.close();
    }

    // all WordNet nouns
    public Iterable<String> nouns() {
        // dont want duplicates only unique nouns
        /*
         * Set<String> all_nouns = new HashSet<>();
         * 
         * //iterate over each in map and add to set
         * for (String[] synset : synsetsMap.values()){
         * for (String noun : synset){
         * all_nouns.add(noun); //adds each unique noun to set
         * }
         * }
         * return all_nouns; //Set
         */
        return all_nouns;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (all_nouns.contains(word)) {
            return true;
        } else {
            return false;
        }
    }

    // a synset (second field of synsets.txt) that is a shortest common ancestor
    // of noun1 and noun2 (defined below)
    public String sca(String noun1, String noun2) {
        if (!isNoun(noun1) || !isNoun(noun2)) {
            throw new IllegalArgumentException("Not a valid WordNet noun");
        }

        Set<String> synsets1 = nounToSynsets.get(noun1);
        Set<String> synsets2 = nounToSynsets.get(noun2);

        // initialize sca and shortest distance
        String sca = null;
        int shortest_len = Integer.MAX_VALUE;

        // Find the shortest common ancestor
        ShortestCommonAncestor sca_finder = new ShortestCommonAncestor(hypernymsGraph);

        // iterate over all pairs
        for (String synset1 : synsets1) {
            for (String synset2 : synsets2) {
                int synsetID1 = Integer.parseInt(synset1);
                int synsetID2 = Integer.parseInt(synset2);

                int dist = sca_finder.length(synsetID1, synsetID2);
                if (dist < shortest_len) {
                    shortest_len = dist;
                    sca = synset1;
                }
            }
        }
        return sca;
    }

    // distance between noun1 and noun2 (defined below)
    public int distance(String noun1, String noun2) {
        if (!isNoun(noun1) || !isNoun(noun2)) {
            throw new IllegalArgumentException("Not a valid WordNet noun");
        }

        Set<String> synsets1 = nounToSynsets.get(noun1);
        Set<String> synsets2 = nounToSynsets.get(noun2);

        // create sca
        ShortestCommonAncestor scaFinder = new ShortestCommonAncestor(hypernymsGraph);

        int shortest_len = Integer.MAX_VALUE;
        for (String synset1 : synsets1) {
            for (String synset2 : synsets2) {
                int synsetID1 = Integer.parseInt(synset1);
                int synsetID2 = Integer.parseInt(synset2);
                int dist = scaFinder.length(synsetID1, synsetID2);
                shortest_len = Math.min(shortest_len, dist);
            }
        }
        return shortest_len;
    }

    // do unit testing of this class
    public static void main(String[] args) throws IOException { // "throw" because the constructor throws.
        WordNet wnet = new WordNet("synsets.txt", "hypernyms.txt");
        // how to test
    }
}