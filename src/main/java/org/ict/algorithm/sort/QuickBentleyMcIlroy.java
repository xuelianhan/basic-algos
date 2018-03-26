package org.ict.algorithm.sort;

import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 * $ javac org/ict/algorithm/sort/QuickBentleyMcIlroy.java 
 * Note: org/ict/algorithm/sort/QuickBentleyMcIlroy.java uses unchecked or unsafe operations.
 * Note: Recompile with -Xlint:unchecked for details.
 * $ java org/ict/algorithm/sort/QuickBentleyMcIlroy < ../resources/tiny.txt 
 * true
 * [A, E, E, L, M, O, P, R, S, T, X]
 *
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
public class QuickBentleyMcIlroy {
    // cutoff to insertion sort, must be >= 1
    private static final int INSERTION_SORT_CUTOFF = 8;
    
    //cutoff to median-of-3 partitioning 
    private static final int MEDIAN_OF_3_CUTOFF = 40;

    //This class should not be instantiated.
    private QuickBentleyMcIlroy() {}

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        int n = hi - lo + 1;
        if (n < INSERTION_SORT_CUTOFF) {
            // cutoff to insertion sort
            insertionSort(a, lo, hi);
            return;
        } else if ( n <= MEDIAN_OF_3_CUTOFF) {
            // use median-of-3 as partitioning element 
            int m = median3(a, lo , lo + n/2, hi);
            exch(a, m, lo);
        } else {
            // use Tukey ninther as partitioning element
            // ---------------------------------
            // ^               ^              ^
            // |               |              |
            // lo             mid             hi
            // ---------------------------------
            // ^   ^   ^   ^   ^   ^   ^   ^   ^
            // |   |   |   |   |   |   |   |   |
            //       2n/8     4n/8    6n/8        
            // ---------------------------------               
            int eps = n/8;
            int mid = lo + n/2;
            int m1 = median3(a, lo, lo + eps, lo + eps + eps);
            int m2 = median3(a, mid - eps, mid, mid + eps);
            int m3 = median3(a, hi - eps - eps, hi - eps, hi);
            int ninther = median3(a, m1, m2, m3);
            exch(a, lo, ninther);
        }

        //Bentley-McIlroy 3-way partitioning
        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi)break;
            }
            while (less(v, a[--j])) {
                if (j == lo)break;
            }

            // pointers cross
            if (i == j && eq(a[i], v)) {
                exch(a, ++p, i);
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
            if (eq(a[i], v)) {
                exch(a, ++p, i);
            }
            if (eq(a[j], v)) {
                exch(a, --q, j);
            }
        }

        i = j + 1; 
        for (int k = lo; k <=p; k++) {
            exch(a, k, j--);
        }
        for (int k = hi; k >=q; k--) {
            exch(a, k, i++);
        }
        sort(a, lo, j);
        sort(a, i, hi);
    }

    // sort from a[lo] to a[hi] using insertion sort 
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    // return the index of the median element amoung a[i], a[j] and a[k]
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
               (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i):
               (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        // optimization when reference equal
        if (v == w) return false; 
        return v.compareTo(w) < 0;
    }

    // does v == w ?
    private static boolean eq(Comparable v, Comparable w) {
        // optimization when reference equal
        if (v == w) return true;
        return v.compareTo(w) == 0;
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
        QuickBentleyMcIlroy.sort(a);
        StdOut.println(isSorted(a));
        StdOut.println(Arrays.toString(a));
    }
}
