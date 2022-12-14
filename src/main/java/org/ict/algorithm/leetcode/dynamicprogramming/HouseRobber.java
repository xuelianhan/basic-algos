package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that
 * adjacent houses have security systems connected,
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * @author sniper
 * @date 14 Dec, 2022
 * LC198
 */
public class HouseRobber {


    public int robV3(int[] nums) {
        return 0;
    }

    public int robV2(int[] nums) {
        return 0;
    }


    public int robV1(int[] nums) {
        return 0;
    }

    /**
     * Recursion + Memorization
     * For each given house i, we have two choices:
     * 1.Take the money if we didn't robber house i-1.
     * 2.Skip the current house i.
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return rob(nums, n - 1, memo);
    }

    private int rob(int[] nums, int i, int[] memo) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] >= 0) {
            return memo[i];
        }
        /**
         * Take the money of house i and add moneys of recursion from house i-2.
         */
        int choose1 = rob(nums, i - 2, memo) + nums[i];
        /**
         * skip the current house i, choose last recursion result from house i-1.
         */
        int choose2 = rob(nums, i - 1, memo);
        memo[i] = Math.max(choose1, choose2);
        return memo[i];
    }
}
