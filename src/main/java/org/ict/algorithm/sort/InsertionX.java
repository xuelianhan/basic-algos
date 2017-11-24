package org.ict.algorithm.sort;
import org.ict.algorithm.util.StdIn;

/**
 *
 *
 * Sorts a sequence of strings from standard input using an optimized
 * version of insertion sort that uses half exchanges instead of 
 * full exchanges to reduce data movement..
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class InsertionX {

    //This class should not be instantiated.
    private InsertionX(){}
    
    public static void sort(Comparable[] a) {
        int n = a.length;    

        //put smallest element in position to serve as sentinel
        int exchanges = 0;
        for (int i = n - 1; i > 0; i--) {
            if (AbstractSortHelper.less(a[i], a[i-1])) {
                AbstractSortHelper.exch(a, i, i-1);
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
            
            while (AbstractSortHelper.less(v, a[j-1])) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }
        assert AbstractSortHelper.isSorted(a);
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        InsertionX.sort(a);
        AbstractSortHelper.show(a);

    }

}
