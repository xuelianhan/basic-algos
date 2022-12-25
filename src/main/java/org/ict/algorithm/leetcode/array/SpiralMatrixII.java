package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

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

    /**
     * x:0, y:1,
     * x:0, y:2,
     * x:0, y:3
     * x:1, y:3
     * x:2, y:3
     * x:3, y:3
     * x:3, y:2
     * x:3, y:1
     * x:3, y:0
     * x:2, y:0
     * x:1, y:0
     * x:1, y:1
     * x:1, y:2
     * x:2, y:2
     * x:2, y:1
     * x:1, y:1
     * [[1,  2, 3, 4],
     * [12, 13, 14, 5],
     * [11, 16, 15, 6],
     * [10,  9, 8, 7]]
     * @param args
     */
    public static void main(String[] args) {
        SpiralMatrixII instance = new SpiralMatrixII();
        int[][] res = instance.generateMatrix(4);
        System.out.println(Arrays.deepToString(res));
    }

    public int[][] generateMatrixV2(int n) {
        return null;
    }

    /**
     * Let us notice one clue property about our spiral matrix:
     * first we need to go to the right and rotate clockwise 90 degrees,
     * then we go down and again when we reached bottom,
     * we rotate 90 degrees clockwise and so on.
     * So, all we need to do is to rotate 90 degrees clockwise when we need:
     *
     * When we reached border of our matrix
     * When we reached cell which is already filled.
     *
     * Let x, y be coordinates on our grid and dx, dy is current direction we need to move.
     * In geometrical sense, rotate by 90 degrees clockwise is written as dx, dy = -dy, dx.
     *
     * Note, that matrix[y][x] is cell with coordinates (x,y), which is not completely obvious.
     *
     * Complexity: time complexity is O(n^2), we process each element once. Space complexity is O(n^2) as well.
     * @see <a href="https://leetcode.com/problems/spiral-matrix-ii/solutions/963128/python-rotate-when-need-explained"></a>
     * @author DBabichev
     * @param n
     * @return
     */
    public int[][] generateMatrixV1(int n) {
        int[][] res = new int[n][n];
        int i = 0, j = 0, di = 0, dj = 1;
        for (int k = 0; k < n * n; k++) {
            res[i][j] = k + 1;
            int x = (i + di) % n;
            int y = (j + dj) % n;
            if (x < 0 || x >= n || y < 0 || y >= n || res[x][y] > 0) {
                int temp = di;
                di = dj;
                dj = -temp;
            }
            i += di;
            j += dj;
        }
        return res;
    }

    /**
     * Time Cost 0ms
     * @author DBabichev
     * @see <a href="https://leetcode.com/problems/spiral-matrix-ii/solutions/963128/python-rotate-when-need-explained"></a>
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        /**
         * (0,1) means move down (x, y + 1)
         * (1,0) means move left (x + 1, y)
         * (0,-1) means move up (x, y - 1)
         * (-1,0) means move right (x - 1, y)
         * the sequence of dir will affect the final result.
         * e.g. you cannot arrange (0, 1) first, then put {-1, 0} afterward.
         * this may incur overflow at res[x][y]
         */
        int[][] dir = {{-1, 0} ,{0, 1}, {1, 0}, {0, -1}};
        int d = 0;
        int start = 1;
        int x = 0, y = 0;
        for (int i = 0; i < n * n; i++) {
            res[x][y] = start++;
            /**
             * Next position waiting to fill.
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
