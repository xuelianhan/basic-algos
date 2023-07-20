package org.ict.spring.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sniper
 * @date 19 Jul 2023
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisLockAspect {

    String key();

    String prefix() default "distributedLock:";

    /**
     * Expired default ten seconds.
     * @return
     */
    long expire() default 10L;

}
