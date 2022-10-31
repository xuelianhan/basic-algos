package org.ict.algorithm.leetcode.slidewindow;

import java.util.ArrayDeque;
import java.util.Deque;

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
     * Cost 86 ms
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
                queue.pollFirst();
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
     * provided by <a href="https://leetcode.com/flyingpenguin/">flyingpenguin</a>, an awesome guy!
     * He has finished all the problems on leetcode. So crazy!
     *
     * Time Cost 33ms
     * Time Complexity: amortised O(N)
     * e.g. k = 3
     * 0 1  2  3 4 5 6 7
     * 1 3 -1 -3 5 3 6 7
     *
     * i:0, queue:empty, push 0 into the queue, queue:0, i:0 not arrive at 2, so no need to store
     * i:1, queue:0, peek:0, i-k+1=1-3+1=-1, peek > -1, nums[0] < nums[1], poll 0 from the queue, push 1 into the queue:1
     * i:2, queue:1, peek:1, i-k+1=2-3+1=0, peek > 0, nums[1] > nums[2], push 2 into the queue:1,2, 2 >= 3-1, res[0]=nums[1]=3
     * i:3, queue:1,2, peek:1, i-k+1=3-3+1=1, peek = 1, nums[2] > nums[3], push 3 into the queue:1,2,3, 3>=3-1, res[1]=nums[1]=3
     * i:4, queue:1,2,3, peek:1, i-k+1=4-3+1=2, peek < 2, poll 1 from the queue,
     *      queue:2,3, nums[3] < nums[4], pull 3 from the queue, queue:2
     *      queue:2, nums[2] < nums[4], pull 2 from the queue, queue:empty, push 4 into the queue, queue:4, 4 >= 3-1, res[2]=nums[4]=5
     * i:5, queue:4, peek:4, i-k+1=5-3+1=3, peek > 3, nums[4] > nums[5], push 5 into the queue, queue:4,5, 5 >= 3-1, res[3]=nums[4]=5
     * i:6, queue:4,5, peek:4, i-k+1=6-3+1=4, peek = 4, nums[5] < nums[6], pull 5 from the queue,
     *      queue:4, nums[4] < nums[6], pull 4 from the queue, queue: empty, push 6 into the queue:, queue:6, 6 >= 3-1, res[4]=nums[6]=6
     * i:7, queue:6, peek:6, i-k+1=7-3+1=5, peek > 5,
     *      queue:6, nums[6] < nums[7], pull 6 from the queue, queue:empty, push 7 into the queue, queue:7, 7 >= 3-1, res[5]=nums[7]=7
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
             * Remove index out of range k from the head of the queue.
             * Kick off the left index out of range, and it no longer participate the subsequent comparision.
             */
            if (!queue.isEmpty() && queue.peek() < i - k + 1) {
                // default pull the head element of the queue.
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
