package org.ict.algorithm.thread;

import java.util.concurrent.Future;

public abstract class ListenableFuture<V> implements Future<V> {
	
	private volatile Runnable runnable;
	
	public void then(Runnable runnable) {
		this.runnable = runnable;
		trigger();
	}
	
	protected final void trigger() {
		if (isDone() && runnable != null) {
			runnable.run();
		}
	}

}
