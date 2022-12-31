package org.ict.algorithm.leetcode.heap;

import java.util.*;

/**
 * You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks,
 * where tasks[i] = [enqueueTime-i, processingTime-i] means that the i​​​​​​th​​​​ task will be available to process at enqueueTime-i,
 * and will take processingTime-i to finish processing.
 *
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 *
 * If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 * If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time.
 * If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
 * Once a task is started, the CPU will process the entire task without stopping.
 * The CPU can finish a task then start a new one instantly.
 * Return the order in which the CPU will process the tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
 * Output: [0,2,3,1]
 * Explanation: The events go as follows:
 * - At time = 1, task 0 is available to process. Available tasks = {0}.
 * - Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
 * - At time = 2, task 1 is available to process. Available tasks = {1}.
 * - At time = 3, task 2 is available to process. Available tasks = {1, 2}.
 * - Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
 * - At time = 4, task 3 is available to process. Available tasks = {1, 3}.
 * - At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
 * - At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
 * - At time = 10, the CPU finishes task 1 and becomes idle.
 * Example 2:
 *
 * Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
 * Output: [4,3,2,0,1]
 * Explanation: The events go as follows:
 * - At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
 * - Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
 * - At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
 * - At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
 * - At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
 * - At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
 * - At time = 40, the CPU finishes task 1 and becomes idle.
 *
 *
 * Constraints:
 *
 * tasks.length == n
 * 1 <= n <= 10^5
 * 1 <= enqueueTimei, processingTimei <= 10^9
 * @author sniper
 * @date 29 Dec, 2022
 * LC1834
 * AMZ,Google,Facebook
 */
public class SingleThreadedCPU {

    public static void main(String[] args) {
        SingleThreadedCPU instance = new SingleThreadedCPU();
        int[][] tasks = {{1, 2}, {2, 4}, {3, 2}, {4, 1}};
        int[] res = instance.getOrder(tasks);
        System.out.println(Arrays.toString(res));
    }


    /**
     * Time Cost 138ms
     * @param tasks
     * @return
     */
    public int[] getOrderV5(int[][] tasks) {
        int n = tasks.length;
        Task[] list = new Task[n];
        for (int i = 0; i < n; i++) {
            list[i] = new Task(i, tasks[i][0], tasks[i][1]);
        }
        Arrays.sort(list, Comparator.comparingInt(t -> t.enqueueTime));

        PriorityQueue<Task> minHeap = new PriorityQueue<>(
                (t1, t2) -> t1.processTime == t2.processTime
                        ? Integer.compare(t1.index, t2.index)
                        : Integer.compare(t1.processTime, t2.processTime));
        int ri = 0;
        int ti = 0;
        int time = list[0].enqueueTime;
        int[] res = new int[n];
        while (ri < n) {
            while (ti < n && time >= list[ti].enqueueTime) {
                Task task = list[ti];
                minHeap.offer(task);
                ti++;
            }

            if (minHeap.isEmpty()) {
                time = list[ti].enqueueTime;
                continue;
            }
            Task top = minHeap.poll();
            res[ri++] = top.index;
            time += top.processTime;
        }
        return res;
    }

    /**
     * Time Cost 137ms
     * Using ArrayList and MinHeap
     * @param tasks
     * @return
     */
    public int[] getOrderV4(int[][] tasks) {
        int n = tasks.length;
        List<Task> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Task t = new Task(i, tasks[i][0], tasks[i][1]);
            list.add(t);
        }
        list.sort(Comparator.comparingInt(t -> t.enqueueTime));
        PriorityQueue<Task> minHeap = new PriorityQueue<>(
                (t1, t2) -> t1.processTime == t2.processTime
                        ? Integer.compare(t1.index, t2.index)
                        : Integer.compare(t1.processTime, t2.processTime));
        int ri = 0;
        int ti = 0;
        int time = list.get(0).enqueueTime;
        int[] res = new int[n];
        while (ri < n) {
            while (ti < n && time >= list.get(ti).enqueueTime) {
                Task task = list.get(ti);
                minHeap.offer(task);
                ti++;
            }

            if (minHeap.isEmpty()) {
                time = list.get(ti).enqueueTime;
                continue;
            }
            Task top = minHeap.poll();
            res[ri++] = top.index;
            time += top.processTime;
        }
        return res;
    }

    class Task implements Comparable<Task> {
        private int index;
        private int enqueueTime;
        private int processTime;

        Task(int index, int enqueueTime, int processTime) {
            this.index = index;
            this.enqueueTime = enqueueTime;
            this.processTime = processTime;
        }

        @Override
        public int compareTo(Task that) {
            if (this.processTime == that.processTime) {
                return Integer.compare(this.index, that.index);
            }
            return Integer.compare(this.processTime, that.processTime);
        }
    }

    /**
     * Time Cost 140ms
     * @param tasks
     * @return
     */
    public int[] getOrderV3(int[][] tasks) {
        int n = tasks.length;
        int[][] extTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            extTasks[i][0] = i;
            extTasks[i][1] = tasks[i][0];
            extTasks[i][2] = tasks[i][1];
        }

        /**
         * Sort by enqueueTime,
         * Always try to use compare method instead of subtracting cause it might overflow.
         */
        Arrays.sort(extTasks, Comparator.comparingInt(a -> a[1]));
        /**
         * Sort by processingTime first, if processingTime equals, then sort by the index.
         * Always try to use compare method instead of subtracting cause it might overflow.
         */
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] == b[2] ? Integer.compare(a[0], b[0]) : Integer.compare(a[2], b[2]));
        int ri = 0;//result index pointer
        int ti = 0;//task index pointer
        int time = 0;//time upper limit allows task enqueue
        int[] res = new int[n];
        while (ri < n) {
            /**
             * If minHeap is empty, this means the CPU is idle,
             * so we can update the time upper to allow more tasks in range to step into the task queue.
             */
            if (minHeap.isEmpty()) {
                time = Math.max(time, extTasks[ti][1]);
            }

            /**
             * If enqueueTime is less than or equal to time upper, push the current task into the minHeap.
             * Notice extTasks[ti][1] <= time here, not extTasks[ti][1] < time.
             */
            while (ti < n && extTasks[ti][1] <= time) {
                minHeap.offer(extTasks[ti++]);
            }

            /**
             * when cpu finished processing task, poll task from the task queue.
             * Mark the index of finished task into the result,
             * and expand the time upper to let more tasks step in.
             */
            int[] bestFit = minHeap.poll();
            time += bestFit[2];
            res[ri++] = bestFit[0];
        }

        return res;
    }


    /**
     * Time Cost 144ms
     * @param tasks
     * @return
     */
    public int[] getOrderV2(int[][] tasks) {
        int n = tasks.length;
        int[][] extTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            extTasks[i][0] = i;
            extTasks[i][1] = tasks[i][0];
            extTasks[i][2] = tasks[i][1];
        }

        /**
         * Sort by enqueueTime,
         * Always try to use compare method instead of subtracting cause it might overflow.
         */
        Arrays.sort(extTasks, Comparator.comparingInt(a -> a[1]));
        /**
         * Sort by processingTime first, if processingTime equals, then sort by the index.
         * Always try to use compare method instead of subtracting cause it might overflow.
         */
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] == b[2] ? Integer.compare(a[0], b[0]) : Integer.compare(a[2], b[2]));
        int ri = 0;//result index pointer
        int ti = 0;//task index pointer
        int time = extTasks[0][1];//time upper limit allows task enqueue
        int[] res = new int[n];
        while (ri < n) {
            /**
             * If enqueueTime is less than or equal to time upper, push the current task into the minHeap.
             * Notice extTasks[ti][1] <= time here, not extTasks[ti][1] < time.
             */
            while (ti < n && extTasks[ti][1] <= time) {
                minHeap.offer(extTasks[ti++]);
            }
            /**
             * If minHeap is empty, this means the CPU is idle,
             * so we can update the time upper to allow more tasks in range to step into the task queue.
             */
            if (minHeap.isEmpty()) {
                time = extTasks[ti][1];
                continue;
            }
            /**
             * when cpu finished processing task, poll task from the task queue.
             * Mark the index of finished task into the result,
             * and expand the time upper to let more tasks step in.
             */
            int[] bestFit = minHeap.poll();
            time += bestFit[2];
            res[ri++] = bestFit[0];
        }

        return res;
    }


    /**
     * Time Cost 144ms
     * @param tasks
     * @return
     */
    public int[] getOrderV1(int[][] tasks) {
        int n = tasks.length;
        int[][] extTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            extTasks[i][0] = i;
            extTasks[i][1] = tasks[i][0];
            extTasks[i][2] = tasks[i][1];
        }

        /**
         * Sort by enqueueTime,
         * Always try to use compare method instead of subtracting cause it might overflow.
         */
        Arrays.sort(extTasks, Comparator.comparingInt(a -> a[1]));
        /**
         * Sort by processingTime first, if processingTime equals, then sort by the index.
         * Always try to use compare method instead of subtracting cause it might overflow.
         */
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] == b[2] ? Integer.compare(a[0], b[0]) : Integer.compare(a[2], b[2]));
        int ri = 0;//result index pointer
        int ti = 0;//task index pointer
        int time = 0;//time upper limit allows task enqueue
        int[] res = new int[n];
        while (ri < n) {
            /**
             * If enqueueTime is less than or equal to time upper, push the current task into the minHeap.
             * Notice extTasks[ti][1] <= time here, not extTasks[ti][1] < time.
             */
            while (ti < n && extTasks[ti][1] <= time) {
                minHeap.offer(extTasks[ti++]);
            }
            /**
             * If minHeap is empty, this means the CPU is idle,
             * so we can update the time upper to allow more tasks in range to step into the task queue.
             */
            if (minHeap.isEmpty()) {
                time = extTasks[ti][1];
                continue;
            }
            /**
             * when cpu finished processing task, poll task from the task queue.
             * Mark the index of finished task into the result,
             * and expand the time upper to let more tasks step in.
             */
            int[] bestFit = minHeap.poll();
            time += bestFit[2];
            res[ri++] = bestFit[0];
        }

        return res;
    }


    /**
     * Time Cost 130ms
     * Time Complexity O(N*logN)
     * Space Complexity O(N)
     *
     * e.g.tasks = [[1,2],[2,4],[3,2],[4,1]]
     * After Arrays sort by enqueueTime:
     * [[0,1,2],[1,2,4],[2,3,2],[3,4,1]]
     *
     * ri:0, ti:0, ri < 4, time:0, ti < 4, task:[0,1,2], enqueueTime:1, enqueueTime > time:0
     *    minHeap is empty, time = enqueueTime = 1, continue
     * ri:0, ti:0, ri < 4, time:1, ti < 4, task:[0,1,2], enqueueTime:1, enqueueTime == time:1
     *    minHeap offer [0, 1, 2], ti++ --> ti:1
     *    ti < 4, task:[1,2,4], enqueueTime:2, 2 > time:1
     *    minHeap poll [0,1,2], res[0] = 0, ri = 0, ri++ --> ri:1
     *    time = 1 + 2 = 3
     * ri:1, ti:1, ri < 4, time:3, ti < 4, task:[1,2,4], enqueueTime:2, 2 < time:3
     *    minHeap offer [1,2,4], ti++ --> ti:2
     *    ti:2, ti < 4, task:[2,3,2], enqueueTime:3, 3 = time:3
     *    minHeap offer [2,3,3], ti++ --> ti:3
     *    ti:3, ti < 4, task:[3,4,1], enqueueTime:4, 4 > time:3
     *    minHeap is not empty, minHeap: [2,3,3], [1,2,4]
     *    minHeap poll [2,3,3], minHeap:[1,2,4], res[1] = 2, ri = 1, ri++ --> ri:2
     *    time = 3 + 3 = 6
     * ri:2, ti:3, ri < 4, time:6, ti < 4, task:[3,4,1], enqueueTime:4, 4 < time:6
     *    minHeap offer[3,4,1], ti++ --> ti:4
     *    minHeap is not empty, minHeap:[3,4,1], [1,2,4]
     *    minHeap poll [3,4,1], minHeap:[1,2,4], res[2] = 3, ri = 2, ri++ --> ri:3
     *    time = 6 + 1 = 7
     * ri:3, ti:4, ri < 4, time:7, ti = 4, no task need to enqueue.
     *    minHeap is not empty, minHeap:[1,2,4]
     *    minHeap poll [1,2,4], minHeap:empty.
     *    res[3] = 1, ri = 3, ri++ --> ri:4
     *    time = 7 + 4 = 11
     * ri:4, ri == 4, end-outer-while-loop.
     * return res:[0, 2, 3, 1]
     *
     * @see <a href="https://leetcode.com/problems/single-threaded-cpu/solutions/1164102/java-sort-by-time-and-use-pq"></a>
     * @author Alena Korney, an engineer in Google of Switzerland.
     * @see <a href="https://leetcode.com/korney_a/"></a>
     * @param tasks
     * @return
     */
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] extTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            extTasks[i][0] = i;
            extTasks[i][1] = tasks[i][0];
            extTasks[i][2] = tasks[i][1];
        }

        /**
         * Sort by enqueueTime
         */
        Arrays.sort(extTasks, (a, b) -> a[1] - b[1]);
        /**
         * Sort by processingTime first, if processingTime equals, then sort by the index.
         */
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] == b[2] ? a[0] - b[0] : a[2] - b[2]);
        int ri = 0;//result index pointer
        int ti = 0;//task index pointer
        int time = 0;//time upper limit allows task enqueue
        int[] res = new int[n];
        while (ri < n) {
            /**
             * If enqueueTime is less than or equal to time upper, push the current task into the minHeap.
             * Notice extTasks[ti][1] <= time here, not extTasks[ti][1] < time.
             */
            while (ti < n && extTasks[ti][1] <= time) {
                minHeap.offer(extTasks[ti++]);
            }
            /**
             * If minHeap is empty, this means the CPU is idle,
             * so we can update the time upper to allow more tasks in range to step into the task queue,
             * then we continue to check whether task can go in.
             */
            if (minHeap.isEmpty()) {
                time = extTasks[ti][1];
                continue;
            }
            /**
             * when cpu finished processing task, poll task from the task queue.
             * Mark the index of finished task into the result,
             * and expand the time upper to let more tasks step in.
             */
            int[] bestFit = minHeap.poll();
            res[ri++] = bestFit[0];
            time += bestFit[2];
        }

        return res;
    }
}
