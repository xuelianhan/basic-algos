package org.ict.systemdesign.ratelimiter;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * If we map it our limiting requests to server use case, water drops from the faucets are the requests,
 * the bucket is the request queue and the water drops leaked from the bucket are the responses.
 * Just as the water dropping to a full bucket will overflow,
 * the requests arrive after the queue becomes full will be rejected.
 * As we can see, in the leaky bucket algorithm,
 * the requests are processed at an approximately fixed rate,
 * which smooths out bursts of requests.
 * Even though the incoming requests can be burst, the outgoing responses are always at a same rate.
 * ----------------------------------------------
 * It is usually implemented with a FIFO queue.
 * The algorithm works as following:
 * When a request arrives, the system checks if the queue is full.
 * If it is not full, the request is added to the queue.
 * Otherwise, the request is dropped.
 * Requests are pulled from the queue and processed at regular intervals.
 * Leaking bucket algorithm takes the following two parameters:
 * Bucket size: it is equal to the queue size. The queue holds the requests to be processed at a fixed rate.
 * Outflow rate:  it defines how many requests can be processed at a fixed rate, usually in seconds.
 *
 * Pros:
 * Memory efficient given the limited queue size.
 * Requests are processed at a fixed rate, therefore it is suitable for use cases that
 * a stable outflow rate is needed.
 *
 * Cons:
 * A burst of traffic fills up the queue with old requests, and if they are not processed in time,
 * recent requests will be rate limited.
 * As we can see, there are two parameters in the algorithm.
 * It might not be easy to tune them properly.
 *
 *
 * @see <a href="https://medium.com/@avocadi/rate-limiter-leaky-bucket-be68c6476385"></a>
 * @author sniper
 * @date 23 Jul 2023
 */
public class LeakingBucket extends RateLimiter {

    private int bucketCapacity;

    private Deque<Long> queue;

    public LeakingBucket(int permitsPerSecond, int bucketCapacity) {
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
