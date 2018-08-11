package org.ict.algorithm.strings;

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 * The {@code LSD} class provides static methods for sorting an 
 * array of <em>w</em>-charater strings or 32-big integer using LSD
 * radix sort.
 * <p>
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class LSD {
    private static final int BITS_PER_BYTE = 8;
    
    // do not instantiate
    private LSD() {}

    /**
     * Rearranges the array of W-charater strings in ascending-order.
     * 
     * @param a the array to be sorted
     * @param w th number of charaters per string
     */
    public static void sort(String[] a, int w) {
        // Sort a[] on leading w characters.
        int n = a.length;
        // extend ASCII alphabet size
        int R = 256;
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // Sort by key-indexed counting on dth character
            // Compute frequencey counts
            int[] count = new int[R+1];
            for (int i  = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // Compute cumulates, Transform counts to indices
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // Distribute data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // Copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }

    /**
     * Rearranges the array of 32-bit integers in ascending order.
     * This is about 2-3x faster than Arrays.sort()
     *
     * @param a the array to be sorted
     */
    public static void sort(int[] a) {
        
    }

    /**
     * Reads in a sequence of fixed-length strings from standard input;
     * LSD radix sorts them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int n = a.length;

        // check that strings have fixed length
        int w = a[0].length();
        for (int i = 0; i < n; i++) {
            assert a[i].length() == w : "Strings must have fixed length";
        }
        
        // sort the strings
        sort(a, w);

        // print results
        for (int i = 0; i < n; i++) {
            StdOut.println(a[i]);
        }
    }
}
