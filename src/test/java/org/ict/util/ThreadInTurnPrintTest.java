package org.ict.util;


/**
 * Printing from 1 to 100 alternately with two threads.
 * @author sniper
 * @date 07 Sep 2023
 */
public class ThreadInTurnPrintTest {
    /**
     * W-director, project arch,
     * Q(jira, stand-meeting, noah, ddd(order), distribute-tx, message-queue).
     * devops(observation, metrics, logs)
     * @param args
     */
    public static void main(String[] args) {
        ThreadDemo instance = new ThreadDemo();

        Thread t1 = new Thread(() -> instance.printEven());
        t1.setName("thread-1");
        Thread t2 = new Thread(() -> instance.printEven());
        t2.setName("thread-2");

        t1.start();
        t2.start();
    }
}

class ThreadDemo {
    int counter = 1;

    public void printEven() {
        synchronized (this) {
            while (counter < 101) {
                System.out.println(Thread.currentThread().getName() + ", counter:" +counter);
                counter++;
                notify();
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void printOdd() {
        synchronized (this) {
            while (counter < 101) {
                System.out.println(Thread.currentThread().getName() + ", counter:" +counter);
                counter++;
                notify();
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}


