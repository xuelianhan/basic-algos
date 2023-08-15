package org.ict.algorithm.leetcode.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k,
 * return true if nums has a good sub-array or false otherwise.
 *
 * A good sub-array is a sub-array where:
 *
 * its length is at least two,
 * and the sum of the elements of the sub-array is a multiple of k.
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
     * ----------------------------------
     * e.g. nums = [23,2,4,6,7], k = 6
     * prefixToIndex:{0,-1}
     * i:0, prefixSum:23, k != 0, prefixSum = 23 % 6 = 5
     * prefixToIndex does not contain key 5, put {5, 0} into prefixToIndex, prefixToIndex:{0,-1}, {5,0}
     * i:1, prefixSum = 5 + 2 = 7, k != 0, prefixSum = 7 % 5 = 2
     * prefixToIndex does not contain key 2, put {2,1} into prefixToIndex, prefixToIndex:{0,-1}, {5,0}, {2,1}
     * i:2, prefixSum = 2 + 4 = 6,  k != 0 prefixSum = 6 % 6 = 0
     * prefixToIndex contains key 0, lastIndex = prefixToIndex.get(0) = -1, i - lastIndex = 2 - (-1) = 3,
     * 3 > 1, return true
     * ----------------------------------
     * e.g. nums = [2, 4, 3], k = 6
     * prefixIndex:{0, -1}
     * i:0, prefixSum:2, k != 0, prefixSum = 2 % 6 = 2
     * prefixToIndex does not contain key 2, put {2, 0} into prefixToIndex, prefixToIndex:{0,-1},{2,0}
     * i:1, prefixSum = 2 + 4 = 6, k != 0, prefixSum = 6 % 6 = 0
     * prefixToIndex contains 0, lastIndex = prefixToIndex.get(0) = -1, i - lastIndex = 1 - (-1) = 2
     * 2 > 1, return true
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubArraySumV1(int[] nums, int k) {
        int prefixSum = 0;
        /**
         * Build the mapping relation of modular number and the index of the array.
         */
        Map<Integer, Integer> prefixToIndex = new HashMap<>();
        /**
         * e.g. nums = [2, 4, 3], k = 6
         * 0 <= nums[i] <= 10^9
         * so prefixSum is greater than or equals to zero.
         * At first, prefixSum:0 does not exist, so its index in the array is -1 here.
         * Notice it's -1 instead of 0, because 0 is a valid index in the array.
         */
        prefixToIndex.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (k != 0) {
                prefixSum %= k;
            }
            if (prefixToIndex.containsKey(prefixSum)) {
                // sub-array length is at least two,
                // and the sum of the elements of the sub-array is a multiple of k
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
