package org.ict.algorithm.leetcode.coupang;

/**
 * The main difference between SingleThreadExecutor and FixedThreadPool with a fixed number of threads equal to 1 is that SingleThreadExecutor will always use the same thread to execute tasks, while FixedThreadPool will use a new thread for each task, as long as there are available threads.
 *
 * Here is a table summarizing the key differences between SingleThreadExecutor and FixedThreadPool with a fixed number of threads equal to 1:
 *
 * Feature	             SingleThreadExecutor	FixedThreadPool
 * Number of threads	  1	                      1
 * Thread reuse	          Yes	                  No
 * Task execution order	  Guaranteed	          Not guaranteed
 * Scalability	          Not scalable	          Scalable
 * Throughput	          Low	                  High
 * SingleThreadExecutor is a good choice for applications that need to execute tasks in a sequential order. For example, you could use SingleThreadExecutor to process a queue of tasks in the order they are received.
 *
 * FixedThreadPool is a good choice for applications that need to execute a large number of tasks. For example, you could use FixedThreadPool to serve a large number of concurrent requests.
 *
 * Here is an example of how to use SingleThreadExecutor to process a queue of tasks in the order they are received:
 *
 * Java
 * import java.util.concurrent.*;
 *
 * public class SingleThreadExecutorExample {
 *
 *     public static void main(String[] args) {
 *         ExecutorService executor = Executors.newSingleThreadExecutor();
 *
 *         Queue<Runnable> tasks = new LinkedList<>();
 *         tasks.add(() -> System.out.println("Task 1"));
 *         tasks.add(() -> System.out.println("Task 2"));
 *         tasks.add(() -> System.out.println("Task 3"));
 *
 *         for (Runnable task : tasks) {
 *             executor.execute(task);
 *         }
 *
 *         executor.shutdown();
 *     }
 * }
 * Use code with caution. Learn more
 * This code creates a SingleThreadExecutor and a queue of tasks. The code then adds three tasks to the queue and submits them to the SingleThreadExecutor. The SingleThreadExecutor will execute the tasks in the order they are received.
 *
 * Here is an example of how to use FixedThreadPool to serve a large number of concurrent requests:
 *
 * Java
 * import java.util.concurrent.*;
 *
 * public class FixedThreadPoolExample {
 *
 *     public static void main(String[] args) {
 *         ExecutorService executor = Executors.newFixedThreadPool(10);
 *
 *         int numTasks = 100;
 *         Queue<Runnable> tasks = new LinkedList<>();
 *         for (int i = 0; i < numTasks; i++) {
 *             tasks.add(() -> System.out.println("Task " + i));
 *         }
 *
 *         for (Runnable task : tasks) {
 *             executor.execute(task);
 *         }
 *
 *         executor.shutdown();
 *     }
 * }
 * Use code with caution. Learn more
 * This code creates a FixedThreadPool with 10 threads and a queue of tasks. The code then creates 100 tasks and submits them to the FixedThreadPool. The FixedThreadPool will execute the tasks in a round-robin fashion, using 10 threads at a time.
 * @author sniper
 * @date 17 Aug 2023
 */
public class SingleThreadExecutorVsFixedThreadPool {
}
