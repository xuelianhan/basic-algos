/**
 * package java.util;
 * public interface Comparator<T>{
 * 	int compare(T o1, T o2);
 * 	boolean equals(Object objObject obj);
 * }
 * 
 * package java.lang;
 * public interface Comparable<T>{
 * 	int compareTo(T o);
 * }
 * 
 * the following implements refer to the Algorithms 4th Edition by Robert Sedgewick and Kevin Wayne
 * you can see the online resources at this url:
 * http://algs4.cs.princeton.edu/21elementary/Selection.java.html
 *
 *
 */
package org.ict.algorithm.sort;
import java.util.Comparator;

import org.ict.algorithm.util.StdIn;

public class Selection {
	
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
		    int min = i;
		    for (int j = i+1; j < N; j++) {
			    if (AbstractSortHelper.less(a[j],a[min])) 
		        	min = j;		  
		    }
		    if (i != min)
		        AbstractSortHelper.exch(a, min, i);
		    assert AbstractSortHelper.isSorted(a,0,i);
		}	
		assert AbstractSortHelper.isSorted(a);
	}

	public static void sort(Object[] a, Comparator c) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i+1; j < N; j++) {
				if (AbstractSortHelper.less(c,a[j],a[min]))
					min = j;
			}
			if (i != min)
			    AbstractSortHelper.exch(a,min,i);
			assert AbstractSortHelper.isSorted(a,c,0,i);
		}
		assert AbstractSortHelper.isSorted(a, c);
	}

	
	/**
	 * Reads in a sequence of strings from standard input; selection sorts them;
	 * and prints them to standard output in ascending order.
	 * @param args
	 */
	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		Selection.sort(a);
		AbstractSortHelper.show(a);
	}
}
