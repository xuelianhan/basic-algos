package org.ict.systemdesign.ratelimiter;

import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Fixed window counter algorithm divides the timeline into fixed-size windows and assign a counter to each window.
 * Each request, based on its arriving time, is mapped to a window.
 * If the counter in the window has reached the limit, requests falling in this window should be rejected.
 * For example, if we set the window size to 1 minute.
 * Then the windows are [00:00, 00:01), [00:01, 00:02), ...[23:59, 00:00).
 * Suppose the limit is 2 requests per minute:
 * A request comes at 00:00:24 belongs to window 1, and it increases the window’s counter to 1.
 * The next request comes at 00:00:36 also belongs to window 1 and the window’s counter becomes 2.
 * The next request that comes at 00:00:49 is rejected because the counter has exceeded the limit.
 * Then the request comes at 00:01:12 can be served because it belongs to window 2.
 *
 * 00:00          00:01           00:02
 * |                |               |
 * ------------------------------------------------------>
 *    ^                    ^
 *    |                    |
 *  00:00:24, counter:1   00:01:12, counter:1
 *        ^
 *        |
 *     00:00:36, counter:2
 *           ^
 *           |
 *       00:00:49, counter:3, rejected
 * ---------------------------------------------------------
 *
 * 
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
        /**
         * the window size is one minute.
         */
        long windowKey = System.currentTimeMillis() / 1000 * 60;
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
