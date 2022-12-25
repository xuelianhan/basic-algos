package org.ict.algorithm.leetcode.array;

/**
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 * @author sniper
 * @date 24 Dec, 2022
 * LC59
 */
public class SpiralMatrixII {

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        /**
         * up, right, down, left
         */
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0;
        int start = 1;
        int x = 0, y = 0;
        for (int i = 0; i < n * n; i++) {
            res[x][y] = start++;
            /**
             * Next number waiting to fill.
             */
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];
            /**
             * if over the boarder or position has been filled with valid value,
             * we need to change the direction.
             */
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || res[nx][ny] > 0) {
                /**
                 * Change next move direction, and mod can
                 * prevent overflow and reset to zero.
                 *
                 */
                d = (d + 1) % 4;
                nx = x + dir[d][0];
                ny = y + dir[d][1];
            }
            x = nx;
            y = ny;
        }
        return res;
    }
}
