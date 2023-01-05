package org.ict.algorithm.leetcode.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed integer array tasks,
 * where tasks[i] represents the difficulty level of a task.
 * In each round, you can complete either 2 or 3 tasks of the same difficulty level.
 *
 * Return the minimum rounds required to complete all the tasks,
 * or -1 if it is not possible to complete all the tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [2,2,3,3,2,4,4,4,4,4]
 * Output: 4
 * Explanation: To complete all the tasks, a possible plan is:
 * - In the first round, you complete 3 tasks of difficulty level 2.
 * - In the second round, you complete 2 tasks of difficulty level 3.
 * - In the third round, you complete 3 tasks of difficulty level 4.
 * - In the fourth round, you complete 2 tasks of difficulty level 4.
 * It can be shown that all the tasks cannot be completed in fewer than 4 rounds, so the answer is 4.
 * Example 2:
 *
 * Input: tasks = [2,3,3]
 * Output: -1
 * Explanation: There is only 1 task of difficulty level 2,
 * but in each round, you can only complete either 2 or 3 tasks of the same difficulty level.
 * Hence, you cannot complete all the tasks, and the answer is -1.
 *
 *
 * Constraints:
 *
 * 1 <= tasks.length <= 10^5
 * 1 <= tasks[i] <= 10^9
 * @author sniper
 * @date 05 Jan, 2023
 * LC2244
 */
public class MinimumRoundsToCompleteAllTasks {

    public static void main(String[] args) {
        int[] tasks = {2, 2, 3, 3, 2, 4, 4, 4, 4, 4};
        MinimumRoundsToCompleteAllTasks instance = new MinimumRoundsToCompleteAllTasks();
        int res = instance.minimumRoundsV3(tasks);
        System.out.println(res);
    }

    /**
     * Understanding the following Solution.
     * Time Cost 96ms
     * @param tasks
     * @return
     */
    public int minimumRoundsV3(int[] tasks) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int task : tasks) {
            freq.put(task, freq.getOrDefault(task, 0) + 1);
        }
        int res = 0;
        for (Integer v : freq.values()) {
            if (v == 1) {
                return -1;
            }
            res += Math.ceil(v / 3.0);
        }
        return res;
    }

    /**
     * Understanding the following Solution.
     * Time Cost 59ms
     * @param tasks
     * @return
     */
    public int minimumRoundsV2(int[] tasks) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int task : tasks) {
            freq.put(task, freq.getOrDefault(task, 0) + 1);
        }
        int res = 0;
        for (Integer v : freq.values()) {
            if (v == 1) {
                return -1;
            }
            res += (v + 2) / 3;
        }
        return res;
    }

    /**
     * Understanding the following Solution.
     * Time Cost 90ms
     * @param tasks
     * @return
     */
    public int minimumRoundsV1(int[] tasks) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int task : tasks) {
            freq.put(task, freq.getOrDefault(task, 0) + 1);
        }
        int res = 0;
        for (Integer v : freq.values()) {
            if (v == 1) {
                return -1;
            }
            res += v / 3 + (v % 3 == 0 ? 0 : 1);
        }
        return res;
    }

    /**
     * Understanding the following Solution.
     * Time Cost 58ms
     * e.g.tasks = [2, 3, 3], expect: -1
     * e.g.tasks = [1, 1, 1, 1, 1], expect: 2
     * e.g.tasks = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1], expect: 4
     * e.g.tasks = [2, 2, 3, 3, 2, 4, 4, 4, 4, 4], expect:4
     * @param tasks
     * @return
     */
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int task : tasks) {
            freq.put(task, freq.getOrDefault(task, 0) + 1);
        }
        int res = 0;
        for (Integer v : freq.values()) {
            if (v == 1) {
                return -1;
            }
            if (v % 3 == 0) {
                res += v / 3;
            } else {
                res += v / 3 + 1;
            }
        }
        return res;
    }
}
