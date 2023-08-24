package org.ict.algorithm.company.coupang;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @see <a href="https://medium.com/@avocadi/rate-limiter-sliding-window-log-44acf1b411b9"></a>
 * @see <a href="https://hechao.li/2018/06/25/Rate-Limiter-Part1/"></a>
 * @see <a href="https://www.quinbay.com/blog/rate-limiter-implementation-sliding-log-algorithm"></a>
 * @author sniper
 * @date 23 Jul 2023
 */
public class SlidingWindowLog implements RateLimiter {
    Queue<Long> slidingWindow;

    int timeWindowInSeconds;

    int bucketCapacity;

    public SlidingWindowLog(int timeWindowInSeconds, int bucketCapacity) {
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.bucketCapacity = bucketCapacity;
        slidingWindow = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean allowAccess() {
        long currentTime = System.currentTimeMillis();
        checkAndUpdateQueue(currentTime);
        if (slidingWindow.size() < bucketCapacity) {
            slidingWindow.offer(currentTime);
            return true;
        }
        return false;
    }

    private void checkAndUpdateQueue(long currentTime) {
        if (slidingWindow.isEmpty()) {
            return;
        }
        long diff = (currentTime - slidingWindow.peek()) / 1000;
        while (diff >= timeWindowInSeconds) {
            slidingWindow.poll();
            if (slidingWindow.isEmpty()) {
                break;
            }
            diff = (currentTime - slidingWindow.peek()) / 1000;
        }
    }
}
