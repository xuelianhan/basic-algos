package org.ict.algorithm.leetcode.binarysearch;

import java.util.Arrays;

/**
 * Given an integer array nums,
 * return the number of triplets chosen from the array that can make triangles
 * if we take them as side lengths of a triangle.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,3,4]
 * Output: 3
 * Explanation: Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Example 2:
 *
 * Input: nums = [4,2,3,4]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * @author sniper
 * @date 24 Aug 2023
 * LC611, Medium
 */
public class ValidTriangleNumber {


    /**
     * e.g. nums = [4,2,3,4]
     * Sorted: 0   1   2   3
     *         2   3   4   4
     *         ^       ^   ^
     *         |       |   |
     *        left  right  i
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        /**
         * Sorting the array before search
         */
        Arrays.sort(nums);
        int res = 0;
        /**
         * Fixed i to n - 1, let right start from i-1, left start from 0
         * so the lower bound of i is no longer 0, because left and right are both
         * before i, so the lower bound is 2.
         * We scan the sorted array in reverse order from back to front.
         */
        for (int i = nums.length - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    res += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }
}
