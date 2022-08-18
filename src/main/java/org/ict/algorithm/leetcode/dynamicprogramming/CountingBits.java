package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * Given an integer n,
 * return an array ans of length n + 1 such that
 * for each i (0 <= i <= n),
 * ans[i] is the number of 1's in the binary representation of i.
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * Example 2:
 *
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 * 6 --> 110
 * 7 --> 111
 * 8 --> 1000
 * 9 --> 1001
 * 10 -->1010
 *
 * Constraints:
 * 0 <= n <= 105
 *
 * Follow up:
 * It is very easy to come up with a solution with a runtime of O(n log n).
 * Can you do it in linear time O(n) and possibly in a single pass?
 * Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
 *
 * @see <a href="https://www.geeksforgeeks.org/count-set-bits-in-an-integer/"></a>
 * @see <a href="https://stackoverflow.com/questions/8871204/count-number-of-1s-in-binary-representation"></a>
 * @see <a href="https://en.wikipedia.org/wiki/Hamming_weight"></a>
 * @author sniper
 * @date 2022/8/18
 * LC338
 */
public class CountingBits {

    public int[] countBits(int n) {
        //todo
        int[] dp = new int[n + 1];

        return dp;
    }
}
