package org.ict.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.ict.rpc.RpcContext;

public class QueryProcessHandler implements ProcessHandler {
    
    private SettableFuture<String> future;
    
    private RpcContext context;
    
    private RequestQuery query;
    
    public QueryProcessHandler(RpcContext context, RequestQuery query) {
        this.context = context;
        this.query = query;
    }
    
    public QueryProcessHandler(SettableFuture<String> future, RequestQuery query) {
        this.future = future;
        this.query = query;
    }
    
    @Override
    public void onError(Throwable t) {
        System.out.println("error occured:" + query.toString());
        SettableFuture<String> future = (SettableFuture<String>)((Future<?>)context.getFuture());
        future.set(null);
    }

    @Override
    public void onSuccessContext(String result) {
        System.out.println("request success:" + query.toString());
        SettableFuture<String> future = (SettableFuture<String>)((Future<?>)context.getFuture());
        future.set(query.getPageSize() + "");
    }
    
    @Override
    public void onSuccessFuture(String httpContent) {
        System.out.println("request success:" + query.toString());
        future.set(query.getPageSize() + "");
    }

    @Override
    public void onFullQueue() {
        SettableFuture<String> future = (SettableFuture<String>)((Future<?>)context.getFuture());
        future.set(null);
        System.out.println("full queue drop query:" +  query.toString());
    }
    
    public String handle(RpcContext context, RequestQuery query) 
            throws InterruptedException, ExecutionException {
        String result = "success";
        onSuccessContext(result);
        SettableFuture<String> future = (SettableFuture<String>)((Future<?>)context.getFuture());
        return future.get();
    }
    
    public String handle(SettableFuture<String> future, RequestQuery query) 
            throws InterruptedException, ExecutionException {
        String result = "success";
        onSuccessFuture(result);
        return future.get();
    }

   

}
