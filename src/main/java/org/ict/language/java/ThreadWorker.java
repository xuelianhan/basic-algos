package org.ict.language.java;

/**
 * @author sniper
 * @date 07 Sep 2023
 */
public class ThreadWorker implements Runnable {

    private OrderSeq orderSeq;
    private volatile int seqNo;

    public ThreadWorker() {}

    public ThreadWorker(OrderSeq orderSeq, int seqNo) {
        this.orderSeq = orderSeq;
        this.seqNo = seqNo;
    }
    @Override
    public void run() {
        synchronized (orderSeq) {
            System.out.println(Thread.currentThread().getName());
            for (int i = 0; i < 100; i++) {
                if (orderSeq.seq > 3) {
                    orderSeq.seq = 1;
                }
                while (orderSeq.seq != seqNo) {
                    try {
                        orderSeq.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(seqNo);
                orderSeq.seq++;
                orderSeq.notifyAll();
            }
        }
    }
}
