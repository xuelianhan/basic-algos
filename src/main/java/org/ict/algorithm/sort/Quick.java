package org.ict.algorithm.sort;

import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
import org.ict.algorithm.util.StdRandom;


/**
 *  quicksort
 *
 *  $ javac org/ict/algorithm/sort/Quick.java 
 *  $ more ../resources/tiny.txt 
 *  S O R T E X A M P L E
 *  $ java org/ict/algorithm/sort/Quick < ../resources/tiny.txt
 *  [A, E, E, L, M, O, P, R, S, T, X]
 *
 *  the 0th value is: A
 *  the 1th value is: E
 *  the 2th value is: E
 *  the 3th value is: L
 *  the 4th value is: M
 *  the 5th value is: O
 *  the 6th value is: P
 *  the 7th value is: R
 *  the 8th value is: S
 *  the 9th value is: T
 *  the 10th value is: X
 *
 */
public class Quick extends AbstractSortHelper {
    
    private Quick() {}

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    //quick sort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
        assert isSorted(a, lo, hi);
    }

    //partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    //and return the index j.
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            //find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }
            
            //find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo)break;
            }

            //check if pointers cross
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        //put partitioning item v at a[j]
        exch(a, lo, j);

        //now ,a[lo..j-1] <= a[j] <= a[j+1..hi]
        return j;
    }


    /**
     * Rearranges the array so that {@code a[k]} contains the kth smallest key;
     * {@code a[0]} through {@code a[k-1]} are less than (or equal to) {@code a[k]};
     * and 
     * {@code a[k+1]} through {@code a[n-1]} are greater than (or equal to) {@code a[k]}.
     *
     * @param a the array
     * @param k the rank of the key
     * @return the key of rank {@code k}
     * @throws IllegalArgumentException unless {@code 0 <= k < a.length};
     */
    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if (i > k) {
                hi = i - 1; 
            } else if (i < k) {
                lo = i + 1;
            } else {
                return a[i];
            }
        }
        return a[lo];
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick.sort(a);

        StdOut.println(Arrays.toString(a));

        //shuffle
        StdRandom.shuffle(a);

        StdOut.println();
        for (int i = 0; i < a.length; i++) {
            String ith = (String)Quick.select(a, i);
            StdOut.println("the " + i + "th value is: " + ith);
        }
    }
}
