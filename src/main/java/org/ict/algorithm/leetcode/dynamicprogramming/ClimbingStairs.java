package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * You are climbing a staircase.
 * It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 *
 * Example 1:
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Constraints:
 * 1 <= n <= 45
 *
 * @author sniper
 * @date 2022/8/18
 * LC70
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        int n = 2;
        ClimbingStairs instance = new ClimbingStairs();
        int result = instance.climbStairs(n);
        System.out.println(result);
    }

    /**
     * Math solution.
     * @param n
     * @return
     */
    public int climbStairsV4(int n) {
        double root = Math.sqrt(5);
        double a = Math.pow((1 + root) / 2, n + 1);
        double b = Math.pow((1 - root) / 2, n + 1);
        double res = (1 / root) * (a - b);
        return (int)res;
    }

    /**
     * Understanding the following solution.
     * @param n
     * @return
     */
    public int climbStairsV3(int n) {
        int[] memo = new int[n + 1];
        return helper(n, memo);
    }

    private int helper(int n , int[] memo) {
        if (n <= 1) {
            return 1;
        }
        if (memo[n] > 0) {
            return memo[n];
        }
        memo[n] = helper(n - 1, memo) + helper(n - 2, memo);
        return memo[n];
    }

    /**
     * Understanding the following solution.
     * n:1, b = 1 + 1 = 2, a = 2 - 1 = 1
     * ---------------------------------
     * n:2, b:2, a:1, n--
     * n:1, b:3, a:2, n--
     * n:0, return a:2
     * ---------------------------------
     * @param n
     * @return
     */
    public int climbStairsV2(int n) {
        int a = 1;
        int b = 1;
        while (n > 0) {
            b += a;
            a = b - a;
            n--;
        }
        return a;
    }

    /**
     * Understanding the following solution.
     * @param n
     * @return
     */
    public int climbStairsV1(int n) {
        if (n <= 1) {
            return 1;
        }
        int pre1 = 1;
        int pre2 = 2;
        for (int i = 3; i <= n; i++) {
            int sum = pre1 + pre2;
            pre1 = pre2;
            pre2 = sum;
        }
        return pre2;
    }


    /**
     * Understanding the following solution.
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }
}
