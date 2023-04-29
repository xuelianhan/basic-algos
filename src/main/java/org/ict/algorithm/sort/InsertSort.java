package org.ict.algorithm.sort;

import java.util.Comparator;
import org.ict.algorithm.util.UtilTools;


public class InsertSort 
{
	public static void sortByBackWard(Comparable[] a) {
		int N = a.length;
		for (int i = 1; i < N; i++) {
			if (less(a[i], a[i-1])) {
				Comparable[] temp = new Comparable[1];
				temp[0] = a[i];
				int j;
				for (j = i-1; less(temp[0],a[j]); j--) {
					a[j+1] = a[j];
				}	
				a[j+1] = temp[0];
			}	
		}	
	}	

	public static void sortByExchange(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
				exchange(a, j, j-1);
			}
			assert isSorted(a, 0, i);
		}
		assert isSorted(a);
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
	
	private static void exchange(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	private static boolean isSorted(Comparable[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(Comparable[] a, int low, int high) {
		for (int i = low + 1; i <= high; i++) {
			if (less(a[i], a[i-1]))
				return false;
		}
		return true; 
	}
	
	private static boolean isSorted(Object[] a, Comparator c) {
		return isSorted(a, c, 0, a.length - 1);
	}

	private static boolean isSorted(Object[] a, Comparator c, int low, int high) {
		for(int i = low + 1; i <= high; i++) {
			if (less(c, a[i], a[i-1]))
				return false;
		}
		return true;
	}
	public static void main(String[] args) {
		String[] a = {"abigail", "matt", "sed", "apache", "tmall","dangdang","jd","suning","guomei","amazon","ebey","avon","google","apple","nutch","cloud"}; 
	//	InsertSort.sortByBackWard(a);
		InsertSort.sortByExchange(a);	
		UtilTools.showArrayData(a);

	}	

}	

