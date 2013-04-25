package org.ict.algorithm.util;

public class UtilTools {
	public static void showArrayData(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			if (i == (a.length - 1))
				System.out.print(a[i]);
			else
				System.out.print(a[i]+ ",");
		}
	}
	



}	
