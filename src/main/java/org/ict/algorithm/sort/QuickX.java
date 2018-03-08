package org.ict.algorithm.sort;


import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
import org.ict.algorithm.util.StdRandom;


/**
 * Optimized 2-way quicksort
 *
 * The {@code QuickX} class provides static methods for sorting
 * an array using an optimized version of quicksort.
 * Uses the Bentley-McIlroy 3-way partitioning scheme,
 * chooses the partitioning element using Tukey's ninther,
 * and cuts off to insertion sort.
 * 
 * Reference: Engineering a Sort Function by Jon L. Bentley
 * and M. Douglas McIlroy. Softwae-Practice and Experience,
 * Vol. 23 (11), 1249-1265 (November 1993).
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */

public class QuickX {
    // cutoff to insertion sort, must be >= 1
    private static final int INSERTION_SORT_CUTOFF = 8;

    // cutoff to median-of-3 partitioning
    private static final int MEDIAN_OF_3_CUTOFF = 40;

    private QuickX() {}

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted.
     */
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        
    }
}
