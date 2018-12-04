package org.ict.thread;

public class WaitTask implements Runnable {

    public static WaitTask obj;
    
    private int count;
    
    public WaitTask() {
        this.count = 10;
    }
    
    @Override
    public void run() {
        try {
            obj = new WaitTask();
            //An object must first acquire a lock before calling wait() method. 
            //So the following codes will throw java.lang.IllegalMonitorStateException
            obj.wait();
            obj.count += 20;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
