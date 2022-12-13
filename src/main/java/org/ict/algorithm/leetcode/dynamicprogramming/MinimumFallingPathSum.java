package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 *
 * A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right.
 * Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * Output: 13
 * Explanation: There are two falling paths with a minimum sum as shown.
 * Example 2:
 *
 *
 * Input: matrix = [[-19,57],[-40,-5]]
 * Output: -59
 * Explanation: The falling path with a minimum sum is shown.
 *
 *
 * Constraints:
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 * @author sniper
 * @date 13 Dec, 2022
 * LC931
 */
public class MinimumFallingPathSum {

    public int minFallingPathSumV3(int[][] matrix) {
        return 0;
    }

    public int minFallingPathSumV2(int[][] matrix) {
        return 0;
    }


    public int minFallingPathSumV1(int[][] matrix) {
        return 0;
    }

    /**
     * Time Cost 35ms
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        int n = matrix.length;
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; ++j) {
                int pre = matrix[i - 1][j];
                if (j > 0) {
                    pre = Math.min(pre, matrix[i - 1][j - 1]);
                }
                if (j < n - 1) {
                    pre = Math.min(pre, matrix[i - 1][j + 1]);
                }
                matrix[i][j] += pre;
                if (i == n - 1) {
                    res = Math.min(res, matrix[i][j]);
                }
            }
        }
        return res;
    }
}
