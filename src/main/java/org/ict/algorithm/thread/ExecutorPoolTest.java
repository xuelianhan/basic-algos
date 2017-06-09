package org.ict.algorithm.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorPoolTest {
    
    private static final int CORE_POOL_SIZE =  8;                                                                                      
    
    private static final int MAXIMUM_POOL_SIZE = 16;                                                                                   
                                                                                                                                       
    private static final long KEEP_ALIVE_TIME = 60l;   
    
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100));  
    
    public static void main(String[] args) {
        ExecutorPoolTest test = new ExecutorPoolTest();
        test.testRun();
    }
    
    public void testRun() {
        for (int i = 0; i < 10000; i++) {
            MessageSender task = new MessageSender(""+i);
            executor.submit(task);
            System.out.println("executor task count:" + executor.getTaskCount());
            System.out.println("executor completed task count:" + executor.getCompletedTaskCount());
        }
    }
    
    class MessageSender implements Runnable {

        private String threadName;
        
        public MessageSender(String threadName) {
            this.threadName = threadName;
        }
        
        @Override
        public void run() {
           long seed = ThreadLocalRandom.current().nextLong();
           System.out.println("thread-"+threadName+",seed:"+seed);
        }
        
    }
}
