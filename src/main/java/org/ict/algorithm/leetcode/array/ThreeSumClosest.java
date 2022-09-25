package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * 
 * Given an integer array nums of length n and an integer target,
 * find three integers in nums such that the sum is closest to target.
 *
 * Return the sum of the three integers.
 *
 * You may assume that each input would have exactly one solution.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * Example 2:
 *
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -10^4 <= target <= 10^4
 *
 * LC16
 *
 */
public class ThreeSumClosest {

    public int threeSumClosestV2(int[] nums, int target) {
        return 0;
    }

	/**
	 * Similar to 3 Sum problem,
     * use 3 pointers to point the current element, the next element and the last element.
	 * If the sum is less than target,
     * it means that we have to add a larger element.
	 * If the sum is greater, it means we have to add a smaller element.
	 * Keep doing this until to the end.
     * Each time compare the difference between sum and target,
	 * if it is less than minimum difference so far, then replace result with it, 
	 * otherwise keep iterating.
	 */
	 public int threeSumClosest(int[] nums, int target) {
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            /**
             * Skip duplicated element.
             */
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return sum;
                }
                if (sum > target) {
                    k--;
                } else {
                    j++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
	}
}
