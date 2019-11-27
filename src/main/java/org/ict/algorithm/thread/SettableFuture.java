package org.ict.algorithm.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @author hanxuelian001
 *
 * @param <V>
 */
public class SettableFuture<V> extends ListenableFuture<V> {

	private V v;

	private volatile boolean done;

	public SettableFuture() {
	}

	public SettableFuture(V v) {
		this.v = v;
		this.done = true;
	}

	public void setV(V v) {
		this.v = v;
		this.done = true;
		trigger();
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return false;
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public V get() throws InterruptedException, ExecutionException {
		return v;
	}

	@Override
	public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return get();
	}

	public static <T> SettableFuture<T> create() {
		return new SettableFuture<T>();
	}

	public static <T> SettableFuture<T> create(T value) {
		return new SettableFuture<T>(value);
	}

}
