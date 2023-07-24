package org.ict.systemdesign.ratelimiter;

/**
 * @author sniper
 * @date 24 Jul 2023
 */
public class TokenBucket extends RateLimiter {

    private int tokens;

    private long lastFillTime;

    public TokenBucket(int permitsPerSecond) {
        super(permitsPerSecond);
        this.tokens = permitsPerSecond;
        this.lastFillTime = System.currentTimeMillis();
    }

    @Override
    public boolean allowAccess() {
        synchronized (this) {
            refillTokens();
            if (tokens == 0) {
                return false;
            }
            tokens--;
            return true;
        }
    }

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
