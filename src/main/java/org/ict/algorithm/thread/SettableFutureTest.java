package org.ict.algorithm.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;


public class SettableFutureTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
	    FutureExecutor executor = new FutureExecutor(service);
	    
	    List<Integer> list = new ArrayList<>();
	    for (int i = 0; i < 4; i++) {
	    	  list.add(i);
	    }
	  
	    Stopwatch watcher = Stopwatch.createStarted();
		CountDownLatch latch = new CountDownLatch(list.size());
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
		list.forEach(item -> {
			SettableFuture<String> childFuture = SettableFuture.create();
			TestHttpGetThread sender = new TestHttpGetThread("test"+item, HttpClientUtilTest.client, childFuture);
			executor.submit(sender);
			childFuture.then(new ResultReceiver(latch, childFuture, queue));
		});
		watcher.stop();
		
		try {
			latch.await(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Stopwatch mergeWatcher = Stopwatch.createStarted();
		while (!queue.isEmpty()) {
			String rs = queue.poll();
			System.err.println("get result is null:" + (rs == null));
		}
		mergeWatcher.stop();
		long mergeCost = mergeWatcher.elapsed(TimeUnit.MILLISECONDS);
		System.err.println("total time cost:" + watcher.elapsed(TimeUnit.MILLISECONDS));
		System.err.println("data merge time Cost:" + mergeCost);
		service.shutdown();
	}
	
	public static class FutureExecutor {
        private ExecutorService executor;

        public FutureExecutor(ExecutorService executor) {
            this.executor = executor;
        }

        public void submit(final TestHttpGetThread sender) {
            executor.submit(sender);
        }
    }
	
	
	
}
