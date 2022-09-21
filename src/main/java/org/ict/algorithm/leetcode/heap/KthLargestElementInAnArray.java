package org.ict.algorithm.leetcode.heap;

import java.util.Arrays;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * You must solve it in O(n) time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * @author sniper
 * @date 21 Sep, 2022
 * LC215
 */
public class KthLargestElementInAnArray {

    public int findKthLargestV2(int[] nums, int k) {
        return 0;
    }

    public int findKthLargestV1(int[] nums, int k) {
        return 0;
    }

    /**
     * A simple solution would be to use an efficient sorting algorithm
     * to sort the array in descending order and return the element at (k-1)'th index.
     *
     * The worst-case time complexity of this approach will be O(n.log(n)),
     * where n is the size of the input.
     * We can improve the time complexity using the Min-Heap or Max-Heap.
     * 
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
