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
     * --------------------------------------------------------
     * This question gives a two-dimensional array of equal length and width,
     * and says that it is to find a column path so that the distance between the numbers in two adjacent positions does not exceed 1.
     * You can understand the meaning of the problem by looking at the example given in the question.
     * Since the cumulative value at each position determined by the smaller one of the three positions in the previous row,
     * this is a typical dynamic programming problem,
     * in order to save space, we directly use the array matrix itself as a dp array,
     * where matrix[i][j] means the last position in the (i, j) of the smallest descent path,
     * then eventually just find the smallest value in the last row is what is required.
     * Since we have to look at the value of the previous row,
     * we have to iterate from the second row,
     * so we first determine whether there is only one row in the array,
     * and if so, we can return the unique number directly.
     * Otherwise, if we start from the second row, the number that must exist is matrix[i-1][j],
     * and the two numbers around it(matrix[i-1][j-1], matrix[i-1][j+1]) need to be judged,
     * and if they exist, we will compare them to get the smaller value and add the final minimum value to the current matrix[i][j].
     * In order to avoid reopening a new for-loop to get the minimum,
     * we check whether the current line is the last one(n-1), if so, we update the final result res.
     *
     *
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
