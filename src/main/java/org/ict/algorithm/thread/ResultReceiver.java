package org.ict.algorithm.thread;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class ResultReceiver implements Runnable{

private CountDownLatch latch;
	
	private SettableFuture<String> future;
	
	private ConcurrentLinkedQueue<String> queue;
	
	public ResultReceiver() {}
	
	public ResultReceiver(CountDownLatch latch, SettableFuture<String> future, ConcurrentLinkedQueue<String> queue) {
		this.latch = latch;
		this.future = future;
		this.queue = queue;
	}
	
	@Override
	public void run() {
		Stopwatch receiveWatcher = Stopwatch.createStarted();
		try {
			String rs = future.get();
            if (rs != null) {
                queue.add(rs);
            }
            latch.countDown();
        } catch(Exception e) {
        	future.setV(null);
            e.printStackTrace();
        }
		receiveWatcher.stop();
		System.err.println("receiver " + " cost:" + receiveWatcher.elapsed(TimeUnit.MILLISECONDS));
	}
}
