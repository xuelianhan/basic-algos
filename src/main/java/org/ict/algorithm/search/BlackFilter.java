package org.ict.algorithm.search;

import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 * The {@code BlackFilter} class provides a client for reading in a 
 * <em>blacklist</em> of words from a file; then, reading in a sequence
 * of words from standard input, printing out each word that 
 * <em>does not</em> appear in the file.
 * It is useful as a test client for various symbol table implementations.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class BlackFilter {

    // Do not instantiate
    private BlackFilter() {}

    public static void main(String[] args) {
        SET<String> set = new SET<String>();

        // read in strings and add to set
        In in = new In(args[0]); 
        while (!in.isEmpty()) {
            String word = in.readString();
            set.add(word);
        }

        // read in string from standard input, printing out all exceptions
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (!set.contains(word)) {
                StdOut.println(word);
            }
        }
    }

}
