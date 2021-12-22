package org.ict.algorithm.leetcode.string;

/**
 * Given an m x n binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 *
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 *
 * Example 3:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 *
 * @author sniper
 * @date 2021/12/17
 * LC221
 */
public class MaximalSquare {

    /**
     * 1)Construct a sum matrix S[R][C] for the given M[R][C].
     *      a)    Copy first row and first columns as it is from M[][] to S[][]
     *      b)    For other entries, use following expressions to construct S[][]
     *          If M[i][j] is 1 then
     *             S[i][j] = min(S[i][j-1], S[i-1][j], S[i-1][j-1]) + 1
     *          Else If M[i][j] is 0
     *          S[i][j]=0
     * 2) Find the maximum entry in S[R][C]
     * 3) Using the value and coordinates of maximum entry in S[i],print sub-matrix ofM[][]
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] aux = new int[m][n];
        /**
         * Copy First row to aux
         */
        for (int j = 0; j < n; j++) {
            aux[0][j] = matrix[0][j];
        }
        /**
         * Copy first column to aux
         */
        for (int i = 0; i < m; i++) {
            aux[i][0] = matrix[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    aux[i][j] = Math.min(aux[i-1][j], Math.min(aux[i][j-1], aux[i-1][j-1])) + 1;
                } else {
                    aux[i][j] = 0;
                }
            }
        }
        int maxSize = 0;
        for (int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (aux[i][j] > maxSize) {
                    maxSize = aux[i][j];
                }
            }
        }
        return maxSize * maxSize;
    }
}
