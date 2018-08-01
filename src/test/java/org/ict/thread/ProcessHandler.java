package org.ict.thread;

public interface ProcessHandler {
    
    public void onError(Throwable t);

    public void onSuccess(String httpContent);

    public void onFullQueue();
    
}
