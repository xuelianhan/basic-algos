package org.ict.algorithm.leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an integer array nums and an integer k, return the k-th-largest element in the array.
 *
 * Note that it is the k-th-largest element in the sorted order, not the kth distinct element.
 *
 * You must solve it in O(n) time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * @author sniper
 * @date 21 Sep, 2022
 * LC215, Medium, frequency=154
 */
public class KthLargestElementInAnArray {

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;

        KthLargestElementInAnArray instance = new KthLargestElementInAnArray();
        int result = instance.findKthLargestV1(nums, k) ;
        System.out.println(result);
    }



    /**
     * Note that it is the kth largest element in the sorted order, not the kth distinct element.
     * So we cannot use TreeSet to solve this problem, Because TreeSet will keep all elements distinct.
     *
     * We can easily solve this problem in O(n + k.log(n)) by using a max-heap.
     * The idea is to simply construct a max-heap of size n and insert all the array elements [0…n-1] into it.
     * Then pop first k-1 elements from it.
     * Now k'th largest element will reside at the root of the max-heap.
     *
     * The real running time is 118ms
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestV3(int[] nums, int k) {
        /**
         * Build a Max-Heap with PriorityQueue.
         * Notice the comparator defined is reversed with default natural order PriorityQueue.
         */
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> {
            if (o1 < o2) {
                return 1;
            } else if (o1 > o2) {
                return -1;
            } else {
                return 0;
            }
        }));

        /**
         * Add all elements into the heap.
         */
        for (int num : nums) {
            maxHeap.offer(num);
        }

        int i = 0;
        int res = 0;
        while (!maxHeap.isEmpty()) {
            res = maxHeap.poll();
            if (i == k - 1) {
                return res;
            }
            i++;
        }

        return res;
    }

    /**
     * We can easily solve this problem in O(n.log(k)) by using a min-heap.
     * The idea is to construct a min-heap of size k and insert the first k elements of array A[0…k-1] into the min-heap.
     *
     * Then for each of the remaining array elements A[k…n-1],
     * if that element is more than the min-heap’s root,
     * replace the root with the current element.
     * Repeat this process until the array is exhausted.
     * Now we will be left with top-k largest array elements in the min-heap,
     * and k'th largest element will reside at the root of the min-heap.
     *
     * Time Complexity O(N.log(k)).
     *
     * Input: nums = [3,2,1,5,6,4], k = 2
     * queue: 1 2 3
     * queue root: 1, nums[3]=5, 5 > root, so dequeue 1, enqueue 5.
     * queue: 2 3 5
     *
     * queue root:2, nums[4]=6, 6 > root, so dequeue 2, enqueue 6.
     * queue: 3 5 6
     *
     * queue root:3, nums[5]=4, 4 > root, so dequeue 3, enqueue 4.
     * queue: 4 5 6
     *
     * queue root:4, 4 is the 3-th largest number in the heap.
     *
     * The real running time is 65ms
     *
     * @param nums
     * @param k
     * @return
     */

    public int findKthLargestV2(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            int root = minHeap.peek();
            if (nums[i] > root) {
                /**
                 * dequeue root and add the current element, heap will heapify automatically.
                 */
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }

    /**
     * Understanding the following solution
     *
     * Use a Min-Heap of priority queue, keep queue size to k.
     * The root of heap is the minimum of the k numbers.
     * It's also the k-th-largest element in the heap.
     *
     * Input: nums = [3,2,1,5,6,4], k = 2
     * i:0, minHeap: 3
     * i:1, minHeap: 2, 3
     * i:2, minHeap: 1, 3, 2, size > 2, poll 1 from the heap, minHeap: 2, 3
     * i:3, minHeap: 2, 3, 5, size > 2, poll 2 from the heap, minHeap: 3, 5
     * i:4, minHeap: 3, 5, 6, size > 2, poll 3 from the heap, minHeap: 5, 6
     * i:5, minHeap: 4, 6, 5, size > 2, poll 4 from the heap, minHeap: 5, 6
     * for-loop-end
     * peek top of the heap: 5
     *
     * Time Complexity O(N.log(k)).
     *
     * The real running time is 152ms
     * -----------------------------------------------
     * class Solution {
     * public:
     *     int findKthLargest(vector<int>& nums, int k) {
     *         priority_queue<int> max_heap;
     *         for (int i = 0; i < nums.size(); i++) {
     *             max_heap.push(-nums[i]);
     *             if (max_heap.size() > k) {
     *                 max_heap.pop();
     *             }
     *         }
     *         return -max_heap.top();
     *     }
     * };
     * -------------------------------------------
     * class Solution:
     *     def findKthLargest(self, nums: List[int], k: int) -> int:
     *         minHeap = []
     *         for num in nums:
     *             heapq.heappush(minHeap, num)
     *             if len(minHeap) > k:
     *                 heapq.heappop(minHeap)
     *         return minHeap[0]
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestV1(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    /**
     * A simple solution would be to use an efficient sorting algorithm
     * to sort the array in descending order and return the element at (k-1)'th index.
     *
     * The worst-case time complexity of this approach will be O(n.log(n)),
     * where n is the size of the input.
     * We can improve the time complexity using the Min-Heap or Max-Heap.
     *
     * The real running time of this method is the fastest.
     * It costs 24ms
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
