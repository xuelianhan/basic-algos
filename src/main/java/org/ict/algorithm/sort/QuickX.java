package org.ict.algorithm.sort;

import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
import org.ict.algorithm.util.StdRandom;


/**
 * $ java org/ict/algorithm/sort/QuickX < ../resources/tiny.txt 
 * exchange a[1]:O and a[0]:P
 * exchange a[3]:E and a[2]:R
 * exchange a[2]:E and a[1]:P
 * exchange a[1]:E and a[0]:O
 * exchange a[4]:E and a[3]:R
 * exchange a[3]:E and a[2]:P
 * exchange a[2]:E and a[1]:O
 * exchange a[5]:L and a[4]:R
 * exchange a[4]:L and a[3]:P
 * exchange a[3]:L and a[2]:O
 * exchange a[6]:A and a[5]:R
 * exchange a[5]:A and a[4]:P
 * exchange a[4]:A and a[3]:O
 * exchange a[3]:A and a[2]:L
 * exchange a[2]:A and a[1]:E
 * exchange a[1]:A and a[0]:E
 * exchange a[7]:M and a[6]:R
 * exchange a[6]:M and a[5]:P
 * exchange a[5]:M and a[4]:O
 * exchange a[10]:T and a[9]:X
 * true
 * [A, E, E, L, M, O, P, R, S, T, X]
 * $ java org/ict/algorithm/sort/QuickX < ../resources/char.log
 *
 * Optimized 2-way quicksort
 *
 * The {@code QuickX} class provides static methods for sorting
 * an array using an optimized version of quicksort.
 * Uses Hoare's 2-way partitioning algorithm, median-of-3 to 
 * choose the partitioning element,
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

    /**
     * quicksort the subarray from a[lo] to a[hi]
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        // cutoff to insertion sort (Insertion.sort() uses half-open intervals)
        int n = hi - lo + 1;
        if (n <= INSERTION_SORT_CUTOFF) {
            Insertion.sort(a, lo, hi + 1);
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    /**
     * partition the subarray a[lo .. hi] so that a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
     * and return the index j.
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        StdOut.println("start partition(a, " + lo + ", " + hi + ")");
        int n = hi - lo + 1;
        int m = median3(a, lo, lo + n/2, hi); 
        exch(a, lo, m);

        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        
        // a[lo] is unique largest element
        while (less(a[++i], v)) {
            if (i == hi) {
                exch(a, lo, hi);
                return hi;
            }
        }

        // a[lo] is unique smallest element
        while (less(v, a[--j])) {
           if (j == lo + 1) {
                return lo;
           }
        }

        // the main loop
        while (i < j) {
            exch(a, i, j);
            while (less(a[++i], v)); 
            while (less(v, a[--j]));
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now , a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    /**
     * return the index of the median element among a[i], a[j], and a[k]
     */
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
               (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i):
               (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        QuickX.sort(a);
        StdOut.println(isSorted(a));
        StdOut.println(Arrays.toString(a));
    }
}
