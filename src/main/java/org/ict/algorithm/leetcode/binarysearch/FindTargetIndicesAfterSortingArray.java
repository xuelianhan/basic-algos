package org.ict.algorithm.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given a 0-indexed integer array nums and a target element target.
 *
 * A target index is an index i such that nums[i] == target.
 *
 * Return a list of the target indices of nums after sorting nums in non-decreasing order.
 * If there are no target indices, return an empty list.
 * The returned list must be sorted in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,5,2,3], target = 2
 * Output: [1,2]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The indices where nums[i] == 2 are 1 and 2.
 * Example 2:
 *
 * Input: nums = [1,2,5,2,3], target = 3
 * Output: [3]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The index where nums[i] == 3 is 3.
 * Example 3:
 *
 * Input: nums = [1,2,5,2,3], target = 5
 * Output: [4]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The index where nums[i] == 5 is 4.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i], target <= 100
 * @author sniper
 * @date 26 Nov, 2022
 * LC2089
 */
public class FindTargetIndicesAfterSortingArray {

    public static void main(String[] args) {
        int[] nums = {1,2,5,2,3,2};
        int target = 2;
        FindTargetIndicesAfterSortingArray instance = new FindTargetIndicesAfterSortingArray();
        List<Integer> result = instance.targetIndices(nums, target);
        System.out.println(result);
    }

    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> result = new ArrayList<>();
        int idx = binarySearch(nums, target);
        if (idx < 0) {
            return result;
        }
        for (int i = idx; i < nums.length; i++) {
            if (nums[i] == target) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Return the index of first element that equals the target.
     * int[] nums = {1,2,5,2,3,2};
     * int target = 2;
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int target) {
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
