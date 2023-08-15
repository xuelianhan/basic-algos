package org.ict.algorithm.leetcode.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k,
 * return true if nums has a good sub-array or false otherwise.
 *
 * A good sub-array is a sub-array where:
 *
 * its length is at least two, and
 * the sum of the elements of the sub-array is a multiple of k.
 * Note that:
 *
 * A sub-array is a contiguous part of the array.
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 *
 * Example 1:
 *
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous sub-array of size 2 whose elements sum up to 6.
 * Example 2:
 *
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous sub-array of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 * Example 3:
 *
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 * @author sniper
 * @date 15 Aug 2023
 * LC523, Medium
 */
public class ContinuousSubArraySum {


    /**
     * Understanding the following solution
     * Time Cost 17ms
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubArraySumV1(int[] nums, int k) {
        int prefixSum = 0;
        Map<Integer, Integer> prefixToIndex = new HashMap<>();
        prefixToIndex.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (k != 0) {
                prefixSum %= k;
            }
            if (prefixToIndex.containsKey(prefixSum)) {
                if (i - prefixToIndex.get(prefixSum) > 1) {
                    return true;
                }
            } else {
                // Only add if absent, because the previous index is better
                prefixToIndex.put(prefixSum, i);
            }
        }
        return false;
    }

    /**
     * Brute-Force solution
     * Time Limit Exceeded.
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubArraySum(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    return true;
                }
                if (k != 0 && sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
