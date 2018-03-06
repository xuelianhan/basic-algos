package org.ict.algorithm.sort;


import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
import org.ict.algorithm.util.StdRandom;


/**
 * $ more ../resources/tiny.txt 
 *  S O R T E X A M P L E
 * $ javac org/ict/algorithm/sort/Quick3way.java 
 *  Note: org/ict/algorithm/sort/Quick3way.java uses unchecked or unsafe operations.
 *  Note: Recompile with -Xlint:unchecked for details.
 * $ java org/ict/algorithm/sort/Quick3way < ../resources/tiny.txt 
 *  before sort:[S, O, R, T, E, X, A, M, P, L, E]
 *  after sort:[A, E, E, L, M, O, P, R, S, T, X]
 *
 * The {@code Quick3way} class provides static methods for sorting
 * an array using quicksort with 3-way partitioning
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class Quick3way extends AbstractSortHelper {
   
    // This class should not be instantiated.
    private Quick3way() {}

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted.
     */
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    //quicksort the subarray a[lo .. hi] using 3-way partitioning
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++); 
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }

        //a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
        assert isSorted(a, lo, hi);
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        StdOut.println("before sort:" + Arrays.toString(a));
        Quick3way.sort(a);
        StdOut.println("after sort:" + Arrays.toString(a));
    }
}
