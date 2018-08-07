package org.ict.algorithm.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @see https://stackoverflow.com/questions/1252190/how-to-wait-for-a-number-of-threads-to-complete
 */
class DoSomethingInAThread implements Runnable {
    CountDownLatch latch;
    
    public DoSomethingInAThread(CountDownLatch latch) {
        this.latch = latch;
    }
    
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName() + " Do some thing");
            latch.countDown();
        }catch(Exception err){
            err.printStackTrace();
        }
    }
}

public class CountDownLatchTest {
    public static void main(String[] args) {
        try {
            int threadCount = 3;
            CountDownLatch latch = new CountDownLatch(threadCount);
            for (int n = 0; n < threadCount; n++) {
                Thread t = new Thread(new DoSomethingInAThread(latch));
                t.start();
            }
            latch.await();
            System.out.println("In Main thread after completion of " + threadCount + " threads");
        } catch(Exception err) {
            err.printStackTrace();
        }
    }
}
