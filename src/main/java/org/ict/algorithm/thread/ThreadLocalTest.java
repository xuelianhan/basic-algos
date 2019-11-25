package org.ict.algorithm.thread;

/**
 * @see https://www.pixelstech.net/article/1399361023-Use-Java-ThreadLocal-with-caution
 * @see https://stackoverflow.com/questions/30992479/threadlocal-memory-leak
 * @see https://stackoverflow.com/questions/17968803/threadlocal-memory-leak
 * The main testing class will create a few WorkerThreads and each WorkerThread will 
 * handle a few requests which reference some ThreadLocal objects. 
 * Since the WorkerThread is never stopped before exiting this program, 
 * all the ThreadLocal objects created will persist in heap and the heap 
 * is eaten up with more and more requests created and finally you will 
 * get the OutOfMemoryError.
 * 
 * @see https://www.cnblogs.com/micrari/p/6790229.html
 */
public class ThreadLocalTest {
    public static void main(String[] args){
        int count = 5;
        int i = 0;
        long totalEatenMemory = 0;
        long maxEatenMemory = Long.MIN_VALUE;
        long minEatenMemory = Long.MAX_VALUE;
         
        long startTime = System.nanoTime();
        while(++i <= count){
            long eatenMmeory = test();
            if(eatenMmeory > maxEatenMemory){
                maxEatenMemory = eatenMmeory;
            }
            if(eatenMmeory < minEatenMemory){
                minEatenMemory = eatenMmeory;
            }
            totalEatenMemory += eatenMmeory;
        }
        long endTime = System.nanoTime();
         
        System.out.println("Tests run : "+count+"; Avg eaten memory : "+(totalEatenMemory*1.0/(count*1000))+" KB");
        System.out.println("Max eaten memory : "+maxEatenMemory);
        System.out.println("Min eaten memory : "+minEatenMemory);
        System.out.println("Total time elapsed : "+((endTime-startTime)*1.0/1000000L)+"ms");
        System.out.println("Complete running");
    }
     
    private static long test(){
        int count = 200;
        int i = 0;
         
        WorkerThread worker = new WorkerThread();
        Thread thread = new Thread(worker);
        thread.start();
        try{
            long initialMemory = Runtime.getRuntime().freeMemory();
            while(++i <= count){
                System.out.println("Free memory "+Runtime.getRuntime().freeMemory()+" before count "+i);
                HeavyObject object = new HeavyObject();
                System.out.println("Free memory "+Runtime.getRuntime().freeMemory()+" after count "+i);
                ThreadRequest request = new ThreadRequest();
                request.setLocal(object);
                worker.handleRequest(request);
                System.out.println("Free memory "+Runtime.getRuntime().freeMemory()+" at count "+i);
            }
            long endMemory = Runtime.getRuntime().freeMemory();
            long eatenMemory = (initialMemory-endMemory);
            System.out.println("Eaten memory : "+eatenMemory);
            return eatenMemory;
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            worker.end();
        }
        return 0;
    }
}

