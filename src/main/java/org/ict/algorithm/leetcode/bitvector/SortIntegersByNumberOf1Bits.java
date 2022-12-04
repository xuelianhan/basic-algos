package org.ict.algorithm.leetcode.bitvector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * You are given an integer array arr.
 * Sort the integers in the array in ascending order by the number of 1's
 * in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.
 *
 * Return the array after sorting it.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [0,1,2,3,4,5,6,7,8]
 * Output: [0,1,2,4,8,3,5,6,7]
 * Explantion: [0] is the only integer with 0 bits.
 * [1,2,4,8] all have 1 bit.
 * [3,5,6] have 2 bits.
 * [7] has 3 bits.
 * The sorted array by bits is [0,1,2,4,8,3,5,6,7]
 * Example 2:
 *
 * Input: arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * Output: [1,2,4,8,16,32,64,128,256,512,1024]
 * Explantion: All integers have 1 bit in the binary representation, you should just sort them in ascending order.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 104
 * @author sniper
 * @date 04 Dec, 2022
 * LC1356
 */
public class SortIntegersByNumberOf1Bits {

    public static void main(String[] args) {
        int[] arr = {10000,10000};
        SortIntegersByNumberOf1Bits instance = new SortIntegersByNumberOf1Bits();
        int[] res = instance.sortByBits(arr);
        System.out.println(Arrays.toString(res));
    }

    public int[] sortByBitsV3(int[] arr) {
        return null;
    }

    public int[] sortByBitsV2(int[] arr) {
        return null;
    }

    /**
     *
     * @param arr
     * @return
     */
    public int[] sortByBitsV1(int[] arr) {

        return null;
    }

    /**
     * Time Cost 36ms
     * @param arr
     * @return
     */
    public int[] sortByBits(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> bitMap = new HashMap<>();
        for (int i : arr) {
            bitMap.put(i,  Integer.bitCount(i));
            freq.put(i,  freq.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> {
            if (e1.getValue() < e2.getValue()) {
                return -1;
            } else if (e1.getValue() > e2.getValue()) {
                return 1;
            } else {
                return e1.getKey() < e2.getKey() ? -1 : 1;
            }
        });

        /**
         * Consider case: int[] arr = {10000,10000};
         */
        for (Map.Entry<Integer, Integer> entry : bitMap.entrySet()) {
            int i = 0;
            while (i < freq.get(entry.getKey())) {
                minHeap.offer(entry);
                i++;
            }
        }

        int[] res = new int[arr.length];
        int i = 0;
        while (!minHeap.isEmpty()) {
            res[i++] = minHeap.poll().getKey();
        }
        return res;
    }
}
