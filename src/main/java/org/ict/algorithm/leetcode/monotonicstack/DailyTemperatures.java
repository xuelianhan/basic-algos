package org.ict.algorithm.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 *
 *
 * Example 1:
 *
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 *
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 *
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 *
 *
 * Constraints:
 *
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 * @author sniper
 * @date 27 Sep, 2022
 * LC739
 */
public class DailyTemperatures {

    /**
     * Improvement version based Standard Monotonic Stack.
     * @param temperatures
     * @return
     */
    public int[] dailyTemperaturesV1(int[] temperatures) {
        int[] stack = new int[temperatures.length];
        int top = -1;
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(top > -1 && temperatures[stack[top]] < temperatures[i]) {
                int idx = stack[top--];
                ret[idx] = i - idx;
            }
            stack[++top] = i;
        }
        return ret;
    }

    /**
     * Input: temperatures = [73,74,75,71,69,72,76,73]
     *  0  1  2  3  4  5  6  7
     * [73,74,75,71,69,72,76,73]
     *
     * [74,75,76,72,72,76,0,0]
     *
     * [1,1,4,2,1,1,0,0]
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            /**
             * Bottom-Top Decreasing Monotonic Stack.
             */
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int top = stack.pop();
                res[top] = i - top;
            }
            stack.push(i);
        }
        return res;
    }

}
