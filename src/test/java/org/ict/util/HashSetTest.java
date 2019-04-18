package org.ict.util;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public class HashSetTest {

	@Test
	public void testSet() {
		Set<Long> accounts = Sets.newHashSet();
		boolean flag = (accounts.contains(null));
		System.out.println(flag);
	}
}
