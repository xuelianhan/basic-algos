package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below.
 * More formally, if you are on index i on the current row,
 * you may move to either index i or index i + 1 on the next row.
 *
 * Example 1:
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 *
 * Example 2:
 * Input: triangle = [[-10]]
 * Output: -10
 *
 *
 * Constraints:
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -10^4 <= triangle[i][j] <= 10^4
 *
 * Follow up: Could you do this using only O(n) extra space,
 * where n is the total number of rows in the triangle?
 * @author sniper
 * @date 26 Jun 2023
 * LC120, Medium,
 */
public class Triangle {

    /**
     * Time Cost 2ms
     * ------------------------------
     * class Solution:
     *     def minimumTotal(self, triangle: List[List[int]]) -> int:
     *         n = len(triangle)
     *         dp = [0] * (n + 1)
     *         for i in range(n - 1, -1, -1):
     *             for j in range(i + 1):
     *                 dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j]
     *         return dp[0]
     * ----------------------------
     * class Solution {
     * public:
     *     int minimumTotal(vector<vector<int>>& triangle) {
     *         int n = triangle.size();
     *         vector<int> dp(n + 1);
     *         for (int i = n - 1; i >= 0; --i)
     *             for (int j = 0; j <= i; ++j)
     *                 dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j];
     *         return dp[0];
     *     }
     * };
     * @param triangle
     * @return
     */
    public int minimumTotalV2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }


    /**
     * Time Cost 2ms
     * @param triangle
     * @return
     */
    public int minimumTotalV1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for (int i = 0; i < triangle.get(n - 1).size(); i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }


    /**
     * Time Cost 8ms
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }

}
