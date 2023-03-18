package org.ict.algorithm.leetcode.binarysearch;

import java.util.Arrays;

/**
 * You are given an array of positive integers price where price[i] denotes the price of the ith candy and a positive integer k.
 * The store sells baskets of k distinct candies.
 * The tastiness of a candy basket is the smallest absolute difference of the prices of any two candies in the basket.
 * Return the maximum tastiness of a candy basket.
 *
 * Example 1:
 * Input: price = [13,5,1,8,21,2], k = 3
 * Output: 8
 * Explanation: Choose the candies with the prices [13,5,21].
 * The tastiness of the candy basket is: min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8.
 * It can be proven that 8 is the maximum tastiness that can be achieved.
 *
 * Example 2:
 * Input: price = [1,3,1], k = 2
 * Output: 2
 * Explanation: Choose the candies with the prices [1,3].
 * The tastiness of the candy basket is: min(|1 - 3|) = min(2) = 2.
 * It can be proven that 2 is the maximum tastiness that can be achieved.
 *
 * Example 3:
 * Input: price = [7,7,7,7], k = 2
 * Output: 0
 * Explanation: Choosing any two distinct candies from the candies we have will result in a tastiness of 0.
 *
 * Constraints:
 * 2 <= k <= price.length <= 10^5
 * 1 <= price[i] <= 10^9
 *
 * @author sniper
 * @date 16 Mar, 2023
 * LC2517, Medium
 */
public class MaximumTastinessOfCandyBasket {


    /**
     * A macro optimization problem can be abstracted as a function whose definition domain is the feasible solutions under the problem,
     * and the values obtained from the evaluation of these solutions constitute the value domain of the function,
     * and the optimal solution is the solution with the best evaluation value,
     * which may be set to be better the higher the rating is.
     * Assuming that the optimal solution is scored as S,
     * it is clear that for any x greater than S, there doesn't exist a legitimate program to reach x points,
     * otherwise it contradicts the optimality of S.
     * For any x less than or equal to S, there must be a legitimate program to reach or exceed x points,
     * because the optimal solution itself satisfies this condition.
     * The value domain of such problem has a special monotonicity---is legal on one side of S and not on the other side of S.
     * This division point S can be found by binary search.
     * With the help of binary search,
     * we can transform the problem of finding an optimal solution into a problem of determining whether there is a feasible solution
     * that makes the score reach mid, given a value mid
     *
     *
     * There are two steps for finding problem whether it is binary search or not
     * step -> 1 ask for maximum and minimum(answer lies in a range)
     * step ->2 at particular point left side of point is valid and right side is invalid situation and vice versa
     *
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
    public int maximumTastinessV1(int[] price, int k) {
        /**
         * Why we sort price here?
         * 1.we need to calculate the upper bound of tastiness;
         * 2.we need to calculate the smallest absolute difference of the prices of any two candies.
         */
        Arrays.sort(price);
        int n = price.length;
        int lo = 0;
        /**
         * while-loop ends condition is lo == hi, and price[n - 1] - price[0] may be the answer.
         * hi >= (price[n - 1] - price[0]) is OK, e.g.int hi = 1000_000_000;
         * but hi cannot less than (price[n - 1] - price[0]).
         */
        int hi = price[n - 1] - price[0];
        while (lo < hi) {
            /**
             * mid is one candidate.
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

    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int lo = 0;
        /**
         * while-loop ends condition is lo == hi, and price[n - 1] - price[0] may be the answer.
         * hi >= (price[n - 1] - price[0]) is OK.
         */
        int hi = 1000_000_000;
        while (lo < hi) {
            /**
             * mid is one candidate.
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
     * Find whether absolute difference mid could form at least k items.
     * We need to search the smallest absolute difference of price pairs,
     * these smallest absolute differences definitely come from the adjacent items in the sorted array.
     * So we have to sort the array which will help us to calculate the absolute differences.
     * e.g.price = [13,5,1,8,21,2], k = 3
     * sort(price):[1,2,5,8,13,21], k = 3
     * Suppose mid equals 1:
     * i:1, 2-1=1, 1 = 1, candy=1+1=2, last=price[1]=2
     * i:2, 5-2=3, 3 > 1, candy=2+1=3, last=price[2]=5
     * i:3, 8-5=3, 3 > 1, candy=3+1=4, last=price[3]=8
     * i:4,13-8=5, 5 > 1, candy=4+1=5, last=price[4]=13
     * i:5,21-13=8,8 > 1, candy=5+1=6, last=price[5]=21
     * candy:6, 6 > 3, return true.
     *
     * @param price
     * @param k
     * @param mid The absolute difference of the prices of any two candies
     * @return
     */
    private boolean feasible(int[] price, int k, int mid) {
        int candy = 1;
        int last = price[0];
        /**
         * The i start from 0 is unnecessary, so i start from 1, and candy start from 1,
         * because last being initialized with price[0].
         */
        for (int i = 1; i < price.length; i++) {
            if ((price[i] - last) >= mid) {
                candy += 1;
                last = price[i];
            }
        }
        return candy >= k;
    }


}
