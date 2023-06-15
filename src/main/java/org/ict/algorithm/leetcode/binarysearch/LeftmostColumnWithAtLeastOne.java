package org.ict.algorithm.leetcode.binarysearch;

import java.util.List;

/**
 * A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.
 * Given a row-sorted binary matrix binaryMatrix,
 * return the index (0-indexed) of the leftmost column with a 1 in it.
 * If such an index does not exist, return -1.
 *
 * You can't access the Binary Matrix directly.
 * You may only access the matrix using a BinaryMatrix interface:
 * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols],
 * which means the matrix is rows x cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * For custom testing purposes, the input will be the entire binary matrix mat.
 * You will not have access to the binary matrix directly.
 *
 * Example 1:
 * Input: mat = [[0,0],[1,1]]
 * Output: 0
 *
 * Example 2:
 * Input: mat = [[0,0],[0,1]]
 * Output: 1
 *
 * Example 3:
 * Input: mat = [[0,0],[0,0]]
 * Output: -1
 *
 * Constraints:
 * rows == mat.length
 * cols == mat[i].length
 * 1 <= rows, cols <= 100
 * mat[i][j] is either 0 or 1.
 * mat[i] is sorted in non-decreasing order.
 * @author sniper
 * @date 15 Jun 2023
 * LC1428, Medium, frequency=10
 */
public class LeftmostColumnWithAtLeastOne {

    /**
     * Understanding the following Solution
     * Binary Search
     * Time Complexity O(M*logN)
     * Space Complexity O(1)
     * @param binaryMatrix
     * @return
     */
    public int leftMostColumnWithOneV1(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int m = dimensions.get(0);
        int n = dimensions.get(1);

        int res = -1;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (existOne(binaryMatrix, m, mid)) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }

    private boolean existOne(BinaryMatrix binaryMatrix, int m, int col) {
        for (int i = 0; i < m; i++) {
            if (binaryMatrix.get(i, col) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Linear Search
     * Time Complexity O(M + N)
     * Space Complexity O(1)
     * @param binaryMatrix
     * @return
     */
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int m = dimensions.get(0);
        int n = dimensions.get(1);

        int res = -1;
        for (int i = 0, j = n - 1; i < m && j >= 0;) {
            if (binaryMatrix.get(i, j) == 1) {
                res = j;
                j--;
            } else {
                i++;
            }
        }
        return res;
    }

    /**
     * // This is the BinaryMatrix's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface BinaryMatrix {
     *     public int get(int row, int col) {}
     *     public List<Integer> dimensions {}
     * };
     */
    interface BinaryMatrix {
        int get(int row, int col);
        List<Integer> dimensions();
    }

}
