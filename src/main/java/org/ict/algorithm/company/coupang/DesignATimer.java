package org.ict.algorithm.company.coupang;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Implement a timer to send notifications to the callback every second.
 * @author sniper
 * @date 21 Jul 2023
 */
public class DesignATimer {

    public static void main(String[] args) {
        DesignATimer instance = new DesignATimer();
        //instance.callbackNotify();
        instance.callbackNotifyV1();
    }

    public void callbackNotifyV1() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        NotifyCommand command = new NotifyCommand();
        /**
         * Scheduling the first task which will execute after 1 seconds and then repeats periodically with a period of 1 seconds.
         */
        executor.scheduleAtFixedRate(command, 1, 1, TimeUnit.SECONDS);
    }

    public void callbackNotify() {
        Timer timer = new Timer();
        CallBackTask task = new CallBackTask();
        timer.schedule(task, 1000, 1000);
    }

    private class NotifyCommand implements Runnable {
        @Override
        public void run() {
            System.out.println("send notification message at time:" + System.currentTimeMillis());
        }
    }

    private class CallBackTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("send notification message at time:" + System.currentTimeMillis());
        }
    }
}
