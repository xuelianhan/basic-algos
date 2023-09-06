package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
 * Once you pay the cost, you can either climb one or two steps.
 *
 * You can either start from the step with index 0, or the step with index 1.
 *
 * Return the minimum cost to reach the top of the floor.
 *
 *
 *
 * Example 1:
 *
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 * Example 2:
 *
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 *
 *
 * Constraints:
 *
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 * @author sniper
 * @date 05 Sep 2023
 * LC746, Easy
 */
public class MinCostClimbingStairs {


    /**
     * Time Cost 0ms
     * @param cost
     * @return
     */
    public int minCostClimbingStairsV3(int[] cost) {
        int a = 0;
        int b = 0;
        for (int num : cost) {
            int t = Math.min(a, b) + num;
            a = b;
            b = t;
        }
        return Math.min(a, b);
    }

    /**
     * Time Cost 0ms
     * @param cost
     * @return
     */
    public int minCostClimbingStairsV2(int[] cost) {
        int n = cost.length;
        for (int i = 2; i < n; i++) {
            cost[i] += Math.min(cost[i - 1], cost[i - 2]);
        }
        return Math.min(cost[n - 1], cost[n - 2]);
    }

    /**
     * Time Cost 1ms
     * @param cost
     * @return
     */
    public int minCostClimbingStairsV1(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        for (int i = 2; i < n + 1; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[n];
    }

    /**
     * Time Cost 2ms
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        Map<Integer, Integer> memo = new HashMap<>();
        return helper(cost, cost.length, memo);
    }

    private int helper(int[] cost, int i, Map<Integer, Integer> memo) {
        if (memo.containsKey(i)) {
            return memo.get(i);
        }
        if (i <= 1) {
            memo.put(i, cost[i]);
            return memo.get(i);
        }
        int res = 0;
        if (i < cost.length) {
            res = cost[i];
        }
        res += Math.min(helper(cost, i - 1, memo), helper(cost, i - 2, memo));
        memo.put(i, res);
        return res;
    }

}
