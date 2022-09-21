package org.ict.algorithm.leetcode.array;

/**
 * Given a non-empty array of integers nums,
 * every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 * Each element in the array appears twice except for one element which appears only once.
 * @author sniper
 * @date 21 Sep, 2022
 * LC136
 *
 */
public class SingleNumber {


    /**
     * Method XOR operation
     * Notice the condition:
     * Every element appears twice except for one.
     * Find that single one.
     *
     * @param nums
     * @return
     */
    public int singleNumberV4(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    public int singleNumberV3(int[] nums) {
        int res = 0;
        return res;
    }

    public int singleNumberV2(int[] nums) {
        int res = 0;
        return res;
    }

    public int singleNumberV1(int[] nums) {
        int res = 0;
        return res;
    }

}
