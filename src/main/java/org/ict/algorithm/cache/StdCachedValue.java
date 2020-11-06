package org.ict.algorithm.cache;

public class StdCachedValue<K, V> implements CachedValue<K, V> {
    private final K key;
    private final V value;

    private long ttl;
    private long maxIdleTime;

    private long creationTime;
    private long lastAccess;

    public StdCachedValue(K key, V value, long ttl, long maxIdleTime) {
        this.value = value;
        this.ttl = ttl;
        this.key = key;
        this.maxIdleTime = maxIdleTime;

        if (ttl != 0 || maxIdleTime != 0) {
            creationTime = System.currentTimeMillis();
            lastAccess = creationTime;
        }
    }

    @Override
    public boolean isExpired() {
        if (maxIdleTime == 0 && ttl == 0) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        if (ttl != 0 && creationTime + ttl < currentTime) {
            return true;
        }
        if (maxIdleTime != 0 && lastAccess + maxIdleTime < currentTime) {
            return true;
        }
        return false;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        lastAccess = System.currentTimeMillis();
        return value;
    }

    @Override
    public String toString() {
        return "CachedValue [key=" + key + ", value=" + value + "]";
    }
}
