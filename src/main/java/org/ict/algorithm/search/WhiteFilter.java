package org.ict.algorithm.search;


import org.ict.algorithm.util.In;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 * The {@code WhiteFilter} class provides a client for reading in a <em>whitelist</em>
 * of words from a file; then, reading in a sequence of words from standard input,
 * printing out each word that appears in the file.
 * It is useful as a test client for various symbol table implementations.
 *
 * $ javac org/ict/algorithm/search/WhiteFilter.java 
 * $ more ../resources/list.txt 
 * was it the of
 * $ more ../resources/tinyTale.txt 
 * it was the best of times it was the worst of times 
 * it was the age of wisdom it was the age of foolishness 
 * it was the epoch of belief it was the epoch of incredulity 
 * it was the season of light it was the season of darkness 
 * it was the spring of hope it was the winter of despair
 * $ java org/ict/algorithm/search/WhiteFilter ../resources/list.txt  < ../resources/tinyTale.txt
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class WhiteFilter {

    // Do not instantiate.
    private WhiteFilter() {}

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
            if (set.contains(word)) {
                StdOut.println(word);
            }
        }
    }

}
