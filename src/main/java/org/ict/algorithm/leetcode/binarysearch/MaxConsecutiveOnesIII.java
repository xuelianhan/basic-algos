package org.ict.algorithm.leetcode.binarysearch;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * Example 2:
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 * @author sniper
 * @date 20 Mar, 2023
 * LC1004, Medium
 */
public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        int[] nums = {0,0,0,1};
        int k = 4;
        MaxConsecutiveOnesIII instance = new MaxConsecutiveOnesIII();
        int res = instance.longestOnes(nums, k);
        System.out.println(res);
    }


    /**
     * e.g. nums = [0,0,0,1], k = 4, expected 4
     * right:0, nums[0]:0, queue:0, queue.size < 4, left:0, res=right-left+1=0-0+1=1
     * right:1, nums[1]:0, queue:0,1, queue.size < 4, left:0, res=right-left+1=1-0+1=2
     * right:2, nums[2]:0, queue:0,1,2, queue.size < 4, left:0, res=right-left+1=2-0+1=3
     * right:3, nums[3]:1, queue:0,1,2, queue.size < 4, left:0, res=right-left+1=3-0+1=4
     * return res:4
     *
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int res = 0;
        int left = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                queue.offer(right);
            }
            /**
             * Two important points:
             * 1.left = queue.poll() + 1, not queue.poll()
             * 2.res = Math.max(res, right - left + 1), not Math.max(res, right - left);
             * e.g. nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2, expected:6
             */
            if (queue.size() > k) {
                left = queue.poll();
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
