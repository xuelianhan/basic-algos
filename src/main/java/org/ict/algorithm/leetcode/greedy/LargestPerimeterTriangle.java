package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * Given an integer array nums, return the largest perimeter of a triangle with a non-zero area,
 * formed from three of these lengths.
 * If it is impossible to form any triangle of a non-zero area, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,1,2]
 * Output: 5
 * Example 2:
 *
 * Input: nums = [1,2,1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^6
 * @author sniper
 * @date 12 Oct, 2022
 * LC976
 */
public class LargestPerimeterTriangle {

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 5, 10};
        int res = largestPerimeter(nums);
        System.out.println(res);
    }

    public int largestPerimeterV1(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            if ((nums[i-1] + nums[i-2]) > nums[i]) {
                return (nums[i] + nums[i-1] + nums[i-2]);
            }
        }
        return 0;
    }

    public static int largestPerimeter(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int sum = nums[i-1] + nums[i-2];
            if (sum > nums[i]) {
                res = (nums[i] + nums[i-1] + nums[i-2]);
                return res;
            }
        }
        return res;
    }
}
