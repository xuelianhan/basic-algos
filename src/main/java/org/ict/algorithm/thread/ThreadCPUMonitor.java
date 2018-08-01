package org.ict.algorithm.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

public class ThreadCPUMonitor {
    
    public static long [][] dumpThreadCPUOccupation() {
        ThreadMXBean tm = ManagementFactory.getThreadMXBean();
        tm.setThreadContentionMonitoringEnabled(true);
        long [] tid = tm.getAllThreadIds();
        ThreadInfo [] tia = tm.getThreadInfo(tid, Integer.MAX_VALUE);

        long [][] threadArray = new long[tia.length][2];

        for (int i = 0; i < tia.length; i++) {
            long threadId = tia[i].getThreadId();
            long cpuTime = tm.getThreadCpuTime(tia[i].getThreadId())/(1000*1000*1000);
            threadArray[i][0] = threadId;
            threadArray[i][1] = cpuTime;
        }
        return threadArray;
    }

    public static void main(String[] args) {
        long [][] threadArray = dumpThreadCPUOccupation();
        System.out.println(Arrays.deepToString(threadArray));
    }
}
