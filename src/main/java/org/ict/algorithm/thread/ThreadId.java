package org.ict.algorithm.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @see https://www.pixelstech.net/article/1399361023-Use-Java-ThreadLocal-with-caution
 * According to Oracle documentation, ThreadLocal is a class provides thread-local variables. 
 * These variables differ from their normal counterparts in that each thread that accesses one 
 * (via its get or set method) has its own, 
 * independently initialized copy of the variable. 
 * ThreadLocal instances are typically private static fields in classes 
 * that wish to associate state with a thread. 
 * In short, ThreadLocal variables are variables belong to a thread, 
 * not a class or an instance of a class.
 * One common use of ThreadLocal is when you want to access some 
 * non thread-safe objects in threads without using synchronization mechanisms 
 * like synchronized block and locks. 
 * These variables will not share states among different threads, 
 * so there is no synchronization problem; 
 * while at the same since each thread will have only one instance of the ThreadLocal object, 
 * it saves memory.For example, the class below generates unique identifiers local to each thread. 
 * A thread's id is assigned the first time it invokes ThreadId.get() and remains unchanged on subsequent calls.
 */
public class ThreadId {
    //Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);
    
    private static ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        @Override 
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };
    
    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
    
}
