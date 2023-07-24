package org.ict.systemdesign.ratelimiter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * As discussed previously, the fixed window counter algorithm has a major issue:
 * it allows more requests to go through at the edges of a window.
 * The sliding window log algorithm fixes the issue.
 * It works as following:
 * The algorithm keeps track of request timestamps.
 * Timestamp data is usually kept in cache, such as sorted sets of Redis.
 * When a new request comes in, remove all the outdated timestamps.
 * Outdated timestamps are defined as those older than the start of the current time window.
 * Add timestamp of the new request to the log.
 * If the log size is the same or lower than the allowed count, a request is accepted.
 * Otherwise, it is rejected.
 *
 * Pros:
 * Rate limiting implemented by this algorithm is very accurate.
 * In any rolling window, requests will not exceed the rate limit.
 *
 * Cons:
 * The algorithm consumes a lot of memory because even if a request is rejected,
 * its timestamp might still be stored in memory.
 *
 * @author sniper
 * @date 24 Jul 2023
 */
public class SlidingWindowLog extends RateLimiter {

    private Queue<Long> log = new LinkedList<>();

    public SlidingWindowLog(int permitsPerSecond) {
        super(permitsPerSecond);
    }

    @Override
    public boolean allowAccess() {
        long curTime = System.currentTimeMillis();
        long boundary = curTime - 1000;
        synchronized (log) {
            while (!log.isEmpty() && log.peek() <= boundary) {
                log.poll();
            }
            log.add(curTime);
            return log.size() <= permitsPerSecond;
        }
    }

}
