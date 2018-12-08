package org.ict.algorithm.thread;

/**
 * @see https://www.pixelstech.net/article/1399361023-Use-Java-ThreadLocal-with-caution
 * Each thread holds an implicit reference to its copy of a thread-local variable 
 * as long as the thread is alive and the ThreadLocal instance is accessible; 
 * after a thread goes away, all of its copies of thread-local instances are subject 
 * to garbage collection (unless other references to these copies exist).
 * Since ThreadLocal will keep a reference to the object it refers to 
 * as long as the thread is alive, there is a risk of memory leak in some applications, 
 * especially in Java EE applications. 
 * As in Java EE applications, to improve the performance, thread pool will be used most probably, 
 * this means that a thread will not be terminated when it completes its task, 
 * instead it will be returned to the thread pool and wait for another request. 
 * This means that if a class which has a ThreadLocal object defined and it is loaded in the thread, 
 * the ThreadLocal will never be GCed until the application terminates. 
 * This in turn will cause memory leaks. 
 * So the best practice is to clean up the ThreadLocal reference by calling 
 * ThreadLocal's remove() method.
 * If you do not clean up when you're done, any references it holds to classes 
 * loaded as part of a deployed webapp will remain in the permanent heap 
 * and will never get garbage collected. 
 * Redeploying/undeploying the webapp will not clean up each Thread's reference 
 * to your webapp's class(es) since the Thread is not something owned by your webapp. 
 * Each successive deployment will create a new instance of the class which will never be garbage collected.
 * You will end up with out of memory exceptions due to java.lang.OutOfMemoryError: PermGen space 
 * and after some googling will probably just increase -XX:MaxPermSize instead of fixing the bug.
 * Below is a simple demonstration on how a memory leak may happen when using ThreadLocal. 
 * This demo will simulate a container which creates a few threads to handle lots of requests. 
 * Each request will reference a ThreadLocal object. 
 * First, we will create a heavy object which has a reference to a field with 10MB in size 
 * and it will be handled by each request created later.
 */
public class HeavyObject {
    int limit = 10*1024*1024;//10MB
    byte[] bytes = new byte[limit];
 
    public HeavyObject(){
        for(int j=0; j<limit; j++){
            bytes[j] = 10;
        }
    }
}
