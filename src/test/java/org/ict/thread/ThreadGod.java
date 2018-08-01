package org.ict.thread;

public class ThreadGod implements Runnable {

    private String threadName;
    
    public ThreadGod(String threadName) {
        this.threadName = threadName;
    }
    
    @Override
    public void run() {
       System.out.println(threadName + " say hello! my name is " + Thread.currentThread().getName());
    }

    public String getThreadName() {
        return threadName;
    }

}
