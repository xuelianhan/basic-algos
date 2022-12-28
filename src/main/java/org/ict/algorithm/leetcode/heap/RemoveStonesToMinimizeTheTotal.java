package org.ict.algorithm.leetcode.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given a 0-indexed integer array piles,
 * where piles[i] represents the number of stones in the ith pile, and an integer k.
 * You should apply the following operation exactly k times:
 *
 * Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
 * Notice that you can apply the operation on the same pile more than once.
 *
 * Return the minimum possible total number of stones remaining after applying the k operations.
 *
 * floor(x) is the greatest integer that is smaller than or equal to x (i.e., rounds x down).
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [5,4,9], k = 2
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [5,4,5].
 * - Apply the operation on pile 0. The resulting piles are [3,4,5].
 * The total number of stones in [3,4,5] is 12.
 * Example 2:
 *
 * Input: piles = [4,3,6,7], k = 3
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [4,3,3,7].
 * - Apply the operation on pile 3. The resulting piles are [4,3,3,4].
 * - Apply the operation on pile 0. The resulting piles are [2,3,3,4].
 * The total number of stones in [2,3,3,4] is 12.
 *
 *
 * Constraints:
 *
 * 1 <= piles.length <= 10^5
 * 1 <= piles[i] <= 10^4
 * 1 <= k <= 10^5
 * @author sniper
 * @date 28 Dec, 2022
 * LC1962
 */
public class RemoveStonesToMinimizeTheTotal {

    public static void main(String[] args) {
        RemoveStonesToMinimizeTheTotal instance = new RemoveStonesToMinimizeTheTotal();
        int[] piles = {4,3,6,7};
        int k = 3;
        int res = instance.minStoneSum(piles, k);
        System.out.println(res);
    }


    /**
     * Time Cost 522ms
     *
     * Use a max heap.
     * Each time pop the max value a,
     * remove a / 2 from the number of stones res
     * and push back the ceil half a - a / 2 to the heap.
     * Repeat this operation k times.
     *
     * Time Complexity O(N*logN)
     * Space Complexity O(N)
     *
     * @author lee215
     * @param piles
     * @param k
     * @return
     */
    public int minStoneSumV2(int[] piles, int k) {
        int n = piles.length;
        int sum = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((Comparator.reverseOrder()));
        for (int i = 0; i < n; i++) {
            maxHeap.offer(piles[i]);
            sum += piles[i];
        }

        while (k-- > 0 && !maxHeap.isEmpty()) {
            int top = maxHeap.poll();
            int d = top / 2;
            maxHeap.offer(top - d);
            sum -= d;
        }
        return sum;
    }

    /**
     * Time Cost 522ms
     * @param piles
     * @param k
     * @return
     */
    public int minStoneSumV1(int[] piles, int k) {
        int n = piles.length;
        int sum = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((Comparator.reverseOrder()));
        for (int i = 0; i < n; i++) {
            maxHeap.offer(piles[i]);
            sum += piles[i];
        }

        while (k > 0 && !maxHeap.isEmpty()) {
            int top = maxHeap.poll();
            int d = (int)(top / 2.0);
            sum -= d;
            maxHeap.offer(top - d);
            k--;
        }
        return sum;
    }

    /**
     * Time Cost 716ms
     * @param piles
     * @param k
     * @return
     */
    public int minStoneSum(int[] piles, int k) {
        int n = piles.length;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((Comparator.reverseOrder()));
        for (int i = 0; i < n; i++) {
            maxHeap.offer(piles[i]);
        }

        while (k > 0 && !maxHeap.isEmpty()) {
            int top = maxHeap.poll();
            int d = (int)(top / 2.0);
            maxHeap.offer(top - d);
            k--;
        }
        int res = 0;
        while (!maxHeap.isEmpty()) {
            res += maxHeap.poll();
        }
        return res;
    }

}
