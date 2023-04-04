package org.ict.algorithm.leetcode.slidewindow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an integer array nums and an integer k,
 * return the length of the shortest non-empty subarray of nums with a sum of at least k.
 * If there is no such subarray, return -1.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1], k = 1
 * Output: 1
 * Example 2:
 *
 * Input: nums = [1,2], k = 4
 * Output: -1
 * Example 3:
 *
 * Input: nums = [2,-1,2], k = 3
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * 1 <= k <= 10^9
 * @author sniper
 * @date 04 Apr, 2023
 * LC862, Hard
 * @see org.ict.algorithm.leetcode.slidewindow.MinimumSizeSubarraySum(209)
 */
public class ShortestSubarrayWithSumAtLeastK {


    /**
     * Understanding the following solution
     * todo
     * analysis here
     * todo
     * What makes this problem hard is that we have negative values.
     * @author lee215
     * @see <a href="https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/solutions/143726/c-java-python-o-n-using-deque"></a>
     * @param nums
     * @param k
     * @return
     */
    public int shortestSubarrayV2(int[] nums, int k) {
        int n = nums.length;
        int res = n + 1;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n + 1; i++) {
            while (dq.size() > 0 && (prefix[i] - prefix[dq.getFirst()]) >= k) {
                res = Math.min(res, i - dq.pollFirst());
            }
            while (dq.size() > 0 && prefix[i] <= prefix[dq.getLast()]) {
                dq.pollLast();
            }
            dq.addLast(i);
        }
        return res <= n ? res : -1;
    }

    public int shortestSubarrayV1(int[] nums, int k) {
        int res = 0;
        //todo
        return res;
    }

    public int shortestSubarray(int[] nums, int k) {
        int res = 0;
        //todo
        return res;
    }
}
