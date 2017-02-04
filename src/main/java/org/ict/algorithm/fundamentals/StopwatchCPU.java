package org.ict.algorithm.fundamentals;

import java.lang.management.ThreadMXBean;
import java.lang.management.ManagementFactory;
import org.ict.algorithm.util.StdOut;

public class StopwatchCPU {
    private static final double NANOSECONDS_PER_SECOND = 1000000000;
    
    private final ThreadMXBean threadTimer;

    private final long start;

    public StopwatchCPU() {
        threadTimer = ManagementFactory.getThreadMXBean();
        start = threadTimer.getCurrentThreadCpuTime();
    }
    
    public double elapsedTime() {
        long now = threadTimer.getCurrentThreadCpuTime();
        return (now -start) / NANOSECONDS_PER_SECOND;
    }

    public static void main(String[] args) {
        int n  = Integer.parseInt(args[0]);

        // sum of square roots of integers from 1 to n using Math.sqrt(x).
        StopwatchCPU timer1 = new StopwatchCPU();
        double sum1 = 0.0;
        for (int i = 1; i <= n; i++) {
            sum1 += Math.sqrt(i);
        }
        double time1 = timer1.elapsedTime();
        StdOut.printf("%e(%.2f seconds)\n", sum1, time1);

        // sum of square roots of integers from 1 to n using Math.pow(x, 0.5).
        StopwatchCPU timer2 = new StopwatchCPU();
        double sum2 = 0.0;
        for (int i = 1; i <=n; i++) {
            sum2 += Math.pow(i, 0.5);
        }
        double time2 = timer2.elapsedTime();
        StdOut.printf("%e (%.2f seconds)\n", sum2, time2);
    }

}
