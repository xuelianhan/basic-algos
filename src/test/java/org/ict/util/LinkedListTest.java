package org.ict.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LinkedListTest {

	public static void main(String[] args) {
		testForeach();
		//testRemove();
	} 
	
	public static void testForeach() {
		List<Integer> numList = Arrays.asList(10,21,31,40,59,60);
		numList.forEach( x-> {
		    if( x%2 == 0) {
		        return; // only skips this iteration.
		    }
		    System.out.println(x);
		});
	}
	
	public static void testRemove() {
		Queue<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < 5; i++) {
			list.add(i);
		}
		System.out.println(list);
		Iterator<Integer> iter = list.iterator();
		while(iter.hasNext()) {
			Integer next = iter.next();
			if (next == 3) {
				iter.remove();
			}
		}
		System.out.println(list);
		Integer[] a = new Integer[list.size()];
		list.toArray(a);
		System.out.println(Arrays.toString(a));
	}
}
