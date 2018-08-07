package org.ict.algorithm.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncFuture {
    
    public static void main(String[] args) {
        
        ExecutorService service = Executors.newFixedThreadPool(5);
        FutureExecutor executor = new FutureExecutor(service);
        ListenableFuture<Integer> future = executor.submit(new DelayedRandomNumber(1000));
        future.addCallback(new ResultPrinter());

        service.shutdown();
    }

    public static class DelayedRandomNumber implements Callable<Integer> {
        private int delay;

        public DelayedRandomNumber(int delay) {
            this.delay = delay;
        }

        @Override
        public Integer call() throws Exception {
            Thread.sleep(delay);
            return new Random().nextInt();
        }
    }

    public static class ResultPrinter implements FutureCallback<Integer> {
        @Override
        public void onSuccess(Integer result) {
            System.out.println("Result: " + result);
        }

        @Override
        public void onFailure(Throwable failure) {
            failure.printStackTrace();
        }
    }

    // Implementation starts here!

    public static interface FutureCallback<V> {
        void onSuccess(V result);

        void onFailure(Throwable failure);
    }

    public static class FutureExecutor {
        private ExecutorService executor;

        public FutureExecutor(ExecutorService executor) {
            this.executor = executor;
        }

        public <V> ListenableFuture<V> submit(final Callable<V> callable) {
            final ListenableFuture<V> future = new ListenableFuture<>();
            executor.submit(new Callable<V>() {
                @Override
                public V call() throws Exception {
                    try {
                        V result = callable.call();
                        future.setResult(result);
                        return result;
                    } catch (Exception e) {
                        future.setFailure(e);
                        throw e;
                    }
                }
            });

            return future;
        }
    }

    public static class ListenableFuture<V> {
        private FutureCallback<V> callback;
        private V result;
        private Throwable failure;
        private boolean isCompleted;

        public void addCallback(FutureCallback<V> callback) {
            this.callback = callback;
            resolve();
        }

        public void setResult(V result) {
            this.result = result;
            isCompleted = true;
            resolve();
        }

        public void setFailure(Throwable failure) {
            this.failure = failure;
            isCompleted = true;
            resolve();
        }

        private void resolve() {
            if (callback != null && isCompleted) {
                if (failure == null) {
                    callback.onSuccess(result);
                } else {
                    callback.onFailure(failure);
                }
            }
        }
    }

}