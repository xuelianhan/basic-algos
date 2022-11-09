package org.ict.algorithm.leetcode.heap;

import java.util.*;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
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
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * @author sniper
 * @date 21 Sep, 2022
 * LC347
 */
public class TopKFrequentElements {

    /**
     * Use Max-Heap can also solve problems like Top-K series, but it needs to add all the elements to the heap.
     * Conversely, Min-Heap will save more spaces, min-heap needs only at most k elements spaces.
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequentV3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums) {
            map.put(n, map.getOrDefault(n,0)+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        for (Map.Entry<Integer,Integer> entry: map.entrySet()) {
            maxHeap.add(entry);
        }

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }



    /**
     * A simplified version of topKFrequent
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequentV2(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }

        /**
         * 1. build hash map : character and how often it appears.
         *  O(N) time.
         */
        Map<Integer, Integer> freq = new HashMap();
        for (int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        /**
         * init minHeap 'the less frequent element first'.
         * create a MinPQ here.
         */
        Queue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(freq::get));
        //Queue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));
        
        /**
         *  2. keep k top frequent elements in the minHeap.
         *  O(N log k) < O(N log N) time.
         *  Notice here using count.keySet() other than the nums array itself.
         */
        for (int num: freq.keySet()) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                /**
                 * minHeap will poll the top element(smallest) out.
                 */
                minHeap.poll();
            }
        }

        /**
         *  3. build an output array and put the kth-biggest at the end position in the array.
         *   O(k log k) time.
         */
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = minHeap.poll();
        }
        return top;
    }

    public int[] topKFrequent(int[] nums, int k) {
        /**
         * Count frequency of each number in the array.
         */
        Map<Integer, Integer> frequency = new HashMap<>();
        for(int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        /**
         * Create a MinPQ with natural-order comparator.
         */
        PriorityQueue<Element> minHeap = new PriorityQueue<>(((o1, o2) -> {
            if (o1.cnt < o2.cnt) {
                return -1;
            } else if (o1.cnt > o2.cnt) {
                return 1;
            } else {
                return 0;
            }
        }));

        /**
         * Find the top-k-maximum via enqueue and dequeue.
         */
        frequency.forEach((num, cnt) -> {
            Element ele = new Element(num, cnt);
            minHeap.offer(ele);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        });

        /**
         * Dequeue and the output order is top-K-maximum.
         */
        int i = 0;
        int[] res = new int[k];
        while (!minHeap.isEmpty()) {
            res[i] = minHeap.poll().num;
            i++;
        }
        return res;
    }

    private static class Element {
        int num;

        int cnt;

        Element() {}

        Element(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
