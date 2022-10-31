package org.ict.algorithm.leetcode.array;

/**
 * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
 *
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * Output: true
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 * Example 2:
 *
 *
 * Input: matrix = [[1,2],[2,2]]
 * Output: false
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 20
 * 0 <= matrix[i][j] <= 99
 *
 *
 * Follow up:
 *
 * What if the matrix is stored on disk,
 * and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
 * What if the matrix is so large that you can only load up a partial row into the memory at once?
 *
 * @author sniper
 * @date 31 Oct, 2022
 * LC766
 */
public class ToeplitzMatrix {


    /**
     * follow up.
     * todo
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrixV2(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return true;
        }
        //todo
        return true;
    }

    /**
     * matrix = [
     *           [1,2,3,4],
     *           [5,1,2,3],
     *           [9,5,1,2]
     *          ]
     * i:0, j:0, mat[0][0] == mat[1][1] == 1
     *      j:1, mat[0][1] == mat[1][2] == 2
     *      j:2, mat[0][2] == mat[1][3] == 3
     * i:1, j:0, mat[1][0] == mat[2][1] == 5
     *      j:1, mat[1][1] == mat[2][2] == 1
     *      j:2, mat[1][2] == mat[2][3] == 2
     *
     * @param matrix
     * @return
     */
    public boolean isToeplitzMatrixV1(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        /**
         * Check top-triangle
         */
        boolean res = true;
        for (int col = 0; col < n; col++) {
            res = checkDiagonalLine(matrix, 0, col, m, n);
            if (res == false) {
                return res;
            }
        }

        /**
         * Check bottom-triangle
         * row = 1 due to diagonal line has been checked in the above top-triangle for-loop(row=0)
         */
        for (int row = 1; row < m; row++) {
            res = checkDiagonalLine(matrix, row, 0, m, n);
            if (res == false) {
                return res;
            }
        }
        return true;
    }

    public boolean checkDiagonalLine(int[][] mat, int row, int col, int m, int n) {
        int r = row, c = col;
        int cur = mat[r][c];
        while (r < m && c < n) {
            if (cur != mat[r][c]) {
                return false;
            }
            r++;
            c++;
        }
        return true;
    }

}
