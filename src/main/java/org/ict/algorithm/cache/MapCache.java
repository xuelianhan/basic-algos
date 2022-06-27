package org.ict.algorithm.cache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface MapCache<K, V> extends Map<K, V> {
    V put(K key, V value, long ttl, TimeUnit ttlUnit, long maxIdleTime, TimeUnit maxIdleUnit);
}
