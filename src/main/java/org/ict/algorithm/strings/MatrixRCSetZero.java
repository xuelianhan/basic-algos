package org.ict.algorithm.strings;

/**
 * Tue Jul  5 11:28:58 CST 2016
 * Write an algorithm such that if an element in an MxN matrix is 0,its entire row an column are set to 0.
 */
public class MatrixRCSetZero {
     
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

    }
    
    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 1},{2, 0, 3},{1, 1, 1}};
        MatrixRCSetZero obj = new MatrixRCSetZero();
        obj.setZeroOne(matrix);
    }

}
