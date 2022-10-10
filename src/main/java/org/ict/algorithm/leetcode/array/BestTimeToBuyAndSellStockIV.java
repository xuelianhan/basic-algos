package org.ict.algorithm.leetcode.array;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 *
 * Find the maximum profit you can achieve. You may complete at most k transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 *
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 * Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 *
 * Constraints:
 *
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 * @author sniper
 * @date 09 Oct, 2022
 * LC188
 */
public class BestTimeToBuyAndSellStockIV {

    /**
     * Solution provided by jinwu
     *
     * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
     * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
     *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
     * dp[0, j] = 0; 0 transactions makes 0 profit.
     * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        /**
         * if k >= n/2, then you can make the maximum number of transactions.
         * Same as BestTimeToBuyAndSellStockII problem.
         * The definition of one transaction means only one buy and sell, only buy or sell is not an integrated transaction.
         * (buy, sell)== one transaction
         *
         * e.g.
         * n = 4
         * 1 2 3 4, (buy at 1, sell at 2), (buy at 2, sell at 3), (buy at 3, sell at 4), so the total transaction times is 3.
         * n = 5
         * 1 2 3 4 5, (buy at 1, sell at 2), (buy at 2, sell at 3), (buy at 3, sell at 4), (buy at 4, sell at 5), so the transaction times is 4
         */
        if (k >= n/2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i-1]) {
                    maxProfit += (prices[i] - prices[i-1]);
                }
            }
            return maxProfit;
        }

        return 0;
    }
}
