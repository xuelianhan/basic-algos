package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right,
 * which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * Example 2:
 *
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 * @author sniper
 * @date 31 Dec, 2022
 * LC64
 */
public class MinimumPathSum {


    /**
     * DP Solution from Bottom to Up
     * Time Cost 2ms.
     * @param grid
     * @return
     */
    public int minPathSumV1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[m - 1][n - 1];
    }

    /**
     * Recursion with memorization From Top to Down
     * Time Cost 1ms
     * Time Complexity O(M*N)
     * Space Complexity O(M*N)
     * minSum(x, y) = grid[y][x] + min(minSum(x - 1, y), minSum(x, y - 1));
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        return helper(grid, memo, n - 1, m - 1);
    }

    private int helper(int[][] grid, int[][] memo, int x, int y) {
        if (x == 0 && y == 0) {
            return grid[y][x];
        }
        if (x < 0 || y < 0) {
            return Integer.MAX_VALUE;
        }
        if (memo[y][x] > 0) {
            return memo[y][x];
        }
        int res = grid[y][x] + Math.min(helper(grid, memo, x - 1, y), helper(grid, memo, x, y - 1));
        memo[y][x] = res;
        return res;
    }
}
