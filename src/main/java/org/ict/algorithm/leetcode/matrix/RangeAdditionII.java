package org.ict.algorithm.leetcode.matrix;

/**
 * You are given an m x n matrix M initialized with all 0's and an array of operations ops,
 * where ops[i] = [ai, bi] means M[x][y] should be incremented by one for all 0 <= x < ai and 0 <= y < bi.
 *
 * Count and return the number of maximum integers in the matrix after performing all the operations.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 3, ops = [[2,2],[3,3]]
 * Output: 4
 * Explanation: The maximum integer in M is 2, and there are four of it in M. So return 4.
 * Example 2:
 *
 * Input: m = 3, n = 3, ops = [[2,2],[3,3],[3,3],[3,3],[2,2],[3,3],[3,3],[3,3],[2,2],[3,3],[3,3],[3,3]]
 * Output: 4
 * Example 3:
 *
 * Input: m = 3, n = 3, ops = []
 * Output: 9
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 4 * 10^4
 * 0 <= ops.length <= 10^4
 * ops[i].length == 2
 * 1 <= ai <= m
 * 1 <= bi <= n
 * @author sniper
 * @date 05 Sep 2023
 * LC598, Easy
 */
public class RangeAdditionII {

    /**
     * Time Cost 0ms
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCountV1(int m, int n, int[][] ops) {
        int row = m;
        int col = n;
        for(int[] op : ops) {
            row = Math.min(row, op[0]);
            col = Math.min(col, op[1]);
        }

        return row * col;
    }

    /**
     * Time Cost 0ms
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCount(int m, int n, int[][] ops) {
        if (ops == null || ops.length == 0) {
            return m * n;
        }

        int row = Integer.MAX_VALUE;
        int col = Integer.MAX_VALUE;
        for(int[] op : ops) {
            row = Math.min(row, op[0]);
            col = Math.min(col, op[1]);
        }

        return row * col;
    }

}
