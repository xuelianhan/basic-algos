package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * There is a robot on an m x n grid.
 * The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 7
 * Output: 28
 * Example 2:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 100
 * @author sniper
 * @date 25 Nov, 2022
 * LC62
 * This problem is similar with ClimbingStairs(LC70)
 */
public class UniquePaths {

    public static void main(String[] args) {
        UniquePaths instance = new UniquePaths();
        int res = instance.uniquePaths(3, 2);
        System.out.println(res);
    }


    /**
     * C(m+n-2, m-1) = (m+n-2)! / ((m-1)! (n-1)!)
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsV3(int m, int n) {
        double num = 1, denominator = 1;
        int small = Math.min(m, n);
        for (int i = 1; i <= small - 1; i++) {
            num *= m + n - 1 - i;
            denominator *= i;
        }
        /**
         * (m+n-2)*(m+n-3)*(m+n-4)*...*(m+n-1-small+1)
         * = (m+n-2)*(m+n-3)*(m+n-4)*...*(m+n-small)
         *
         * 1*2*3*...*(small-1)
         */
        return (int)(num / denominator);
    }


    /**
     * Understanding the following solution
     *
     * Top-Down DP with Memorization
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsV2(int m, int n) {
        Map<String, Integer> memo = new HashMap<>();
        return topDownDp(m, n, memo);
    }

    private int topDownDp(int m, int n, Map<String, Integer> memo) {
        if (m < 0 || n < 0) {
            return 0;
        }
        if (m == 1 && n == 1) {
            return 1;
        }
        String key = m + "," + n;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int rightPath = topDownDp(m - 1, n, memo);
        int downPath = topDownDp(m, n - 1, memo);
        memo.put(key, rightPath + downPath);
        return memo.get(key);
    }

    /**
     * Similar with 2-dimension-DP, we use a 1-dimension array to save space and
     * fresh value line by line.
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsV1(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    /**
     * Understanding the following solution
     *
     * Iterative-DP-from-Down-to-Top.
     *                      ---------------
     *                         path(3,2)
     *                      ---------------
     *                     /               \
     *            path(2,2)                path(3,1)
     *            --------                 ---------
     *           /        \               /         \
     *    path(1,2)       path(2,1)     path(2,1)   (3,0)
     *    --------        ---------     --------
     *   /       \        /      \      /      \
     * (0,2) path(1,1)path(1,1) (2,0) path(1,1) (2,0)
     *
     * So dp[3][2] = dp[3-1][2] + dp[3][2-1]
     *             = dp[2][2] + dp[3][1]
     *             = dp[1][1] + dp[1][1] + dp[1][1]
     *             = 3
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        /**
         * both m and n are 1, this means that we don't need to move and keep staying original point,
         * it has one choice only, so the dp[1][1] is 1.
         * dp[0][0] = 0, m and n both are zero, so no paths for this case.
         * dp[1][2] = dp[1][1] + dp[0][2] = 1 + 0 = 1
         * dp[2][1] = dp[2][0] + dp[1][1] = 0 + 1 = 1
         * dp[2][2] = dp[2][1] + dp[1][2] = 1 + 1 = 2
         */
        dp[1][1] = 1;
        for(int x = 1; x <= m; ++x) {
            for (int y = 1; y <= n; ++y)
                if (x == 1 && y == 1) {
                    /**
                     * Because we have assigned value for dp[1][1],
                     * we do this step to assure the value not to be overwritten.
                     */
                    continue;
                } else {
                    dp[x][y] = dp[x][y-1] + dp[x-1][y];
                }
            }
        return dp[m][n];
    }
}
