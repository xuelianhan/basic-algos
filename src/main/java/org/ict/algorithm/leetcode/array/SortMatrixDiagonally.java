package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A matrix diagonal is a diagonal line of cells starting from some cell
 * in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end.
 * For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix,
 * includes cells mat[2][0], mat[3][1], and mat[4][2].
 *
 * Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 * Example 2:
 *
 * Input: mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
 * Output: [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 * @author sniper
 * @date 28 Aug, 2022
 * LC1329
 */
public class SortMatrixDiagonally {

    public int[][] diagonalSortV3(int[][] mat) {
        //todo
        return mat;
    }


    public int[][] diagonalSortV2(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        /**
         * row = 0
         * top-triangle include diagonal line
         */
        for (int col = 0; col < n; col++) {
            countingSort(mat, 0, col, m, n);
        }


        /**
         * col=0
         * bottom-triangle not include diagonal line
         * row = 1 due to diagonal line has been sorted in the up for-loop(row=0)
         */
        for (int row = 1; row < m; row++) {
            countingSort(mat, row, 0, m, n);
        }

        return mat;
    }

    /**
     * Use Counting Sort
     * Time O((m+n)*k)
     * Space O(1)
     *
     * Use condition: 1 <= mat[i][j] <= 100
     *
     * @param mat
     * @param row
     * @param col
     * @param m
     * @param n
     */
    public void countingSort(int[][] mat, int row, int col, int m, int n) {
        int[] list = new int[101];
        int r = row, c = col;
        while (r < m && c < n) {
            list[mat[r][c]]++;
            r++;
            c++;
        }


        r = row;
        c = col;
        for (int i = 1; i < 101; i++) {
            if (list[i] > 0) {
                int count = list[i];
                while (count-- > 0) {
                    mat[r][c] = i;
                    r++;
                    c++;
                }
            }
        }
    }

    /**
     * Time O((m+n)*klogk)
     * Space O(k)
     * where k = min(m,n)
     *
     * @param mat
     * @return
     */
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        /**
         * row = 0
         * top-triangle include diagonal line
         */
        for (int col = 0; col < n; col++) {
            sort(mat, 0, col, m, n);
        }


        /**
         * col=0
         * bottom-triangle not include diagonal line
         * row = 1 due to diagonal line has been sorted in the up for-loop(row=0)
         */
        for (int row = 1; row < m; row++) {
            sort(mat, row, 0, m, n);
        }

        return mat;
    }

    /**
     * This solution should be very familiar.It's recommended.
     * @param mat matrix
     * @param row start row
     * @param col start col
     * @param m row limit
     * @param n column limit
     */
    public void sort(int[][] mat, int row, int col, int m, int n) {
        List<Integer> list = new ArrayList<>();
        int r = row, c = col;
        while (r < m && c < n) {
            list.add(mat[r][c]);
            r++;
            c++;
        }

        Collections.sort(list);

        r = row;
        c = col;
        int idx = 0;
        while (r < m && c < n) {
            mat[r][c] = list.get(idx++);
            r++;
            c++;
        }
    }
}
