package org.ict.language.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sniper
 * @date 07 Sep 2023
 */
public class ThreadJoinTest {

    public static void main(String[] args) {
        ThreadJoinTest instance = new ThreadJoinTest();
        instance.testJoin1();
    }

    public void testJoin1() {
        OrderSeq orderSeq = new OrderSeq();
        Runnable r1 = new ThreadWorker(orderSeq, 1);
        Runnable r2 = new ThreadWorker(orderSeq, 2);
        Runnable r3 = new ThreadWorker(orderSeq, 3);

        List<Runnable> runnableList = new ArrayList<>();
        runnableList.add(r1);
        runnableList.add(r2);
        runnableList.add(r3);

        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < runnableList.size(); i++) {
            pool.submit(runnableList.get(i));
        }
        pool.shutdown();
    }

    public void testJoin() {
        Thread t1 = new Thread(new ThreadWorker(), "1");
        Thread t2 = new Thread(new ThreadWorker(), "2");
        Thread t3 = new Thread(new ThreadWorker(), "3");

        t1.start();
        try{
            t1.join();
        }catch (Exception e){

        }
        t2.start();
        try{
            t2.join();
        }catch (Exception e){

        }
        t3.start();
        try{
            t3.join();
        }catch (Exception e){

        }
    }

}
