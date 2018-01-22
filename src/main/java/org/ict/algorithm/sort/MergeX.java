package org.ict.algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;
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
        //no copy to auxiliary array compared with merge in Merge.java

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

    // sort from a[lo] to a[hi] using insertion sort
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <=hi; i++) {
            for (int j = i; j > lo && AbstractSortHelper.less(a[j], a[j-1]); j--) {
               AbstractSortHelper.exch(a, j, j-1);     
            }
        }
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        // Compare to sort method in Merge.java
        // if (hi <= lo) return;
        
        if (hi <= (lo + CUTOFF)) {
            StdOut.println("invoke Insertion Sort before:<" + lo + ", " + hi + "> " + ", dst: " + Arrays.toString(dst));    
            //Don't use Insertion sort method, because the upper bound is not included due out of array size
            //Insertion.sort(dst, lo, hi);
            insertionSort(dst, lo, hi);
            StdOut.println("invoke Insertion Sort after: <" + lo + ", " + hi + "> " + ", dst: " + Arrays.toString(dst));    
            return;
        }
        
        int mid = lo + (hi - lo) / 2;
        //exchange param sequence
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);
        
        //using System.arraycopy() is a bit faster than the normal-for-loop
        StdOut.println("src[" + (mid+1) + "]:" + src[mid+1] + ", src[" + mid + "]:" + src[mid]);
        if (!less(src[mid+1], src[mid])) {
            StdOut.println("System array copy start:" + "src:" + Arrays.toString(src) + ", dst:" + Arrays.toString(dst));
            System.arraycopy(src, lo, dst, lo, hi-lo+1);
            StdOut.println("System array copy end:  " + "src:" + Arrays.toString(src) + ", dst:" + Arrays.toString(dst));
            return;
        }
        merge(src, dst, lo, mid, hi);
        StdOut.println("merge(src, dst, " + lo  + "," + mid + "," + hi+"), src:" +Arrays.toString(src)+ ", dst:" + Arrays.toString(dst));    
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

    /**
     * Rearranges the array in ascending order, using the provided order
     * @param a the array to be sorted
     * @param comparator the comparator that defines the total order
     */
    public static void sort(Object[] a, Comparator comparator) {
        Object[] aux = a.clone();
        sort(aux, a, 0, a.length-1, comparator);
        assert AbstractSortHelper.isSorted(a, comparator);
    }
    
    private static void merge(Object[] src, Object[] dst, int lo, int mid, int hi, Comparator comparator) {
        //no copy to auxiliary array compared with merge in Merge.java

        //precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays
        assert AbstractSortHelper.isSorted(src, lo, mid, comparator);
        assert AbstractSortHelper.isSorted(src, mid+1, hi, comparator);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (AbstractSortHelper.less(src[j], src[i], comparator)) {//to ensure stability
                dst[k] = src[j++]; 
            } else {
                dst[k] = src[i++]; 
            }
        }

        //postcondition:dst[lo .. hi] is sorted subarray
        assert AbstractSortHelper.isSorted(dst, lo, hi, comparator);
    }

    private static void sort(Object[] src, Object[] dst, int lo, int hi, Comparator comparator) {
        // Compare to sort method in Merge.java
        // if (hi <= lo) return;
        
        if (hi <= (lo + CUTOFF)) {
            //Don't use Insertion sort method, because the upper bound is not included due to out of array size
            //Insertion.sort(dst, lo, hi, comparator);
            insertionSort(dst, lo, hi, comparator);
            return;
        }
        
        int mid = lo + (hi - lo) / 2;
        //exchange param sequence
        sort(dst, src, lo, mid, comparator);
        sort(dst, src, mid+1, hi, comparator);
        
        //using System.arraycopy() is a bit faster than the normal-for-loop
        if (!less(src[mid+1], src[mid], comparator)) {
            System.arraycopy(src, lo, dst, lo, hi-lo+1);
            return;
        }
        StdOut.println("merge(src, dst, " + lo  + "," + mid + "," + hi+"), src:" +Arrays.toString(src)+ ", dst:" + Arrays.toString(dst));    
        merge(src, dst, lo, mid, hi, comparator);
    }

    //sort from a[lo] to a[hi] using insertion sort
    private static void insertionSort(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && AbstractSortHelper.less(a[j], a[j-1], comparator); j--) {
                AbstractSortHelper.exch(a, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        StdOut.println("a before sort:" + Arrays.toString(a)); 
        MergeX.sort(a);
        StdOut.println("a after sort: " + Arrays.toString(a)); 
    }
}

