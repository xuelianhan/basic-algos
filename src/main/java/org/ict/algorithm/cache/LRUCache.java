package org.ict.algorithm.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sniper
 * @date 2022/6/9 10:33 AM
 */
public class LRUCache<K, V> implements Cache<K, V> {

    private final LinkedHashMap<K, V> cache;

    public LRUCache(final int maxSize) {
        cache = new LinkedHashMap<K, V>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return this.size() > maxSize;
            }
        };
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public boolean remove(K key) {
        return cache.remove(key) != null;
    }

    @Override
    public long size() {
        return cache.size();
    }
}
