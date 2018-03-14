package org.ict.algorithm.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @see https://stackoverflow.com/questions/575685/looking-for-simple-java-in-memory-cache
 * @see https://www.javaworld.com/article/2073352/core-java/simply-singleton.html?page=2
 *
 */
public class SimpleCacheManager {
    
    private static SimpleCacheManager instance;
    
    private static Object monitor = new Object();
    
    private Map<String, Object> cache = Collections.synchronizedMap(new HashMap<String, Object>());

    private SimpleCacheManager() {
    }

    public void put(String cacheKey, Object value) {
        cache.put(cacheKey, value);
    }

    public Object get(String cacheKey) {
        return cache.get(cacheKey);
    }

    public void clear(String cacheKey) {
        cache.put(cacheKey, null);
    }

    public void clear() {
        cache.clear();
    }

    public synchronized static SimpleCacheManager getInstance() {
        if (instance == null) {
            synchronized (monitor) {
                if (instance == null) {
                    instance = new SimpleCacheManager();
                }
            }
        }
        return instance;
    }
}
