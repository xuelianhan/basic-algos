package org.ict.util;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import com.google.common.collect.Sets;

public class HashSetTest {

	public static void main(String[] args) {
		testIntersection();
	}

	public static void testIntersection() {
		String s1 = "45301,45302,45303,45305,45306,45505,45966,45967,45968,45973,45974,45975,45976,45981,45983,45984,45989,46226,46232,46233,46236,46237,46239,46355,46647,46653,46654,46655,46711,46728,46731,46733,46734,46735,46806,46835";
		String s2 = "45301,45307,45310,45314,45969,45971,45977,45978,45979,45980,45982,45986,45987,45988,45992,45993,46227,46234,46235,46238,46240,46354,46686,46729,46730,46732,46807";
		Set<String> set1 = Stream.of(s1.trim().split("\\s*,\\s*"))
				.collect(Collectors.toSet());
		Set<String> set2 = Stream.of(s2.trim().split("\\s*,\\s*"))
				.collect(Collectors.toSet());
		Set<String> result = Sets.intersection(set1, set2);
		System.out.println(result);
	}



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

	public void testSet() {
		Set<Long> accounts = Sets.newHashSet();
		boolean flag = (accounts.contains(null));
		System.out.println(flag);
	}
}
