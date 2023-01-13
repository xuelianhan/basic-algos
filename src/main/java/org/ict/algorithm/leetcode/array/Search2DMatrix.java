package org.ict.algorithm.leetcode.array;


/**
 * You are given an m x n integer matrix matrix with the following two properties:
 *
 * Each row is sorted in non-decreasing order.
 * The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 *
 * You must write a solution in O(log(m * n)) time complexity.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * Example 2:
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -10^4 <= matrix[i][j], target <= 10^4
 * @author sniper
 * @date 12 Jan, 2023
 * LC74, Medium
 */
public class Search2DMatrix {

    public static void main(String[] args) {
        //int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        //int target = 3;

        //int[][] matrix = {{1, 1}};
        //int target = 1;

        int[][] matrix = {{1}, {3}};
        int target = 3;
        Search2DMatrix instance = new Search2DMatrix();
        boolean res = instance.searchMatrix(matrix, target);
        System.out.println(res);
    }

    public boolean searchMatrixV4(int[][] matrix, int target) {
        //todo
        return false;
    }

    public boolean searchMatrixV3(int[][] matrix, int target) {
        //todo
        return false;
    }

    /**
     * Treat the 2D-array as 1D-array, we access every element in S-curve.
     * Time Complexity O(M*N)
     * Space Complexity O(1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixV2(int[][] matrix, int target) {
        if (matrix == null || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0;
        int high = m * n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int r = mid / n;
            int c = mid % n;
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return false;
    }

    /**
     * Understanding the Following Solution.
     *
     * Iterate row by row, using binary search for each row.
     * Time Complexity M*logN
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixV1(int[][] matrix, int target) {
        if (matrix == null || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        for (int i = 0; i < m; i++) {
            int low = 0;
            int high = matrix[i].length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (matrix[i][mid] == target) {
                    return true;
                } else if (matrix[i][mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return false;
    }

    /**
     * Find the row first, then search target in this row column by column.
     * Time Complexity O(m + n)
     * Space Complexity O(1)
     * Time Cost 0ms
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0;
        for (int i = 0; i < m; i++) {
            if (target >= matrix[i][0] && target <= matrix[i][n - 1]) {
                row = i;
                break;
            }
        }

        for (int j = 0; j < n; j++) {
            if (matrix[row][j] == target) {
                return true;
            }
        }
        return false;
    }
}
