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
 * LC198, Medium, frequency=6
 */
public class HouseRobber {


    /**
     * Understanding the following solution
     * Dynamic Programming
     * Time Complexity O(N)
     * Space Complexity O(1)
     *
     * e.g.nums = [2,7,9,3,1]
     * i:0, choose1:0, choose2:0, choose = max(0 + 2, 0) = 2
     *      choose2 = choose1 = 0, choose1 = choose = 2
     * i:1, choose1:2, choose2:0, choose = max(0 + 7, 2) = 7
     *      choose2 = choose1 = 2, choose1 = choose = 7
     * i:2, choose1:7, choose2:2, choose = max(9 + 2, 7) = 11
     *      choose2 = choose1 = 7, choose1 = choose = 11
     * i:3, choose1:11, choose2:7, choose = max(7 + 3, 11) = 11
     *      choose2 = choose1 = 11, choose1 = choose = 11
     * i:4, choose1:11, choose2:11, choose = max(11 + 1, 11) = 12
     *      choose2 = choose1 = 11, choose1 = choose = 12
     * return choose1:12
     * -----------------------------------------------------
     * e.g.nums = [3, 2, 1, 5]
     * i:0, choose1:0, choose2:0, choose = max(0 + 3, 0) = 3
     *      choose2 = choose1 = 0, choose1 = choose = 3
     * i:1, choose1:3, choose2:0, choose = max(0 + 2, 3) = 3
     *      choose2 = choose1 = 3, choose1 = choose = 3
     * i:2, choose1:3, choose2:3, choose = max(3 + 1, 3) = 4
     *      choose2 = choose1 = 3, choose1 = choose = 4
     * i:3, choose1:4, choose2:3, choose = max(3 + 5, 4) = 8
     *      choose2 = choose1 = 4, choose1 = choose = 8
     * return choose1:8
     *
     * @param nums
     * @return
     */
    public int robV3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int choose1 = 0;
        int choose2 = 0;
        for (int i = 0; i < n; i++) {
            int choose = Math.max(choose2 + nums[i], choose1);
            choose2 = choose1;
            choose1 = choose;
        }
        return choose1;
    }

    /**
     * Dynamic Programming
     * Time Complexity O(N)
     * Space Complexity O(N)
     * @param nums
     * @return
     */
    public int robV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int choose1 =  (i > 0 ? dp[i - 1] : 0);
            int choose2 = ((i > 1 ? dp[i - 2] : 0) + nums[i]);
            dp[i] = Math.max(choose1, choose2);
        }
        return dp[n - 1];
    }


    /**
     * Dynamic Programming
     *
     * Time Complexity O(N)
     * Space Complexity O(N)
     * dp[i] means the maximum robber result of range from [0, i]
     * @param nums
     * @return
     */
    public int robV1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int choose1 = 0;
            int choose2 = nums[i];
            if (i > 0) {
                choose1 = dp[i - 1];
                if (i > 1) {
                    choose2 += dp[i - 2];
                }
            }
            dp[i] = Math.max(choose1, choose2);
        }
        return dp[n - 1];
    }

    /**
     * Recursion + Memorization
     * For each given house i, we have two choices:
     * 1.Take the money if we didn't robber house i-1.
     * 2.Skip the current house i.
     * Time Complexity O(N)
     * Space Complexity O(N)
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
         * skip the current house i, choose last recursion result from house i-1.
         */
        int choose1 = rob(nums, i - 1, memo);
        /**
         * Take the money of house i and add moneys of recursion from house i-2.
         */
        int choose2 = rob(nums, i - 2, memo) + nums[i];

        memo[i] = Math.max(choose1, choose2);
        return memo[i];
    }
}
