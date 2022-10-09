package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
 * However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 *
 * @author sniper
 * @date 08 Oct, 2022
 * LC122
 */
public class BestTimeToBuyAndSellStockII {

    /**
     * Same with maxProfitV5 but a little difference lies at the initialization of s1.
     * @param prices
     * @return
     */
    public int maxProfitV6(int[] prices) {
        int s1 = Integer.MIN_VALUE;
        int s2 = 0;
        for (int i = 0; i < prices.length; i++) {
            s1 = Math.max(s1, s2 - prices[i]);
            s2 = Math.max(s2, s1 + prices[i]);
        }
        return s2;
    }

    /**
     * Can be made more concise as below and can be done using state machines:
     * s1: state of buying (you can come to this state after selling i.e. s2)
     * s2: state of selling (you can come to this state after buying i.e. s1)
     * The aim is here is to maximize the profit and hence Math.max(). The profit will be maximum only in s2 and hence return that.
     * @param prices
     * @return
     */
    public int maxProfitV5(int[] prices) {
        int s1 = -prices[0];
        int s2 = 0;
        for (int i = 0; i < prices.length; i++) {
            s1 = Math.max(s1, s2 - prices[i]);
            s2 = Math.max(s2, s1 + prices[i]);
        }
        return s2;
    }

    public int maxProfitV4(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int lastBuy = Integer.MIN_VALUE;
        int lastSold = 0;
        for (int i = 0; i < prices.length; i++) {
            int curBuy = Math.max(lastBuy, lastSold - prices[i]);
            int curSold = Math.max(lastSold, lastBuy + prices[i]);

            lastBuy = curBuy;
            lastSold = curSold;
        }
        return lastSold;
    }

    /**
     * The following solution provided by chipbk10
     *
     * The action we can do on ith day is either buy(if last action is sell) or sell(if last action is buy), or
     * do nothing(not buy, not sell)
     *
     * Think of lastBuy and lastSold as the state of your finance
     * i.e. the amount of cash you own after the last transaction (either buy/sell).
     * We started out with $0 and after making the first purchase(assume that you managed to borrow the money from a bank),
     * and your net cash is minus the price of your first stock purchase (minus means the money borrowed / you are in debt).
     * With the same reasoning, after you have completed all the transactions (selective buy sell across N),
     * you return 'lastSold' because it's the state of your cash you have after selling the last stock (especially it's the profit since we started from $0).
     *
     * This is the first time you buy. When you buy, you have to pay A[0]. The current debt is 0 - A[0].
     *
     * Let's take a short explanation:
     * lastSold will hold the final profit as you can see from the code.
     * How do we populate lastSold with the maximum profit?
     *
     * At every iteration, you have these choices:
     * 1. Don't do anything on lastBuy or lastSold(because there is no change in the amount of money you have).
     * 2. Sell them for a profit(lastBuy + current, because you are selling the amount of money you have increases).
     * 3. Buy new stock(lastSold - current, you need money to buy stock, so you sell your profit to buy a new stock).
     *
     * Base on the above choices:
     * curBuy is to determine if you should buy a stock, therefore, it is the maximum of the what you already bought
     * and buying the current one by giving up previous profit.
     *
     * curSold is to determine if you should sell a stock, therefore, it is the maximum of what your existing profit(lastSold)
     * and selling the current one.
     *
     *
     * state machine graph:
     *
     *    -----------------
     *    |                |
     *    |                |
     * not holding       holding
     *    |                |
     *    |                |
     *    ------------------
     *
     * @param prices
     * @return
     */
    public int maxProfitV3(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        /**
         * At first time, I have no money(account balance amount is zero), so I have to buy first stock in debt(0-prices[0]).
         * Notice here, lastBuy cannot be initialized with zero, but can be initialized with Integer.MIN_VALUE.
         */
        int lastBuy = - prices[0];
        /**
         * lastBuy initialized with Integer.MIN_VALUE is ok too.
         */
        //int lastBuy = Integer.MIN_VALUE;
        int lastSold = 0;

        for (int i = 0; i < prices.length; i++) {
            /**
             * Buy as much as possible, so we use max function instead of min.
             * If lastBuy is greater than (lastSold - prices[i]), it means we keep the stock holding, we buy nothing currently.
             * If (lastSold - prices[i]) is greater than lastBuy, it means we buy the stock from profit balance.
             */
            int curBuy = Math.max(lastBuy, lastSold - prices[i]);
            /**
             * Sell as much as possible, so we use max function instead of min.
             * We need to decide whether keep not holding or sell the stock.
             * If lastSold is greater than (lastBuy + prices[i]), it means we keep the stock not holding, we sell nothing currently.
             * If (lastBuy + prices[i]) is greater than lastSold, it means we sell stock at price of (lastBuy + prices[i]).
             */
            int curSold = Math.max(lastSold, lastBuy + prices[i]);

            lastBuy = curBuy;
            lastSold = curSold;
        }
        /**
         * Our profit comes from the result of last sell.
         * lastSold will ultimately determine your profit.
         */
        return lastSold;
    }


    /**
     * Modify the Kadane's Algorithm
     * @param prices
     * @return
     */
    public int maxProfitV2(int[] prices) {
        int currentMax = 0;
        for (int i = 1; i < prices.length; i++) {
            currentMax += Math.max(0, (prices[i] - prices[i-1]));
        }
        return currentMax;
    }

    /**
     * Modify the Kadane's Algorithm
     * @param prices
     * @return
     */
    public int maxProfitV1(int[] prices) {
        int currentMax = 0;
        int soFarFoundMax = 0;
        for (int i = 1; i < prices.length; i++) {
            currentMax = Math.max(0, (prices[i] - prices[i-1]));
            soFarFoundMax += currentMax;
        }
        return soFarFoundMax;
    }

    /**
     * You can buy it then immediately sell it on the same day. This means that your net profit is zero on that day.
     * We will only buy a stock and sell it some other day, only if the net profit is more than zero.
     * So our net profit is nothing, but the summation of increase in price of the stocks every alternate days.
     *
     * 4ms
     * Time Complexity O(N)
     * Space Complexity O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int currentMax = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                currentMax += (prices[i] - prices[i-1]);
            }
        }
        return currentMax;
    }
}
