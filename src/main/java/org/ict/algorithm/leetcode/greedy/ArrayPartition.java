package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * Given an integer array nums of 2n integers,
 * group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn) such that the sum of min(ai, bi) for all i is maximized.
 * Return the maximized sum.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,4,3,2]
 * Output: 4
 * Explanation: All possible pairings (ignoring the ordering of elements) are:
 * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
 * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
 * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
 * So the maximum possible sum is 4.
 * Example 2:
 *
 * Input: nums = [6,2,6,5,1,2]
 * Output: 9
 * Explanation: The optimal pairing is (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^4
 * nums.length == 2 * n
 * -10^4 <= nums[i] <= 10^4
 * @author sniper
 * @date 07 Jan, 2023
 * LC561, Easy
 */
public class ArrayPartition {


    /**
     * Time Cost 12ms
     * e.g.nums = [6,2,6,5,1,2]
     * nums:[1, 2, 2, 5, 6, 6]
     * expect: 1 + 2 + 6 = 9
     *
     * e.g.nums = [1,4,3,2]
     * nums:[1, 2, 3, 4]
     * expect:1 + 3 = 4
     * @param nums
     * @return
     */
    public int arrayPairSumV1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n;) {
            res += nums[i];
            i += 2;
        }
        return res;
    }

    /**
     * Time Cost 14ms
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int i = n - 1, j = i - 1; j >= 0;) {
            res += Math.min(nums[i], nums[j]);
            i -= 2;
            j -= 2;
        }
        return res;
    }
}
