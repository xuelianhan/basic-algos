package org.ict.algorithm.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @see <a href=http://chriswu.me/blog/a-lru-cache-in-10-lines-of-java/"></a>
 * @see <a href=http://stackoverflow.com/questions/23772102/lru-cache-in-java-with-generics-and-o1-operations"></a>
 * @see <a href=http://www.programcreek.com/2013/03/leetcode-lru-cache-java/"></a>
 * @see <a href="http://www.source-code.biz/snippets/java/6.htm"></a>
 * @see org.ict.algorithm.util.LRUCacheWithMap
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

	@Override
	public synchronized V get(Object key) {
	    return super.get(key);
	}
	
	@Override
	public synchronized V put(K key, V value) {
	    return super.put(key, value);
	}
	
	@Override
	public synchronized void clear() {
	    super.clear();
	}
	
	public synchronized int size() {
	    return super.size();
	}
	
	public int getCacheSize() {
        return cacheSize;
    }

	public static void main(String[] args) {
		LRUCache<String, String> cache = new LRUCache<String, String>(5);
		for (int i = 0; i < 6; i++) {
			cache.put((i + 1) + "", "A" + (i + 1));

		}
		System.out.println(cache);
	}
}
