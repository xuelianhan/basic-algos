/**
 * $ javac org/ict/algorithm/search/DeDup.java
 * $ more ../resources/tinyTale.txt 
 * it was the best of times it was the worst of times 
 * it was the age of wisdom it was the age of foolishness 
 * it was the epoch of belief it was the epoch of incredulity 
 * it was the season of light it was the season of darkness 
 * it was the spring of hope it was the winter of despair
 * $ java org/ict/algorithm/search/DeDup < ../resources/tinyTale.txt 
 * it
 * was
 * the
 * best
 * of
 * times
 * worst
 * age
 * wisdom
 * foolishness
 * epoch
 * belief
 * incredulity
 * season
 * light
 * darkness
 * spring
 * hope
 * winter
 * despair
 */
package org.ict.algorithm.search;

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;


/**
 * 
 * The {@code DeDup} class provides a client fo reading in a sequence of 
 * words from standard input and printing each word, removing any duplicates.
 * It is useful as a test client for various symbol table implementations.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class DeDup {
    // Do not instantiate.
    private DeDup() {}

    public static void main(String[] args) {
        SET<String> set = new SET<String>();

        // read in strings and add to set
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (!set.contains(key)) {
                set.add(key);
                StdOut.println(key);
            }
        }
    }
}
