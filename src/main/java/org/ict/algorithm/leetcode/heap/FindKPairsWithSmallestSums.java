package org.ict.algorithm.leetcode.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 * Example 1:
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation:
 * The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 * Example 2:
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation:
 * The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 * Example 3:
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [[1,3],[2,3]]
 * Explanation:
 * All possible pairs are returned from the sequence: [1,3],[2,3]
 *
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 10^5
 * -10^9 <= nums1[i], nums2[i] <= 10^9
 * nums1 and nums2 both are sorted in ascending order.
 * 1 <= k <= 10^4
 *
 * @author sniper
 * @date 24 Jun 2023
 * LC373, Medium, High Frequency, Top-150
 * Amazon, Google, Microsoft
 */
public class FindKPairsWithSmallestSums {

    /**
     * Understanding the following solution
     * Time Cost 38ms
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairsV2(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        List<List<Integer>> res = new ArrayList<>();

        PriorityQueue<SumPair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.sum));
        minHeap.offer(new SumPair(0, 0, nums1[0] + nums2[0]));
        while (!minHeap.isEmpty() && k > 0) {
            SumPair pair = minHeap.poll();
            int i = pair.i;
            int j = pair.j;
            res.add(Arrays.asList(nums1[i], nums2[j]));
            k -= 1;

            if (i == j) {
                if ((i + 1) < n) {
                    minHeap.offer(new SumPair(i + 1, j, nums1[i + 1] + nums2[j]));
                }
                if ((j + 1) < m) {
                    minHeap.offer(new SumPair(i, j + 1, nums1[i] + nums2[j + 1]));
                }
                if ((i + 1) < n && (j + 1) < m) {
                    minHeap.offer(new SumPair(i + 1, j + 1, nums1[i + 1] + nums2[j + 1]));
                }
            } else if (i > j) {
                if ((i + 1) < n) {
                    minHeap.offer(new SumPair(i + 1, j, nums1[i + 1] + nums2[j]));
                }
            } else {
                if ((j + 1) < m) {
                    minHeap.offer(new SumPair(i, j + 1, nums1[i] + nums2[j + 1]));
                }
            }
        }

        return res;
    }

    /**
     * Understanding the following solution
     * Time Cost 40ms
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairsV1(int[] nums1, int[] nums2, int k) {
        PriorityQueue<SumPair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.sum));
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new SumPair(i, 0, nums1[i] + nums2[0]));
        }

        List<List<Integer>> res = new ArrayList<>();
        while (!minHeap.isEmpty() && res.size() < k) {
            SumPair pair = minHeap.poll();
            int i = pair.i;
            int j = pair.j;
            res.add(Arrays.asList(nums1[i], nums2[j]));
            if (j + 1 < nums2.length) {
                minHeap.offer(new SumPair(i, j + 1, nums1[i] + nums2[j + 1]));
            }
        }

        return res;
    }

    /**
     * Understanding the following solution
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<SumPair> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.sum));
        for (int i = 0; i < k && i < nums1.length; i++) {
            minHeap.offer(new SumPair(i, 0, nums1[i] + nums2[0]));
        }

        List<List<Integer>> res = new ArrayList<>();
        while (!minHeap.isEmpty() && res.size() < k) {
            int i = minHeap.peek().i;
            int j = minHeap.poll().j;
            res.add(Arrays.asList(nums1[i], nums2[j]));
            if (j + 1 < nums2.length) {
                minHeap.offer(new SumPair(i, j + 1, nums1[i] + nums2[j + 1]));
            }
        }

        return res;
    }

    private static class SumPair {
        private int i;
        private int j;
        private int sum;
        public SumPair(int i, int j, int sum) {
            this.i = i;
            this.j = j;
            this.sum = sum;
        }
    }

    /**
     * Time Limit Exceeded
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairsOutOfTime(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] + b[1] - a[0] - a[1]);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                maxHeap.offer(new int[] {nums1[i], nums2[j]});
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int[] item : maxHeap) {
            res.add(Arrays.stream(item).boxed().collect(Collectors.toList()));
        }
        return res;
    }
}
