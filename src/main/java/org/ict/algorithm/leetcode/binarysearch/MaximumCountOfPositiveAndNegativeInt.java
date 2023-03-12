package org.ict.algorithm.leetcode.binarysearch;

/**
 * Given an array nums sorted in non-decreasing order, return the maximum between the number of positive integers and the number of negative integers.
 *
 * In other words, if the number of positive integers in nums is pos and the number of negative integers is neg, then return the maximum of pos and neg.
 * Note that 0 is neither positive nor negative.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,-1,-1,1,2,3]
 * Output: 3
 * Explanation: There are 3 positive integers and 3 negative integers. The maximum count among them is 3.
 * Example 2:
 *
 * Input: nums = [-3,-2,-1,0,0,1,2]
 * Output: 3
 * Explanation: There are 2 positive integers and 3 negative integers. The maximum count among them is 3.
 * Example 3:
 *
 * Input: nums = [5,20,66,1314]
 * Output: 4
 * Explanation: There are 4 positive integers and 0 negative integers. The maximum count among them is 4.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2000
 * -2000 <= nums[i] <= 2000
 * nums is sorted in a non-decreasing order.
 *
 *
 * Follow up: Can you solve the problem in O(log(n)) time complexity?
 * @author sniper
 * @date 12 Mar, 2023
 * LC2529, Easy
 */
public class MaximumCountOfPositiveAndNegativeInt {

    public int maximumCountV1(int[] nums) {
        int negativeIdx = binarySearch(nums, 0);
        int positiveIdx = binarySearch(nums, 1);
        return Math.max(negativeIdx, nums.length - positiveIdx);
    }

    public int maximumCount(int[] nums) {
        int n = nums.length;
        /**
         * Deal with the case that all numbers are the positive or negative.
         */
        if (nums[0] > 0 || nums[n - 1] < 0) {
            return n;
        }
        if (nums[0] == nums[n - 1]) {
            /**
             * Deal with the case that all numbers are the same.
             */
            return (nums[0] == 0 ? 0 : n);
        }
        int x = binarySearch(nums, 0);
        int y = x;
        while (nums[y] == 0) {
            y++;
        }
        if (x == y) {
            return Math.max(x, n - x);
        } else {
            return Math.max(x, n - y);
        }
    }

    private int binarySearch(int[] nums, int x) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= x) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
