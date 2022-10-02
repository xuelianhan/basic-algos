package org.ict.algorithm.leetcode.array;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction.
 * If you cannot achieve any profit, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * Example 2:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 * @author sniper
 * @date 27 Sep, 2022
 * LC121
 */
public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        //int[] prices = {7,6,4,3,1};
        maxProfitV2(prices);
    }

    /**
     * Solution provided by Earl<a href="https://leetcode.com/earlme/">Earl</a>
     * Kadane's Algorithm
     *
     * The logic to solve this problem is same as "max subarray problem" using Kadane's Algorithm.
     * Since no body has mentioned this so far, I thought it's a good thing for everybody to know.
     *
     * All the straight forward solution should work,
     * but if the interviewer twists the question slightly by giving the difference array of prices, Ex: for {1, 7, 4, 11},
     * if he gives {0, 6, -3, 7},
     * you might end up being confused.
     *
     * Here, the logic is to calculate the difference (maxCur += prices[i] - prices[i-1]) of the original array,
     * and find a contiguous subarray giving maximum profit. If the difference falls below 0, reset it to zero.
     *
     * @param prices
     * @return
     */
    public static int maxProfitV3(int[] prices) {
        int currentMax = 0;
        int soFarFoundMax = 0;
        for (int i = 1; i < prices.length; i++) {
            currentMax = Math.max(0, currentMax += (prices[i] - prices[i-1]));
            soFarFoundMax = Math.max(soFarFoundMax, currentMax);
        }
        return soFarFoundMax;
    }


    public static int maxProfitV2(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int price : prices) {
            minPrice = Math.min(price, minPrice);
            int profit = price - minPrice;
            maxProfit = Math.max(profit, maxProfit);
        }
        return maxProfit;
    }

    /**
     * This problem can be solved using the greedy approach.
     * To maximize the profit we have to minimize the buy cost and to sell it at the maximum price.
     * Follow the steps bellow to implement the above idea.
     * 1.Declare a buy variable to store the buy cost and a maxProfix variable to store the maximum.
     * 2.Initialize the buy variable to the first element of the prices array.
     * Iterate over the prices array and check if the current price is minimum or not.
     * a.If the current price is minimum then buy on this ith day.
     * b.If the current price is greater than the previous buy cost then make profit from it and maximize the maxProfit.
     * 3.Finally, return the maxProfit.
     *
     * Time Complexity O(N)
     * Auxiliary Space O(1)
     *
     * @param prices
     * @return
     */
    public static int maxProfixV1(int[] prices) {
        int n = prices.length;
        int buy = prices[0], maxProfit = 0;
        for (int i = 1; i < n; i++) {
            if (buy > prices[i]) {
                /**
                 * Checking for lower buy value
                 */
                buy = prices[i];
            } else if (prices[i] - buy > maxProfit) {
                /**
                 * Checking for higher profit
                 */
                maxProfit = prices[i] - buy;
            }
        }
        return maxProfit;
    }

    /**
     * Same with method maxProfix.
     * @param prices
     * @return
     */
    public static int maxProfixV0(int[] prices) {
        int n = prices.length;
        int minBuySoFar = prices[0], maxProfit = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > minBuySoFar) {
                maxProfit = Math.max(maxProfit, prices[i] - minBuySoFar);
            } else {
                minBuySoFar = prices[i];
            }
        }
        return maxProfit;
    }

    
    public static int maxProfix(int[] prices) {
        int n = prices.length;
        int minBuySoFar = prices[0], maxProfit = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] < minBuySoFar) {
                minBuySoFar = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minBuySoFar);
            }
        }
        return maxProfit;
    }
}
