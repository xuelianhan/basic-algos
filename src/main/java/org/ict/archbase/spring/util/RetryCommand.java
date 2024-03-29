package org.ict.archbase.spring.util;

import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.ict.algorithm.util.RandomGenUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Retry with Java8, Don't need any extra library
 * @author sniper
 * @date 2022/1/27 11:56 AM
 */
public class RetryCommand<T> {
    private int maxRetries;

    private static final int DEF_SLEEP_INTERVAL = 1000;

    public RetryCommand(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    /**
     * Takes a function and executes it, if fails, passes the function to the retry command
     */
    public T run(Supplier<T> function) throws Exception {
        try {
            return function.get();
        } catch (Exception e) {
            return retry(function);
        }
    }

    public T run(Supplier<T> function, int sleep, Set<String> breakMessage) throws Exception {
        try {
            return function.get();
        } catch (Exception e) {
            return retry(function, sleep, breakMessage);
        }
    }

    private T retry(Supplier<T> function) throws Exception {
        return retry(function, DEF_SLEEP_INTERVAL, new HashSet<String>());
    }

    private T retry(Supplier<T> function, int sleep, Set<String> breakMessage) throws Exception {
        int retryCounter = 0;
        Exception res = null;
        while (retryCounter < maxRetries) {
            try {
                return function.get();
            } catch (Exception ex) {
                res = ex;
                retryCounter++;
                if (CollectionUtils.isNotEmpty(breakMessage) && breakMessage.contains(ex.getMessage())) {
                    break;
                }
                if (retryCounter >= maxRetries) {
                    break;
                }
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    // Restore interrupted state...
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (res != null) {
            throw res;
        }
        return null;
    }

    public static void main(String[] args) {
        int retryTimes = 3;
        RetryCommand<String> retryCommand = new RetryCommand<>(retryTimes);
        Supplier<String> supplier = () -> retryLogic();
        try {
            retryCommand.run(supplier, RandomGenUtil.getRandomNumberInRangeV4(3000, 4000), Sets.newHashSet("tips here"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String retryLogic() {
        System.out.println("retry logic here");
        return "retry result";
    }
}
