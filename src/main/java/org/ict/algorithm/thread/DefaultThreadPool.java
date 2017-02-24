package org.ict.algorithm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * Notice the private inner static class that contains the instance of the singleton class. 
 * When the singleton class is loaded, SingletonHelper class is not loaded into memory and 
 * only when someone calls the getInstance method, this class gets loaded and creates the 
 * Singleton class instance.This is the most widely used approach for Singleton class as it 
 * doesn’t require synchronization. 
 * 
 * @see http://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples
 */
public final class DefaultThreadPool {
	
	private static final int THREAD_POOL_SIZE_THREHOLD = 4;
	
	private static ExecutorService executor = null;
	
	
	private static class SingletonHelper {
		private static final DefaultThreadPool pool = new DefaultThreadPool();
	}
	
	private DefaultThreadPool() {
		executor =  Executors.newFixedThreadPool(THREAD_POOL_SIZE_THREHOLD);
	}
	
	/* Lazy initialization and thread safe singleton, Bill Pugh Singleton implementation */
	public static DefaultThreadPool getInstance() {
		return SingletonHelper.pool;
	}
	
	public void executeTask(Runnable task) {
		executor.execute(task);
	}
}
