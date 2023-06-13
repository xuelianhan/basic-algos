package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * A company is planning to interview 2n people.
 * Given the array costs where costs[i] = [aCost-i, bCost-i],
 * the cost of flying the i-th person to city a is aCost-i,
 * and the cost of flying the ith person to city b is bCost-i.
 * Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
 *
 * Example 1:
 * Input: costs = [[10,20],[30,200],[400,50],[30,20]]
 * Output: 110
 * Explanation:
 * The first person goes to city A for a cost of 10.
 * The second person goes to city A for a cost of 30.
 * The third person goes to city B for a cost of 50.
 * The fourth person goes to city B for a cost of 20.
 *
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 *
 *
 * Example 2:
 * Input: costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
 * Output: 1859
 *
 *
 * Example 3:
 * Input: costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
 * Output: 3086
 *
 *
 * Constraints:
 * 2 * n == costs.length
 * 2 <= costs.length <= 100
 * costs.length is even.
 * 1 <= aCost-i, bCost-i <= 1000
 * @author sniper
 * @date 13 Jun 2023
 * LC1029, Medium, frequency=14
 */
public class TwoCityScheduling {

    /**
     * Time Cost 2ms
     * @param costs
     * @return
     */
    public int twoCitySchedCostV1(int[][] costs) {
        int n = costs.length / 2;
        int res = 0;

        // How much money can we save if we fly a person to A instead of B?
        // To save money, we should
        //   1) fly the person with the max saving to A
        //   2) fly the person with the min saving to B

        // Sort in descending order by the money saved if we fly a person to A
        // instead of B.
        Arrays.sort(costs, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        for (int i = 0; i < n; i++) {
            res += costs[i][0] + costs[i + n][1];
        }
        return res;
    }

    /**
     * Time Cost 1ms
     * @param costs
     * @return
     */
    public int twoCitySchedCost(int[][] costs) {
        int n = costs.length / 2;
        int res = 0;
        int[] refund = new int[n * 2];
        for (int i = 0; i < n * 2; i++) {
            res += costs[i][0];
            refund[i] = costs[i][1] - costs[i][0];
        }
        Arrays.sort(refund);
        for (int i = 0; i < n; i++) {
            res += refund[i];
        }
        return res;
    }
}
