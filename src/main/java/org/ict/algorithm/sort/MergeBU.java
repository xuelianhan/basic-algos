package org.ict.algorithm.sort;

import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;


/**
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
