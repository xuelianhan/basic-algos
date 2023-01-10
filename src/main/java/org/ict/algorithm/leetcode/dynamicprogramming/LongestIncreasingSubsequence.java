package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.Arrays;

/**
 * Given an integer array nums, return the length of the longest strictly increasing-subsequence
 * .
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 *
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 * @author sniper
 * @date 09 Jan, 2023
 * LC300, Medium
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLISV5(int[] nums) {
        return 0;
    }

    public int lengthOfLISV4(int[] nums) {
        return 0;
    }

    public int lengthOfLISV3(int[] nums) {
        return 0;
    }

    public int lengthOfLISV2(int[] nums) {
        return 0;
    }

    public int lengthOfLISV1(int[] nums) {
        //todo
        return 0;
    }

    /**
     * Brute-Forth Dynamic Programming Solution.
     * Time Cost 74ms
     * Time Complexity O(N^2)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        /**
         * dp[i] means length of LIS end with nums[i]
         */
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 0;
        /**
         * e.g. nums = [0], expect output 1
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
