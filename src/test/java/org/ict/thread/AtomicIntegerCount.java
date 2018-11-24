package org.ict.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCount {

   public static void main(String[] args) {
       ProcessingThread pt = new ProcessingThread();
       Thread t1 = new Thread(pt, "t1");
       t1.start();
       Thread t2 = new Thread(pt, "t2");
       t2.start();
       try {
           t1.join();
           t2.join();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       System.out.println("Processing count=" + pt.getCount());
   }
}

class ProcessingThread implements Runnable {
    
    private static final int diff = 10;
    
    private static final int base_step = 20;
    
    private AtomicInteger count = new AtomicInteger(0);
    
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            processSomething(i);
            System.out.println("Thread" + Thread.currentThread().getName() + ": now - " + diff + " <= pay + " + base_step + " * " + count.incrementAndGet() + " <= now");
        }
    }
    
    private void processSomething(int i) {
        // processing some job
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public int getCount() {
        return this.count.get();
    }
            
}
