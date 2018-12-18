package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

public class ArrayPrintByRule {

    /**
     * take the 4*4 matrix as example,
     * k
     * 0  00
     * 1  01 10
     * 2  02 11 20
     * 3  03 12 21 30
     * 4  13 22 31
     * 5  23 32
     * 6  33
     */
    public static void printArrayByDiagonalV2(int[][] a) {
        int N = a.length;
        int M = a[0].length;
        if (M != N) {
            throw new IllegalArgumentException("The input array must be N*N matrix!");
        }
        for (int k = 0; k <= 2*(N-1); k++) {
           int i = 0, j = k - i;
           if (k > N - 1) {
             i = k - (N - 1);
             j = k - i;
           }
           if (i == j) {
             System.out.println(a[i][j]);
           } else {
             int tr = i;
             int tc = j;
             while (tr <= j && tc >= i) {
                System.out.println(a[tr++][tc--]);
             }
           }
        }
    }

    /**
     * Give a N*N matrix a, print the value by diagnoal.eg.
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     * print result is following:
     * 1 2 4 3 5 7 6 8 9
     * Draw 4*4 matrix and the x-y-coordinate point (x,y)
     * will find the key rule!
     * 00 01 10
     * 02 11 20 
     * 03 12 21 30
     * 13 22 31
     * 23 32
     * 33
     */
    public static void printArrayByDiagonal(int[][] a) {
      int N = a.length;
      int M = a[0].length;
      if (M != N) {
          throw new IllegalArgumentException("The input array must be N*N matrix!");
      }
      boolean reset = false;
      for (int i = 0; i <= N - 1; i++) {
        for (int j = 0; j <= N - 1; j++) {
            if (j == N -1) {
                reset = true;
            }
            if (reset) {
                j = N -1;
            }
            if (i == j) {
                System.out.println(a[i][j]);
            } else {
                int tr = i;
                int tc = j;
                while (tc >= i && tr <= j) {
                    System.out.println(a[tr++][tc--]);
                }
            }
        }
      }
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 1, 1},{2, 0, 3, 9},{1, 1, 1, 4}, {8, 6, 3, 5}};
        for(int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        printArrayByDiagonal(matrix);
        System.out.println("--------------solution V2--------------");
        printArrayByDiagonalV2(matrix);
    }

}
