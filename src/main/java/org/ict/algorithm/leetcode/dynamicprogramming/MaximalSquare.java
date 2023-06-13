package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * Given an m x n binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 *
 * Example 1:
 * Input: matrix = [["1","0","1","0","0"],
 *                  ["1","0","1","1","1"],
 *                  ["1","1","1","1","1"],
 *                  ["1","0","0","1","0"]]
 * Output: 4
 *
 * Example 2:
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 *
 * Example 3:
 * Input: matrix = [["0"]]
 * Output: 0
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 *
 * @author sniper
 * @date 13 Jun 2023
 * LC221, Medium, frequency=14
 */
public class MaximalSquare {


    /**
     * 1-Dimension Dynamic Programming
     * @param matrix
     * @return
     */
    public int maximalSquareV2(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n];
        int maxLength = 0;
        /**
         * dp[i - 1][j - 1]
         */
        int prev = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cache = dp[j];
                if (i == 0 || j == 0 || matrix[i][j] == '0') {
                    dp[j] = (matrix[i][j] == '1' ? 1 : 0);
                } else {
                    dp[j] = Math.min(prev, Math.min(dp[j], dp[j - 1])) + 1;
                }
                maxLength = Math.max(maxLength, dp[j]);
                prev = cache;
            }
        }

        return maxLength * maxLength;
    }

    /**
     * 2-Dimension Dynamic Programming
     * Time Cost 7ms
     * @param matrix
     * @return
     */
    public int maximalSquareV1(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxLength = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || matrix[i][j] == '0') {
                    dp[i][j] = (matrix[i][j] == '1' ? 1 : 0);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength * maxLength;
    }


    /**
     * Time Cost 3ms
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        /**
         * number of rows
         */
        int rows = matrix.length;

        /**
         * number of columns
         */
        int cols = matrix[0].length;
        int[][] aux = new int[rows][cols];
        /**
         * Copy First row to aux
         */
        for (int j = 0; j < cols; j++) {
            //Converting char to int value, such as '1' to 1
            aux[0][j] = matrix[0][j] - '0';
        }
        /**
         * Copy first column to aux
         */
        for (int i = 0; i < rows; i++) {
            //Converting char to int value, such as '1' to 1
            aux[i][0] = matrix[i][0]  - '0';
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    aux[i][j] = Math.min(aux[i - 1][j], Math.min(aux[i][j - 1], aux[i - 1][j - 1])) + 1;
                } else {
                    aux[i][j] = 0;
                }
            }
        }
        int maxSize = 0;
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if (aux[i][j] > maxSize) {
                    maxSize = aux[i][j];
                }
            }
        }
        return maxSize * maxSize;
    }
}
