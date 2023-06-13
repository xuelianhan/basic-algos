package org.ict.algorithm.leetcode.slidewindow;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Given an array of integers nums and an integer limit,
 * return the size of the longest non-empty subarray
 * such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * Example 2:
 *
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * Example 3:
 *
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 *
 * @author sniper
 * @date 13 Jun 2023
 * LC1438, Medium, frequency=14
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    public static void main(String[] args) {
        int[] nums = {8,2,4,7};
        int limit = 4;
        LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit instance = new LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit();
        int res = instance.longestSubarrayV1(nums, limit);
        System.out.println(res);
    }

    /**
     * TreeMap Solution
     * Time Cost 111ms
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarrayV3(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int l = 0;
        int r = 0;
        for (; r < nums.length; r++) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            if (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }
                l++;
            }
        }
        return r - l;
    }

    /**
     * TreeMap Solution
     * Time Cost 89ms
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarrayV2(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int res = 0;
        for (int l = 0, r = 0; r < nums.length; r++) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    /**
     * Two-Deque Solution
     * Time Cost 35ms
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarrayV1(int[] nums, int limit) {
        Deque<Integer> maxQueue = new ArrayDeque<>();
        Deque<Integer> minQueue = new ArrayDeque<>();
        int i = 0;
        int j = 0;
        for (; j < nums.length; j++) {
            while (!maxQueue.isEmpty() && nums[j] > maxQueue.peekLast()) {
                maxQueue.pollLast();
            }
            while (!minQueue.isEmpty() && nums[j] < minQueue.peekLast()) {
                minQueue.pollLast();
            }
            maxQueue.add(nums[j]);//addLast
            minQueue.add(nums[j]);//addLast
            if (maxQueue.peek() - minQueue.peek() > limit) {
                if (maxQueue.peek() == nums[i]) {
                    maxQueue.poll();//pollFirst
                }
                if (minQueue.peek() == nums[i]) {
                    minQueue.poll();//pollFirst
                }
                i++;
            }
        }
        return j - i;
    }

    /**
     * Two-Heap Solution
     * Time Cost 58ms
     * Time Complexity O(NlogN)
     * Space Complexity O(N)
     * -----------------------------
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray(int[] nums, int limit) {
        int res = 1;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int l = 0, r = 0; r < nums.length; r++) {
            while (!minHeap.isEmpty() && minHeap.peek() > nums[r]) {
                minHeap.poll();
            }
            minHeap.offer(nums[r]);
            while (!maxHeap.isEmpty() && maxHeap.peek() < nums[r]) {
                maxHeap.poll();
            }
            maxHeap.offer(nums[r]);
            while (!maxHeap.isEmpty() && !minHeap.isEmpty() && (maxHeap.peek() - minHeap.peek()) > limit) {
                if (minHeap.peek() == nums[l]) {
                    minHeap.poll();
                }
                if (maxHeap.peek() == nums[l]) {
                    maxHeap.poll();
                }
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
