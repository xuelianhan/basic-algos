package org.ict.algorithm.company.coupang;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * This rate limiter is implemented using a concurrent hash map.
 * The hash map maps event names to RateLimit objects.
 * The RateLimit object stores the limit, the unit, and the next allowed timestamp.
 *
 * The isRateLimited() method first checks if the rate limiter has been created for the event.
 * If it has not, then the rate limiter is created.
 * The method then checks if the timestamp is before the next allowed timestamp.
 * If it is, then the method returns true, indicating that the event is limited.
 * Otherwise, the method returns false.
 *
 * The increment() method increments the counter for the event.
 * This is done by setting the next allowed timestamp to the maximum of the current timestamp and the timestamp plus the limit.
 * @author sniper
 * @date 17 Aug 2023
 */
public class DefaultRateLimiter {

    private final ConcurrentHashMap<String, RateLimit> rateLimits;

    public DefaultRateLimiter() {
        this.rateLimits = new ConcurrentHashMap<>();
    }

    public boolean isRateLimited(int timestamp, String event, String rate, boolean increment) {
        RateLimit rateLimit = this.rateLimits.get(event);
        if (rateLimit == null) {
            rateLimit = new RateLimit(rate);
            this.rateLimits.put(event, rateLimit);
        }

        if (increment) {
            rateLimit.increment(timestamp);
        }

        return rateLimit.isLimited(timestamp);
    }

    private class RateLimit {

        private final int limit;
        private final TimeUnit unit;
        private long nextAllowed;

        public RateLimit(String rate) {
            String[] parts = rate.split("/");
            this.limit = Integer.parseInt(parts[0]);
            this.unit = TimeUnit.valueOf(parts[1]);
            this.nextAllowed = System.currentTimeMillis();
        }

        public void increment(int timestamp) {
            this.nextAllowed = Math.max(this.nextAllowed, timestamp + this.unit.toMillis(this.limit));
        }

        public boolean isLimited(int timestamp) {
            return timestamp < this.nextAllowed;
        }
    }
}
