package org.ict.algorithm.cache;

/**
 * @author sniper
 * @date 2022/6/9 10:32 AM
 */
public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

    boolean remove(K key);

    long size();

}
