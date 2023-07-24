package org.ict.systemdesign.ratelimiter;

import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @see <a href="https://medium.com/@avocadi/rate-limiter-leaky-bucket-be68c6476385"></a>
 * @author sniper
 * @date 23 Jul 2023
 */
public class LeakyBucket extends RateLimiter {

    private int bucketCapacity;

    private Deque<Long> queue;

    public LeakyBucket(int permitsPerSecond, int bucketCapacity) {
        super(permitsPerSecond);
        this.bucketCapacity = bucketCapacity;
        queue = new ConcurrentLinkedDeque<>();
    }

    @Override
    public boolean allowAccess() {
        if (queue.size() > bucketCapacity) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        queue.offer(currentTime);
        return true;
    }

    public void process() throws InterruptedException {
        while (true) {
            // Leak by fixed speed.
            Thread.sleep(1000/super.permitsPerSecond);
            if (queue.isEmpty()) {
                return;
            }
            Long cur = queue.poll();
            //process cur
        }
    }
}
