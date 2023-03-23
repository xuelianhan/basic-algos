package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 *
 * Follow up:
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 * @author sniper
 * @date 23 Mar, 2023
 * LC189, Medium
 */
public class RotateArray {

    /**
     * Flip the first n-k digits, then flip the next k digits, and finally flip the entire array.
     * e.g.
     * 1 2 3 4 5 6 7
     * 4 3 2 1 5 6 7
     * 4 3 2 1 7 6 5
     * 5 6 7 1 2 3 4
     * @see <a href="https://leetcode.com/problems/rotate-array/solutions/54250/easy-to-read-java-solution"></a>
     * @author danny6514
     * @param nums
     * @param k
     */
    public void rotateV2(int[] nums, int k) {
        /**
         * e.g.nums:[-1], k:2
         */
        int n = nums.length;
        /**
         * Very important, because k might be greater than n.
         */
        k = k % n;
        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, n);
        reverse(nums, 0, n);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void rotateV1(int[] nums, int k) {
        int n = nums.length;
        int[] copy = new int[n];
        copy = Arrays.copyOf(nums, n);
        for (int i = 0; i < n; i++) {
            int to = (i + k) % n;
            nums[to] = copy[i];
        }
    }

    /**
     * Move the i-th element to the index of (i + k) % n.
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] copy = new int[n];
        for (int i = 0; i < n; i++) {
            int to = (i + k) % n;
            copy[to] = nums[i];
        }
        for (int i = 0; i < n; i++) {
            nums[i] = copy[i];
        }
    }

}
