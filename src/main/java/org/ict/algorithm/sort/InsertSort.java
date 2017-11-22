package org.ict.algorithm.sort;
import org.ict.algorithm.util.UtilTools;

public class InsertSort {
	public static void sortByBackWard(Comparable[] a) {
		int N = a.length;
		for (int i = 1; i < N; i++) {
			if (AbstractSortHelper.less(a[i], a[i-1])) {
				Comparable[] temp = new Comparable[1];
				temp[0] = a[i];
				int j;
				for (j = i-1; AbstractSortHelper.less(temp[0],a[j]); j--) {
					a[j+1] = a[j];
				}	
				a[j+1] = temp[0];
			}	
		}	
	}	

	public static void sortByExchange(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = i; j > 0 && AbstractSortHelper.less(a[j], a[j-1]); j--) {
			    AbstractSortHelper.exch(a, j, j-1);
			}
			assert AbstractSortHelper.isSorted(a, 0, i);
		}
		assert AbstractSortHelper.isSorted(a);
	}	
	
	
	public static void main(String[] args) {
		String[] a = {"abigail", "matt", "sed", "apache", "tmall","dangdang","jd","suning","guomei","amazon","ebey","avon","google","apple","nutch","cloud"}; 
	//	InsertSort.sortByBackWard(a);
		InsertSort.sortByExchange(a);	
		UtilTools.showArrayData(a);
	}	
}	
