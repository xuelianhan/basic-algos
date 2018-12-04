package org.ict.thread;

/**
 * @see https://www.geeksforgeeks.org/output-of-java-program-set-16-threads/
 */
public class ThreadOutPut implements Runnable {
    
    public static void main(String[] args) {
        ThreadOutPut obj = new ThreadOutPut();
        Thread thread = new Thread(obj);
        thread.start();
        System.out.printf("Geeks "); 
        try { 
            thread.join(); 
        }  catch (InterruptedException e) { 
            e.printStackTrace(); 
        } 
        System.out.println("for "); 
    }
    
    @Override
    public void run() {
        System.out.printf("GFG "); 
        System.out.printf("Geeks "); 
    }
}
