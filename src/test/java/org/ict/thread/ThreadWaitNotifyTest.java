package org.ict.thread;

public class ThreadWaitNotifyTest {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new WaitTask());
        Thread thread2 = new Thread(new NotifyTask());
        thread1.start(); 
        thread2.start(); 
      
        System.out.printf(" GFG - "); 
    }
}
