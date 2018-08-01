package org.ict.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SettableFuture<V> extends ListeningFuture<V> {
    
    private V v;

    private volatile boolean done;
    
    public SettableFuture() {}
    
    public SettableFuture(V v) {
        this.v = v;
        this.done = done;
    }
    
    public void set(V v) {
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
        return this.v;
    }

    @Override
    public V get(long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        return get();
    }

    public static <T> SettableFuture<T> create() {
        return new SettableFuture<T>();
    }

    public static <T> SettableFuture<T> create(T value) {
        return new SettableFuture<T>(value);
    }
}
