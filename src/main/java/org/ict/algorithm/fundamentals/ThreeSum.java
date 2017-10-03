package org.ict.algorithm.fundamentals;
import org.ict.algorithm.util.StdOut;
import org.ict.algorithm.util.In;

/**
 * Compilation: javac org/ict/algorithm/fundamentals/ThreeSum.java
 * Execution: java org/ict/algorithm/fundamentals/ThreeSum ../resources/1Kints.txt
 *
 * The {@code ThreeSum} class provides static methods for counting
 * and printing the number of triples in an array of integers that
 * sum to 0(ignoring integer overflow).
 * 
 * This implementation uses a triply nested loop and takes proportional
 * to n^3, where n is the number of integers.
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 */
public class ThreeSum {
   private ThreeSum(){} 

   /**
    * Prints to standard output the (i, j, k) with {@code i < j < k}
    * such that {@code a[i] + a[j] + a[k] == 0}
    *
    */

   public static void printAll(int[] a) {
       int n = a.length;
       for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        StdOut.println(a[i] + " " + a[j] + " " + a[k]);
                    }
                }
            }
       }
   }


   public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if ((a[i] + a[j] + a[k]) == 0) {
                       count++; 
                    }
                }
            }
        }
        return count;
   }


   public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        Stopwatch timer = new Stopwatch();
        int count = count(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
        StdOut.println(count);
   }

}
