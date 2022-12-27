package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 *
 * You have n bags numbered from 0 to n - 1.
 * You are given two 0-indexed integer arrays capacity and rocks.
 * The ith bag can hold a maximum of capacity[i] rocks and currently contains rocks[i] rocks.
 * You are also given an integer additionalRocks, the number of additional rocks you can place in any of the bags.
 *
 * Return the maximum number of bags that could have full capacity after placing the additional rocks in some bags.
 *
 *
 *
 * Example 1:
 * Input: capacity = [2,3,4,5], rocks = [1,2,4,4], additionalRocks = 2
 * Output: 3
 * Explanation:
 * Place 1 rock in bag 0 and 1 rock in bag 1.
 * The number of rocks in each bag are now [2,3,4,4].
 * Bags 0, 1, and 2 have full capacity.
 * There are 3 bags at full capacity, so we return 3.
 * It can be shown that it is not possible to have more than 3 bags at full capacity.
 * Note that there may be other ways of placing the rocks that result in an answer of 3.
 *
 * Example 2:
 * Input: capacity = [10,2,2], rocks = [2,2,0], additionalRocks = 100
 * Output: 3
 * Explanation:
 * Place 8 rocks in bag 0 and 2 rocks in bag 2.
 * The number of rocks in each bag are now [10,2,2].
 * Bags 0, 1, and 2 have full capacity.
 * There are 3 bags at full capacity, so we return 3.
 * It can be shown that it is not possible to have more than 3 bags at full capacity.
 * Note that we did not use all of the additional rocks.
 *
 *
 * Constraints:
 *
 * n == capacity.length == rocks.length
 * 1 <= n <= 5 * 10^4
 * 1 <= capacity[i] <= 10^9
 * 0 <= rocks[i] <= capacity[i]
 * 1 <= additionalRocks <= 10^9
 * @author sniper
 * @date 27 Dec, 2022
 * LC2279
 */
public class MaximumBagsWithFullCapacityOfRocks {

    public static void main(String[] args) {
        MaximumBagsWithFullCapacityOfRocks instance = new MaximumBagsWithFullCapacityOfRocks();
        /**
         * expected 3
         */
        /*int[] capacity = {2,3,4,5};
        int[] rocks = {1,2,4,4};
        int additionalRocks = 2;*/

        /**
         * expected 3
         */
        /*int[] capacity = {10, 2, 2};
        int[] rocks = {2, 2, 0};
        int additionalRocks = 100;*/

        /**
         * expected 1, real output 7
         */
        /*int[] capacity = {91,54,63,99,24,45,78};
        int[] rocks = {35,32,45,98,6,1,25};
        int additionalRocks = 17;*/


        /**
         * expected 13, real output 3
         */
        int[] capacity = {54,18,91,49,51,45,58,54,47,91,90,20,85,20,90,49,10,84,59,29,40,9,100,1,64,71,30,46,91};
        int[] rocks = {14,13,16,44,8,20,51,15,46,76,51,20,77,13,14,35,6,34,34,13,3,8,1,1,61,5,2,15,18};
        int additionalRocks = 77;

        int res = instance.maximumBags(capacity, rocks, additionalRocks);
        System.out.println(res);
    }

    /**
     * Time Cost 15ms
     * @param capacity
     * @param rocks
     * @param additionalRocks
     * @return
     */
    public int maximumBagsV2(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(diff);

        int res = 0;
        for (int i = 0; i < n && (diff[i] - additionalRocks) <= 0; i++) {
            res++;
            additionalRocks -= diff[i];
        }
        return res;
    }


    /**
     * Time Cost 15ms
     * @param capacity
     * @param rocks
     * @param additionalRocks
     * @return
     */
    public int maximumBagsV1(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(diff);

        int res = 0;
        int remain = additionalRocks;
        for (int i = 0; i < n; i++) {
            if (diff[i] == 0) {
                res++;
            } else {
                if (remain >= diff[i]) {
                    remain -= diff[i];
                    res++;
                } else {
                    break;
                }
            }
        }
        return res;
    }

    /**
     * Time Cost 14ms
     * Calculate all empty space for each bag.
     * Sort them from small to big, including 0 empty space.
     * Greedily Fill the bags, from small empty space to bigger one.
     * return the number of full bags.
     * Time Complexity O(N*logN)
     * Space Complexity O(N)
     *
     * @param capacity
     * @param rocks
     * @param additionalRocks
     * @return
     */
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(diff);
        int res = 0;
        int remain = additionalRocks;
        for (int i = 0; i < n && remain >= diff[i]; i++) {
            remain -= diff[i];
            res++;
        }
        return res;
    }
}
