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
     * The thinking is simple and is inspired by the best solution from Single Number II (I read through the discussion after I use DP).
     * Assume we only have 0 money at first;
     * 4 Variables to maintain some interested 'ceilings' so far:
     * The maximum of if we've just buy 1st stock, if we've just sold 1nd stock, if we've just buy 2nd stock, if we've just sold 2nd stock.
     * Very simple code too and work well. I have to say the logic is simple than those in Single Number II.
     *
     * init --> oneBuy --> oneSold --> twoBuy --> twoSold
     *
     * Notice the order of the code should be reversed,
     * although the normal order can get the same result, but the logic of normal order is not right.
     *
     * twoSold: Final profit.
     * twoBuy: Best profit so far, if you buy the stock not after Day i (2nd transaction).
     * oneSold: Best profit so far, if you sell the stock not after Day i (1st transaction).
     * oneBuy: Minimum price to buy the stock.
     *
     * @param prices
     * @return
     */
    public int maxProfitV1(int[] prices) {
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

    /**
     * Buy at lowest price, sell at highest price, So we can get the maximum profit.
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int oneBuy = Integer.MAX_VALUE;
        int twoBuy = Integer.MAX_VALUE;
        int oneSold = 0;
        int twoSold = 0;
        for (int i = 0; i < prices.length; i++) {
            int p = prices[i];
            /**
             * twoSold is the profit at the second time.
             * twoBuy is the cost, so need to be subtracted.
             */
            twoSold = Math.max(twoSold, p - twoBuy);
            /**
             * When buy at the second time, oneSold is the profit in our hand.
             * So we need only cost (p - oneSold) or do nothing.
             */
            twoBuy = Math.min(twoBuy, p - oneSold);

            /**
             * sell price must greater than buy price
             * oneSold is the profit at the first time.
             * oneBuy is the cost, so need to be subtracted.
             */
            oneSold = Math.max(oneSold, p - oneBuy);
            /**
             * buy at lowest price
             */
            oneBuy = Math.min(oneBuy, p);
        }
        return twoSold;
    }
}
