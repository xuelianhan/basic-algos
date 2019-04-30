package org.ict.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ArrayTest {

	
	public void printArray(Long... a) {
		for (long l : a) {
			System.out.println(l);
		}
	}
	
	@Test
	public void testList() {
		List<Long> a = new ArrayList<Long>();
		for(long i = 0; i < 3; i++) {
			//a.add(i);
			a.add(i);
		}
		printArray(a.toArray(new Long[0]));
	}
}
