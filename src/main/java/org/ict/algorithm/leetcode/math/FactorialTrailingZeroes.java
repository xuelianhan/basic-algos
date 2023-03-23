package org.ict.algorithm.leetcode.math;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 * Example 2:
 *
 * Input: n = 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 * Example 3:
 *
 * Input: n = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= n <= 10^4
 *
 *
 * Follow up: Could you write a solution that works in logarithmic time complexity?
 * @author sniper
 * @date 23 Mar, 2023
 * LC172, Medium
 */
public class FactorialTrailingZeroes {


    /**
     *
     * todo
     *
     * Let f(n) give the number of trailing zeros in the base ten representation of n!.
     * Then, f(n) = floor(n/5) + floor(n/5^2) + floor(n/5^3) +...+floor(n/5^k)
     * k = floor(log5toN)
     * Time Complexity O(log5toN)
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        if (n < 0) {
            return -1;
        }
        int res = 0;
        for (int i = 5; n / i >= 1; i *= 5) {
            res += n / i;
        }
        return res;
    }
}
