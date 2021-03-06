import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

    private final WordNet wordnet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int outcast = -1;
        int maxDist = -1;
        for (int i = 0; i < nouns.length; i++) {
            int distSum = 0;
            for (int j = 0; j < nouns.length; j++) {
                int dist = wordnet.distance(nouns[i], nouns[j]);
                distSum += dist;
            }

            if (distSum > maxDist) {
                maxDist = distSum;
                outcast = i;
            }
        }

        return nouns[outcast];
    }

    // see test client below
    public static void main(String[] args) {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
        String[] testFiles = {"outcast5.txt", "outcast8.txt", "outcast11.txt"};
        for (int t = 0; t < testFiles.length; t++) {
            In in = new In(testFiles[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(testFiles[t] + ": " + outcast.outcast(nouns));
        }
    }
}