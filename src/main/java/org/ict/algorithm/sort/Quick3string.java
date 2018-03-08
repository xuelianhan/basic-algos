package org.ict.algorithm.sort;


import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
import org.ict.algorithm.util.StdRandom;


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
        //the running result is different as running every time
        StdRandom.shuffle(a);
        StdOut.println("sort input: " + Arrays.toString(a));
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
           StdOut.println("invoke Insertion Sort before <lo, hi, d>:<" + lo + ", " + hi + ", " + d + ">, a:"+ Arrays.toString(a));
           insertion(a, lo, hi, d); 
           StdOut.println("invoke Insertion Sort after: <lo, hi, d>:<" + lo + ", " + hi + ", " + d + ">, a:"+ Arrays.toString(a));
           return;
        }

        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;

        while (i <= gt) {
            int t = charAt(a[i], d);
            StdOut.println("<lt, i, gt, d, t, v>=" + "<" +lt + ", " + i + ", " + gt + ", " + d + ", " + (char)t + "," + (char)v + ">, a[" + i + "]=" + a[i] +  ", a[" + lo + "]=" + a[lo] + ", a:" + Arrays.toString(a));
            if (t < v) {
                exch(a, lt++, i++);
            } else if (t > v) {
                exch(a, i, gt--);
            } else {
                i++;
            }
            StdOut.println("<lt, i, gt, d, t, v>=" + "<" +lt + ", " + i + ", " + gt + ", " + d + ", " + (char)t + "," + (char)v + ">, a[" + i + "]=" + a[i] +  ", a[" + lo + "]=" + a[lo] + ", a:" + Arrays.toString(a));
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt - 1, d);

        //ASCII-table code from 0 to 127, when the d-bit equals, go to next round d+1
        if (v >= 0) {
            StdOut.println("v:" + v + ", a[" + i + "]:" + a[i] + ", d:" + d);
            sort(a, lt, gt, d+1);
        }

        //
        sort(a, gt + 1, hi, d);
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
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) {
                return true;
            }
            if (v.charAt(i) > w.charAt(i)) {
                return false;
            }
        }    
        return v.length() < w.length();
    }


    // is the array sorted
    private static boolean isSorted(String[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i-1]) < 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        //read in the strings from standard input
        String[] a = StdIn.readAllStrings();
        int n = a.length;

        //sort the strings
        StdOut.println("a before sort: " + Arrays.toString(a));
        sort(a);

        //print the results
        StdOut.println("a after sort: " + Arrays.toString(a));
    }
}

