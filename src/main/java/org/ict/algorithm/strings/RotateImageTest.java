package org.ict.algorithm.strings;

/**
 *
 * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
 * write a method to rotate the image by 90 degrees.Can you do this in place?
 * @see http://stackoverflow.com/questions/25882480/rotating-a-nxn-matrix-in-java
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
                int top = matrix[first][i];
                //left to top
                matrix[first][i] = matrix[last - offset][first]; 
                //bottom to left
                matrix[last - offset][first] = matrix[last][last - offset]; 
                //right to bottom
                matrix[last][last - offset] = matrix[i][last];
                //top to right
                matrix[i][last] = top;
            }
        }
    }

    /**
     * if MxN matrix and M != N, you need to create a new NxM matrix.
     *
     */
    public static void rotate(int[][] matrix, int M, int N) {

    }

    public static void main(String[] args) {


    }

}
