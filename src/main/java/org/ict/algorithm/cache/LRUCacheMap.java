package org.ict.algorithm.cache;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class LRUCacheMap<K, V> extends AbstractCacheMap<K, V>  {

    private final AtomicLong index = new AtomicLong();
    private final List<Collection<CachedValue<K, V>>> queues =
            new ArrayList<Collection<CachedValue<K, V>>>();

    public LRUCacheMap(int size, long timeToLiveInMillis, long maxIdleInMillis) {
        super(size, timeToLiveInMillis, maxIdleInMillis);

        for (int i = 0; i < Runtime.getRuntime().availableProcessors()*2; i++) {
            Set<CachedValue<K, V>> instance = Collections.synchronizedSet(new LinkedHashSet<CachedValue<K, V>>());
            queues.add(instance);
        }
    }

    @Override
    protected void onValueCreate(CachedValue<K, V> value) {
        Collection<CachedValue<K, V>> queue = getQueue(value);
        queue.add(value);
    }

    private Collection<CachedValue<K, V>> getQueue(CachedValue<K, V> value) {
        return queues.get(Math.abs(value.hashCode() % queues.size()));
    }

    @Override
    protected void onValueRemove(CachedValue<K, V> value) {
        Collection<CachedValue<K, V>> queue = getQueue(value);
        queue.remove(value);
    }

    @Override
    protected void onValueRead(CachedValue<K, V> value) {
        Collection<CachedValue<K, V>> queue = getQueue(value);
        // move value to tail of queue
        if (queue.remove(value)) {
            queue.add(value);
        }
    }

    @Override
    protected void onMapFull() {
        int startIndex = -1;
        while (true) {
            int queueIndex = (int) Math.abs(index.incrementAndGet() % queues.size());
            if (queueIndex == startIndex) {
                return;
            }
            if (startIndex == -1) {
                startIndex = queueIndex;
            }
            Collection<CachedValue<K, V>> queue = queues.get(queueIndex);
            synchronized (queue) {
                Iterator<CachedValue<K, V>> iter = queue.iterator();
                if (iter.hasNext()) {
                    CachedValue<K, V> value = iter.next();
                    iter.remove();
                    map.remove(value.getKey(), value);
                    return;
                }
            }
        }
    }

    @Override
    public void clear() {
        for (Collection<CachedValue<K, V>> collection : queues) {
            collection.clear();
        }
        super.clear();
    }
}
