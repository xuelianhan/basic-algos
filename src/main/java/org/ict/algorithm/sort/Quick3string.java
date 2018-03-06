package org.ict.algorithm.sort;


import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;


/**
 * The {@code Quick3string} class provides static methods for sorting an array of 
 * strings using 3-way radix quicksort.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class Quick3string {

    //cutoff to insertion sort
    private static final int CUTOFF = 15;

    //do not instantiate
    private Quick3string() {}

    /**
     * Rearranges the array of strings in ascending order
     *
     * @param a the array to be sorted
     */
    public static void sort(String[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1, 0);
        assert isSorted(a);
    }

    //return the dth character of s, -1 if d == length of s
    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length(); 
        if (d == s.length()) {
            return -1;
        }
        return s.charAt(d);
    }

    // 3-way string quicksort a[lo..hi] starting at dth character
    private static void sort(String[] a, int lo, int hi, int d) {
        //cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
           insertion(a, lo, hi, d); 
           return;
        }

        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;

        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) {
                exch(a, lt++, i++);
            } else if (t > v) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt - 1, d);

        //????
        if (v >= 0) {
            sort(a, lt, gt, d+1);
        }

        //
        sort(a, gt + 1, hi, d);
    }

    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--) {
                exch(a, j, j-1);
            }
        }
    }


    //sort from a[lo] to a[hi], starting at the dth character
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--) {
                exch(a, j, j-1);
            }
        }
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    private static boolean less(String v, String w, int d) {
        assert v.substring(0, d).equals(w.substring(0, d));
        for () {

        }
    }
}

