package org.ict.algorithm.leetcode.twopointers;

import java.util.Arrays;

/**
 * You are given an m x n matrix of characters box representing a side-view of a box.
 * Each cell of the box is one of the following:
 * A stone '#'
 * A stationary obstacle '*'
 * Empty '.'
 *
 * The box is rotated 90 degrees clockwise, causing some stones to fall due to gravity.
 * Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box.
 * Gravity does not affect the obstacles' positions,
 * and the inertia from the box's rotation does not affect the stones' horizontal positions.
 * It is guaranteed that each stone in box rests on an obstacle,
 * another stone, or the bottom of the box.
 * Return an n x m matrix representing the box after the rotation described above.
 *
 *
 * Example 1:
 * Input: box = [["#",".","#"]]
 * Output: [["."],
 *          ["#"],
 *          ["#"]]
 *
 * Example 2:
 * Input: box = [["#",".","*","."],
 *               ["#","#","*","."]]
 * Output: [["#","."],
 *          ["#","#"],
 *          ["*","*"],
 *          [".","."]]
 *
 *
 * Example 3:
 * Input: box = [["#","#","*",".","*","."],
 *               ["#","#","#","*",".","."],
 *               ["#","#","#",".","#","."]]
 * Output: [[".","#","#"],
 *          [".","#","#"],
 *          ["#","#","*"],
 *          ["#","*","."],
 *          ["#",".","*"],
 *          ["#",".","."]]
 *
 *
 * Constraints:
 * m == box.length
 * n == box[i].length
 * 1 <= m, n <= 500
 * box[i][j] is either '#', '*', or '.'.
 * @author sniper
 * @date 26 Aug 2023
 * LC1861, Medium, frequency=8
 */
public class RotatingTheBox {

    public char[][] rotateTheBoxV2(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char stone = '#';
        char obstacle = '*';
        char empty = '.';
        /**
         * Notice after rotated 90 degrees clockwise, the
         * result matrix is n X m, not m X n
         */
        char[][] res = new char[n][m];
        for (int i = 0; i < m; i++) {
            /**
             * Putting the initialization of k in the for-loop instead.
             */
            for (int j = n - 1, k = n - 1; j >= 0; j--) {
                if (box[i][j] == obstacle) {
                    k = j - 1;
                }
                if (box[i][j] == stone) {
                    box[i][j] = empty;
                    box[i][k] = stone;
                    k--;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                res[j][m - i - 1] = box[i][j];
            }
        }

        return res;
    }


    /**
     * Time Cost 7ms
     * @param box
     * @return
     */
    public char[][] rotateTheBoxV1(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char stone = '#';
        char obstacle = '*';
        char empty = '.';
        /**
         * Notice after rotated 90 degrees clockwise, the
         * result matrix is n X m, not m X n
         */
        char[][] res = new char[n][m];
        for (int i = 0; i < m; i++) {
            int k = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                if (box[i][j] == obstacle) {
                    k = j - 1;
                }
                if (box[i][j] == stone) {
                    box[i][j] = empty;
                    box[i][k] = stone;
                    k--;
                }
            }
        }

        /**
         * Rotating 90 degrees.
         */
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                res[j][m - i - 1] = box[i][j];
            }
        }

        return res;
    }

    /**
     * Time Cost 10ms
     * '#' represents stone
     * '*' represents obstacle
     * '.' represents empty
     * ---------------------------
     * e.g. box = [["#",".","#"]]
     * @param box
     * @return
     */
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        /**
         * Notice after rotated 90 degrees clockwise, the
         * result matrix is n X m, not m X n
         */
        char[][] res = new char[n][m];
        char obstacle = '*';
        char empty = '.';
        /**
         * Don't forget fill '.' here
         */
        Arrays.stream(res).forEach(row -> Arrays.fill(row, '.'));
        for (int i = 0; i < m; i++) {
            /**
             * Notice the j starts from the tail to head here.
             */
            for (int j = n - 1, k = n - 1; j >= 0; j--) {
                /**
                 * box[i][j] is stone or obstacle.
                 */
                if (box[i][j] != empty) {
                    if (box[i][j] ==  obstacle) {
                        /**
                         * It is guaranteed that each stone in box rests on an obstacle,
                         */
                        k = j;
                    }
                    res[k--][m - i - 1] = box[i][j];
                }
            }
        }
        return res;
    }

}
