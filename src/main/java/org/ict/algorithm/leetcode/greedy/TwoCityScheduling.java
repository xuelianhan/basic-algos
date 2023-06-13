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

    public static void main(String[] args) {
        int[][] costs = {{10,20}, {30,200}, {400,50}, {30,20}};
        TwoCityScheduling instance = new TwoCityScheduling();
        instance.twoCitySchedCostV1(costs);
    }

    /**
     * Time Cost 2ms
     * e.g. costs = {{10,20}, {30,200}, {400,50}, {30,20}};
     * After sorting:
     * [[30, 200], [10, 20], [30, 20], [400, 50]]
     * 170, 10, -10, -350
     * res = 30 + 20 + 10 + 50 = 110
     *
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
        //System.out.println(Arrays.deepToString(costs));
        for (int i = 0; i < n; i++) {
            res += costs[i][0] + costs[i + n][1];
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Time Cost 1ms
     * -------------------
     * First of all, suppose we let all people go to city A, then the total cost is to add up the cost of all people going to city A.
     * But now we need to let half of them go to city B.
     * Since the cost is different, how to calculate it?
     * If the cost of going to city B is less than the cost of going to city A,
     * then the difference between the two should be refunded,
     * and if the cost of going to city A is less than the cost of going to city B,
     * then the difference between the two should be added.
     * So, if the cost to city B is negative, then it is refund, and if it is positive, then it is the additional cost.
     * Of course, we want it to be negative, and the smaller, the better,
     * so that we can refund and make the whole cost smaller.
     * So we start by iterating through the costs array,
     * add the cost to city A to the result res,
     * then put the difference between the cost to city B and the cost to city A into the refund array,
     * then sort the refund array and add the first n values to the result res.
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
