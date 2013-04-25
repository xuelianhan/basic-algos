package org.ict.algorithm.sort;

import java.util.Comparator;
import org.ict.algorithm.util.UtilTools;


public class InsertSort {
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
	
	public static void main(String[] args) {
		String[] a = {"abigail", "matt", "sed", "apache", "tmall","dangdang","jd","suning","guomei","amazon","ebey","avon","google","apple","nutch","cloud"}; 
		InsertSort.sortByBackWard(a);
		UtilTools.showArrayData(a);

	}	

}	

