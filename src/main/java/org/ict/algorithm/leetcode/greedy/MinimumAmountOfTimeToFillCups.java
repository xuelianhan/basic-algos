package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * You have a water dispenser that can dispense cold, warm, and hot water.
 * Every second, you can either fill up 2 cups with different types of water, or 1 cup of any type of water.
 *
 * You are given a 0-indexed integer array amount of length 3
 * where amount[0], amount[1], and amount[2] denote the number of cold, warm, and hot water cups you need to fill respectively.
 * Return the minimum number of seconds needed to fill up all the cups.
 *
 *
 *
 * Example 1:
 *
 * Input: amount = [1,4,2]
 * Output: 4
 * Explanation: One way to fill up the cups is:
 * Second 1: Fill up a cold cup and a warm cup.
 * Second 2: Fill up a warm cup and a hot cup.
 * Second 3: Fill up a warm cup and a hot cup.
 * Second 4: Fill up a warm cup.
 * It can be proven that 4 is the minimum number of seconds needed.
 * Example 2:
 *
 * Input: amount = [5,4,4]
 * Output: 7
 * Explanation: One way to fill up the cups is:
 * Second 1: Fill up a cold cup, and a hot cup.
 * Second 2: Fill up a cold cup, and a warm cup.
 * Second 3: Fill up a cold cup, and a warm cup.
 * Second 4: Fill up a warm cup, and a hot cup.
 * Second 5: Fill up a cold cup, and a hot cup.
 * Second 6: Fill up a cold cup, and a warm cup.
 * Second 7: Fill up a hot cup.
 * Example 3:
 *
 * Input: amount = [5,0,0]
 * Output: 5
 * Explanation: Every second, we fill up a cold cup.
 *
 *
 * Constraints:
 *
 * amount.length == 3
 * 0 <= amount[i] <= 100
 * @author sniper
 * @date 26 Jun 2023
 * LC2335, Easy
 */
public class MinimumAmountOfTimeToFillCups {

    /**
     * class Solution {
     * public:
     *     int fillCups(vector<int>& amount) {
     *         const int max = *max_element(amount.begin(), amount.end());
     *         const int sum = accumulate(amount.begin(), amount.end(), 0);
     *         return std::max(max, (sum + 1) / 2);
     *     }
     * };
     * -------------------------------------------------------
     * class Solution:
     *     def fillCups(self, amount: List[int]) -> int:
     *         return max(max(amount), (sum(amount) + 1) // 2)
     * -------------------------------------------------------
     * @param amount
     * @return
     */
    public int fillCupsV1(int[] amount) {
        int max = Arrays.stream(amount).max().getAsInt();
        int sum = Arrays.stream(amount).sum();
        return Math.max(max, (sum + 1) / 2);
    }

    /**
     * Understanding the following solution.
     * @param amount
     * @return
     */
    public int fillCups(int[] amount) {
        int max = 0;
        int sum = 0;
        for (int a : amount) {
            max = Math.max(max, a);
            sum += a;
        }
        return Math.max(max, (sum + 1) / 2);
    }
}
