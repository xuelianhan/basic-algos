package org.ict.algorithm.util;

import java.util.ArrayList;
import java.util.List;

public class UtilTools {
	public static void showArrayData(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			if (i == (a.length - 1))
				System.out.print(a[i]);
			else
				System.out.print(a[i]+ ",");
		}
	}
	
	public static void testListAddNull() {
	    List<String> list = new ArrayList<String>();
	    list.add(null);
	    System.out.println(list.size());
	}

	public static void main(String[] args) {
	    testListAddNull();
	}

}	
