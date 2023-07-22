package org.ict.algorithm.leetcode.coupang;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author sniper
 * @date 22 Jul 2023
 */
public class DesignProducerConsumerModel {

    private static final String BYE_MSG = "bye";

    public static void main(String[] args) {
        DesignProducerConsumerModel instance = new DesignProducerConsumerModel();
        instance.testProduceAndConsume();
    }

    public void testProduceAndConsume() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Producer implements Runnable {

        private BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue) {
            this.queue = queue;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000L);
                    String msg = "msg" + i;
                    queue.offer(msg);
                    System.out.println(msg + " has been put into the queue");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(BYE_MSG);
            System.out.println(BYE_MSG + " has been put into the queue");
        }
    }

    static class Consumer implements Runnable {

        private BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> queue) {
            this.queue = queue;
        }
        @Override
        public void run() {
            try {
                String msg = null;
                while (!BYE_MSG.equals(msg = queue.take())) {
                    System.out.println(msg + " has been consumed");
                    Thread.sleep(1000);
                }
                System.out.println("End consume with " + msg);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
