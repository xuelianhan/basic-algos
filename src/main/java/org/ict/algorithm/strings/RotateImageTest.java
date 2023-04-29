package org.ict.algorithm.strings;

import java.util.Arrays;

/**
 *
 * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
 * write a method to rotate the image by 90 degrees.Can you do this in place?
 * @see <a href="http://stackoverflow.com/questions/25882480/rotating-a-nxn-matrix-in-java"></a>
 * @see <a href="http://stackoverflow.com/questions/2799755/rotate-array-clockwise"></a>
 * @see <a href="http://www.programcreek.com/2013/01/leetcode-rotate-image-java/"></a>
 * @see <a href="http://www.geeksforgeeks.org/rotate-matrix-elements/"></a>
 *
 */
public class RotateImageTest {

    public static void rotate(int[][] matrix, int N) {
        if (matrix.length == 0 || matrix.length != N) {
            return;
        }
        for (int layer = 0; layer < N / 2; layer++) {
            int first = layer;
            int last = N - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int j = last - offset;
                int top = matrix[first][i];
                //left to top
                matrix[first][i] = matrix[j][first]; 
                //bottom to left
                matrix[j][first] = matrix[last][j]; 
                //right to bottom
                matrix[last][j] = matrix[i][last];
            }
        }
    }

    /**
     * if MxN matrix and M != N, you need to create a new NxM matrix.
     *
     */
    public static int[][] rotate(int[][] matrix, int M, int N) {
        if (M <=0 || N <= 0) {
            return null;
        }
        int[][] result = new int[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                result[j][M - 1 - i] = matrix[i][j];
            }
        }
        return result;
    }

    public static int[][] transposeMatrix(int[][] matrix, int M, int N) {
        if (M <=0 || N <= 0) {
            return null;
        }
        if (M == N) {
            for (int i = 0; i < M; i++) {
                for (int j = i + 1; j < N; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
            return matrix;
        } else {
            int[][] result = new int[N][M];
            for (int j = 0; j < N; j++) {
                for (int i = 0; i < M; i++) {
                    result[j][i] = matrix[i][j]; 
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println("Input matrix:\n");
        int[][] matrix = {{1, 2, 3, 4},{5, 6, 7, 8},{9, 10, 11, 12},{13, 14, 15, 16}};

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
               System.out.print(matrix[i][j]+"\t"); 
            }
            System.out.println("");
        }
        rotate(matrix, 4);
        System.out.println("After one times rotate the matrix:\n");
        System.out.println(Arrays.deepToString(matrix));
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("After two times rotate the matrix:\n");
        int[][] result = rotate(matrix, 4, 4);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Transpose the matrix:\n");
        int[][] transposeResult = transposeMatrix(result, 4, 4);
        for (int[] row : transposeResult) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Input an MxN matrix and M != N:");
        int[][] MxN = {{1, 2, 3},{4, 5, 6},{7, 8, 9},{10, 11, 12}};
        for (int[] row : MxN) {
            System.out.println(Arrays.toString(row));
        }
        int[][] transposeMxN = transposeMatrix(MxN, 4, 3);
        System.out.println("Transpose an MxN matrix and M != N:");
        for (int[] row : transposeMxN) {
            System.out.println(Arrays.toString(row));
        }
    }

}
