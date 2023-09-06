package org.ict.archbase.spring.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author sniper
 * @date 19 Jul 2023
 */
@Slf4j
@Component
public class RedisLock {


    private static final String RELEASE_LOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    private static final Long RELEASE_LOCK_SUCCESS_RESULT = 1L;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Redis SETNX command
     * Try to lock and return immediately.
     */
    public boolean lock(String key, String value, long expire) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * Try to lock, and wait at most timeout.
     * @param key
     * @param value
     * @param expire
     * @param timeout
     * @param waitMillEachLoop
     * @param unit
     * @return
     */
    public boolean lock(String key, String value, long expire, long timeout, long waitMillEachLoop, TimeUnit unit) {
        long waitMillis = unit.toMillis(timeout);
        long waitAlready = 0;
        while (!stringRedisTemplate.opsForValue().setIfAbsent(key, value, expire, TimeUnit.SECONDS) && waitAlready < waitMillis) {
            try {
                // e.g. waitMillEachLoop = 100
                Thread.sleep(waitMillEachLoop);
            } catch (InterruptedException e) {
                log.error("Interrupted while trying to get a lock, key: {}", key, e);
            }
            waitAlready += waitMillEachLoop;
        }

        if (waitAlready < waitMillis) {
            return true;
        }
        log.warn("Redis lock {} failed after waiting for {} ms", key, waitAlready);
        return false;
    }



    /**
     * Release lock
     * @param key  lock key
     * @param value lock value
     */
    public boolean unLock(String key, String value) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(RELEASE_LOCK_LUA_SCRIPT, Long.class);
        long result = stringRedisTemplate.execute(redisScript, Collections.singletonList(key), value);
        return Objects.equals(result, RELEASE_LOCK_SUCCESS_RESULT);
    }

}
