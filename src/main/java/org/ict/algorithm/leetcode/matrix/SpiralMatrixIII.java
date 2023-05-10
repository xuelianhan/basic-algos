package org.ict.algorithm.leetcode.matrix;

import java.util.Arrays;

/**
 * You start at the cell (rStart, cStart) of a rows x cols grid facing east.
 * The northwest corner is at the first row and column in the grid,
 * and the southeast corner is at the last row and column.
 * You will walk in a clockwise spiral shape to visit every position in this grid.
 * Whenever you move outside the grid's boundary,
 * we continue our walk outside the grid (but may return to the grid boundary later.).
 * Eventually, we reach all rows * cols spaces of the grid.
 * Return an array of coordinates representing the positions of the grid in the order you visited them.
 *
 * Example 1:
 * Input: rows = 1, cols = 4, rStart = 0, cStart = 0
 * Output: [[0,0],[0,1],[0,2],[0,3]]
 * Example 2:
 *
 *
 * Input: rows = 5, cols = 6, rStart = 1, cStart = 4
 * Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
 *
 *
 * Constraints:
 *
 * 1 <= rows, cols <= 100
 * 0 <= rStart < rows
 * 0 <= cStart < cols
 * @author sniper
 * @date 09 May 2023
 * LC885, Medium, frequency=2
 */
public class SpiralMatrixIII {

    public static void main(String[] args){
        int rows = 5, cols = 6, rStart = 1, cStart = 4;
        SpiralMatrixIII instance = new SpiralMatrixIII();
        int[][] res = instance.spiralMatrixIIIV4(rows, cols, rStart, cStart);
        System.out.println(Arrays.deepToString(res));
    }

    /**
     * Understanding the following solution
     * Time Cost 4ms
     * -------------------
     * class Solution:
     *     def spiralMatrixIII(self, rows: int, cols: int, rStart: int, cStart: int) -> List[List[int]]:
     *         rowCnt = rows * cols
     *         res = [[rStart, cStart]]
     *         x, y, cnt, i = 0, 1, 1, 0
     *         while cnt < rowCnt:
     *             for k in range(0, i // 2 + 1):
     *                 rStart += x
     *                 cStart += y
     *                 if rStart >=0 and rStart < rows and cStart >= 0 and cStart < cols:
     *                     res.append([rStart, cStart])
     *                     cnt += 1
     *             x, y = y, -x
     *             i += 1
     *         return res
     * ---------------------------------------------
     * class Solution:
     *     def spiralMatrixIII(self, rows: int, cols: int, x: int, y: int) -> List[List[int]]:
     *         res = []
     *         dx, dy, n = 0, 1, 0
     *         while len(res) < rows * cols:
     *             for i in range(n // 2 + 1):
     *                 if 0 <= x < rows and 0 <= y < cols:
     *                     res.append([x, y])
     *                 x, y = x + dx, y + dy
     *             dx, dy, n = dy, -dx, n + 1
     *         return res
     * ----------------------------------------------
     * @param rows
     * @param cols
     * @param rStart
     * @param cStart
     * @return
     */
    public int[][] spiralMatrixIIIV5(int rows, int cols, int rStart, int cStart) {
        int rowCnt = rows * cols;
        int[][] res = new int[rowCnt][2];
        //int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        res[0] = new int[]{rStart, cStart};
        int x = 0, y = 1, temp = 0;
        int cnt = 1;
        for (int i = 0; cnt < rowCnt; i++) {
            for (int k = 0; k < i / 2 + 1; k++) {
                rStart += x;
                cStart += y;
                if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                    res[cnt++] = new int[]{rStart, cStart};
                }
            }
            //d = (d + 1) % 4;
            temp = x;
            x = y;
            y = -temp;
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Time Cost 5ms
     * d has 4 values:[0, 1, 2, 3]
     * i:0, d:0, step:1
     * i:1, d:1, step:1
     * i:2, d:2, step:2
     * i:3, d:3, step:2
     * i:
     * i:4, d:0, step:3
     * i:5, d:1, step:3
     * i:6, d:2: step:4
     * i:7, d:3, step:4
     * i:
     * i:8, d:0, step:5
     * i:9, d:1, step:5
     * i:10, d:2: step:6
     * i:11, d:3, step:6
     * ......
     * So the step = n / 2 + 1, n >= 0
     * @param rows
     * @param cols
     * @param rStart
     * @param cStart
     * @return
     */
    public int[][] spiralMatrixIIIV4(int rows, int cols, int rStart, int cStart) {
        int rowCnt = rows * cols;
        int[][] res = new int[rowCnt][2];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        res[0] = new int[]{rStart, cStart};
        int x = rStart, y = cStart;
        int d = 0;
        int cnt = 1;
        for (int i = 0; cnt < rowCnt; i++) {
            for (int k = 0; k < i / 2 + 1; k++) {
                x += dir[d][0];
                y += dir[d][1];
                if (x >= 0 && x < rows && y >= 0 && y < cols) {
                    res[cnt++] = new int[]{x, y};
                }
            }
            d = (d + 1) % 4;
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Time Cost 4ms
     * @param rows
     * @param cols
     * @param rStart
     * @param cStart
     * @return
     */
    public int[][] spiralMatrixIIIV3(int rows, int cols, int rStart, int cStart) {
        int rowCnt = rows * cols;
        int[][] res = new int[rowCnt][2];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        res[0] = new int[]{rStart, cStart};
        int x = rStart, y = cStart;
        int d = 0, step = 0;
        int cnt = 1;
        for (; cnt < rowCnt;) {
            if (d == 0 || d == 2) {
                step++;
            }
            for (int k = 0; k < step; k++) {
                x += dir[d][0];
                y += dir[d][1];
                if (x >= 0 && x < rows && y >= 0 && y < cols) {
                    res[cnt++] = new int[]{x, y};
                }
            }
            d = (d + 1) % 4;
        }
        return res;
    }

    /**
     * Time Cost 4ms
     * @param rows
     * @param cols
     * @param rStart
     * @param cStart
     * @return
     */
    public int[][] spiralMatrixIIIV2(int rows, int cols, int rStart, int cStart) {
        int rowCnt = rows * cols;
        int[][] res = new int[rowCnt][2];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        res[0] = new int[]{rStart, cStart};
        int x = rStart, y = cStart;
        int d = 0, step = 0;
        int cnt = 1;
        for (; cnt < rowCnt;) {
            if (d == 0 || d == 2) {
                step++;
            }
            for (int k = 0; k < step; k++) {
                x = x + dir[d][0];
                y = y + dir[d][1];
                if (x >= 0 && x < rows && y >= 0 && y < cols) {
                    res[cnt++] = new int[]{x, y};
                }
            }
            d = (d + 1) % 4;
        }
        return res;
    }

    /**
     * Time Cost 5ms
     * @param rows
     * @param cols
     * @param rStart
     * @param cStart
     * @return
     */
    public int[][] spiralMatrixIIIV1(int rows, int cols, int rStart, int cStart) {
        int[][] res = new int[rows * cols][2];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        res[0] = new int[]{rStart, cStart};
        int x = rStart, y = cStart;
        int d = 0, step = 0;
        int cnt = 1;
        for (; cnt < rows * cols;) {
            /**
             * We need to increment the step while we move left or right.
             */
            if (d == 0 || d == 2) {
                step++;
            }
            for (int k = 0; k < step; k++) {
                x = x + dir[d][0];
                y = y + dir[d][1];
                if (x >= 0 && x < rows && y >= 0 && y < cols) {
                    res[cnt] = new int[]{x, y};
                    cnt++;
                }
            }
            d = (d + 1) % 4;
        }
        return res;
    }


    /**
     * Time Cost 5ms
     *
     * @param rows
     * @param cols
     * @param rStart
     * @param cStart
     * @return
     */
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        Pair[] res = new Pair[rows * cols];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        res[0] = new Pair(rStart, cStart);
        int x = rStart, y = cStart;
        int d = 0, step = 0;
        /**
         * We have put [rStart, cStart] into the res at first,
         * so we start from 1 here.
         */
        int cnt = 1;
        for (; cnt < rows * cols;) {
            /**
             * We need to increment the step while we move left or right.
             */
            if (d == 0 || d == 2) {
                step++;
            }
            for (int k = 0; k < step; k++) {
                x = x + dir[d][0];
                y = y + dir[d][1];
                if (x >= 0 && x < rows && y >= 0 && y < cols) {
                    res[cnt] = new Pair(x, y);
                    /**
                     * Increment cnt only at giving the value to res[cnt].
                     */
                    cnt++;
                }
            }
            d = (d + 1) % 4;
        }

        int[][] list = new int[cnt][2];
        for (int k = 0; k < cnt; k++) {
            list[k] = new int[] {res[k].x, res[k].y};
        }
        return list;
    }

    static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
