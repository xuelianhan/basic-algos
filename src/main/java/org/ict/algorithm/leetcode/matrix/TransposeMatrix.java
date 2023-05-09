package org.ict.algorithm.leetcode.matrix;

/**
 * Given a 2D integer array matrix, return the transpose of matrix.
 * The transpose of a matrix is the matrix flipped over its main diagonal,
 * switching the matrix's row and column indices.
 *
 * Example 1:
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[1,4,7],[2,5,8],[3,6,9]]
 * Example 2:
 *
 * Input: matrix = [[1,2,3],[4,5,6]]
 * Output: [[1,4],[2,5],[3,6]]
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 10^5
 * -10^9 <= matrix[i][j] <= 10^9
 * @author sniper
 * @date 09 May 2023
 * LC867, Easy, frequency=2
 */
public class TransposeMatrix {

    /**
     * ---------------------------------------------------------------
     * class Solution {
     * public:
     *     vector<vector<int>> transpose(vector<vector<int>>& matrix) {
     *         int m = matrix.size(), n = matrix[0].size();
     *         vector<vector<int>> res(n, vector<int>(m));
     *         for (int i = 0; i < m; i++) {
     *             for (int j = 0; j < n; j++) {
     *                 res[j][i] = matrix[i][j];
     *             }
     *         }
     *         return res;
     *     }
     * };
     * --------------------------
     * class Solution:
     *     def transpose(self, matrix: List[List[int]]) -> List[List[int]]:
     *         res = [[0] * len(matrix) for _ in range(len(matrix[0]))]
     *         for i in range(len(matrix)):
     *             for j in range(len(matrix[0])):
     *                 res[j][i] = matrix[i][j]
     *         return res
     * --------------------------
     * >>> a = [[1,2,3],[4,5,6],[7,8,9]]
     * >>> print(a)
     * [[1, 2, 3],
     *  [4, 5, 6],
     *  [7, 8, 9]]
     * >>> b = zip(*a)
     * >>> print(b)
     * <zip object at 0x10958d340>
     * >>> print(list(b))
     * [(1, 4, 7),
     *  (2, 5, 8),
     *  (3, 6, 9)]
     * >>> exit()
     * --------------
     * class Solution:
     *    def transpose(self, matrix: List[List[int]]) -> List[List[int]]:
     *          return list(zip(*matrix))
     * --------------------------
     * @param matrix
     * @return
     */
    public int[][] transposeV1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }

    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = matrix[j][i];
            }
        }
        return res;
    }
}
