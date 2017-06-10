package org.ict.algorithm.thread;

import java.lang.management.ManagementFactory;
import java.util.Set;

/**
 * @see https://stackoverflow.com/questions/1922290/how-to-get-the-number-of-threads-in-a-java-process
 * @see https://stackoverflow.com/questions/11536938/how-to-check-the-number-of-currently-running-threads-in-java
 * @see https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.State.html
 * @author hanxuelian
 */

public class ThreadGodTest {

    public static void main(String[] args) {
        ThreadGodTest bean = new ThreadGodTest();
        bean.testThreadCount();
        
        int totalThreadCount = ManagementFactory.getThreadMXBean().getThreadCount();
        System.out.println("Total Thread count:" + totalThreadCount);
    }
    
    public void testThreadCount() {
        for ( int i=0; i< 10; i++){
            Thread t = new Thread(new LittleThread("thread-"+i));
            t.start();
        }
        int threadCount = 0;
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for ( Thread t : threadSet){
            //only current thread-group, not contains system thread
            //if ( t.getThreadGroup() == Thread.currentThread().getThreadGroup()){
                System.out.println("Thread :"+t.getName()+":"+", state:"+t.getState());
                ++threadCount;
            //}
        }
        System.out.println("Thread count started by Main thread:"+threadCount);
        
       
    }
    
    class LittleThread implements Runnable {

        private String threadName;
        
        public LittleThread(String threadName) {
            this.threadName = threadName;
        }
        @Override
        public void run() {
           try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
        }
        
    }
}
