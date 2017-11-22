package org.ict.algorithm.sort;
import org.ict.algorithm.util.StdOut;
import java.util.Comparator;

/**
 * there is no notion of static Inheritance in Java as inheritance always is in context of Object level.
 * Only member methods (non-static) can be inherited by sub classes having appropriate access modifiers.
 * @see https://stackoverflow.com/questions/16079745/java-generic-static-methods
 */
public abstract class AbstractSortHelper {
    //is v < w ? 
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    //is v < w ? 
    public static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }
    // exchange a[i] and a[j]
    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    //is the array a[] sorted?
    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }
    //is the array sorted from a[lo] to a[hi]
    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }
    //is the array sorted from a[lo] to a[hi]
    public static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(comparator, a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }

    //is the array a[] sorted?
    public static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, comparator, 0, a.length - 1); 
    }
    //is the array h-sorted? 
    public static boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++) {
            if (less(a[i], a[i-h])) {
                return false;
            }
        }
        return true;
    }
    //print array to standard output 
    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
}
