package org.ict.algorithm.leetcode.slidewindow;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
 * Example 1:
 * Input: [1,0,1,1,0]
 * Output: 4
 * Explanation: Flip the first zero will get the maximum number of consecutive 1s.
 * After flipping, the maximum number of consecutive 1s is 4.
 * Note:
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 *
 * Follow up:
 * What if the input numbers come in one by one as an infinite stream?
 * In other words, you can’t store all numbers coming from the stream as it’s too large to hold in memory.
 * Could you solve it efficiently?
 *
 * @author sniper
 * @date 21 Mar, 2023
 * LC487, Medium
 */
public class MaxConsecutiveOnesII {


    /**
     * Slide Window Solution for Follow up
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int left = 0;
        /**
         * You can flip at most k times of 0.
         * For this Question, k is fixed one.
         */
        int k = 1;
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
                left = queue.poll() + 1;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
