package org.ict.algorithm.leetcode.array;

/**
 * Given a square matrix mat, return the sum of the matrix diagonals.
 *
 * Only include the sum of all the elements on the primary diagonal,
 * and all the elements on the secondary diagonal that are not part of the primary diagonal.
 *
 * Example 1:
 * Input: mat = [[1,2,3],
 *               [4,5,6],
 *               [7,8,9]]
 * Output: 25
 * Explanation: Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
 * Notice that element mat[1][1] = 5 is counted only once.
 *
 * Example 2:
 * Input: mat = [[1,1,1,1],
 *               [1,1,1,1],
 *               [1,1,1,1],
 *               [1,1,1,1]]
 * Output: 8
 * Example 3:
 * Input: mat = [[5]]
 * Output: 5
 *
 *
 * Constraints:
 * n == mat.length == mat[i].length
 * 1 <= n <= 100
 * 1 <= mat[i][j] <= 100
 * @author sniper
 * @date 08 May 2023
 * LC1572, Easy
 */
public class MatrixDiagonalSum {

    /**
     * class Solution:
     *     def diagonalSum(self, mat: List[List[int]]) -> int:
     *         res = 0
     *         n = len(mat)
     *         for i in range(n):
     *             res += mat[i][i]
     *             res += mat[n - i - 1][i]
     *         if n % 2 != 0:
     *             mid = n // 2
     *             res -= mat[mid][mid]
     *         return res
     * -----------------------------------------------------
     * class Solution {
     * public:
     *     int diagonalSum(vector<vector<int>>& mat) {
     *         int res = 0;
     *         int n = mat.size();
     *         for (int i = 0; i < n; i++) {
     *             res += mat[i][i];
     *             res += mat[n - i - 1][i];
     *         }
     *         if (n % 2 != 0) {
     *             int mid = n / 2;
     *             res -= mat[mid][mid];
     *         }
     *         return res;
     *     }
     * };
     * @param mat
     * @return
     */
    public int diagonalSumV1(int[][] mat) {
        int res = 0;
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            res += mat[i][i];
            res += mat[n - i - 1][i];
        }

        /**
         * Even length matrix has no crossed central element.
         * Only Odd length matrix has crossed central element.
         */
        if (n % 2 != 0) {
            int mid = n / 2;
            res -= mat[mid][mid];
        }
        return res;
    }

    /**
     * e.g. mat:
     * [[1,1,1,1],
     *  [1,1,1,1],
     *  [1,1,1,1],
     *  [1,1,1,1]]
     * @param mat
     * @return
     */
    public int diagonalSum(int[][] mat) {
        int res = 0;
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            res += mat[i][i];
        }

        for (int i = n - 1; i >= 0; i--) {
            res += mat[n - i - 1][i];
        }
        /**
         * Even length matrix has no crossed central element.
         * Only Odd length matrix has crossed central element.
         */
        if (n % 2 != 0) {
            int mid = n / 2;
            res -= mat[mid][mid];
        }
        return res;
    }

}
