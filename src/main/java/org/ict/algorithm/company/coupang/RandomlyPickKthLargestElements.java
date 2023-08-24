package org.ict.algorithm.company.coupang;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author sniper
 * @date 17 Aug 2023
 */
public class RandomlyPickKthLargestElements {

    /**
     * This code first creates a max-heap of size k.
     * This ensures that the largest k-th elements in the array are always in the max-heap.
     * The code then iterates over the remaining elements in the array,
     * and adds them to the max-heap with probability (k - 1) / (n - i).
     * This means that the probability of adding an element to the max-heap decreases as we move further down the array.
     *
     * The code finally returns the elements in the max-heap.
     * @param arr
     * @param k
     * @return
     */
    public List<Integer> getKthLargestElementsRandomly(int[] arr, int k) {
        // Create a max-heap of size k.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Add the first k elements to the max-heap.
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }

        // Iterate over the remaining elements and add them to the max-heap with probability (k - 1) / (n - i).
        for (int i = k; i < arr.length; i++) {
            double probability = (k - 1) / (arr.length - i);
            if (Math.random() < probability) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }

        // Return the elements in the max-heap.
        List<Integer> result = new ArrayList<>(maxHeap);
        return result;
    }
}
