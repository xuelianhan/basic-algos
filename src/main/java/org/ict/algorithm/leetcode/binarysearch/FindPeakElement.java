package org.ict.algorithm.leetcode.binarysearch;


/**
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given a 0-indexed integer array nums, find a peak element, and return its index.
 * If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 * In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums[i] != nums[i + 1] for all valid i.
 * @author sniper
 * @date 16 Jan, 2023
 * LC162, Medium
 */
public class FindPeakElement {

    public static void main(String[] args) {
        FindPeakElement instance = new FindPeakElement();
        int[] nums = {1, 2};
        int result = instance.findPeakElement(nums);
        System.out.println(result);
    }


    /**
     * Understanding the following solution.
     * Time Complexity O(logN)
     * Space Complexity O(1)
     * @param nums
     * @return
     */
    public int findPeakElementV2(int[] nums) {
       int low = 0;
       int high = nums.length - 1;
       while (low < high) {
           int mid = low + (high - low) / 2;
           if (nums[mid] < nums[mid + 1]) {
               low = mid + 1;
           } else {
               high = mid;
           }
       }
       return high;
    }

    /**
     *
     * Time Complexity O(N)
     * Space Complexity O(1)
     * @param nums
     * @return
     */
    public int findPeakElementV1(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                return i - 1;
            }
        }
        return nums.length - 1;
    }

    /**
     * Not recommend.
     * Time Complexity O(N)
     * Space Complexity O(N)
     * e.g. nums = [1, 2], expected 1
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int n = nums.length;
        int[] copy = new int[n + 2];
        for (int i = 1; i < copy.length - 1; i++) {
            copy[i] = nums[i - 1];
        }
        copy[0] = Integer.MIN_VALUE;
        copy[n + 1] = Integer.MIN_VALUE;
        for (int i = 1; i < copy.length -1; i++) {
            if (copy[i] > copy[i - 1] && copy[i] > copy[i + 1]) {
                return i - 1;
            }
        }
        return -1;
    }

}
