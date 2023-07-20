package org.ict.algorithm.leetcode.prefixsum;

import java.util.Arrays;

/**
 * Given an array of integers nums, calculate the pivot index of this array.
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to
 * the sum of all the numbers strictly to the index's right.
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left.
 * This also applies to the right edge of the array.
 * Return the leftmost pivot index. If no such index exists, return -1.
 *
 * Example 1:
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The pivot index is 3.
 * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
 * Right sum = nums[4] + nums[5] = 5 + 6 = 11
 *
 * Example 2:
 * Input: nums = [1,2,3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 *
 * Example 3:
 * Input: nums = [2,1,-1]
 * Output: 0
 * Explanation:
 * The pivot index is 0.
 * Left sum = 0 (no elements to the left of index 0)
 * Right sum = nums[1] + nums[2] = 1 + -1 = 0
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 *
 * Note: This question is the same as 1991: https://leetcode.com/problems/find-the-middle-index-in-array/
 * @author sniper
 * @date 14 Jun 2023
 * LC724, Easy, frequency=13
 * Coupang
 */
public class FindPivotIndex {


    /**
     * Understanding the following solution
     * Time Cost 4ms
     * @param nums
     * @return
     */
    public int pivotIndexV2(int[] nums) {
        int n = nums.length;
        int prefix_sum = 0;
        int sum = Arrays.stream(nums).sum();
        for (int i = 0; i < n; i++) {
            if (prefix_sum == sum - prefix_sum - nums[i]) {
                return i;
            }
            prefix_sum += nums[i];
        }
        return -1;
    }

    public int pivotIndexV1(int[] nums) {
        int n = nums.length;
        int prefix_sum = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        for (int i = 0; i < n; i++) {
            if (prefix_sum == sum - prefix_sum - nums[i]) {
                return i;
            }
            prefix_sum += nums[i];
        }
        return -1;
    }


    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] prefix_sum = new int[n + 1];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            prefix_sum[i + 1] = prefix_sum[i] + nums[i];
            sum += nums[i];
        }
        for (int i = 0; i < n; i++) {
            if (prefix_sum[i] == sum - prefix_sum[i] - nums[i]) {
                return i;
            }
        }
        return -1;
    }
}
