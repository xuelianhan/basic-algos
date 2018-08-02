package org.ict.thread;

public interface ProcessHandler {
    
    public void onError(Throwable t);

    public void onSuccessContext(String httpContent);
    
    public void onSuccessFuture(String httpContent);

    public void onFullQueue();
    
}
