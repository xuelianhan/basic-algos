package org.ict.algorithm.sort;

import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
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

    private void sort(Comparable[] a, lo, hi) {
        int n = hi - lo + 1;
        if (n < INSERTION_SORT_CUTOFF) {
            // cutoff to insertion sort
            insertionSort(a, lo, hi);
            return;
        } else if ( n <= median_of_3_cutoff) {
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
            
        }
        


    }


}
