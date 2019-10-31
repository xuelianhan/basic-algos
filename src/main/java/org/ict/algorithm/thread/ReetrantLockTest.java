package org.ict.algorithm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReetrantLockTest {

	public static void main(String[] args) {
		ReetrantLockTest instance = new ReetrantLockTest();
		ReentrantLock lock = new ReentrantLock();
        ExecutorService ex = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 4; i++){
            ex.execute(instance.new Worker("Thread-"+i, lock));
        }
        ex.shutdown();
	}
	
	class Worker implements Runnable {
		private String tname;
		
		private ReentrantLock lock;
		
		public Worker(String tname, ReentrantLock lock) {
			this.tname = tname;
			this.lock = lock;
		}
		
		@Override
		public void run() {
			if(lock.tryLock()) {
				System.out.println(tname + " obtains the lock:" + lock);
				executeJob();
				System.out.println("thread " + tname + ", count of locks held is:" + lock.getHoldCount());
			} else {
				System.out.println(tname + " not obtains the lock:" + lock);
			}
		}
		
		public void executeJob() {
			lock.lock();
			try {
				System.out.println("thread " + tname + ", count of locks held is:" + lock.getHoldCount());
			} finally {
				lock.unlock();
			}
		}
	}
}
