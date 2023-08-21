package org.ict.algorithm.leetcode.coupang;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sniper
 * @date 23 Jul 2023
 */
public class UserBucketCreator {

    public static void main(String[] args) {
        UserBucketCreator creator = new UserBucketCreator(1);
        ExecutorService executor = Executors.newFixedThreadPool(12);
        for (int i = 0; i < 12; i++) {
            executor.execute(() -> creator.accessApplication(1));
        }
        executor.shutdown();
    }

    Map<Integer, SlidingWindowLog> bucket;

    public UserBucketCreator(int id) {
        bucket = new ConcurrentHashMap<>();
        bucket.put(id, new SlidingWindowLog(1, 5));
    }

    public void accessApplication(int id) {
        if (bucket.get(id).allowAccess()) {
            System.out.println(Thread.currentThread().getName() + " can access the Application.");
        } else {
            System.out.println(Thread.currentThread().getName() + " Too many requests, please try again later.");
        }
    }

    enum BucketType {
        LEAKY_BUCKET,
        TOKEN_BUCKET,
        FIXED_WINDOW_COUNTER,

        SLIDING_WINDOW_LOG,
        SLIDING_WINDOW_COUNTER;
    }
}
