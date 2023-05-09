package org.ict.algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 *  $ javac org/ict/algorithm/sort/MergeX.java
 *  $ more ../resources/tiny.txt 
 *  S O R T E X A M P L E
 *  $ java org/ict/algorithm/sort/MergeX  < ../resources/tiny.txt 
 *  a before sort:[S, O, R, T, E, X, A, M, P, L, E]
 *  sort(src, dst, 0,10), src:[S, O, R, T, E, X, A, M, P, L, E], dst:[S, O, R, T, E, X, A, M, P, L, E]
 *  sort(src, dst, 0,5), src:[S, O, R, T, E, X, A, M, P, L, E], dst:[S, O, R, T, E, X, A, M, P, L, E]
 *  invoke Insertion Sort before:<0, 5> , dst: [S, O, R, T, E, X, A, M, P, L, E]
 *  invoke Insertion Sort after: <0, 5> , dst: [E, O, R, S, T, X, A, M, P, L, E]
 *  sort(src, dst, 6,10), src:[S, O, R, T, E, X, A, M, P, L, E], dst:[E, O, R, S, T, X, A, M, P, L, E]
 *  invoke Insertion Sort before:<6, 10> , dst: [E, O, R, S, T, X, A, M, P, L, E]
 *  invoke Insertion Sort after: <6, 10> , dst: [E, O, R, S, T, X, A, E, L, M, P]
 *  src[6]:A, src[5]:X
 *  before merge(src, dst, 0,5,10), src:[E, O, R, S, T, X, A, E, L, M, P], dst:[S, O, R, T, E, X, A, M, P, L, E]
 *  after merge(src, dst, 0,5,10), src:[E, O, R, S, T, X, A, E, L, M, P], dst:[A, E, E, L, M, O, P, R, S, T, X]
 *  a after sort: [A, E, E, L, M, O, P, R, S, T, X]
 *
 *  The {@code MergeX} class provides static methods for sorting an 
 *  array using an optimized version of mergesort
 *  Improvements
 *  Use insertion sort for small subarrays
 *  Test whether array is already in order
 *  Eliminate the copy to the auxiliary array
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/22mergesort/">Section 2.2</a>
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class MergeX extends AbstractSortHelper {
    private static final int CUTOFF = 7;

    //This class should not be instantiated
    private MergeX() {}
    
    private static <T> void merge(Comparable<T>[] src, Comparable<T>[] dst, int lo, int mid, int hi) {
        //no copy to auxiliary array compared with merge in Merge.java
        StdOut.println("before merge(src, dst, " + lo  + "," + mid + "," + hi+"), src:" +Arrays.toString(src)+ ", dst:" + Arrays.toString(dst));
        //precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid+1, hi);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (less(src[j], src[i])) {//to ensure stability
                dst[k] = src[j++]; 
            } else {
                dst[k] = src[i++]; 
            }
        }
        StdOut.println("after merge(src, dst, " + lo  + "," + mid + "," + hi+"), src:" +Arrays.toString(src)+ ", dst:" + Arrays.toString(dst));    

        //postcondition:dst[lo .. hi] is sorted subarray
        assert isSorted(dst, lo, hi);
    }

    // sort from a[lo] to a[hi] using insertion sort
    private static <T> void insertionSort(Comparable<T>[] a, int lo, int hi) {
        for (int i = lo; i <=hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
               exch(a, j, j-1);
            }
        }
    }

    /**
     *  Improvements. We can cut the running time of mergesort sustantially with some carefully considered 
     *  modifications to the implementation.
     *  Use insertion sort for small subarrays.We can improve most recursive algorithms by handing small 
     *  cases differently.Switching to insertion sort for small subarrays will improve the running time of a
     *  typical mergesort implementation by 10 to 15 percent.
     *
     *  Test whether array is already in order. We can reduce the running time to be linear for arrays that 
     *  are already in order by adding a test to skip call to merge() if a[mid] is less than or equal to 
     *  a[mid+1]. With this change, we still do all the recursive calls,but the running time for any sorted
     *  subarray is linear.
     *
     *  Eliminate the copy to the auxiliary array.It is possible to eliminate the time(but not the spaces)
     *  taken to copy to the auxiliary array used for merging. To do so,we use two invocations of the sort
     *  method,one that takes its input from the given array and puts the sorted output in the auxliary
     *  array;the other takes its input from the auxiliary array  and puts the sorted output in the given
     *  array.With this approach,in a bit of mindbending recursive trikery, we can arrange the recursive 
     *  calls such that the computation switches the roles of the input array and the auiliary array at
     *  each level.
     *
     *
     */
    private static <T> void sort(Comparable<T>[] src, Comparable<T>[] dst, int lo, int hi) {
        // Compare to sort method in Merge.java
        // if (hi <= lo) return;
        StdOut.println("sort(src, dst, " + lo  + "," + hi+"), src:" +Arrays.toString(src)+ ", dst:" + Arrays.toString(dst));    
        
        if (hi <= (lo + CUTOFF)) {
            StdOut.println("invoke Insertion Sort before:<" + lo + ", " + hi + "> " + ", dst: " + Arrays.toString(dst));
            //Don't use Insertion sort method, because the upper bound is not included due out of array size
            //Insertion.sort(dst, lo, hi);
            insertionSort(dst, lo, hi);
            StdOut.println("invoke Insertion Sort after: <" + lo + ", " + hi + "> " + ", dst: " + Arrays.toString(dst));    
            return;
        }
        
        int mid = lo + (hi - lo) / 2;
        //exchange param sequence, so that src can keep order with dst
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
    }

    /**
     * Rearranges the array in ascending order, using the natural order
     * @param <T>
     * @param a the array to be sorted
     *
     */
    public static <T> void sort(Comparable<T>[] a) {
        Comparable<T>[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
        assert isSorted(a);
    }

    /**
     * Rearranges the array in ascending order, using the provided order
     * @param <T>
     * @param a the array to be sorted
     * @param comparator the comparator that defines the total order
     */
    public static <T> void sort(Object[] a, Comparator<T> comparator) {
        Object[] aux = a.clone();
        sort(aux, a, 0, a.length-1, comparator);
        assert isSorted(a, comparator);
    }

    private static <T> void merge(Object[] src, Object[] dst, int lo, int mid, int hi, Comparator<T> comparator) {
        //no copy to auxiliary array compared with merge in Merge.java
        //precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays
        assert isSorted(src, lo, mid, comparator);
        assert isSorted(src, mid+1, hi, comparator);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (less(src[j], src[i], comparator)) {//to ensure stability
                dst[k] = src[j++]; 
            } else {
                dst[k] = src[i++]; 
            }
        }

        //postcondition:dst[lo .. hi] is sorted subarray
        assert isSorted(dst, lo, hi, comparator);
    }

    private static <T> void sort(Object[] src, Object[] dst, int lo, int hi, Comparator<T> comparator) {
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
    private static <T> void insertionSort(Object[] a, int lo, int hi, Comparator<T> comparator) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1], comparator); j--) {
                exch(a, j, j-1);
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

