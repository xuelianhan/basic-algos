package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected,
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 *
 * Input: nums = [1,2,3]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 * @author sniper
 * @date 28 Aug 2023
 * LC213, Medium
 */
public class HouseRobberII {

    /**
     * Now the houses are arranged in a circle.
     * If we grab the first, we can not grab the last one, because the first and last connected.
     * So we can only grap one of the first and last, or are not robbed.
     * If one of the first and last are removed, we calculated the maximum value of them,
     * and then compare the two values, the larger one is wanted.
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int removeLastRes = rob(nums, 0, n - 2);
        int removeFirstRes = rob(nums, 1, n - 1);
        return Math.max(removeLastRes, removeFirstRes);
    }

    private int rob(int[] nums, int left, int right) {
        int choose1 = 0;
        int choose2 = 0;
        for (int i = left; i <= right; i++) {
            int choose = Math.max(choose2 + nums[i], choose1);
            choose2 = choose1;
            choose1 = choose;
        }
        return choose1;
    }

}
