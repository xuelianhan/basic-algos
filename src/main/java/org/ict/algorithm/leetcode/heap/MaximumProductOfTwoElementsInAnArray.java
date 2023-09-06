package org.ict.algorithm.leetcode.heap;

import java.util.PriorityQueue;

/**
 * Given the array of integers nums, you will choose two different indices i and j of that array.
 * Return the maximum value of (nums[i]-1)*(nums[j]-1).
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,2]
 * Output: 12
 * Explanation: If you choose the indices i=1 and j=2 (indexed from 0),
 * you will get the maximum value,
 * that is, (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12.
 * Example 2:
 *
 * Input: nums = [1,5,4,5]
 * Output: 16
 * Explanation: Choosing the indices i=1 and j=3 (indexed from 0),
 * you will get the maximum value of (5-1)*(5-1) = 16.
 * Example 3:
 *
 * Input: nums = [3,7]
 * Output: 12
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 500
 * 1 <= nums[i] <= 10^3
 * @author sniper
 * @date 05 Sep 2023
 * LC1464, Easy
 */
public class MaximumProductOfTwoElementsInAnArray {

    /**
     * Time Cost 0ms
     * @param nums
     * @return
     */
    public int maxProductV1(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = max1;
        for (int n : nums) {
            if (n > max1) {
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max2 = n;
            }
        }
        return (max1 - 1) * (max2 - 1);
    }

    /**
     * Time Cost 77ms
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int product = (nums[i] - 1) * (nums[j] - 1);
                minHeap.offer(product);
                if (minHeap.size() > 1) {
                    minHeap.poll();
                }
            }

        }
        return minHeap.peek();
    }
}
