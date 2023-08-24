package org.ict.algorithm.company.coupang;

import java.util.concurrent.Semaphore;

/**
 * This flow limiter is implemented using a semaphore.
 * The semaphore has a fixed number of permits,
 * which represents the maximum number of concurrent requests that can be made.
 * When a request is made to the flow limiter,
 * it must acquire a permit from the semaphore.
 * If there are no available permits, then the request will be blocked.
 * When a request completes, it releases a permit back to the semaphore.
 *
 * To use the flow limiter, you would first create an instance of the FlowLimiter class with the desired number of permits. T
 * hen, you would call the acquire() method before making a request.
 * If the call to acquire() blocks, then you know that the system is currently under high load,
 * and you should try again later.
 * Once you have acquired a permit, you can make your request.
 * When you are finished with your request,
 * you should call the release() method to release the permit back to the semaphore.
 * @author sniper
 * @date 17 Aug 2023
 */
public class FlowLimiter {

    public static void main(String[] args) {
        FlowLimiter limiter = new FlowLimiter(10);
        try {
            limiter.acquire();
            // Make a request
            limiter.release();
        } catch (InterruptedException e) {
            // Handle the exception
        }
    }

    private final Semaphore semaphore;

    public FlowLimiter(int permits) {
        this.semaphore = new Semaphore(permits);
    }

    public void acquire() throws InterruptedException {
        this.semaphore.acquire();
    }

    public void release() {
        this.semaphore.release();
    }
}
