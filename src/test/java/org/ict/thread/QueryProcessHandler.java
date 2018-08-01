package org.ict.thread;

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
    public void onSuccess(String httpContent) throws Exception {
        System.out.println("request success" + query.toString());
        future.set(query.getPageSize() + "");
    }

    @Override
    public void onFullQueue() {
        future.set(null);
        System.out.println("full queue drop query " +  query.toString());
    }

}
