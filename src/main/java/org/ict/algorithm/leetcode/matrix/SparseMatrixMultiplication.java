package org.ict.algorithm.leetcode.matrix;

import java.util.Arrays;

/**
 * Description
 * Given two sparse matrices
 * mat1 of size m x k and mat2 of size k x n,
 * return the result of mat1 x mat2.
 * You may assume that multiplication is always possible.
 *
 *
 * Example 1:
 * Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
 * Output: [[7,0,0],[-7,0,3]]
 *
 * Example 2:
 * Input: mat1 = [[0]], mat2 = [[0]]
 * Output: [[0]]
 *
 *
 * Constraints:
 * m == mat1.length
 * k == mat1[i].length == mat2.length
 * n == mat2[i].length
 * 1 <= m, n, k <= 100
 * -100 <= mat1[i][j], mat2[i][j] <= 100
 * @author sniper
 * @date 09 May 2023
 * LC311, Medium, frequency=7
 */
public class SparseMatrixMultiplication {

    public static void main(String[] args) {
        int[][] mat1 = {{1, 0, 0}, {-1, 0, 3}};
        int[][] mat2 = {{7 ,0, 0}, {0, 0, 0}, {0, 0, 1}};
        SparseMatrixMultiplication instance = new SparseMatrixMultiplication();
        int[][] res = instance.multiply(mat1, mat2);
        System.out.println(Arrays.deepToString(res));
    }


    public int[][] multiplyV1(int[][] mat1, int[][] mat2) {
        //todo
        return null;
    }

    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int k = mat1[0].length;
        int n = mat2[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int l = 0; l < k; l++) {
                if (mat1[i][l] != 0) {
                    for (int j = 0; j < n; j++) {
                        if (mat2[l][j] != 0) {
                            res[i][j] += mat1[i][l] * mat2[l][j];
                        }
                    }
                }
            }
        }
        return res;
    }
}
