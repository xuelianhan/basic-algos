package org.ict.algorithm.leetcode.coupang;

/**
 * Implement a rate limiter,
 * provide one method: isRateLimited(timestamp, event, rate, increment).
 *
 * timestamp: The current timestamp, which is an integer and in second unit.
 * event: The string to distinct different event. for example, "login" or "signup".
 * rate: The rate of the limit.
 * 1/s (1 time per second),
 * 2/m (2 times per minute),
 * 10/h (10 times per hour),
 * 100/d (100 times per day).
 * The format is [integer]/[s/m/h/d].
 * increment: Whether we should increase the counter.
 * (or take this call as a hit of the given event)、
 * The method should return true or false to indicate the event is limited or not.
 * Note: Login failure is not recorded in login times.
 *
 * @author sniper
 * @date 19 Jul 2023
 */
public interface RateLimiter {

    /**
     * @param timestamp the current timestamp
     * @param event the string to distinct different event
     * @param rate the format is [integer]/[s/m/h/d]
     * @param increment whether we should increase the counter
     * @return true or false to indicate the event is limited or not
     */
    default boolean isRateLimited(int timestamp, String event, String rate, boolean increment) {
        //todo
        return false;
    }

    boolean allowAccess();
}
