package org.ict.algorithm.leetcode.binarysearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer which appears two or more times.
 *
 *
 * Follow up:
 *
 * How can we prove that at least one duplicate number must exist in nums?
 * Can you solve the problem in linear runtime complexity?
 * @author sniper
 * @date 26 Nov, 2022
 * LC287
 */
public class FindTheDuplicateNumber {

    public int findDuplicateV4(int[] nums) {
        return 0;
    }


    public int findDuplicateV3(int[] nums) {
        return 0;
    }



    public int findDuplicateV2(int[] nums) {
        return 0;
    }

    /**
     * Time Cost 84 ms
     * Not Satisfy the constraint of using only constant extra space.
     * @param nums
     * @return
     */
    public int findDuplicateV1(int[] nums) {
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            if (freq.getOrDefault(nums[i], 0) > 1) {
                res = nums[i];
                break;
            }
        }
        return res;
    }


    /**
     * Time Cost 32ms
     * Not Satisfy the constraint of using only constant extra space.
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int max = 0, sum = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            if (freq.getOrDefault(nums[i], 0) > 1) {
                return nums[i];
            }
            sum += nums[i];
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        for (int i = 1; i <= max; i++) {
            sum -= i;
        }
        return sum;
    }
}
