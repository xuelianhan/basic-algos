package org.ict.algorithm.sort;

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 *
 * The {@code MergeX} class provides static methods for sorting an 
 * array using an optimized version of mergesort
 * Improvements
 * Use insertion sort for small subarrays
 * Test whether array is already in order
 * Eliminate the copy to the auxiliary array
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/22mergesort/">Section 2.2</a>
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class MergeX extends AbstractSortHelper {
    private static final int CUTOFF = 7;

    //This class should not be instantiated
    private MergeX() {}

    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        //precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays
        assert AbstractSortHelper.isSorted(src, lo, mid);
        assert AbstractSortHelper.isSorted(src, mid+1, hi);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (AbstractSortHelper.less(src[j], src[i])) {//to ensure stability
                dst[k] = src[j++]; 
            } else {
                dst[k] = src[i++]; 
            }
        }

        //postcondition:dst[lo .. hi] is sorted subarray
        assert AbstractSortHelper.isSorted(dst, lo, hi);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        // Compare to sort method in Merge.java
        // if (hi <= lo) return;
        
        if (hi <= (lo + CUTOFF)) {
            Insertion.sort(dst, lo, hi);
            return;
        }
        
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);
        
        //using System.arraycopy() is a bit faster than the normal-for-loop
        if (!less(src[mid+1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi-lo+1);
            return;
        }
        
        merge(src, dst, lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order
     * @param a the array to be sorted
     *
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
        assert AbstractSortHelper.isSorted(a);
    }
    
    private static void merge() {

    }


    public static void main(String[] args) {
        
    }
}

