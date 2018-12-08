package org.ict.algorithm.thread;

/**
 * Next, we will create a request class which will be passed to the worker thread and getting handled.
 * @see https://www.pixelstech.net/article/1399361023-Use-Java-ThreadLocal-with-caution
 *
 */
public class ThreadRequest {
    ThreadLocal<HeavyObject> local = new ThreadLocal<HeavyObject>();
    
    public void setLocal(HeavyObject object){
        local.set(object);;
    }
     
    public void showLocal(){
        System.out.println(local.get());
    }
}
