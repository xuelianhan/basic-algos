package org.ict.algorithm.leetcode.heap;

import java.util.PriorityQueue;

/**
 * @author sniper
 * @date 25 Nov, 2022
 */
public class KthSmallestElementInAnArray {

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        int result = findKthSmallest(nums, k) ;
        System.out.println(result);
    }


    /**
     * Use a Max-Heap of priority queue, keep queue size to k.
     * The root of heap is the maximum of the k numbers.
     * It's also the k'th-smallest element in the heap.
     *
     * Input: nums = [3,2,1,5,6,4], k = 2
     * i:0, maxHeap: 3
     * i:1, maxHeap: 3, 2
     * i:2, maxHeap: 3, 2, 1, size > 2, poll 3 from the heap, maxHeap: 2, 1
     * i:3, maxHeap: 5, 1, 2, size > 2, poll 5 from the heap, maxHeap: 2, 1
     * i:4, maxHeap: 6, 1, 2, size > 2, poll 6 from the heap, maxHeap: 2, 1
     * i:5, maxHeap: 4, 1, 2, size > 2, poll 4 from the heap, maxHeap: 2, 1
     * for-loop-end
     * peek top of the heap: 2
     *
     * Time Complexity O(N.log(k)).
     * @param nums
     * @param k
     * @return
     */
    public static int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> {
            if (o1 < o2) {
                return 1;
            } else if (o1 > o2) {
                return -1;
            } else {
                return 0;
            }
        }));
        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }
}
