package org.ict.archbase.spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.ict.archbase.spring.lock.RedisLock;
import org.ict.archbase.spring.util.RequestUtil;

/**
 * @author sniper
 * @date 19 Jul 2023
 */
@Aspect
@Slf4j
public class DistributedLockAspect {

    private RedisLock redisLock;

    public DistributedLockAspect(RedisLock redisLock) {
        this.redisLock = redisLock;
    }

    @Around(value = "@annotation(lockable)")
    public Object distLock(ProceedingJoinPoint point, RedisLockAspect lockable) throws Throwable {
        boolean locked = false;
        String key = lockable.prefix() + lockable.key();
        try {
            locked = redisLock.lock(key, RequestUtil.getRequestId(), lockable.expire());
            if(locked) {
                return point.proceed();
            } else {
                log.info("Did not get a lock for key {}", key);
                return null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if(locked) {
                if(!redisLock.unLock(key, RequestUtil.getRequestId())){
                    log.warn("Unlock {} failed, maybe locked by another client already. ", lockable.key());
                }
            }
        }
    }
}
