package org.ict.algorithm.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {

	public volatile int inc = 0;
	
	Lock lock = new ReentrantLock();
	
	public AtomicInteger incAtomic = new AtomicInteger();
    
    public void increase() {
        inc++;
    }
    
    public synchronized void increaseSync() {
    	 inc++;
    }
    
    public void increaseLock() {
    	lock.lock();
        try {
            inc++;
        } finally{
            lock.unlock();
        }
   }
    
   public void increaseAtomic() {
	   incAtomic.getAndIncrement();
   }
    
   public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for(int i = 0; i < 10; i++){
            new Thread(){
                public void run() {
                    for(int j = 0; j < 1000; j++) {
                    	//test.increase();// the result is not guaranteed
                    	//test.increaseSync();// the result is assured
                    	test.increaseLock();// the result is assured
                    	//test.increaseAtomic();// the result is assured
                    }
                };
            }.start();
        }
         
        while(Thread.activeCount() > 1)
            Thread.yield();
        System.out.println(test.inc);
    }
}
