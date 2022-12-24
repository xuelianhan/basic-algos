package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve.
 * You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 *
 * Input: prices = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 * @author sniper
 * @date 23 Dec, 2022
 * LC309
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    /**
     * This is one of the best explanations for this kind of problems!
     * As the state transitions only involve limited states and steps,
     * we should be able to improve the space complexity to O(1)
     *
     *       rest
     *   buy/    \rest
     *    hold---sold
     *        sell
     * @author npvinhphat
     * @author
     * @param prices
     * @return
     */
    public int maxProfitV1(int[] prices) {
        int sold = 0;
        int rest = 0;
        int hold = Integer.MIN_VALUE;
        for (int price : prices) {
            int prevSold = sold;
            sold = hold + price;//from s1 to s2
            hold = Math.max(hold, rest - price);//from s0 to s1
            rest = Math.max(rest, prevSold);//from s2 to s0.
        }
        return Math.max(rest, sold);
    }

    /**
     * There are three states, according to the action that you can take.
     *
     * Hence, from there, you can now the profit at a state at time i as:
     *
     * s0[i] = max(s0[i - 1], s2[i - 1]); // Stay at s0, or rest from s2
     * s1[i] = max(s1[i - 1], s0[i - 1] - prices[i]); // Stay at s1, or buy from s0
     * s2[i] = s1[i - 1] + prices[i]; // Only one way from s1
     * Then, you just find the maximum of s0[n] and s2[n],
     * since they will be the maximum profit we need (No one can buy stock and left with more profit that sell right :) )
     *
     * Define base case:
     *
     * s0[0] = 0; // At the start, you don't have any stock if you just rest
     * s1[0] = -prices[0]; // After buy, you should have -prices[0] profit. Be positive!
     * s2[0] = INT_MIN; // Lower base case
     *
     * s0 means we can buy
     * s1 means we can sell
     * s2 means we take a rest,
     * since we have to take a rest before we buy again,
     * s1 can not go to s0 directly and have to go to s2 first.
     *
     *             rest
     * (rest)S0 <----------S2
     *        \            /
     *         \          /
     *    (buy) \        /(sell)
     *           \      /
     *            \    /
     *             \  /
     *           (rest)S1
     *
     * @see <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solutions/75928/share-my-dp-solution-by-state-machine-thinking/"></a>
     * @author npvinhphat
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int[] s0 = new int[n];
        int[] s1 = new int[n];
        int[] s2 = new int[n];
        s0[0] = 0;
        s1[0] = -prices[0];
        s2[0] = Integer.MIN_VALUE;

        for (int i = 1; i < n; i++) {
            s0[i] = Math.max(s0[i - 1], s2[i - 1]);//we can buy
            s1[i] = Math.max(s1[i - 1], s0[i - 1] - prices[i]);//we can sell
            s2[i] = s1[i - 1] + prices[i];//take a rest
        }
        return Math.max(s0[n - 1], s2[n - 1]);
    }

}
