package org.ict.algorithm.leetcode.binarysearch;

/**
 * Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise,
 * return the number of negative numbers in grid.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[4, 3, 2, -1],
 *                [3, 2, 1, -1],
 *                [1, 1,-1, -2],
 *                [-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negatives number in the matrix.
 * Example 2:
 *
 * Input: grid = [[3,2],[1,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * -100 <= grid[i][j] <= 100
 *
 *
 * Follow up: Could you find an O(n + m) solution?
 *
 * More Hard Problem is LC378
 * @author sniper
 * @date 26 Nov, 2022
 * LC1351
 */
public class CountNegativeNumbersInSortedMatrix {


    /**
     * Time cost 0ms
     * @param grid
     * @return
     */
    public int countNegativesV1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[m-1][n-1] > 0) {
            return 0;
        }
        if (grid[0][0] < 0) {
            return (m * n);
        }

        /**
         * Start from left-down-corner.
         * i = m - 1, j = 0
         */
        int cnt = 0, i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (grid[i][j] < 0) {
                /**
                 *  sorted in non-increasing order both row-wise and column-wise.
                 *  Move up.
                 */
                cnt += (n  - j);
                i--;
            } else if (grid[i][j] >= 0) {
                /**
                 * Move right.
                 */
                j++;
            }
        }
        return cnt;
    }

    /**
     * Brute-Force Solution
     * @param grid
     * @return
     */
    public int countNegatives(int[][] grid) {
        int cnt = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] < 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
