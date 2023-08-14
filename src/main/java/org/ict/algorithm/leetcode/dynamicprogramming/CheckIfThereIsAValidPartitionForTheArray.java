package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * You are given a 0-indexed integer array nums.
 * You have to partition the array into one or more contiguous sub-arrays.
 *
 * We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:
 *
 * The sub-array consists of exactly 2 equal elements. For example, the sub-array [2,2] is good.
 * The sub-array consists of exactly 3 equal elements. For example, the sub-array [4,4,4] is good.
 * The sub-array consists of exactly 3 consecutive increasing elements,
 * that is, the difference between adjacent elements is 1.
 * For example, the sub-array [3,4,5] is good, but the sub-array [1,3,5] is not.
 * Return true if the array has at least one valid partition. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,4,4,5,6]
 * Output: true
 * Explanation: The array can be partitioned into the sub-arrays [4,4] and [4,5,6].
 * This partition is valid, so we return true.
 * Example 2:
 *
 * Input: nums = [1,1,1,2]
 * Output: false
 * Explanation: There is no valid partition for this array.
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * @author sniper
 * @date 13 Aug 2023
 * LC2369, Medium
 */
public class CheckIfThereIsAValidPartitionForTheArray {

    public boolean validPartitionV1(int[] nums) {
        return false;
    }

    /**
     * Time Cost 5ms
     * ------------------------------------------------------
     * class Solution:
     *     def validPartition(self, nums: List[int]) -> bool:
     *         n = len(nums)
     *         # dp[i] := True if there's a valid partition for first i elements
     *         dp = [False] * (n + 1)
     *         dp[0] = True
     *         dp[2] = nums[1] == nums[0]
     *         for i in range(3, n + 1):
     *             dp[i] = (dp[i - 2] and nums[i - 2] == nums[i - 1]) or \
     *             (dp[i - 3] and ((nums[i - 3] == nums[i - 2] and nums[i - 2] == nums[i - 1]) or \
     *                             (nums[i - 3] + 1 == nums[i - 2] and nums[i - 2] + 1 == nums[i - 1])))
     *         return dp[n]
     *
     * @param nums
     * @return
     */
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        /**
         * dp[i] := true if there's a valid partition for first i elements
         */
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        dp[2] = (nums[0] == nums[1]);
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 2] && nums[i - 2] == nums[i - 1])
                    || (dp[i - 3] && (nums[i - 3] == nums[i - 2] && nums[i - 2] == nums[i - 1]))
                    || (dp[i - 3] && (nums[i - 3] + 1 == nums[i - 2] && nums[i - 2] + 1 == nums[i - 1]));
        }
        return dp[n];
    }
}
