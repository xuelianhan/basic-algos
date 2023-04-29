package org.ict.algorithm.strings;

import java.util.Arrays;
/**
 * Tue Jul  5 11:28:58 CST 2016
 * Write an algorithm such that if an element in an MxN matrix is 0,its entire row a column are set to 0.
 */
public class MatrixRCSetZero {
    /**
     * The following code takes O(N) space by using an boolean array to indicate the row and collumn
     * whether has zero or not.
     */
    public void setZeroOne(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];
        
        for (int i = 0; i < matrix.length; i++) {
          for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[i][j] == 0) {
              row[i] = true;
              column[j] = true;
            }
          }
        }

        for (int i = 0; i < matrix.length; i++) {
          if (row[i]) {
            for (int j = 0; j < matrix[i].length; j++) {
              matrix[i][j] = 0;
            }
          }
        }

        for (int j = 0; j < matrix[0].length; j++) {
          if (column[j]) {
            for (int i = 0; i < matrix.length; i++) {
              matrix[i][j] = 0; 
            }
          }
        }
    }

    /**
     * Reduce the space to O(1) by using the first row as replacement
     * for the row array and the first column as a replacement for the
     * column array.
     *
     */ 
    public void setZeroTwo(int[][] matrix) {
       boolean rowOneHasZero = false;
       boolean colOneHasZero = false;
       for (int j = 0; j < matrix[0].length; j++) {
         if (matrix[0][j] == 0) {
           rowOneHasZero = true;
           break;
         }
       }

       for (int i = 0; i < matrix.length; i++) {
         if (matrix[i][0] == 0) {
           colOneHasZero = true;
           break;
         }
       }

       for (int i = 1; i < matrix.length; i++) {
         for (int j = 1; j < matrix[i].length; j++) {
           if (matrix[i][j] == 0) {
             matrix[i][0] = 0;
             matrix[0][j] = 0;
           }
         }
       }

       for (int i = 1; i < matrix.length; i++) {
         if (matrix[i][0] == 0) {
           for (int j = 1; j < matrix[i].length; j++) {
             matrix[i][j] = 0;
           }
         }
       }

       for (int j = 1; j < matrix[0].length; j++) {
         if (matrix[0][j] == 0) {
           for (int i = 1; i < matrix.length; i++) {
             matrix[i][j] = 0;
           }
         }
       }

       if (rowOneHasZero) {
         for (int j = 0; j < matrix[0].length; j++) {
           matrix[0][j] = 0; 
         }
       }
       
       if (colOneHasZero) {
         for (int i = 0; i < matrix.length; i++) {
           matrix[i][0] = 0;
         }
       }
    }
    
    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 1, 1}, {2, 0, 3, 9}, {1, 1, 1, 4}, {8, 6, 3, 5}};
        System.out.println("Raw input matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        MatrixRCSetZero obj = new MatrixRCSetZero();
        //obj.setZeroOne(matrix);
        obj.setZeroTwo(matrix);
        System.out.println("After being set to zero matrix:");
    }

}
