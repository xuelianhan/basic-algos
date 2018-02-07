package org.ict.algorithm.sort;

import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;


/**
 *  $ javac org/ict/algorithm/sort/MergeBU.java 
 *  $ more ../resources/tiny.txt 
 *   S O R T E X A M P L E
 *  $ java org/ict/algorithm/sort/MergeBU < ../resources/tiny.txt 
 *   a before sort: [S, O, R, T, E, X, A, M, P, L, E]
 *   len:1, size: 2, merge(a, aux, 0, 0, 1)  a:[O, S, R, T, E, X, A, M, P, L, E]
 *   len:1, size: 2, merge(a, aux, 2, 2, 3)  a:[O, S, R, T, E, X, A, M, P, L, E]
 *   len:1, size: 2, merge(a, aux, 4, 4, 5)  a:[O, S, R, T, E, X, A, M, P, L, E]
 *   len:1, size: 2, merge(a, aux, 6, 6, 7)  a:[O, S, R, T, E, X, A, M, P, L, E]
 *   len:1, size: 2, merge(a, aux, 8, 8, 9)  a:[O, S, R, T, E, X, A, M, L, P, E]
 *   len:2, size: 4, merge(a, aux, 0, 1, 3)  a:[O, R, S, T, E, X, A, M, L, P, E]
 *   len:2, size: 4, merge(a, aux, 4, 5, 7)  a:[O, R, S, T, A, E, M, X, L, P, E]
 *   len:2, size: 4, merge(a, aux, 8, 9, 10)  a:[O, R, S, T, A, E, M, X, E, L, P]
 *   len:4, size: 8, merge(a, aux, 0, 3, 7)  a:[A, E, M, O, R, S, T, X, E, L, P]
 *   len:8, size: 16, merge(a, aux, 0, 7, 10)  a:[A, E, E, L, M, O, P, R, S, T, X]
 *   a after sort: [A, E, E, L, M, O, P, R, S, T, X]
 *
 *  The {@code MergeBU} class provides static methods for sorting an 
 *  array using bottom-up mergesort.
 *
 *  @author Robert Sedgewick
 *  @author Kevin wayne 
 *
 *
 */
public class MergeBU extends AbstractSortHelper {
    
    // This class should not be instantiated.
    private MergeBU() {}

    // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        //copy to auxiliary array
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        //merge back to a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (AbstractSortHelper.less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++]; 
            }
        }
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     *
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < (n - len); lo += len + len) {
                int mid = lo + len - 1;
                int hi = Math.min(lo + len + len - 1, n - 1);
                merge(a, aux, lo, mid, hi);
                StdOut.println("len:" + len + ", size: " + (2*len)+ ", merge(a, aux, " + lo + ", " + mid + ", " + hi + ")  a:" + Arrays.toString(a));
            }
        }
        assert AbstractSortHelper.isSorted(a);
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        StdOut.println("a before sort: " + Arrays.toString(a));
        MergeBU.sort(a);
        StdOut.println("a after sort: " + Arrays.toString(a));
    }
}
