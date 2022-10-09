package org.ict.algorithm.leetcode.array;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later,
 * as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^5
 *
 * @author sniper
 * @date 08 Oct, 2022
 * LC123
 */
public class BestTimeToBuyAndSellStockIII {

    /**
     * Solution provided by weijiac
     *
     * init --> oneBuy --> oneSold --> twoBuy --> twoSold
     *
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int oneBuy = Integer.MIN_VALUE;
        int twoBuy = Integer.MIN_VALUE;
        int oneSold = 0;
        int twoSold = 0;
        for (int i = 0; i < prices.length; i++) {
            twoSold = Math.max(twoSold, twoBuy + prices[i]);
            twoBuy = Math.max(twoBuy, oneSold - prices[i]);
            oneSold = Math.max(oneSold, oneBuy + prices[i]);
            oneBuy = Math.max(oneBuy, -prices[i]);
        }
        return Math.max(twoSold, oneSold);
    }
}