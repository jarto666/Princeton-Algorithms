import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WordNet {

    private final Digraph graph;
    private final SAP sap;
    private final Map<String, Set<Integer>> nouns = new HashMap<>();
    private final Map<Integer, String> synsets = new HashMap<>();
    private final Map<Integer, List<Integer>> hypernyms = new HashMap<>();

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {

        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException();
        }

        readSynsets(synsets);
        readHypernyms(hypernyms);

        graph = new Digraph(this.synsets.size());
        for (var entry : this.hypernyms.entrySet()) {
            int synId = entry.getKey();
            entry.getValue().forEach(id -> {
                graph.addEdge(synId, id);
            });
        }

        var cycleCheck = new DirectedCycle(graph);
        if (cycleCheck.hasCycle()) {
            throw new IllegalArgumentException();
        }

        int rooted = 0;
        for (int i = 0; i < graph.V(); i++) {
            if (!this.graph.adj(i).iterator().hasNext())
                rooted++;
        }

        if (rooted != 1) {
            throw new IllegalArgumentException("Not a rooted DAG");
        }

        sap = new SAP(graph);
    }

    private void readHypernyms(String hypernyms) {
        var hypernymsIn = new In(hypernyms);
        while (hypernymsIn.hasNextLine()) {
            var line = hypernymsIn.readLine();
            var lineSplit = line.split(",");
            int id = Integer.parseInt(lineSplit[0]);

            if (lineSplit.length > 1) {
                var hypernymsIds = Arrays.stream(lineSplit).skip(1).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
                this.hypernyms.put(id, hypernymsIds);
            }
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }

        return nouns.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        validateNoun(nounA);
        validateNoun(nounB);

        return this.sap.length(this.nouns.get(nounA), this.nouns.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        validateNoun(nounA);
        validateNoun(nounB);

        int sca = this.sap.ancestor(this.nouns.get(nounA), this.nouns.get(nounB));

        return this.synsets.get(sca);
    }

    private void validateNoun(String noun) {
        if (noun == null || !isNoun(noun)) {
            throw new IllegalArgumentException();
        }
    }

    private void readSynsets(String synsets) {
        var synsetsIn = new In(synsets);
        while (synsetsIn.hasNextLine()) {
            var line = synsetsIn.readLine();
            var lineSplit = line.split(",");
            int id = Integer.parseInt(lineSplit[0]);
            String synsString = lineSplit[1];
            String[] synset = synsString.split("\\s");

            this.synsets.put(id, synsString);
            for (var noun : synset) {
                var synIds = nouns.computeIfAbsent(noun, i -> new HashSet<Integer>());
                synIds.add(id);
                nouns.put(noun, synIds);
            }
        }
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wordnet = new WordNet("synsets11.txt", "hypernyms11AmbiguousAncestor.txt");
        int dist = wordnet.distance("a", "g");
        StdOut.println(dist);
        String sap = wordnet.sap("a", "g");
        StdOut.println(sap);
    }
}