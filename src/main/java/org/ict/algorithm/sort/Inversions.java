package org.ict.algorithm.sort;

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 *  The {@code Inversions} class provides static methods to count the
 *  number of <em>inversions</em> in either an array of integers or 
 *  comparables
 *  An inversion in an array {@code a[]} is a pair of indicies {@code i}
 *  and {@code j} such that {@code i < j} and {@code a[i] > a[j]}
 *  <p>
 *  This implementation uses a generalization of mergesort
 *  The <em>count</em> operation takes time proportional to <em>nlogn</em>,
 *  where <em>n</em> is the number of keys in the array
 *  
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 */
public class Inversions {
    // do not instantiate
    private Inversions() {}

    // merge and count
    private static long merge(int[] a, int[] aux, int lo, int mid, int hi) {
        long inversions = 0;

        //copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        //merge back to a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {

            } else if (j > hi) {

            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                inversions += (mid - i + 1);
            } else {
                a[k] = aux[i++];
            }
        }
        return inversions;
    }

    //return the number of inversions in the subarray b[lo..hi]
    //side effect b[lo..hi] is rearranged in ascending order
    private static long count(int[] a, int[] b, int[] aux, int lo, int hi) {
        long inversions = 0;
        if (hi <= lo) {
            return 0;
        }
        int mid = lo +  (hi - lo) / 2;

    }

}
