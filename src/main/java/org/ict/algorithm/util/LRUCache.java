package org.ict.algorithm.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * http://chriswu.me/blog/a-lru-cache-in-10-lines-of-java/
 * http://stackoverflow.com/questions/23772102/lru-cache-in-java-with-generics-and-o1-operations
 * http://www.programcreek.com/2013/03/leetcode-lru-cache-java/
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> extends LinkedHashMap<K,V> {

	private static final long serialVersionUID = 707710759143902306L;
	
	private int cacheSize;
	
	public LRUCache(int cacheSize) {
		super(16, 0.75f, true);
		this.cacheSize = cacheSize;
	}
	
	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() >= cacheSize;
	}

	public static void main(String[] args) {
		LRUCache<String, String> cache = new LRUCache<String, String>(5);
		for (int i = 0; i < 6; i++) {
			cache.put((i + 1) + "", "A" + i);
		}
		System.out.println(cache);
	}
}
