package org.ict.algorithm.sort;

import org.ict.algorithm.util.StdIn;

/**
 * $ javac org/ict/algorithm/sort/Merge.java
 * $ more ../resources/tiny.txt 
 * S O R T E X A M P L E
 * $ java org/ict/algorithm/sort/Merge < ../resources/tiny.txt
 *  A
 *  E
 *  E
 *  L
 *  M
 *  O
 *  P
 *  R
 *  S
 *  T
 *  X
 *
 */

/**
 * The {@code Merge} class provides static methods for sorting an array using mergesort.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/22mergesort">Section 2.2</a>
 * of <i>Algorithms, 4th Edition</i>
 * For an optimized version, see{@link MergeX}
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Merge extends AbstractSortHelper {
    //This class should not be instantiated.
    private Merge() {}

    //stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        //precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays 
        assert AbstractSortHelper.isSorted(a, lo, mid); 
        assert AbstractSortHelper.isSorted(a, mid+1, hi); 

        //copy to aux[]
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
        //postcondition: a[lo .. hi] is sorted
        assert AbstractSortHelper.isSorted(a, lo, hi);
    }

    //mergesort a[lo .. hi] using auxiliary array aux[lo .. hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Rearranges the array in ascending order, using the natural order
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
        assert AbstractSortHelper.isSorted(a);
    }


    /**
     * Index mergesort
     */
    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {
        //copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = index[k];
        }

        //merge back to a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                index[k] = aux[j++];
            } else if (j > hi) {
                index[k] = aux[i++];
            } else if (AbstractSortHelper.less(a[aux[j]], a[aux[i]])) {
                index[k] = aux[j++];
            } else {
                index[k] = aux[i++];
            }
        }
    }

    /**
     * Returns a permutation that gives the elements in the array in ascending order
     * @param a the array 
     * @return a permutation {@code p[]} such that {@code a[p[0]]}, {@code a[p[1]]}
     * ..., {@code a[p[N-1]]} are in ascending order
     */
    public static int[] indexSort(Comparable[] a) {
        int n = a.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }

        int[] aux = new int[n];
        sort(a, index, aux, 0, n-1);
        return index;
    }

    //mergesort a[lo .. hi] using auxiliary array aux[lo .. hi]
    private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, index, aux, lo , mid);
        sort(a, index, aux, mid+1 , hi);
        merge(a, index, aux, lo, mid, hi);
    }

    /**
     * Reads in a sequence of strings from standard input; mergesorts them;
     * and prints theme to standard output in ascending order.
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Merge.sort(a);
        AbstractSortHelper.show(a);
    }
}
