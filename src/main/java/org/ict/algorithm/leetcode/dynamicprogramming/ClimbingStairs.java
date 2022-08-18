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
        int result = climbStairs(n);
        System.out.println(result);
    }

    public static int climbStairs(int n) {
        //Declare an array to store  numbers
        int[] d = new int[n + 2];
        d[0] = 1;
        d[1] = 2;
        for (int i = 2; i <=n; i++) {
            d[i] = d[i-1] + d[i-2];
        }
        return d[n-1];
    }
}
