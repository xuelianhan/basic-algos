package org.ict.algorithm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @see https://www.geeksforgeeks.org/joining-threads-in-java/
 * @see https://www.concretepage.com/java/newsinglethreadexecutor_java
 *
 */
public class ThreadJoin extends Thread {
	
	private String threadName;
	
	public ThreadJoin(String threadName) {
		this.threadName = threadName;
	}

	@Override
	public void run() {
		for (int i = 0; i < 2; i++) {
			try {
				Thread.sleep(500);
				System.out.println("Current Thread: " + threadName);
			} catch (Exception ex) {
				System.out.println("Exception has" + " been caught" + ex);
			}
			System.out.println(i);
		}
	}

	public static void main(String[] args) {
		//joinWay();
		threadExecutorWay();
	}
	
	public static void threadExecutorWay() {
		// Don't recommend use Executors to create thread pool, it's queue allow Integer.MAX_VALUE requests,
		// this may incur Out of memory error when requests are too many.
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		// creating two threads
		ThreadJoin t1 = new ThreadJoin("t1");
		ThreadJoin t2 = new ThreadJoin("t2");
		ThreadJoin t3 = new ThreadJoin("t3");
		executor.submit(t1);
		executor.submit(t2);
		executor.submit(t3);
		
		final boolean isTerminated = executor.isTerminated();
        System.out.println(isTerminated);
        // waits for termination for 5 seconds only
        try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        executor.shutdownNow();
	}

	public static void joinWay() {
		// creating two threads
		ThreadJoin t1 = new ThreadJoin("t1");
		ThreadJoin t2 = new ThreadJoin("t2");
		ThreadJoin t3 = new ThreadJoin("t3");

		// thread t1 starts
		t1.start();

		// starts second thread after when
		// first thread t1 has died.
		try {
			System.out.println("Current Thread: " + Thread.currentThread().getName());
			t1.join();
		} catch (Exception ex) {
			System.out.println("Exception has " + "been caught" + ex);
		}

		// t2 starts
		t2.start();
		// starts t3 after when thread t2 has died.
		try {
			System.out.println("Current Thread: " + Thread.currentThread().getName());
			t2.join();
		} catch (Exception ex) {
			System.out.println("Exception has been" + " caught" + ex);
		}
		t3.start();
	}

}
