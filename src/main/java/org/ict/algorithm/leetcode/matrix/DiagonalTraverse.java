package org.ict.algorithm.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,4,7,5,3,6,8,9]
 * Example 2:
 *
 * Input: mat = [[1,2],[3,4]]
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * -10^5 <= mat[i][j] <= 10^5
 * @author sniper
 * @date 28 Aug 2023
 * LC498, Medium
 */
public class DiagonalTraverse {

    public int[] findDiagonalOrderV3(int[][] mat) {
        //todo
        return null;
    }

    public int[] findDiagonalOrderV2(int[][] mat) {
        //todo
        return null;
    }

    public int[] findDiagonalOrderV1(int[][] mat) {
        //todo
        return null;
    }

    /**
     * Brute-Force Solution
     * Time Cost 834 ms, very slow.
     * Using the property that the sum
     * of the horizontal and vertical coordinates of the numbers on the diagonal is constant
     * @param mat
     * @return
     */
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return null;
        }
        int m = mat.length;
        int n = mat[0].length;
        List<Integer> list = new ArrayList<>();
        for (int k = 0; k < m + n - 1; k++) {
            int delta = 1 - 2 * (k % 2 == 0 ?  1 : 0);
            int x = (m - 1) * (k % 2 == 0 ?  1 : 0);
            int y = (n - 1) * (k % 2 == 0 ?  1 : 0);
            for (int i = x; i >= 0 && i < m; i += delta) {
                for (int j = y; j >= 0 && j < n; j += delta) {
                    if (i + j == k) {
                        list.add(mat[i][j]);
                    }
                }
            }
        }

        /**
         * Using list.stream().mapToInt(Integer::intValue).toArray(); is OK too.
         */
        return list.stream().mapToInt(i->i).toArray();
    }
}
