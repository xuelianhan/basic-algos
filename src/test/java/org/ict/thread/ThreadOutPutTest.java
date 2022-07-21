package org.ict.thread;

/**
 * Geeks GFG Geeks for 
 * 
 * @see https://www.geeksforgeeks.org/output-of-java-program-set-16-threads/
 * 
 * Always output:
 * Geeks GFG Geeks for 
 *
 */
public class ThreadOutPutTest {

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
    
}
