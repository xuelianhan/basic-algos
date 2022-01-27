package org.ict.spring.util;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
            log.error("FAILED - Command failed, will be retried " + maxRetries + " times.");
            return retry(function);
        }
    }

    public T run(Supplier<T> function, int sleep, Set<String> breakMessage) throws Exception {
        try {
            return function.get();
        } catch (Exception e) {
            log.error("FAILED - Command failed, will be retried " + maxRetries + " times.");
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
                log.warn("FAILED - Command failed on retry " + retryCounter + " of " + maxRetries, ex);
                if (CollectionUtils.isNotEmpty(breakMessage) && breakMessage.contains(ex.getMessage())) {
                    log.warn("breakMessage matched:{}.", ex.getMessage());
                    break;
                }
                if (retryCounter >= maxRetries) {
                    log.warn("Max retries exceeded.");
                    break;
                }
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    log.warn("Interrupted!", e);
                    // Restore interrupted state...
                    Thread.currentThread().interrupt();
                }
            }
        }
        log.info("Command failed on all of " + retryCounter + " retries");
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
