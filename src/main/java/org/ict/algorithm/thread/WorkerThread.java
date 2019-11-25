package org.ict.algorithm.thread;

public class WorkerThread implements Runnable {
    private ThreadLocal<Byte[]> local = new ThreadLocal<Byte[]>();
    private volatile boolean isStopped = false;
     
    public void run(){
        System.out.println("Running...");
         
        while(!isStopped){
            try {
                System.out.println(local.get());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
     
    public void setList(Byte[] bytes){
        local.set(bytes);
    }
     
    public void handleRequest(ThreadRequest request){
        request.showLocal();
    }
     
    public void end(){
        isStopped = true;
    }
}
