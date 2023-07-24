package org.ict.systemdesign.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The sliding window counter algorithm is a hybrid approach that combines the fixed window counter
 * and sliding window log. The algorithm can be implemented by two different approaches.
 * Sliding window counter is similar to fixed window counter,
 * but it smooths out bursts of traffic by adding a weighted count in previous window to the count in current window.
 * For example, suppose the limit is 10 per minute.
 * There are 9 requests in window [00:00, 00:01) and 5 requests in window [00:01, 00:02).
 * For a request arrives at 00:01:15, which is at 25% position of window [00:01, 00:02),
 * we calculate the request count by the formula: 9 x (1 - 25%) + 5 = 11.75 > 10.
 * Thus we reject this request.
 * Even though both windows don’t exceed the limit,
 * the request is rejected because the weighted sum of previous and current window does exceed the limit.
 *
 * @see <a href="https://medium.com/@avocadi/rate-limiter-sliding-window-counter-7ec08dbe21d6"></a>
 * @author sniper
 * @date 24 Jul 2023
 */
public class SlidingWindowCounter extends RateLimiter {

    private ConcurrentHashMap<Long, AtomicInteger> windows = new ConcurrentHashMap<>();

    public SlidingWindowCounter(int permitsPerSecond) {
        super(permitsPerSecond);
    }

    @Override
    public boolean allowAccess() {
        long curTime = System.currentTimeMillis();
        //todo
        long curWindowKey = curTime / 1000 * 1000;
        windows.putIfAbsent(curWindowKey, new AtomicInteger(0));
        long preWindowKey = curWindowKey - 1000;
        AtomicInteger preCnt = windows.get(preWindowKey);
        if (preCnt == null) {
            return windows.get(curWindowKey).incrementAndGet() <= permitsPerSecond;
        }
        double preWeight = 1 - (curTime - curWindowKey) / 1000.0;
        long cnt = (long)(preCnt.get() * preWeight + windows.get(curWindowKey).incrementAndGet());
        return cnt <= permitsPerSecond;
    }

}
