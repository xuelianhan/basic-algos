package org.ict.algorithm.leetcode.sort;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * You may assume the input array always has a valid answer.
 *
 * Example 1:
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also accepted.
 *
 * Example 2:
 * Input: nums = [1,3,2,2,3,1]
 * Output: [2,3,1,3,1,2]
 *
 * Constraints:
 * 1 <= nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 5000
 * It is guaranteed that there will be an answer for the given input nums.
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 * @author sniper
 * @date 28 Mar, 2023
 * LC324, Medium
 */
public class WiggleSortII {

    public void wiggleSortV4(int[] nums) {
        //todo
    }

    public void wiggleSortV3(int[] nums) {
        //todo
    }

    public void wiggleSortV2(int[] nums) {
        //todo
    }

    public void wiggleSortV1(int[] nums) {
        //todo
    }

    /**
     * Sort the array first, and then do the adjustment.
     * The adjustment method is to find the middle of the array,
     * equivalent to the ordered array from the middle into two parts,
     * and then take one from the end of the first half,
     * and go one from the end of the second half,
     * to ensure that the first number is less than the second number,
     * and then take the penultimate number from the first half,
     * and the penultimate number from the second half,
     * which ensures that the second number is greater than the third number,
     * and the third number is less than the fourth number, and so on until all the numbers are taken.
     *
     * e.g. nums=[1,2,3,4,5]
     * mid:3, split array into two parts:[1,2,3], [4,5]
     * 1,4,2,5,3
     * Space Complexity O(N)
     * @author fun4LeetCode
     * @see <a href="https://leetcode.com/problems/wiggle-sort-ii/solutions/77684/summary-of-the-various-solutions-to-wiggle-sort-for-your-reference"></a>
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int m = (n + 1) >> 1;
        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);

        /**
         * Fill the front half
         */
        for (int i = m - 1, j = 0; i >= 0; i--, j += 2) {
            nums[j] = copy[i];
        }
        /**
         * Fill the back half
         */
        for (int i = n - 1, j = 1; i >= m; i--, j += 2) {
            nums[j] = copy[i];
        }
    }
}
