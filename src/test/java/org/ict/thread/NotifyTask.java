package org.ict.thread;

public class NotifyTask implements Runnable {

    @Override
    public void run() {
        //throw java.lang.IllegalMonitorStateException
        WaitTask.obj.notify();
    }

}
