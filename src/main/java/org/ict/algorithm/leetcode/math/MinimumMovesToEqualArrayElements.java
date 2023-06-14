package org.ict.algorithm.leetcode.math;

import java.util.Arrays;

/**
 * Given an integer array nums of size n,
 * return the minimum number of moves required to make all array elements equal.
 * In one move, you can increment n - 1 elements of the array by 1.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: 3
 * Explanation: Only three moves are needed (remember each move increments two elements):
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 *
 * Example 2:
 * Input: nums = [1,1,1]
 * Output: 0
 *
 * Constraints:
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * The answer is guaranteed to fit in a 32-bit integer.
 * @author sniper
 * @date 14 Jun 2023
 * LC453, Medium, frequency=13
 */
public class MinimumMovesToEqualArrayElements {

    /**
     * Time Cost 9ms
     * @param nums
     * @return
     */
    public int minMovesV1(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int min = Arrays.stream(nums).min().getAsInt();
        return sum - min * nums.length;
    }

    /**
     * Understanding the following solution
     * Time Cost 2ms
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            min = Math.min(min, num);
        }
        return sum - min * nums.length;
    }
}
