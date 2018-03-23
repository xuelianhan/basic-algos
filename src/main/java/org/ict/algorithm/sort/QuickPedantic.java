package org.ict.algorithm.sort;

import java.util.Arrays;
import org.ict.algorithm.util.StdRandom;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;
/**
 * Sorts a sequence of of strings from standard input using quicksort.
 * This version uses static generics for type safety.
 *
 * $ javac org/ict/algorithm/sort/QuickPedantic.java 
 * $ java org/ict/algorithm/sort/QuickPedantic < ../resources/tiny.txt 
 * [A, E, E, L, M, O, P, R, S, T, X]
 * true
 *
 * A
 * E
 * E
 * L
 * M
 * O
 * P
 * R
 * S
 * T
 * X
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class QuickPedantic {

    // quicksort the array
    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
    
    // quicksort the subarray from a[lo] to a[hi]
    private static <Key extends Comparable<Key>> void sort(Key[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    // partition the subarray a[lo .. hi] by returning an index j
    // so that a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    private static <Key extends Comparable<Key>> int partition(Key[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Key v = a[lo];
        while (true) {
            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }
            // find item on hi to swap
            while (less(v, a[--j])) {
                // redundant since a[lo] acts as sentinel
                if (j == lo) break; 
            }
            // check if pointer cross
            if ( i >= j) {
                break;
            }
            exch(a, i, j);
        }
        // put v = a[j] into position
        exch(a, lo, j);

        // with a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    /**
     * Rearranges the elements in a so that a[k] is the kth smallest element,
     * and a[0] through a[k-1] are less than or equal to a[k], and
     * a[k+1] through a[n-1] are greater than or equal to a[k].
     */
    public static <Key extends Comparable<Key>> Key select(Key[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IndexOutOfBoundsException("selected element out of bounds!");
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

    /**
     * Helper sorting functions.
     */
    // is v < w ?
    private static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    //exchange a[i] and a[j]
    private static <Key extends Comparable<Key>> void exch(Key[] a, int i, int j) {
        Key swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static <Key extends Comparable<Key>> boolean isSorted(Key[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        QuickPedantic.sort(a);
        StdOut.println(Arrays.toString(a));
        StdOut.println(isSorted(a));

        StdOut.println();
        for (int i = 0; i < a.length; i++) {
            String ith = QuickPedantic.select(a, i);
            StdOut.println(ith);
        }
    }
}
