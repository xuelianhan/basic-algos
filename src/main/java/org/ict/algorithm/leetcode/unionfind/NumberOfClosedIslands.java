package org.ict.algorithm.leetcode.unionfind;

/**
 * Given a 2D grid consists of 0s (land) and 1s (water).
 * An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally
 * (all left, top, right, bottom) surrounded by 1s.
 * Return the number of closed islands.
 *
 * Example 1:
 * Input: grid = [[1,1,1,1,1,1,1,0],
 *                [1,0,0,0,0,1,1,0],
 *                [1,0,1,0,1,1,1,0],
 *                [1,0,0,0,0,1,0,1],
 *                [1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 *
 * Example 2:
 * Input: grid = [[0,0,1,0,0],
 *                [0,1,0,1,0],
 *                [0,1,1,1,0]]
 * Output: 1
 *
 * Example 3:
 * Input: grid = [[1,1,1,1,1,1,1],
 *                [1,0,0,0,0,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,1,0,1,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,0,0,0,0,1],
 *                [1,1,1,1,1,1,1]]
 * Output: 2
 *
 * Constraints:
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 * @author sniper
 * @date 06 Apr, 2023
 * LC1254, Medium
 */
public class NumberOfClosedIslands {


    public int closedIslandV2(int[][] grid) {
        //todo
        return 0;
    }

    public int closedIslandV1(int[][] grid) {
        //todo
        return 0;
    }

    /**
     * Depth-First-Search Solution
     * Time Cost 1ms
     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                /**
                 * 0 is land, so 0 is not island totally.
                 * 1 is water
                 * So we only need to check whether a land is island or not, we don't need to check a grid of water.
                 */
                if (grid[i][j] == 0) {
                    res += dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    /**
     * Check grid[i][j] is island or not
     * return 0 if it's not island, otherwise 1 if it's island.
     * @param grid
     * @param i
     * @param j
     * @return 0 if it's not island, 1 if it's island
     */
    private int dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if ( i < 0 || i >=m || j < 0 || j >= n) {
            return 0;
        }
        /**
         * while grid[i][j] greater than 0,
         * this grid[i][j] may be water, or may have been visited(marked as 2 below).
         */
        if (grid[i][j] > 0) {
            return 1;
        }
        /**
         * Mark grid[i][j] has been visited, using 2.
         */
        grid[i][j] = 2;
        /**
         * Multiply operation here can be replaced with bit | or &.
         */
        return dfs(grid, i + 1, j) * dfs(grid, i - 1, j) * dfs(grid, i, j + 1) * dfs(grid, i, j - 1);
    }
}
