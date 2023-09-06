package org.ict.algorithm.leetcode.matrix;

/**
 * In MATLAB,
 * there is a handy function called reshape which can reshape an m x n matrix into a new one with a different size r x c keeping its original data.
 *
 * You are given an m x n matrix mat and two integers r and c representing the number of rows,
 * and the number of columns of the wanted reshaped matrix.
 *
 * The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing order as they were.
 *
 * If the reshape operation with given parameters is possible and legal,
 * output the new reshaped matrix; Otherwise, output the original matrix.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,2],[3,4]], r = 1, c = 4
 * Output: [[1,2,3,4]]
 * Example 2:
 *
 *
 * Input: mat = [[1,2],[3,4]], r = 2, c = 4
 * Output: [[1,2],[3,4]]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * -1000 <= mat[i][j] <= 1000
 * 1 <= r, c <= 300
 * @author sniper
 * @date 05 Sep 2023
 * LC566, Easy
 */
public class ReshapeTheMatrix {

    /**
     * Time Cost 0ms
     * ----------------------------------------
     * class Solution:
     *     def matrixReshape(self, mat: List[List[int]], r: int, c: int) -> List[List[int]]:
     *         if mat == [] or r * c != len(mat) * len(mat[0]):
     *             return mat
     *
     *         res = [[0 for j in range(c)] for i in range(r)]
     *         k = 0
     *
     *         for row in mat:
     *             for num in row:
     *                 res[k // c][k % c] = num
     *                 k += 1
     *
     *         return res
     * ---------------------------------------
     * @param mat
     * @param r
     * @param c
     * @return
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if (mat == null || mat.length == 0) {
            return mat;
        }

        int m = mat.length;
        int n = mat[0].length;
        if (r * c != m * n) {
            return mat;
        }

        int[][] res = new int[r][c];
        int k = 0;
        for (int[] row : mat) {
            for (int num : row) {
                res[k / c][k % c] = num;
                k++;
            }
        }
        return res;
    }
}
