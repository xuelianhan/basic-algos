package org.ict.algorithm.sort;
import java.util.Comparator;

import org.ict.algorithm.util.StdIn;

public class Insertion {
    
    //This class should not be instantiated.
    private Insertion(){}
    
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i = 1; i < N; i++) {
			for (int j = i; j > 0 && AbstractSortHelper.less(a[j], a[j-1]); j--) {
			    AbstractSortHelper.exch(a, j, j-1);
			}
			assert AbstractSortHelper.isSorted(a, 0, i);
		}
		assert AbstractSortHelper.isSorted(a);
	}
	
	/**
	 * Rearranges the subarray a[lo] to a[hi] in ascending order, using the natural order.
	 * @param a the array to be sorted
     * @param lo left endpoint (inclusive)
     * @param hi right endpoint (exclusive)
	 */
	public static void sort(Comparable[] a, int lo, int hi) {
	    for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo && AbstractSortHelper.less(a[j], a[j-1]); j--) {
                AbstractSortHelper.exch(a, j, j-1);
            }
        }
        assert AbstractSortHelper.isSorted(a, lo, hi);
	}
    
    /**
     * Rearranges the array in ascending order, using a comparator.
     * @param a the array
     * @param comparator the comparator specifying the order
     */
    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && AbstractSortHelper.less(comparator, a[j], a[j-1]); j--) {
                AbstractSortHelper.exch(a, j, j-1);
            }
            assert AbstractSortHelper.isSorted(a, comparator, 0, i);
        }
        assert AbstractSortHelper.isSorted(a, comparator);
    }

    /**
     * Rearranges the subarray a[lo] to a[hi] in ascending order,
     * using a comparator.
     * @param a the array
     * @param lo left endpoint (inclusive)
     * @param hi right endpoint (exclusive)
     */
    public static void sort(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo && AbstractSortHelper.less(comparator, a[j], a[j-1]); j--) {
                AbstractSortHelper.exch(a, j, j-1);
            }
        }
        assert AbstractSortHelper.isSorted(a, comparator, lo, hi);
    }

    /**
     * Returns a permutation that gives the elements in the array
     * in ascending order.
     * @param a the array.
     * @return a permutation {@code p[]} such that {@code a[p[0]]},
     * {@code a[p[1]]}, ..., {@code a[p[n-1]]}
     * are in ascending order
     *
     */
    public static int[] indexSort(Comparable[] a) {
        int n = a.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && AbstractSortHelper.less(a[index[j]], a[index[j-1]]); j--) {
                AbstractSortHelper.exch(index, j, j-1);
            }
        }
        return index;
    }
	
	public static void main(String[] args) {
	    String[] a = StdIn.readAllStrings();
        Insertion.sort(a);
        AbstractSortHelper.show(a);
	}	
}	
