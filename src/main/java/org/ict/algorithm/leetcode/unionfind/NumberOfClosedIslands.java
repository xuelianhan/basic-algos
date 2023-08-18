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

    private final int land = 0;

    private final int water = 1;

    /**
     * Depth-First-Search Solution
     * Time Cost 1ms
     * @param grid
     * @return
     */
    public int closedIslandV2(int[][] grid) {
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
                if (grid[i][j] == land) {
                    res += dfsV2(grid, i, j);
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
    private int dfsV2(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if ( i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }
        /**
         * while grid[i][j] greater than 0,
         * this grid[i][j] may be water, or may have been visited(marked as 2 below).
         */
        if (grid[i][j] > land) {
            return 1;
        }
        /**
         * Mark grid[i][j] has been visited, using 2.
         */
        grid[i][j] = 2;
        /**
         * Multiply operation here can be replaced with bit | or &.
         * But it cannot be replaced with &&(logical and)
         */
        return dfsV2(grid, i + 1, j) * dfsV2(grid, i - 1, j) * dfsV2(grid, i, j + 1) * dfsV2(grid, i, j - 1);
    }

    /**
     * Similar with closedIsland.
     * Time Cost 2ms
     * @param grid
     * @return
     */
    public int closedIslandV1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        /**
         * Remove lands connected to edge
         */
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i * j == 0 || i == m - 1 || j == n - 1) {
                    /**
                     * 0 is land, so 0 is not island totally.
                     * 1 is water
                     */
                    if (grid[i][j] == 0) {
                        dfsV1(grid, i, j);
                    }
                }
            }
        }

        /**
         * Reduce to 200. Number of Islands
         */
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    dfsV1(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfsV1(int[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length) {
            return;
        }
        /**
         * grid[i][j] != 0 is OK here.
         */
        if (grid[i][j] == 1) {
            return;
        }
        grid[i][j] = 1;
        dfsV1(grid, i + 1, j);
        dfsV1(grid, i - 1, j);
        dfsV1(grid, i, j + 1);
        dfsV1(grid, i, j - 1);
    }

    /**
     * Time Cost 2ms
     * First find and mark all the connected regions that are connected to the boundary,
     * so that any connected region found later must be an island.
     * So first traverse the array once, encounter land on the boundary,
     * then start DFS traversal, and mark the connected region,
     * after completion, traverse the array again,
     * encounter land on the boundary,
     * then start DFS traversal,
     * and mark the connected region,
     * at this time, after finding a connected region,
     * we can increase the number of islands
     * @param grid
     * @return
     */
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * Marking all the lands connected to edges as '2'.
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                /**
                 * 0 is land, so 0 is not island totally.
                 * 1 is water
                 */
                if ((i * j == 0 || i == m - 1 || j == n - 1) && grid[i][j] == 0) {
                    dfsV0(grid, i, j);
                }
            }
        }

        /**
         * Reduce to LC200.Number of Islands
         */
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                /**
                 * 0 is land, 1 is water, 2 is the lands connected to edges which have been visited.
                 * grid[i][j] != 0 means the current cell(i,j) is water, or it has been visited.
                 * We don't need to care it, so skip directly.
                 */
                if (grid[i][j] != 0) {
                    continue;
                }
                dfsV0(grid, i, j);
                res++;
            }
        }

        return res;
    }

    private void dfsV0(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 0) {
            return;
        }
        grid[i][j] = '2';
        dfsV0(grid, i + 1, j);
        dfsV0(grid, i - 1, j);
        dfsV0(grid, i, j + 1);
        dfsV0(grid, i, j - 1);
    }
}
