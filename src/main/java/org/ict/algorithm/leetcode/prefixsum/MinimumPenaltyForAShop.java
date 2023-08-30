package org.ict.algorithm.leetcode.prefixsum;

/**
 * You are given the customer visit log of a shop represented by a 0-indexed string customers consisting only of characters 'N' and 'Y':
 *
 * if the ith character is 'Y', it means that customers come at the ith hour
 * whereas 'N' indicates that no customers come at the ith hour.
 * If the shop closes at the jth hour (0 <= j <= n), the penalty is calculated as follows:
 *
 * For every hour when the shop is open and no customers come, the penalty increases by 1.
 * For every hour when the shop is closed and customers come, the penalty increases by 1.
 * Return the earliest hour at which the shop must be closed to incur a minimum penalty.
 *
 * Note that if a shop closes at the jth hour, it means the shop is closed at the hour j.
 *
 *
 *
 * Example 1:
 *
 * Input: customers = "YYNY"
 * Output: 2
 * Explanation:
 * - Closing the shop at the 0th hour incurs in 1+1+0+1 = 3 penalty.
 * - Closing the shop at the 1st hour incurs in 0+1+0+1 = 2 penalty.
 * - Closing the shop at the 2nd hour incurs in 0+0+0+1 = 1 penalty.
 * - Closing the shop at the 3rd hour incurs in 0+0+1+1 = 2 penalty.
 * - Closing the shop at the 4th hour incurs in 0+0+1+0 = 1 penalty.
 * Closing the shop at 2nd or 4th hour gives a minimum penalty. Since 2 is earlier, the optimal closing time is 2.
 * Example 2:
 *
 * Input: customers = "NNNNN"
 * Output: 0
 * Explanation: It is best to close the shop at the 0th hour as no customers arrive.
 * Example 3:
 *
 * Input: customers = "YYYY"
 * Output: 4
 * Explanation: It is best to close the shop at the 4th hour as customers arrive at each hour.
 *
 *
 * Constraints:
 *
 * 1 <= customers.length <= 10^5
 * customers consists only of characters 'Y' and 'N'.
 * @author sniper
 * @date 29 Aug 2023
 * LC2483, Medium
 *
 */
public class MinimumPenaltyForAShop {

    public static void main(String[] args) {
        String customers = "YNYY";
        MinimumPenaltyForAShop instance = new MinimumPenaltyForAShop();
        instance.bestClosingTime(customers);
    }

    /**
     * Time Cost 8ms
     * Time Complexity O(N)
     * Space Complexity O(1)
     * ----------------------------------
     * How to Start Coding?
     * 1.Understand the Problem:
     * Before you even touch the keyboard, make sure you understand what the problem is asking.
     * Do a dry run with a sample input.
     *
     * 2.Choose Your Approach:
     * Based on the problem constraints and what you're comfortable with, select an approach.
     * Each has its merits and demerits.
     *
     * 3.Pseudocode First:
     * Write down a quick pseudocode to outline your thoughts.
     * It helps to see the logic on paper before coding.
     *
     * 4.Code and Test:
     * Now, dive into the code.
     * Keep testing at every significant step to ensure you're on the right track.
     *
     * 5.Edge Cases:
     * Don't forget them; they're the Achilles heel in coding interviews.
     * Make sure your solution handles all edge cases.
     *
     * 6.Review:
     * Before saying you're done, review the code for any possible optimizations or errors.
     *
     * @param customers
     * @return
     */
    public int bestClosingTimeV1(String customers) {
        /**
         * Instead of computing the minimum penalty,
         * we can compute the max profit.
         */
        int res = 0;
        int profit = 0;
        int maxProfit = 0;
        for (int i = 0; i < customers.length(); i++) {
            profit += (customers.charAt(i) == 'Y' ? 1: -1);
            if (profit > maxProfit) {
                maxProfit = profit;
                res = i + 1;
            }
        }
        return res;
    }

    /**
     * todo
     * e.g. "YNYY", expected 4, but output 1
     * Prefix Sum Solution
     * @param customers
     * @return
     */
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int cntY = 0;
        int cntN = 0;

        int[] prefixN = new int[n + 1];
        int[] suffixY = new int[n + 1];

        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'N') {
                cntN++;
                prefixN[i + 1] = cntN;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (customers.charAt(i) == 'Y') {
                cntY++;
                suffixY[n - i] = cntY;
            }
        }

        int bestHour = 0;
        int minCustomers = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            int customersServed = prefixN[i] + suffixY[n - i];
            if (customersServed < minCustomers) {
                bestHour = i;
                minCustomers = customersServed;
            }
        }

        return bestHour;
    }
}
