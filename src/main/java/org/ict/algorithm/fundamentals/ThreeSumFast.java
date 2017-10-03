package org.ict.algorithm.fundamentals;
import java.util.Arrays;
import org.ict.algorithm.util.StdOut;
import org.ict.algorithm.util.In;

/**
 * Compilation: javac org/ict/algorithm/fundamentals/ThreeSumFast.java 
 * Execution: java org/ict/algorithm/fundamentals/ThreeSumFast  ../resources/1Kints.txt 
 * 70
 * The {@code ThreeSumFast} class provides static methods for counting
 * and printing the number of triples in an array of distinct integers
 * that sum to 0(ignoring integer overflow).
 * <p>
 * This implementation uses sorting and binary search and takes time
 * proportional to n^2 log n, where n is the number of integers.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class ThreeSumFast {

    //Do not instantiate
    private ThreeSumFast(){}

    //returns true if the sorted array a[] contains any duplicated integers 
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i-1]) {
                return true;    
            }
        }
        return false;
    }

    /**
     * Prints to standard output the (i, j, k) with{@code i < j < k}
     * such that {@code a[i] + a[j] + a[k] == 0}
     *
     */
    public static void printAll(int[] a) {
        int n = a.length;
        Arrays.sort(a); 
        if (containsDuplicates(a)) {
            throw new IllegalArgumentException("array contains duplicate integers");
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j])); 
                if (k > j) {
                    StdOut.println(a[i] + " " + a[j] + " " + a[k]);
                }
            }
        }
    }


    /**
     * Returns the number of triples (i, j, k) with {@code i < j < k}
     * such that {@code a[i] + a[j] + a[k] == 0}
     *
     */
    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) {
            throw new IllegalArgumentException("array contains duplicate integers");
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int k = Arrays.binarySearch(a ,-(a[i] + a[j]));
                if (k > j) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        In in  = new In(args[0]);
        int[] a = in.readAllInts();
        int count = count(a);
        StdOut.println(count);
    }
}
