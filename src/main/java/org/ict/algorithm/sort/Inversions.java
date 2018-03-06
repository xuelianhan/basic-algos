package org.ict.algorithm.sort;

import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 *  The {@code Inversions} class provides static methods to count the
 *  number of <em>inversions</em> in either an array of integers or 
 *  comparables
 *  An inversion in an array {@code a[]} is a pair of indicies {@code i}
 *  and {@code j} such that {@code i < j} and {@code a[i] > a[j]}
 *  <p>
 *  This implementation uses a generalization of mergesort
 *  The <em>count</em> operation takes time proportional to <em>nlogn</em>,
 *  where <em>n</em> is the number of keys in the array
 *  
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 */
public class Inversions {

    public static void main(String[] args) {
        int[] a = StdIn.readAllInts();
        int n = a.length;
        Integer[] b = new Integer[n];
        for (int i = 0; i < n; i++) {
            b[i] = a[i];
        }
        StdOut.println(Inversions.count(a));
        StdOut.println(Inversions.count(b));
    }

    // do not instantiate
    private Inversions() {}

    // merge and count
    private static long merge(int[] a, int[] aux, int lo, int mid, int hi) {
        long inversions = 0;

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
                //before invoking merge, the left half is sorte, j=mid+1, so j-i is the inversions
                inversions += (mid - i + 1);
            } else {
                a[k] = aux[i++];
            }
        }
        return inversions;
    }

    //return the number of inversions in the subarray b[lo..hi]
    //side effect b[lo..hi] is rearranged in ascending order
    private static long count(int[] a, int[] b, int[] aux, int lo, int hi) {
        long inversions = 0;
        if (hi <= lo) {
            return 0;
        }
        int mid = lo +  (hi - lo) / 2;
        inversions += count(a, b, aux, lo, mid);
        inversions += count(a, b, aux, mid+1, hi);
        //why use b in merge? because a is the original array, it should not be modified and it is prepared 
        //to use in brute method which not modify the input array.
        inversions += merge(b, aux, lo, mid, hi);

        //check inversions get by merge is equal to brute method for validating the correctness
        assert inversions == brute(a, lo, hi);
        return inversions;
    }

    /**
     *  Returns the number of inversions in the integer array
     *  The argument array is not modified
     *  @param a the array
     *  @return the number of inversions in the array.
     *          An inversion is a pair of indicies {@code i} 
     *          and {@code j} such that {@code i < j} 
     *          and {@code a[i] >  a[j]}
     *
     */
    public static long count(int[] a) {
        int[] b = new int[a.length];
        int[] aux = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        long inversions = count(a, b, aux, 0, a.length - 1);
        return inversions;
    }

    //count number of inversions in a[lo..hi] via brute force (for debugging only)
    private static long brute(int[] a, int lo, int hi) {
        long inversions = 0;
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                if (a[j] > a[i]) {
                    inversions++;
                }
            }
        }
        return inversions;
    }

    /**
     *  merge and count(Comparable version)
     *  @see https://stackoverflow.com/questions/21544716/implementing-comparable-with-a-generic-class
     *  @see https://docs.oracle.com/javase/tutorial/java/generics/boundedTypeParams.html
     *  @see https://docs.oracle.com/javase/tutorial/java/generics/inheritance.html
     *  @see https://docs.oracle.com/javase/tutorial/java/generics/subtyping.html
     *  @see https://docs.oracle.com/javase/tutorial/java/generics/upperBounded.html
     *  @see 
     */
    private static <Key extends Comparable<Key>> long merge(Key[] a, Key[] aux, int lo, int mid, int hi) {
        long inversions = 0; 

        //copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        //merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
                inversions += (mid - i + 1);
            } else {
                a[k] = aux[i++];
            }
        }
        return inversions;   
    }

    // is v < w ?
    private static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return (v.compareTo(w) < 0); 
    }

    /**
     * Returns the number os inversions in the comparable array.
     * The argument array is not modified.
     * @param a the array
     * @param <Key> the inferred type of the elements in the array
     * @return the number of inversions in the array. An inversion is a 
     * pair of indices {@code i} and {@code j} such that {@code i < j} 
     * and {@code a[i].compareTo(a[j]) > 0}.
     *
     */
    public static <Key extends Comparable<Key>> long count(Key[] a) {
        Key[] b = a.clone();
        Key[] aux = a.clone();
        long inversions = count(a, b, aux, 0, a.length - 1);
        return inversions;
    }

    //return the number of inversions in the subarray b[lo..hi]
    //side effect b[lo..hi] is rearranged in ascending order
    private static <Key extends Comparable<Key>> long count(Key[] a, Key[] b, Key[] aux, int lo, int hi) {
        long inversions = 0;
        if(hi <= lo) {
            return 0;
        }
        int mid = lo + (hi - lo) / 2;
        inversions += count(a, b, aux, lo, mid);
        inversions += count(a, b, aux, mid+1, hi);
        inversions += merge(b, aux, lo, mid, hi);
        assert inversions == brute(a, lo, hi);
        return inversions;
    }

    private static <Key extends Comparable<Key>> long brute(Key[] a, int lo, int hi) {
        long inversions = 0;
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                if (less(a[j], a[i])) {
                    inversions++;
                }
            }
        }
        return inversions;
    }
}
