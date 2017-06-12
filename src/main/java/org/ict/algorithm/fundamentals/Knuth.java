package org.ict.algorithm.fundamentals;

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 *
 * $ javac org/ict/algorithm/fundamentals/Knuth.java 
 * $ java org/ict/algorithm/fundamentals/Knuth < ../resources/cards.txt
 * $ java org/ict/algorithm/fundamentals/Knuth < ../resources/cardsUnicode.txt 
 *
 *
 *
 * Reads in a list of strings and prints them in random order
 * The knuth (or Fisher-Yates) shuffling algorithm guarantees
 * to rearrange the elements in uniformly random order, under
 * the assumption that Math.random() generates independent and
 * uniformly distributed numbers between 0 and 1.
 * For additional documentation,
 * see <a href="http://algs4.cs.princeton.edu/11model">Section 1.1</a>
 * of <i>Algorithms, 4th Edition</i>by Rebert Sedgewick and Kevin Wayne.
 * See {@link StdRandom} for versions that shuffle arrays and subarrays 
 * of objects, doubles, and ints.
 *
 */
public class Knuth {
    //this class should not be instantiated
    private Knuth() {}

    /**
     * Rearranges an array of objects in uniformly random order
     * (under the assumption that{@code Math.random()}) generates
     * independent and uniformly distributed numbers between 0 and
     * 1)
     * 0.0 =< Math.random() < 1.0
     * @param a the array to be shuffled
     */
    public static void shuffle(Object[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            //choose index uniformly in [0, i]
            int r = (int)(Math.random() * (i + 1));
            Object swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    /**
     * Rearranges an array of objects in uniformly random order
     * (under the assumption that{@code Math.random()}) generates
     * independent and uniformly distributed numbers between 0 and
     * 1)
     *
     * @param a the array to be shuffled
     */
    public static void shuffleAlternate(Object[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            //choose index uniformly in [i, n - i]
            int r = i + (int)(Math.random() * (n - i));
            Object swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }


    public static void main(String[] args) {
        //read in the data
        String[] a = StdIn.readAllStrings();

        //shuffle the array
        Knuth.shuffle(a);

        //print results.
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
}
