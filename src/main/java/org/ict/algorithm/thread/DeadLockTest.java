package org.ict.algorithm.thread;

/**
 * 
 * @see Java36-series by xiaofeng.yang 
 *  *[hanxuelian@ict workspace]$ jstack 12734
 * 2018-12-07 11:00:29
 * Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.131-b11 mixed mode):
 * 
 * "Attach Listener" #11 daemon prio=9 os_prio=0 tid=0x00007f36a0001000 nid=0x3247 waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 * 
 * "Thread2" #10 prio=5 os_prio=0 tid=0x00007f36e412a800 nid=0x31d3 waiting for monitor entry [0x00007f36c7dfc000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *     at org.ict.algorithm.thread.DeadLockTest.run(DeadLockTest.java:27)
 *     - waiting to lock <0x000000078df982d8> (a java.lang.String)
 *     - locked <0x000000078df98310> (a java.lang.String)
 * 
 * "Thread1" #9 prio=5 os_prio=0 tid=0x00007f36e4129000 nid=0x31d2 waiting for monitor entry [0x00007f36c7efd000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *     at org.ict.algorithm.thread.DeadLockTest.run(DeadLockTest.java:27)
 *     - waiting to lock <0x000000078df98310> (a java.lang.String)
 *     - locked <0x000000078df982d8> (a java.lang.String)
 * 
 * "Service Thread" #8 daemon prio=9 os_prio=0 tid=0x00007f36e40d4800 nid=0x31d0 runnable [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 * 
 * "C1 CompilerThread2" #7 daemon prio=9 os_prio=0 tid=0x00007f36e40bf800 nid=0x31cf waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 * 
 * "C2 CompilerThread1" #6 daemon prio=9 os_prio=0 tid=0x00007f36e40be000 nid=0x31ce waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 * 
 * "C2 CompilerThread0" #5 daemon prio=9 os_prio=0 tid=0x00007f36e40bb000 nid=0x31cd waiting on condition [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 * 
 * "Signal Dispatcher" #4 daemon prio=9 os_prio=0 tid=0x00007f36e40b9800 nid=0x31cc runnable [0x0000000000000000]
 *    java.lang.Thread.State: RUNNABLE
 * 
 * "Finalizer" #3 daemon prio=8 os_prio=0 tid=0x00007f36e4086800 nid=0x31cb in Object.wait() [0x00007f36d46b3000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 *     at java.lang.Object.wait(Native Method)
 *     - waiting on <0x000000078df08ec8> (a java.lang.ref.ReferenceQueue$Lock)
 *     at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
 *     - locked <0x000000078df08ec8> (a java.lang.ref.ReferenceQueue$Lock)
 *     at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
 *     at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)
 * 
 * "Reference Handler" #2 daemon prio=10 os_prio=0 tid=0x00007f36e4081800 nid=0x31ca in Object.wait() [0x00007f36d47b4000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 *     at java.lang.Object.wait(Native Method)
 *     - waiting on <0x000000078df06b68> (a java.lang.ref.Reference$Lock)
 *     at java.lang.Object.wait(Object.java:502)
 *     at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
 *     - locked <0x000000078df06b68> (a java.lang.ref.Reference$Lock)
 *     at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)
 * 
 * "main" #1 prio=5 os_prio=0 tid=0x00007f36e400c000 nid=0x31c0 in Object.wait() [0x00007f36eb1f4000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 *     at java.lang.Object.wait(Native Method)
 *     - waiting on <0x000000078df98348> (a org.ict.algorithm.thread.DeadLockTest)
 *     at java.lang.Thread.join(Thread.java:1252)
 *     - locked <0x000000078df98348> (a org.ict.algorithm.thread.DeadLockTest)
 *     at java.lang.Thread.join(Thread.java:1326)
 *     at org.ict.algorithm.thread.DeadLockTest.main(DeadLockTest.java:42)
 * 
 * "VM Thread" os_prio=0 tid=0x00007f36e407a000 nid=0x31c9 runnable 
 * 
 * "GC task thread#0 (ParallelGC)" os_prio=0 tid=0x00007f36e4021000 nid=0x31c1 runnable 
 * 
 * "GC task thread#1 (ParallelGC)" os_prio=0 tid=0x00007f36e4023000 nid=0x31c2 runnable 
 * 
 * "GC task thread#2 (ParallelGC)" os_prio=0 tid=0x00007f36e4025000 nid=0x31c3 runnable 
 * 
 * "GC task thread#3 (ParallelGC)" os_prio=0 tid=0x00007f36e4026800 nid=0x31c4 runnable 
 * 
 * "VM Periodic Task Thread" os_prio=0 tid=0x00007f36e40e7800 nid=0x31d1 waiting on condition 
 * 
 * JNI global references: 6
 * 
 * 
 * Found one Java-level deadlock:
 * =============================
 * "Thread2":
 *   waiting to lock monitor 0x00007f36ac0062c8 (object 0x000000078df982d8, a java.lang.String),
 *   which is held by "Thread1"
 * "Thread1":
 *   waiting to lock monitor 0x00007f36ac006168 (object 0x000000078df98310, a java.lang.String),
 *   which is held by "Thread2"
 * 
 * Java stack information for the threads listed above:
 * ===================================================
 * "Thread2":
 *     at org.ict.algorithm.thread.DeadLockTest.run(DeadLockTest.java:27)
 *     - waiting to lock <0x000000078df982d8> (a java.lang.String)
 *     - locked <0x000000078df98310> (a java.lang.String)
 * "Thread1":
 *     at org.ict.algorithm.thread.DeadLockTest.run(DeadLockTest.java:27)
 *     - waiting to lock <0x000000078df98310> (a java.lang.String)
 *     - locked <0x000000078df982d8> (a java.lang.String)
 * 
 * Found 1 deadlock.
 * 
 */
public class DeadLockTest extends Thread {

    private String first;
    private String second;
    
    public DeadLockTest(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }
    
    @Override
    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained:" + first);
            
            try {
                Thread.sleep(1000l);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained:" + second);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        String lockA = "lockA";
        String lockB = "lockB";
        DeadLockTest t1 = new DeadLockTest("Thread1", lockA, lockB);
        DeadLockTest t2 = new DeadLockTest("Thread2", lockB, lockA);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
