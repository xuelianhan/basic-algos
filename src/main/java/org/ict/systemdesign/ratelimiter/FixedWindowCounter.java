package org.ict.systemdesign.ratelimiter;

import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sniper
 * @date 24 Jul 2023
 */
public class FixedWindowCounter extends RateLimiter {

    private final ConcurrentHashMap<Long, AtomicInteger> windows = new ConcurrentHashMap<>();

    private final Deque<Long> windowQueue = new ConcurrentLinkedDeque<>();

    public FixedWindowCounter(int permitsPerSecond) {
        super(permitsPerSecond);
    }

    @Override
    public boolean allowAccess() {
        long windowKey = System.currentTimeMillis() / 1000 * 1000;
        if (!windows.containsKey(windowKey)) {
            windows.put(windowKey, new AtomicInteger(0));
            windowQueue.offer(windowKey);
        }
        return windows.get(windowKey).incrementAndGet() <= super.permitsPerSecond;
    }

    /**
     * Cleaning up of stale entries in the windows.
     * We can run a job to clean stale windows regularly.
     * For instance, schedule a task running at 00:00:00 to remove all the entries created in previous day.
     * @param clearTimeBefore
     */
    public void clearStaleWindows(long clearTimeBefore) {
        while (!windowQueue.isEmpty() && windowQueue.peek() <= clearTimeBefore) {
            long staleItem = windowQueue.poll();
            windows.remove(staleItem);
        }
    }
}
