package org.ict.algorithm.leetcode.binarysearch;

/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums is a non-decreasing array.
 * -10^9 <= target <= 10^9
 * @author sniper
 * @date 26 Nov, 2022
 * LC34
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * Time Cost 0ms
     * Understanding the following solution.
     * The Improvement of searchRange.
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRangeV1(int[] nums, int target) {
        int firstIdx = binarySearchFirst(nums, target);
        if (firstIdx < 0) {
            return new int[] {-1, -1};
        }
        int lastIdx = binarySearchLast(nums, target);
        return new int[]{firstIdx, lastIdx};
    }


    /**
     * Time Cost 303ms
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int idx = binarySearchFirst(nums, target);
        if (idx < 0) {
            return new int[] {-1, -1};
        }
        int[] res = new int[2];
        for (int i = idx; i < nums.length; i++) {
            if (nums[i] == target) {
                if (i == idx) {
                    res[0] = i;
                }
                res[1] = i;
            }
        }
        return res;
    }

    /**
     * Find the index of last element equals target, If no such element, return -1
     * @param nums
     * @param target
     * @return
     */
    public int binarySearchLast(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                /**
                 * Being Different with the standard Binary-Search without duplicated elements.
                 */
                if (mid == (n - 1) || nums[mid + 1] != target) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * Find the index of first element equals target, If no such element, return -1
     * @param nums
     * @param target
     * @return
     */
    public int binarySearchFirst(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                /**
                 * Being Different with the standard Binary-Search without duplicated elements.
                 */
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
