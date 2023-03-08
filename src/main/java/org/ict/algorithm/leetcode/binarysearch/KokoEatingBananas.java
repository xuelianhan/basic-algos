package org.ict.algorithm.leetcode.binarysearch;

/**
 * Koko loves to eat bananas.
 * There are n piles of bananas, the i-th pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k.
 * Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas,
 * she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * Example 2:
 *
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * Example 3:
 *
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 *
 * Constraints:
 *
 * 1 <= piles.length <= 10^4
 * piles.length <= h <= 10^9
 * 1 <= piles[i] <= 10^9
 *
 * @author sniper
 * @date 08 Mar, 2023
 * LC875, Medium
 */
public class KokoEatingBananas {


    /**
     * e.g.piles = [3,6,7,11], h = 8
     * k:3, piles = [3,3,3,3,3,1,3,3,3,2], the size is greater than 8.
     * k:4, piles = [3,4,2,4,3,4,4,3], the size is equal to 8, k:4 is the minimum number
     * k:5, piles = [3,5,1,5,2,5,5,1], the size is equal to 8, but k:5 is not the minimum number
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        //3,6,7,11

        //3,4,2,4,3,4,4,3
        //todo
        return 0;
    }
}
