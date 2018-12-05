package org.ict.algorithm.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @see https://en.wikipedia.org/wiki/Compare-and-swap
 * @see https://dzone.com/articles/how-cas-compare-and-swap-java
 *
 */
public class CASTest {
    // volatile only guarantee visibility, it can not guarantee atomicity
    private volatile int count1 = 0;
    public  void updateVisitorsV1() {
        ++count1;
    }

    /*
     * Does this code guarantee atomicity? Yes.
     * Does this code guarantee visibility? Yes.
     * Then what is the problem?
     * It makes use of locking and that introduces lot of delay and overhead. 
     * Check this article(http://flex4java.blogspot.com/2015/03/is-multi-threading-really-worth-it.html)
     * (This article has been saved in Evernote)
     * This is very expensive way of making things work.
     */
    private volatile int count2 = 0;
    public synchronized void updateVisitorsV2() {
        ++count2;
    }
    
    /*
     * To overcome these problems described above, atomic constructs were introduced. 
     * If we make use of an AtomicInteger to track the count it will work.
     * The classes that support atomic operations e.g. AtomicInteger, AtomicLong etc. 
     * makes use of CAS. 
     * CAS does not make use of locking rather it is very optimistic in nature. 
     * It follows these steps:
     * 1.Compare the value of the primitive to the value we have got in hand
     * 2.If the values do not match it means some thread in between has changed the value. 
     * 3.Else it will go ahead and swap the value with new value.
     * 
     * Before JDK8, CAS look like this:
     * public final long incrementAndGet() {
     *   for (;;) {
     *     long current = get();
     *     long next = current + 1;
     *     if (compareAndSet(current, next))
     *       return next;
     *   }
     * }
     * 
     * In JDK8 the above code has been changed to a single intrinsic:
     * return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
     * What advantage this single intrinsic have?
     * Actually this single line is JVM intrinsic which is translated by JIT into an optimized instruction sequence. 
     * In case of x86 architecture 
     * it is just a single CPU instruction LOCK XADD which might yield better performance than classic load CAS loop.
     */
    private AtomicInteger count3 = new AtomicInteger(0);
    public void upateVisitorsV3() {
        count3.incrementAndGet();
        //or count3.getAndIncrement();
    }
    
    /*
     * Now think about the possibility when we have high contention 
     * and a number of threads want to update the same atomic variable. 
     * In that case there is a possibility that locking will outperform the atomic variables 
     * but in realistic contention levels atomic variables outperform lock. 
     * There is one more construct introduced in Java 8, LongAdder. 
     * As per the documentation:
     * This class is usually preferable to AtomicLong 
     * when multiple threads update a common sum that is used for purposes 
     * such as collecting statistics, not for fine-grained synchronization control. 
     * Under low update contention, the two classes have similar characteristics. 
     * But under high contention, expected throughput of this class is significantly higher, 
     * at the expense of higher space consumption.
     * So LongAdder is not always a replacement for AtomicLong. We need to consider the following aspects:
     * 1.When no contention is present AtomicLong performs better.
     * 2.LongAdder will allocate Cells (a final class declared in abstract class Striped64) 
     * to avoid contention which consumes memory. 
     * So in case we have a tight memory budget we should prefer AtomicLong.
     */
}
