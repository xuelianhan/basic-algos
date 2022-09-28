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
        maxProfit(prices);
    }

    public static int maxProfitV1(int[] prices) {
        int n = prices.length;
        int maxSP[] = new int[n];
        int max = Integer.MIN_VALUE;
        // Construct the maxSP array
        for (int i = n - 1; i >= 0; i--) {
            if (prices[i] > max) {
                max = prices[i];
                maxSP[i] = Integer.MIN_VALUE;
            } else {
                maxSP[i] = max;
            }
        }
        int profit = 0;
        for (int i = 0; i < n; i++) {
            if (maxSP[i] != Integer.MIN_VALUE) {
                profit = Math.max(profit, maxSP[i] - prices[i]);
            }
        }
        // Return profit
        return profit;
    }

    /**
     * Time Limit Exceeded.
     *
     * [3,2,6,5,0,3]
     * expected:4
     *
     * [7,1,5,3,6,4]
     * expected:5
     *
     *  0 1 2 3 4
     * [7,6,4,3,1]
     * expected:0
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int maxRes = 0;
        for (int i = 0; i < prices.length; i++) {
            int maxRight = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > maxRight) {
                    maxRight = prices[j];
                }
            }
            System.out.println("maxRight:" + maxRight + ", price[" + i +"]:" + prices[i] + ", maxRes:" + maxRes);
            if ((maxRight - prices[i]) > maxRes) {
                maxRes = (maxRight - prices[i]);
            }
            System.out.println(prices[i] + ", maxRes:" + maxRes);
        }
        return maxRes;
    }
}
