package org.ict.util;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public class HashSetTest {
	
	@Test
	public void testDiff() {
		Set<Long> oldSearchResult = Sets.newHashSet();
		Set<Long> newSearchResult = Sets.newHashSet();
		
		for(long i = 0; i < 3; i++) {
			oldSearchResult.add(i);
			newSearchResult.add(i);
		}
		for(long i = 3; i < 6; i++) {
			oldSearchResult.add(i);
			
		}
		
		for(long i = 6; i < 9; i++) {
			newSearchResult.add(i);
		}
		System.out.println("oldSearchResult:" + oldSearchResult);
		System.out.println("newSearchResult:" + newSearchResult);
		
		Set<Long> set1 = Sets.difference(oldSearchResult, newSearchResult);
		Set<Long> set2 = Sets.difference(newSearchResult, oldSearchResult);
		
		System.out.println("set1:" + set1);
		System.out.println("set2:" + set2);
	}

	@Test
	public void testSet() {
		Set<Long> accounts = Sets.newHashSet();
		boolean flag = (accounts.contains(null));
		System.out.println(flag);
	}
}
