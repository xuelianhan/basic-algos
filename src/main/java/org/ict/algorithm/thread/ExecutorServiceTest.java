package org.ict.algorithm.thread;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * http://stackoverflow.com/questions/12191029/running-two-independent-tasks-simultaneously-using-threads
 * https://stackoverflow.com/questions/6113746/naming-threads-and-thread-pools-of-executorservice
 * https://www.programcreek.com/2011/03/java-write-to-a-file-code-example/
 * @author MadProgrammer
 *
 */
public class ExecutorServiceTest {
	
	public static void main(String[] args) throws InterruptedException {
		//change this to 1 and see what happens
		//This is the way to control the number of simultaneously threads that executor can use while processing it's queue.
	    ExecutorService service = Executors.newFixedThreadPool(2);
	    //ExecutorService service = Executors.newFixedThreadPool(1);
	    
	    service.submit(new PathScanner());
	    service.submit(new Counter());

	    service.shutdown();
	    service.awaitTermination(1, TimeUnit.DAYS);

	    System.exit(0);
	}

	public static class PathScanner implements Callable<Object> {

	    @Override
	    public Object call() throws Exception {
	        scan(new File("/home/hanxuelian/workspace/"), 0);
	        return null;
	    }

	    protected void scan(File path, int deepth) {
	        if (deepth < 15) {
	            System.out.println("Scanning " + path + " at a deepth of " + deepth);

	            File[] files = path.listFiles();
	            for (File file : files) {
	                if (file.isDirectory()) {
	                    scan(file, ++deepth);
	                }
	            }
	        }
	    }
	}

	public static class Counter implements Callable<Object> {

	    @Override
	    public Object call() throws Exception {
	        for (int index = 0; index < 1000; index++) {
	            Thread.sleep(1);
	            System.out.println(index);
	        }
	        return null;
	    }
	}
}
