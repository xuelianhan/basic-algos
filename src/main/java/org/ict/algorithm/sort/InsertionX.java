package org.ict.algorithm.sort;

import java.util.Arrays;
import org.ict.algorithm.util.StdIn;
import org.ict.algorithm.util.StdOut;

/**
 *
 *
 * Sorts a sequence of strings from standard input using an optimized
 * version of insertion sort that uses half exchanges instead of 
 * full exchanges to reduce data movement..
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class InsertionX extends AbstractSortHelper {
    
    //This class should not be instantiated.
    private InsertionX(){}
    
    public static void sort(Comparable[] a) {
        int n = a.length;    

        //put smallest element in position to serve as sentinel
        int exchanges = 0;
        for (int i = n - 1; i > 0; i--) {
            if (less(a[i], a[i-1])) {
                exch(a, i, i-1);
                exchanges++;
            }
        }
        if (exchanges == 0) {
            return;
        }

        //insertion sort with half-exchanges
        for (int i = 2; i < n; i++) {
            Comparable v = a[i];
            int j = i;
            //wrong code
            //while (AbstractSortHelper.less(a[j], a[j-1])) {
            //wrong code
            while (less(v, a[j-1])) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }
        assert isSorted(a);
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        InsertionX.sort(a);
        StdOut.println(Arrays.toString(a));
    }

}
