package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * Given an integer array nums,
 * find three numbers whose product is maximum and return the maximum product.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: 6
 *
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: 24
 *
 * Example 3:
 * Input: nums = [-1,-2,-3]
 * Output: -6
 *
 *
 * Constraints:
 * 3 <= nums.length <= 104
 * -1000 <= nums[i] <= 1000
 * @author sniper
 * @date 11 Apr, 2023
 * LC628, Easy, frequency=7
 */
public class MaximumProductOfThreeNumbers {

    /**
     * e.g. nums = [-3, -2, 0, 1, 2], max = (-3)*(-2)*2
     * e.g. nums = [-1, 0, 1, 2], max = 0 * 1 * 2
     * Time Cost 13ms
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int n = nums.length;
        if (n == 3) {
            return nums[0] * nums[1] * nums[2];
        }
        Arrays.sort(nums);
        int t1 = nums[0] * nums[1] * nums[n - 1];
        int t2 = nums[n - 1] * nums[n - 2] * nums[n - 3];
        return Math.max(t1, t2);
    }
}
