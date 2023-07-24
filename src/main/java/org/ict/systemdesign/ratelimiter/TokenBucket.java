package org.ict.systemdesign.ratelimiter;

/**
 * The token bucket algorithm works as follows:
 * A token bucket is a container that has pre-defined capacity.
 * Tokens are put in the bucket at preset rates periodically.
 * Once the bucket is full, no more tokens are added.
 * Each request consumes one token.
 * When a request arrives, we check if there are enough tokens
 * in the bucket.
 * If there are enough tokens, we take one token out for each request,
 * and the request goes through.
 * If there are not enough tokens, the request is dropped.
 * ---------------------------------------------------------
 * Pros:
 * The algorithm is easy to implement;
 * Memory efficient;
 * Token buckets allows a burst of traffic for short periods.
 * A request can go through as long as there are tokens left.
 *
 * Cons:
 * Two parameters in the algorithms are bucket size,
 * and token refill rate.
 * However, it might be challenging to tune them properly.
 *
 * @author sniper
 * @date 24 Jul 2023
 */
public class TokenBucket extends RateLimiter {

    private int tokens;

    private long lastFillTime;

    /**
     * @param permitsPerSecond this is the bucket size
     */
    public TokenBucket(int permitsPerSecond) {
        super(permitsPerSecond);
        this.tokens = permitsPerSecond;
        this.lastFillTime = System.currentTimeMillis();
    }

    @Override
    public boolean allowAccess() {
        synchronized (this) {
            /**
             * Refill the bucket lazily when a request comes in.
             */
            refillTokens();
            if (tokens == 0) {
                return false;
            }
            tokens--;
            return true;
        }
    }

    /**
     * Refill rate: number of tokens put into the bucket every second.
     */
    private void refillTokens() {
        long curTime = System.currentTimeMillis();
        double secondsSinceLastFill = (curTime - lastFillTime) / 1000.0;
        int count = (int)(secondsSinceLastFill * super.permitsPerSecond);
        if (count > 0) {
            tokens = Math.min(tokens + count, super.permitsPerSecond);
            lastFillTime = curTime;
        }
    }

}
