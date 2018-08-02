package org.ict.thread;

import java.util.concurrent.ExecutionException;

import org.ict.rpc.RpcContext;

public class ThreadGod implements Runnable {

    private String threadName;
    
    private RequestQuery query;
    
    private RpcContext context;
    
    public ThreadGod(String threadName, RequestQuery query, RpcContext context) {
        this.threadName = threadName;
        this.query = query;
        this.context = context;
    }
    
    @Override
    public void run() {
       System.out.println(threadName + " say hello! my name is " + Thread.currentThread().getName());
       SettableFuture<String> future = SettableFuture.create();
       context.setFuture(future);
       QueryProcessHandler handler = new QueryProcessHandler(context, query);
       //QueryProcessHandler handler = new QueryProcessHandler(future, query);
       try {
            //String rs = handler.handle(future, query);
            String rs = handler.handle(context, query);
            System.out.println("Thread name" + Thread.currentThread().getName() + ", rs:" + rs);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public String getThreadName() {
        return threadName;
    }

}
