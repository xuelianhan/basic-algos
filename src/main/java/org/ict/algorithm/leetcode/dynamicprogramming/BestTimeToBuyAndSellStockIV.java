package org.ict.algorithm.leetcode.dynamicprogramming;

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
 *
 *
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
     * dp[0, j] = 0; 0 transactions makes 0 profit
     * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
     *
     * ------------------------------------------------------------------------------------
     * Description provided by Sireesh
     * Easy to understand explanation for the above solution
     * dp[i][j] = maximum profit from at most i transactions using prices[0..j]
     *
     * A transaction is defined as one buy + sell.
     * Now on day j, we have two options
     * Do nothing (or buy) which doesn't change the acquired profit : dp[i][j] = dp[i][j-1]
     *
     * Sell the stock: In order to sell the stock, you must've bought it on a day t=[0..j-1].
     * Maximum profit that can be attained is t:0->j-1 max(prices[j]-prices[t]+dp[i-1][t-1])
     * where prices[j]-prices[t] is the profit from buying on day t and selling on day j.
     * dp[i-1][t-1] is the maximum profit that can be made with at most i-1 transactions with prices prices[0..t-1].
     *
     * Time complexity of this approach is O(n2k).
     * In order to reduce it to O(nk),
     * we must find t:0->j-1 max(prices[j]-prices[t]+dp[i-1][t-1]) this expression in constant time.
     * If you see carefully,
     *
     * t:0->j-1 max(prices[j]-prices[t]+dp[i-1][t-1]) is same as
     * prices[j] + t:0->j-1 max(dp[i-1][t-1]-prices[t])
     *
     * Second part of the above expression maxTemp = t:0->j-1 max(dp[i-1][t-1]-prices[t])
     * can be included in the dp loop by keeping track of the maximum value till j-1.
     *
     * Base case:
     *
     * dp[0][j] = 0; dp[i][0] = 0
     *
     * DP loop:
     *
     * for i : 1 -> k
     *     maxTemp = -prices[0];
     *     for j : 1 -> n-1
     *         dp[i][j] = max(dp[i][j-1], prices[j]+maxTemp);
     *         maxTemp = max(maxTemp, dp[i-1][j-1]-prices[j]);
     * return dp[k][n-1];
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
         * one transaction = buy & sell
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
         * the probability of prices[i] greater than prices[i-1] is 1/2.
         *
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

        int[][] dp = new int[k+1][n];
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i-1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j] + localMax);
                localMax = Math.max(localMax, dp[i-1][j] - prices[j]);
            }
        }
        return dp[k][n-1];
    }
}
