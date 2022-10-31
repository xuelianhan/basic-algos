package org.ict.algorithm.leetcode.slidewindow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given an array of integers nums,
 * there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 * @author sniper
 * @date 30 Oct, 2022
 * LC239
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        maxSlidingWindow(nums, k);
    }

    /**
     * Understanding the following solution
     * Monotonic Queue Solution
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindowV1(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            /**
             * Remove index out of range k from the head of the queue
             */
            if (!queue.isEmpty() && queue.peek() < i - k + 1) {
                queue.poll();
            }

            /**
             * Remove smaller elements in range k from the tail of the queue
             */
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }

            queue.offer(i);
            /**
             * Notice the res array length is [n - k + 1], index i start from 0 to n-1.
             */
            if (i - k + 1 >= 0) {
                res[i - k + 1] = nums[queue.peek()];
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     *
     * Monotonic Queue Solution
     * Time Cost 33ms
     * e.g. k = 3
     * 0 1  2  3 4 5 6 7
     * 1 3 -1 -3 5 3 6 7
     *
     * i:0, queue:empty, push 0 into the queue, queue:0, i:0 not arrive at 2, so no need to store
     * i:1, queue:0,
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int j = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            /**
             * Remove index out of range k from the head of the queue
             */
            if (!queue.isEmpty() && queue.peek() < i - k + 1) {
                queue.poll();
            }

            /**
             * Remove smaller elements in range k from the tail of the queue
             */
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }

            queue.offer(i);
            /**
             * The head of the queue contains the maximum of the current window.
             * Notice the res array length is [n - k + 1]
             * both i and j start from 0, so the j index can be replaced with i - k + 1
             * if k == 3, the first element should be stored at the index of i == 2
             * 2 >= 3 - 1.
             * So we start from k - 1, and no need to consider the upper limit, because i < n assure the iteration in proper range.
             */
            if (i >= k - 1) {
                res[j] = nums[queue.peek()];
                j++;
            }
        }
        return res;
    }
}
