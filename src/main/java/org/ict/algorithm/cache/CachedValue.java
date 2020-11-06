package org.ict.algorithm.cache;

public interface CachedValue<K, V> extends ExpirableValue {

    K getKey();

    V getValue();

}
