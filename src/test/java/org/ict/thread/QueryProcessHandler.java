package org.ict.thread;

import java.util.concurrent.ExecutionException;

public class QueryProcessHandler implements ProcessHandler {

    private SettableFuture<String> future;
    
    private RequestQuery query;
    
    public QueryProcessHandler(SettableFuture<String> future, RequestQuery query) {
        this.future = future;
        this.query = query;
    }
    
    @Override
    public void onError(Throwable t) {
        System.out.println("error occured" + query.toString());
        future.set(null);
    }

    @Override
    public void onSuccess(String result) {
        System.out.println("request success" + query.toString());
        future.set(query.getPageSize() + "");
    }

    @Override
    public void onFullQueue() {
        future.set(null);
        System.out.println("full queue drop query " +  query.toString());
    }
    
    public String handle(SettableFuture<String> future, RequestQuery query) throws InterruptedException, ExecutionException 
             {
        String result = "success";
        onSuccess(result);
        return future.get();
    }

}
