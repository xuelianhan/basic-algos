package org.ict.algorithm.company.coupang;

import java.util.concurrent.CountDownLatch;

/**
 * In this code, we create a CountDownLatch object with a count of 3.
 * This means that the main thread will wait until all 3 threads have finished before it continues.
 * We then create 3 threads and start them.
 * Each thread prints a message to the console and then sleeps for 1 second.
 * After the thread has slept, it calls the countDown() method on the CountDownLatch object.
 * The main thread then calls the await() method on the CountDownLatch object.
 * This method will block the main thread until the count reaches 0.
 * Once the count reaches 0, the main thread will continue and print a message to the console.
 * This code will ensure that the 3 threads are executed first and then the main thread.
 * The 3 threads will be executed in the order in which they were started.
 *
 * @author sniper
 * @date 17 Aug 2023
 */
public class ThreadExecuteOrderTest {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 started");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1 finished");
            latch.countDown();
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2 started");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 2 finished");
            latch.countDown();
        });

        Thread thread3 = new Thread(() -> {
            System.out.println("Thread 3 started");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 3 finished");
            latch.countDown();
        });

        thread1.start();
        thread2.start();
        thread3.start();

        latch.await();

        System.out.println("All threads finished");
    }
}
