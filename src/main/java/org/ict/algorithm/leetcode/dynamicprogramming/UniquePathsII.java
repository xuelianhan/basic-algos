package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * You are given an m x n integer array grid.
 * There is a robot initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 *
 * An obstacle and space are marked as 1 or 0 respectively in grid.
 * A path that the robot takes cannot include any square that is an obstacle.
 *
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The testcases are generated so that the answer will be less than or equal to 2 * 109.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * Example 2:
 *
 *
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] is 0 or 1.
 * @author sniper
 * @date 31 Dec, 2022
 * LC63
 */
public class UniquePathsII {

    public int uniquePathsWithObstaclesV2(int[][] obstacleGrid) {
        return 0;
    }

    /**
     * Using One-Dimension Array
     * Time Cost 0ms
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstaclesV1(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }

    /**
     * Understanding the following Solution.
     * Time Cost 0ms
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        /**
         * dp[i][j] means the number of different paths can arrive at (i - 1, j - 1)
         * so the pointer-i is in [0, m], the pointer-j is in [0, n].
         * The state transfer equotation is same as {@link UniquePaths.java}
         * dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
         * The only different point is that we need to skip to obstacle(grid[i][j] == 1).
         */
        int[][] dp = new int[m + 1][n + 1];
        /**
         * dp[1][1] = dp[0][1] + dp[1][0] = 1,
         * So we need only to initialize dp[0][1] or dp[1][0] to 1, the other remains 0.
         * In here, we initialize dp[0][1] with 1.
         */
        dp[0][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (obstacleGrid[i - 1][j - 1] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }
}
