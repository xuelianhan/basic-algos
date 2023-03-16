package org.ict.algorithm.leetcode.binarysearch;

import java.util.Arrays;

/**
 * You are given an array of positive integers price where price[i] denotes the price of the ith candy and a positive integer k.
 *
 * The store sells baskets of k distinct candies.
 * The tastiness of a candy basket is the smallest absolute difference of the prices of any two candies in the basket.
 *
 * Return the maximum tastiness of a candy basket.
 *
 *
 *
 * Example 1:
 *
 * Input: price = [13,5,1,8,21,2], k = 3
 * Output: 8
 * Explanation: Choose the candies with the prices [13,5,21].
 * The tastiness of the candy basket is: min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8.
 * It can be proven that 8 is the maximum tastiness that can be achieved.
 * Example 2:
 *
 * Input: price = [1,3,1], k = 2
 * Output: 2
 * Explanation: Choose the candies with the prices [1,3].
 * The tastiness of the candy basket is: min(|1 - 3|) = min(2) = 2.
 * It can be proven that 2 is the maximum tastiness that can be achieved.
 * Example 3:
 *
 * Input: price = [7,7,7,7], k = 2
 * Output: 0
 * Explanation: Choosing any two distinct candies from the candies we have will result in a tastiness of 0.
 *
 *
 * Constraints:
 *
 * 2 <= k <= price.length <= 10^5
 * 1 <= price[i] <= 10^9
 *
 * @author sniper
 * @date 16 Mar, 2023
 * LC2517, Medium
 */
public class MaximumTastinessOfCandyBasket {

    /**
     * 1.Find the maximum tastiness of a candy basket.
     * 2.The tastiness of a candy basket is the smallest absolute difference of the prices of any two candies in the basket.
     * 3.The store sells baskets of k distinct candies.
     * 4.price[i] denotes the price of the ith candy
     * What is the tastiness? It's absolute difference of two prices.
     * tastiness = Math.abs(price[i] - price[j]), 1 <= price[i] <= 10^9
     * So the minimum of tastiness is zero, the maximum of tastiness is Math.abs[max(price) - min(price)]
     *
     * Our target is to find the maximum minimum absolute difference of any two prices from at least k price items
     * e.g.price = [13,5,1,8,21,2], k = 3
     * we need to find 3 price items, p1, p2, p3,
     * tastiness = min{abs(p1, p2), abs(p2, p3), abs(p1, p3)}
     * Now we need to find the maximum of all the tastiness, that is what the binary search doing.
     *
     * @param price
     * @param k
     * @return
     */
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);

        int n = price.length;
        int lo = 0;
        /**
         * while-loop ends condition is lo == hi,
         * price[n - 1] - price[0] may be the answer,
         * so we need plus one at last, in this way lo's value
         * can stay on price[n - 1] - price[0].
         */
        int hi = price[n - 1] - price[0] + 1;

        while (lo < hi) {
            /**
             * mid is one candidate,
             */
            int mid = lo + (hi - lo + 1) / 2;
            /**
             * Now we check whether mid feasible or not.
             * If feasible, lo is the maximum that satisfying the condition of greater than or equal to mid.
             */
            if (feasible(price, k, mid)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    /**
     * We can change the idea to find whether positive integer mid could form k items with at least distance mid.
     * @param price
     * @param k
     * @param mid
     * @return
     */
    private boolean feasible(int[] price, int k, int mid) {
        int candy = 1;
        int last = price[0];
        for (int i = 0; i < price.length; i++) {
            if ((price[i] - last) >= mid) {
                candy += 1;
                last = price[i];
            }
        }
        return candy >= k;
    }
}
