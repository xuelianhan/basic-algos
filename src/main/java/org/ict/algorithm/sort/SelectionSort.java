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


public class SelectionSort {
	
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
		    int min = i;
		    for (int j = i+1; j < N; j++) {
			    if (less(a[j],a[min])) 
		        	min = j;		  
		    }
		    if (i != min)
			    exchange(a, min, i);
		    assert isSorted(a,0,i);
		}	
		assert isSorted(a);
	}

	public static void sort(Object[] a, Comparator c) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i+1; j < N; j++) {
				if (less(c,a[j],a[min]))
					min = j;
			}
			if (i != min)
				exchange(a,min,i);
			assert isSorted(a,c,0,N);
		}

	}

	private static boolean less(Comparable v, Comparable w) {
		return (v.compareTo(w) < 0);	
	}

	private static boolean less(Comparator c, Object v, Object w) {
		return (c.compare(v, w) < 0);
	}

	private static void exchange(Object[] a, int i, int j) {
		Object tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static void checkRange(int low, int high, int  length) {
		if (high < low) 
			throw new IllegalArgumentException("high:("+high+") < low:("+low+")");
		if (low < 0)
			throw new ArrayIndexOutOfBoundsException(low);
		if (high < length)
			throw new ArrayIndexOutOfBoundsException(high);

	}

	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(Comparable[] a, int low, int high) {
		checkRange(low,high,a.length);
		for (int i = low; i < high; i++) {
			if (less(a[i+1], a[i])) {
				return false;
		        }
		}
		return true;
	}
	
	private static boolean isSorted(Object[] a, Comparator c) {
		return isSorted(a,c,0,a.length);
	}

	private static boolean isSorted(Object[] a, Comparator c, int low, int high) {
		checkRange(low,high,a.length);
		for (int i = low; i < high; i++) {
			if (less(c,a[i+1],a[i])) {
				return false;
			}
	        }
		return true;
	}

	private static void showArrayData(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			if (i == (a.length - 1))
			    System.out.print(a[i]);
			else 
			    System.out.print(a[i]+ ",");
		}	
	}

	public static void main(String[] args) {
		/*
		String[] a = {"abigail", "matt", "sed", "apache", "tmall","dangdang","jd","suning","guomei","amazon","ebey","avon","google","apple","nutch","cloud"};	
		sort(a);
		showArrayData(a);
		*/
	}
}
