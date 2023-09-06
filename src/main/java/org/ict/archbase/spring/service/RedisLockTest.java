package org.ict.archbase.spring.service;

import org.ict.archbase.spring.aspect.RedisLockAspect;

import java.time.LocalDateTime;

/**
 * @author sniper
 * @date 19 Jul 2023
 */
public class RedisLockTest {

    /**
     * todo
     */
    @RedisLockAspect(key = "test", expire = 10)
    public void test(){
        System.out.println("Thread-"+Thread.currentThread().getName()+" start to run..." + LocalDateTime.now());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread-"+Thread.currentThread().getName()+"end to run..." + LocalDateTime.now());
    }
}
