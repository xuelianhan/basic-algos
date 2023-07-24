package org.ict.systemdesign.ratelimiter;

/**
 * @author sniper
 * @date 24 Jul 2023
 */
public class SlidingWindowLog extends RateLimiter {

    public SlidingWindowLog(int permitsPerSecond) {
        super(permitsPerSecond);
    }


}
