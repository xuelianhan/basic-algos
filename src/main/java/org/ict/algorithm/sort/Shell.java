package org.ict.algorithm.sort;


/**
 * The {@code Shell} class provides static methods for sorting an
 * array using Shellsort with Knuth's increment sequence(1, 4, 13, 40, ...)
 * <p>
 * For additional documentation, see<a href="https://algs4.cs.princeton.edu/21elementary">Section 2.1</a>
 * of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and kevin Wayne.
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Shell extends AbstractSortHelper {
    
    //This class should not be instantiated.
    private Shell(){}
   
    public static void sort(Comparable[] a) {
        int n = a.length;

        //3x + 1 increment sequence: 1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3) h = 3 * h + 1;

        while (h >= 1) {
            //h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            assert isHsorted(a, h);
            h /= 3;
        }
        assert isSorted(a);
    }
}
