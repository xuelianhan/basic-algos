package org.ict.systemdesign.ratelimiter;

/**
 * @author sniper
 * @date 24 Jul 2023
 */
public class LeakyBucketV1 extends RateLimiter {

    private long nextAllowedTime;

    private long requestIntervalMillis;

    public LeakyBucketV1(int permitsPerSecond) {
        super(permitsPerSecond);
        requestIntervalMillis = 1000 / permitsPerSecond;
        nextAllowedTime = System.currentTimeMillis();
    }

    @Override
    public boolean allowAccess() {
        long curTime = System.currentTimeMillis();
        synchronized (this) {
            if (curTime >= nextAllowedTime) {
                nextAllowedTime = curTime + requestIntervalMillis;
                return true;
            }
            return false;
        }
    }

}
