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

    /**
     * Time Cost 3ms
     * @param mat
     * @return
     */
    public int[] findDiagonalOrderV3(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return null;
        }

        int m = mat.length;
        int n = mat[0].length;
        int k = 0;
        int size = m * n;
        int[] res = new int[size];
        for (int i = 0; i < m + n - 1; i++) {
            int lo = Math.max(0, i - n + 1);
            int hi = Math.min(i, m - 1);
            if (i % 2 == 0) {
                for (int j = hi; j >= lo; j--) {
                    res[k++] = mat[j][i - j];
                }
            } else {
                for (int j = lo; j <= hi; j++) {
                    res[k++] = mat[j][i - j];
                }
            }
        }

        return res;
    }

    /**
     * Time Cost 2ms
     * @param mat
     * @return
     */
    public int[] findDiagonalOrderV2(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return null;
        }

        int m = mat.length;
        int n = mat[0].length;
        int size = m * n;
        int r = 0;
        int c = 0;
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = mat[r][c];
            if ((r + c) % 2 == 0) {
                if (c == n - 1) {
                    r++;
                } else if (r == 0) {
                    c++;
                } else {
                    r--;
                    c++;
                }
            } else {
                if (r == m - 1) {
                    c++;
                } else if (c == 0) {
                    r++;
                } else {
                    r++;
                    c--;
                }
            }
        }

        return res;
    }

    /**
     * Time Cost 5ms
     * Understanding the following solution
     *
     * Traversing both diagonal directions to the upper right and lower left will have the possibility of crossing the line,
     * but except for the lower left and upper right position crossing the line need to change two coordinates,
     * the rest of the crossing only need to change one.
     * Then we will first determine to change the two coordinates at the same time to cross the border,
     * that is, in the upper right corner and the lower left corner of the position.
     * If in the upper right corner of the position to go up to the right,
     * then to move to the position below it,
     * then if col exceeds the range of n-1,
     * then col reset to n-1, and row incremented by 2,
     * and then change the direction of traversal.
     *
     * Similarly, if row exceeds the range of m-1,
     * then row is reset to m-1,
     * and col is incremented by 2,
     * and then the direction of traversal is changed.
     * Then we will determine the general case of crossing the boundary,
     * if row is less than 0, then row resets to 0,
     * and then changes the direction of traversal.
     * Similarly, if col is less than 0,
     * then col resets to 0,
     * and then changes the direction of traversal.
     * @param mat
     * @return
     */
    public int[] findDiagonalOrderV1(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return null;
        }

        int m = mat.length;
        int n = mat[0].length;
        int size = m * n;
        int r = 0;
        int c = 0;
        int k = 0;
        int[] res = new int[size];
        /**
         * Move to the right-up: {-1, 1}
         * Move to the left-down: {1, -1}
         */
        int[][] dirs = new int[][] {{-1, 1}, {1, -1}};

        for (int i = 0; i < size; i++) {
            res[i] = mat[r][c];
            r += dirs[k][0];
            c += dirs[k][1];
            if (r >= m) {
                r = m - 1;
                c += 2;
                k = 1 - k;
            }
            if (c >= n) {
                c = n - 1;
                r += 2;
                k = 1 - k;
            }
            if (r < 0) {
                r = 0;
                k = 1 - k;
            }
            if (c < 0) {
                c = 0;
                k = 1 - k;
            }
        }

        return res;
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
