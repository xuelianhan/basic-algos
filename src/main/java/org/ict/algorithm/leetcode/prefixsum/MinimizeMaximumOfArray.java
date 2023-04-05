package org.ict.algorithm.leetcode.prefixsum;

/**
 * You are given a 0-indexed array nums comprising n non-negative integers.
 *
 * In one operation, you must:
 *
 * Choose an integer i such that 1 <= i < n and nums[i] > 0.
 * Decrease nums[i] by 1.
 * Increase nums[i - 1] by 1.
 * Return the minimum possible value of the maximum integer of nums after performing any number of operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,7,1,6]
 * Output: 5
 * Explanation:
 * One set of optimal operations is as follows:
 * 1. Choose i = 1, and nums becomes [4,6,1,6].
 * 2. Choose i = 3, and nums becomes [4,6,2,5].
 * 3. Choose i = 1, and nums becomes [5,5,2,5].
 * The maximum integer of nums is 5. It can be shown that the maximum number cannot be less than 5.
 * Therefore, we return 5.
 * Example 2:
 *
 * Input: nums = [10,1]
 * Output: 10
 * Explanation:
 * It is optimal to leave nums as is, and since 10 is the maximum value, we return 10.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 2 <= n <= 10^5
 * 0 <= nums[i] <= 10^9
 * @author sniper
 * @date 05 Apr, 2023
 * LC2439, Medium
 */
public class MinimizeMaximumOfArray {

    public int minimizeArrayValueV3(int[] nums) {
        return 0;
    }

    /**
     * Time Cost 9ms
     * @param nums
     * @return
     */
    public int minimizeArrayValueV2(int[] nums) {
        long sum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            res = Math.max(res, ((sum + i) / (i + 1)));
        }
        return (int)res;
    }

    public int minimizeArrayValueV1(int[] nums) {
        return 0;
    }

    /**
     * Binary Search Solution
     * Time Cost 25ms
     * @param nums
     * @return
     */
    public int minimizeArrayValue(int[] nums) {
        long lo = 0;
        long hi = 1000_000_000;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2L;
            if (feasible(nums, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return (int)lo;
    }

    private boolean feasible(int[] nums, long target) {
        long carry = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            long x = nums[i];
            x += carry;
            carry = 0;
            if (x >= target) {
                carry = x - target;
            }
        }
        return carry == 0;
    }
}
