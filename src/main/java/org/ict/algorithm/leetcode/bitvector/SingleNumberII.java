package org.ict.algorithm.leetcode.bitvector;

import java.util.Arrays;

/**
 * Given an integer array nums where every element appears three times except for one,
 * which appears exactly once.
 * Find the single element and return it.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * Example 1:
 * Input: nums = [2,2,3,2]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * Each element in nums appears exactly three times except for one element which appears once.
 *
 * @author sniper
 * @date 26 Jun 2023
 * LC137, Medium, High Frequency, Top-150
 */
public class SingleNumberII {

    public static void main(String[] args) {
        SingleNumberII instance = new SingleNumberII();
        int[] nums = {2,2,3,2};
        instance.singleNumber(nums);
    }

    /**
     * class Solution:
     *     def singleNumber(self, nums: List[int]) -> int:
     *         ones, twos = 0, 0
     *         for num in nums:
     *             ones ^= (num & ~twos)
     *             twos ^= (num & ~ones)
     *         return ones
     *
     * @param nums
     * @return
     */
    public int singleNumberV3(int[] nums) {
        int ones = 0;
        int twos = 0;
        for (final int num : nums) {
            ones ^= (num & ~twos);
            twos ^= (num & ~ones);
        }
        return ones;
    }

    /**
     * Digital Circuit Magic
     * @param nums
     * @return
     */
    public int singleNumberV2(int[] nums) {
        int a = 0, b = 0;
        for (int c : nums) {
            int aa = (~a & b & c) | (a & ~b & ~c);
            int bb = ~a & (b ^ c);
            a = aa;
            b = bb;
        }
        return b;
    }

    public int singleNumberV1(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                sum += ((num >> i) & 1);
            }
            sum %= 3;
            res |= (sum << i);
        }
        return res;
    }

    /**
     * e.g.nums = [2,2,3,2]
     * 010
     * 010
     * 011
     * 010
     * -----------
     * 041
     * -----------
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int[] bitSum = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bitSum[i] += (num >> (31 - i)) & 1;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) + bitSum[i] % 3;
        }
        return res;
    }
}
