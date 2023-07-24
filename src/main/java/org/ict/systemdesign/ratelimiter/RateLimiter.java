package org.ict.systemdesign.ratelimiter;

/**
 * @author sniper
 * @date 23 Jul 2023
 */
public abstract class RateLimiter {

    protected final int permitsPerSecond;

    public RateLimiter(int permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    public boolean allowAccess() {
        return false;
    }

}
