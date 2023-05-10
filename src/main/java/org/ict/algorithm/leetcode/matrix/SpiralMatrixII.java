package org.ict.algorithm.leetcode.matrix;

import java.util.Arrays;

/**
 * Given a positive integer n,
 * generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 *
 * Example 1:
 * Input: n = 3
 * Output: [[1,2,3],
 *          [8,9,4],
 *          [7,6,5]]
 *
 * Example 2:
 * Input: n = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 * 1 <= n <= 20
 *
 * @author sniper
 * @date 24 Dec, 2022
 * LC59, Medium, frequency=6
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

    /**
     * Time Cost 0ms
     * @param n
     * @return
     */
    public int[][] generateMatrixV5(int n) {
        int[][] res = new int[n][n];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0;
        int x = 0;
        int y = 0;
        int start = 1;
        while (start <= n * n) {
            res[x][y] = start++;
            /**
             * todo
             */
            int nx = Math.floorMod(x + dir[d][0], n);
            int ny = Math.floorMod(y + dir[d][1], n);
            if (res[nx][ny] != 0) {
                d = (d + 1) % 4;
            }
            x += dir[d][0];
            y += dir[d][1];
        }
        return res;
    }

    /**
     * Understanding the following Solution.
     * Time Cost 0ms
     * @param n
     * @return
     */
    public int[][] generateMatrixV4(int n) {
        int[][] res = new int[n][n];
        int x = 0;
        int y = 0;
        /**
         * start point (0, 1)
         */
        int dx = 0;
        int dy = 1;
        for (int i = 0; i < n * n; i++) {
            res[x][y] = i + 1;
            int nx = (x + dx) % n;
            int ny = (y + dy) % n;
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || res[nx][ny] != 0) {
                int temp = dx;
                dx = dy;
                dy = -temp;
                nx = (x + dx) % n;
                ny = (y + dy) % n;
            }
            x = nx;
            y = ny;
        }
        return res;
    }

    /**
     * Understanding the following Solution.
     * Time Cost 0ms
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
     * In geometrical sense, rotate by 90 degrees clockwise is written as dx, dy = -dy, dx in the python.
     * (x, y) --> rotate 90 degrees --> (y, -x)
     * Note, that matrix[y][x] is cell with coordinates (x,y), which is not completely obvious.
     *
     * Complexity: time complexity is O(n^2), we process each element once. Space complexity is O(n^2) as well.
     * @see <a href="https://leetcode.com/problems/spiral-matrix-ii/solutions/963128/python-rotate-when-need-explained"></a>
     * @author DBabichev
     * @param n
     * @return
     */
    public int[][] generateMatrixV3(int n) {
        int[][] res = new int[n][n];
        int x = 0;
        int y = 0;
        /**
         * start point (0, 1)
         */
        int dx = 0;
        int dy = 1;
        for (int k = 0; k < n * n; k++) {
            res[x][y] = k + 1;
            int nx = (x + dx) % n;
            int ny = (y + dy) % n;
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || res[nx][ny] > 0) {
                /**
                 * Rotate 90 degrees:(x, y) = (y, -x)
                 */
                int temp = dx;
                dx = dy;
                dy = -temp;
            }
            x += dx;
            y += dy;
        }
        return res;
    }

    /**
     * Understanding the following Solution.
     * ------------------------------------------------------
     * class Solution:
     *     def generateMatrix(self, n: int) -> List[List[int]]:
     *         dir = [[0, 1], [1, 0], [0, -1], [-1, 0]]
     *         res = [[0] * n for _ in range(0, n)]
     *         x = y = d= 0
     *         for i in range(1, n * n + 1):
     *             res[x][y] = i
     *             nx, ny = x + dir[d][0], y + dir[d][1]
     *             if nx < 0 or nx >= n or ny < 0 or ny >= n or res[nx][ny] != 0:
     *                 d = (d + 1) % 4
     *                 nx, ny = x + dir[d][0], y + dir[d][1]
     *             x, y = nx, ny
     *         return res
     * -----------------------------------------------------
     * class Solution:
     *     def generateMatrix(self, n: int) -> List[List[int]]:
     *         res = [[0] * n for _ in range(n)]
     *         dirs = ((0, 1), (1, 0), (0, -1), (-1, 0))
     *         i = j = k = 0
     *         for v in range(1, n * n + 1):
     *             res[i][j] = v
     *             x, y = i + dirs[k][0], j + dirs[k][1]
     *             if x < 0 or y < 0 or x >= n or y >= n or res[x][y]:
     *                 k = (k + 1) % 4
     *                 x, y = i + dirs[k][0], j + dirs[k][1]
     *             i, j = x, y
     *         return res
     * @param n
     * @return
     */
    public int[][] generateMatrixV2(int n) {
        int[][] res = new int[n][n];
        /**
         * Notice the sequence of the direction.
         * right -->down --> left --> up
         * {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}
         * the first one can swap with the last one, because
         * we check the bound later, once it's out of bound
         * we move direction to the next element.
         */
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < n * n; i++) {
            res[x][y] = i + 1;
            int nx = (x + dir[d][0]);
            int ny = (y + dir[d][1]);
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || res[nx][ny] != 0) {
                d = (d + 1) % 4;
                nx = (x + dir[d][0]);
                ny = (y + dir[d][1]);
            }
            x = nx;
            y = ny;
        }
        return res;
    }

    /**
     * Understanding the following Solution.
     * @param n
     * @return
     */
    public int[][] generateMatrixV1(int n) {
        int[][] res = new int[n][n];
        /**
         * the sequence of dir will affect the final result.
         * e.g. you cannot arrange (0, 1) first, then put {-1, 0} afterward.
         * this may incur overflow at res[x][y].
         * Why? the transfer must rotate 90 degrees.
         * (0, 1) --> (-1, 0) is not a clockwise 90 degrees rotate.
         */
        int[][] dir = {{-1, 0} ,{0, 1}, {1, 0}, {0, -1}};
        /**
         * index of which element to choose.
         */
        int d = 0;
        int x = 0, y = 0;
        for (int i = 0; i < n * n; i++) {
            /**
             * Use the index-i directly.
             */
            res[x][y] = i + 1;
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
                 * d + 1 means to move to the next position.
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



    /**
     * Understanding the following Solution.
     * Time Cost 0ms
     * @author DBabichev
     * @see <a href="https://leetcode.com/problems/spiral-matrix-ii/solutions/963128/python-rotate-when-need-explained"></a>
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        /**
         * {-1, 0}, move up
         * {0, 1}, move right
         * {1, 0}, move down
         * {0, -1}, move left
         *
         * the sequence of dir will affect the final result.
         * e.g. you cannot arrange (0, 1) first, then put {-1, 0} afterward.
         * this may incur overflow at res[x][y].
         * Why? the transfer must rotate 90 degrees.
         * (0, 1) --> (-1, 0) is not a clockwise 90 degrees rotate.
         *
         * The following sequence can be replaced with:
         * {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
         */
        int[][] dir = {{-1, 0} ,{0, 1}, {1, 0}, {0, -1}};
        /**
         * index of which element to choose.
         */
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
                 * d + 1 means to move to the next position.
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
