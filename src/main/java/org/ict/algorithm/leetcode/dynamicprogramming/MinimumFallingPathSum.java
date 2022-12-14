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

    public static void main(String[] args) {
        int[][] matrix = {{-19, 57}, {-40, -5}};
        MinimumFallingPathSum instance = new MinimumFallingPathSum();
        int result = instance.minFallingPathSum(matrix);
        System.out.println(result);
    }

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
     * matrix = [[2, 1, 3],
     *           [6, 5, 4],
     *           [7, 8, 9]]
     * i:1, j:0, pre = matrix[i-1][j] = matrix[0][0] = 2
     * j < 2, pre = min(pre, matrix[i-1][j+1]) = min(2, 1) = 1
     * matrix[1][0] = 6 + 1 = 7
     * matrix = [[2, 1, 3],
     *           [7, 5, 4],
     *           [7, 8, 9]]
     * -------------------------------------------------------
     * i:1, j:1, pre = matrix[i-1][j] = matrix[0][1] = 1
     * j > 0, pre = min(pre, matrix[i-1][j-1]) = min(1, 2) = 1
     * j < 2, pre = min(pre, matrix[i-1][j+1]) = min(1, 3) = 1
     * matrix[1][1] = 5 + 1 = 6
     * matrix = [[2, 1, 3],
     *           [7, 6, 4],
     *           [7, 8, 9]]
     * --------------------------------------------------------
     * i:1, j:2, pre = matrix[i-1][j] = matrix[0][2] = 3
     * j > 0, pre = min(pre, matrix[i-1][j-1]) = min(3, 1) = 1
     * matrix[1][2] = 4 + 1 = 5
     * matrix = [[2, 1, 3],
     *           [7, 6, 5],
     *           [7, 8, 9]]
     * --------------------------------------------------------
     * i:2, j:0, pre = matrix[i-1][j] = matrix[1][0] = 7
     * j < 2, pre = min(pre, matrix[i-1][j+1]) = min(7, 6) = 6
     * matrix[2][0] = 7 + 6 = 13
     * matrix = [[2, 1, 3],
     *           [7, 6, 5],
     *           [13, 8, 9]]
     * i = n - 1 = 2, res = min(res, matrix[2][0]) = 13
     * --------------------------------------------------------
     * i:2, j:1, pre = matrix[i-1][j] = matrix[1][1] = 6
     * j > 0, pre = min(pre, matrix[i-1][j-1]) = min(6, 7) = 6
     * j < 2, pre = min(pre, matrix[i-1][j+1]) = min(6, 5) = 5
     * matrix[2][1] = 8 + 5 = 13
     * matrix = [[ 2, 1, 3],
     *           [ 7, 6, 5],
     *           [13, 13, 9]]
     * i = n - 1 = 2, res = min(res, matrix[2][1]) = min(13, 13) = 13
     * --------------------------------------------------------
     * i:2, j:2, pre = matrix[i-1][j] = matrix[1][2] = 5
     * j > 0, pre = min(pre, matrix[i-1][j-1]) = min(5, 6) = 5
     * matrix[2][2] = 9 + 5 = 14
     * matrix = [[ 2, 1, 3],
     *           [ 7, 6, 5],
     *           [13, 13, 13]]
     * i = n - 1 = 2, res = min(res, matrix[2][2]) = min(13, 13) = 13
     * --------------------------------------------------------
     * return res:13
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
