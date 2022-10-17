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

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4, 3};
        sumSubarrayMins(arr);
    }

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
     * i = 0, all contiguous sub-arrays end with A[0] = 3:
     * [3]
     * stack:-1, dp[0 + 1] = dp[0] + (0- (-1))*A[0] = 0 + 1 * 3 = 3
     * push 0 into the stack, stack: -1, 0
     * res: 0 + dp[1] = 3
     *
     * i = 1, all contiguous sub-arrays end with A[1] = 1:
     * [3, 1], [1]
     * stack: -1, 0, stack peek is 0, arr[i] = arr[1] < arr[0], pop 0 from the stack, stack: -1
     * dp[1 + 1] = dp[-1 + 1] + (1 - (-1)) * arr[1] = dp[0] + 2 * arr[1] = 0 + 2 * 1 = 1 + 1 = 2
     * push 1 into the stack, stack: -1, 1
     * res = 3 + dp[2] = 3 + 2 = 5
     *
     * i = 2, all contiguous sub-arrays end with A[2] = 2:
     * [3, 1, 2], [1, 2], [2]
     * stack: -1, 1, arr[2] > arr[stack.peek()] --> arr[2] > arr[1]
     * dp[2 + 1] = dp[1 + 1] + (2 - 1) * arr[2] = dp[2] + arr[2] = 2 + 2 = 1 + 1 + 2 = 4
     * push 2 into the stack, stack: -1, 1, 2
     * res = 5 + 4 = 9
     *
     * i = 3, all contiguous sub-arrays end with A[3] = 4:
     * [3, 1, 2, 4], [1, 2, 4], [2, 4], [4]
     * stack: -1, 1, 2, arr[3] > arr[stack.peek()] --> arr[3] > arr[2]
     * dp[4] = dp[2 + 1] + (3 - 2) * arr[3] = 4 + 1 * 4 = 1 + 1 + 2 + 4 = 8
     * push 3 into the stack, stack: -1, 1, 2, 3
     * res = 9 + 8 = 17
     *
     * i = 4, all contiguous sub-arrays end with A[4] = 3:
     * [3, 1, 2, 4, 3], [1, 2, 4, 3], [2, 4, 3], [4, 3], [3]
     * stack: -1, 1, 2, 3, arr[4] < arr[stack.peek()] --> arr[4] < arr[3], pop 3 from the stack, stack: -1, 1, 2
     * dp[5] = dp[2 + 1] + (4 - 2) * arr[4] = 4 + 2 * 3 = 1 + 1 + 2 + 3 + 3 = 10
     * push 4 into the stack, stack: -1, 1, 2, 4
     * res = 17 + 10 = 27
     * for-loop end
     * return res = 27
     * (
     *   In this case, stack.peek() = 2, A[4] = 3.
     *   For the last 2 sub-arrays [3] and [4, 3], sum of minimum = (i - stack.peek()) * A[i] = (4 - 2)*3 = 6,
     *   For the first 3 sub-arrays [2, 4, 3], [1, 2, 4, 3] and [3, 1, 2, 4, 3],
     *   since they all contain a 2 which is less than 3, sum of minimum = dp[stack.peek() + 1] = 4.
     *   Then, dp[4 + 1] = 4 + 6 = 10
     * )
     *
     * We see in the whole
     * e.g.[3, 1, 2, 4, 3]:
     *
     * 0: [3]
     * 1: [3, 1], [1]
     * 2: [3, 1, 2], [1, 2], [2]
     * 3: [3, 1, 2, 4], [1, 2, 4], [2, 4], [4]
     * 4: [3, 1, 2, 4, 3], [1, 2, 4, 3], [2, 4, 3], [4, 3], [3]
     *
     * 0: sum(min) = 3
     * 1: sum(min) = 1 + 1 = 2
     * 2: sum(min) = 1 + 1 + 2 = 4
     * 3: sum(min) = 1 + 1 + 2 + 4 = 8
     * 4: sum(min) = 1 + 1 + 2 + 3 + 3 = 10
     *
     * @param arr
     * @return
     */
    public static int sumSubarrayMins(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] dp = new int[arr.length + 1];
        /**
         *
         */
        stack.push(-1);
        int res = 0;
        /**
         * 1e9 + 7 = 10^9 + 7, to prevent overflow.
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
            System.out.println("i:" + i + ", dp[" + (i + 1) + "]:" + dp[i+1] + ", res:" + res);
        }
        return res;
    }
}
