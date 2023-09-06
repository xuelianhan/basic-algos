package org.ict.algorithm.leetcode.math;

/**
 * You are given an n x n grid where we place some 1 x 1 x 1 cubes that are axis-aligned with the x, y, and z axes.
 * Each value v = grid[i][j] represents a tower of v cubes placed on top of the cell (i, j).
 * We view the projection of these cubes onto the xy, yz, and zx planes.
 * A projection is like a shadow, that maps our 3-dimensional figure to a 2-dimensional plane.
 * We are viewing the "shadow" when looking at the cubes from the top, the front, and the side.
 *
 * Return the total area of all three projections.
 *
 * Example 1:
 * Input: grid = [[1,2],[3,4]]
 * Output: 17
 * Explanation: Here are the three projections ("shadows") of the shape made with each axis-aligned plane.
 *
 * Example 2:
 * Input: grid = [[2]]
 * Output: 5
 *
 * Example 3:
 * Input: grid = [[1,0],[0,2]]
 * Output: 8
 *
 *
 * Constraints:
 * n == grid.length == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] <= 50
 * @author sniper
 * @date 05 Sep 2023
 * LC883, Easy
 */
public class ProjectionAreaOf3DShapes {

    /**
     * Time Cost 2ms
     * @param grid
     * @return
     */
    public int projectionAreaV1(int[][] grid) {
        int res = 0;
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            int rowMax = 0;
            int colMax = 0;
            for (int j = 0; j < n; j++) {
                rowMax = Math.max(rowMax, grid[i][j]);
                colMax = Math.max(colMax, grid[j][i]);
                if (grid[i][j] > 0) {
                    res++;
                }
            }
            res += rowMax + colMax;
        }
        return res;
    }

    /**
     * Time Cost 2ms
     * @param grid
     * @return
     */
    public int projectionArea(int[][] grid) {
        int res = 0;
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            int rowMax = 0;
            int colMax = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    res++;
                }
                rowMax = Math.max(rowMax, grid[i][j]);
                colMax = Math.max(colMax, grid[j][i]);
            }
            res += rowMax + colMax;
        }
        return res;
    }
}
