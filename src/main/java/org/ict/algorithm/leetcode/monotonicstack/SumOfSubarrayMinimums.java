package org.ict.algorithm.leetcode.monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array of integers arr,
 * find the sum of min(b), where b ranges over every (contiguous) subarray of arr.
 * Since the answer may be large,
 * return the answer modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 * Example 2:
 *
 * Input: arr = [11,81,94,43,3]
 * Output: 444
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 3 * 10^4
 * 1 <= arr[i] <= 3 * 10^4
 *
 * The number 1e9 + 71e9+7 fits nicely into a signed 32-bit integer.
 * It is also the first 10-digit prime number.
 * In some problems we need to compute the Modular Multiplicative Inverse and it helps very much that this number is prime.
 *
 * In fact any prime number less then 2^{30} will be fine in order to prevent possible overflows.
 * But this one can be easily written as 1e9 + 71e9+7.
 * This reasoning almost uniquely determined this number.
 *
 * @author sniper
 * @date 27 Sep, 2022
 * LC907
 */
public class SumOfSubarrayMinimums {

    /**
     * Solution provided by Zane Wang
     *
     * stack: An increasing stack, store the index
     * dp[i + 1]: Sum of minimum of sub-arrays which end with A[i]
     * dp[i + 1] = dp[prev + 1] + (i - prev) * A[i], where prev is the last number which is less than A[i],
     * since we maintain a monotonous increasing stack, prev = stack.peek()
     *
     * eg. [3, 1, 2, 4, 3]:
     * 0 1 2 3 4
     * 3 1 2 4 3
     *
     * when i = 4, all sub-arrays end with A[4] = 3:
     * [3, 1, 2, 4, 3], [1, 2, 4, 3], [2, 4, 3], [4, 3], [3]
     * dp[5] = 1 + 1 + 2 + 3 + 3 = 10
     *
     * when i = 3, all sub-arrays end with A[3] = 4:
     * [3, 1, 2, 4], [3, 1, 4], [3, 2, 4], [1, 2, 4], [3, 4], [1, 4], [2, 4], [4]
     * dp[4] = 1 + 1 + 2 + 1 + 3 + 1 + 2 + 4 = 16
     *
     * when i = 2, all sub-arrays end with A[2] = 2:
     * [3, 1, 2], [3, 2], [1, 2], [2]
     * dp[3] = 1 + 2 + 1 + 2 = 6
     *
     * when i = 1, all sub-arrays end with A[1] = 1:
     * [3, 1], [1]
     * dp[2]= 1 + 1 = 2
     *
     * when i = 0, all sub-arrays end with A[0] = 3:
     * [3]
     * dp[1] = 3
     *
     * In this case, stack.peek() = 2, A[2] = 2.
     * For the first 2 sub-arrays [3] and [4, 3], sum of minimum = (i - stack.peek()) * A[i] = 6.
     * For the last 3 sub-arrays [2, 4, 3], [1, 2, 4, 3] and [3, 1, 2, 4, 3],
     * since they all contain a 2 which is less than 3, sum of minimum = dp[stack.peek() + 1] = 4.
     * Then, dp[i + 1] = 4 + 6 = 10
     *
     * @param arr
     * @return
     */
    public int sumSubarrayMins(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] dp = new int[arr.length + 1];
        /**
         *
         */
        stack.push(-1);
        int res = 0;
        /**
         * 1e9 + 7 = 10^9 + 7
         */
        int mod = (int)1e9 + 7;
        for (int i = 0; i < arr.length; i++) {
            while (stack.peek() != -1 && arr[i] < arr[stack.peek()]) {
                stack.pop();
            }
            dp[i + 1] = (dp[stack.peek() + 1] + (i - stack.peek()) * arr[i]) % mod;
            stack.push(i);
            res += dp[i + 1];
            res %= mod;
        }
        return res;
    }
}
