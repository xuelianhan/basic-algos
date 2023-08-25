package org.ict.algorithm.leetcode.array;

/**
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 *
 * An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j].
 * An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].
 *
 * Given an integer array nums, return true if the given array is monotonic, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,3]
 * Output: true
 * Example 2:
 *
 * Input: nums = [6,5,4,4]
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,3,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * @author sniper
 * @date 25 Aug 2023
 * LC896, Easy
 */
public class MonotonicArray {


    /**
     * Time Cost 3ms
     * @param nums
     * @return
     */
    public boolean isMonotonicV1(int[] nums) {
        int inc = 1;
        int dec = 1;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] <= nums[i]) {
                inc += 1;
            }
            if (nums[i - 1] >= nums[i]) {
                dec += 1;
            }
        }
        return (inc == n || dec == n);
    }


    /**
     * Time Cost 2ms
     * @param nums
     * @return
     */
    public boolean isMonotonic(int[] nums) {
        boolean inc = true;
        boolean dec = true;
        for (int i = 1; i < nums.length; i++) {
            inc &= (nums[i - 1] <= nums[i]);
            dec &= (nums[i - 1] >= nums[i]);
            if (!inc && !dec) {
                return false;
            }
        }
        return true;
    }

}
