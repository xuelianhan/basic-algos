package org.ict.algorithm.leetcode.matrix;

import java.util.Arrays;

import org.ict.algorithm.sort.AbstractSortHelper;


/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Note:
 * You have to rotate the image in-place, 
 * which means you have to modify the input 2D matrix directly. 
 * DO NOT allocate another 2D matrix and do the rotation.
 *  Example 1:
 *  Given input matrix = 
 *  [
 *    [1,2,3],
 *    [4,5,6],
 *    [7,8,9]
 *  ],
 *  rotate the input matrix in-place such that it becomes:
 *  [
 *    [7,4,1],
 *    [8,5,2],
 *    [9,6,3]
 *  ]
 *  Example 2:
 *  
 *  Given input matrix =
 *  [
 *    [ 5, 1, 9,11],
 *    [ 2, 4, 8,10],
 *    [13, 3, 6, 7],
 *    [15,14,12,16]
 *  ], 
 *  
 *  rotate the input matrix in-place such that it becomes:
 *  [
 *    [15,13, 2, 5],
 *    [14, 3, 4, 1],
 *    [12, 6, 8, 9],
 *    [16, 7,10,11]
 *  ]
 *
 * @see <a href="https://stackoverflow.com/questions/30426909/get-columns-from-two-dimensional-array-in-java"></a>
 * LC48, Medium, frequency=26
 *
 */
public class RotateImage {

	public static void main(String[] args) {
		int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		System.out.println("before rotate: " + Arrays.deepToString(matrix));
		//rotateV1(matrix);
		//rotateV2(matrix);
		antiRotateV1(matrix);
		
		System.out.println("after rotate: " + Arrays.deepToString(matrix));
	}

	public void rotateV3(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return;
		}

		// first transpose
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix[i].length; j++) {
				swapMatrix(matrix, i, j, j, i);
			}
		}

		// then reverse from left to right by swapping (i, j ) with (i , matrix[i].length - i - j)
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length/2; j++) {
				swapMatrix(matrix, i, j, i, matrix[i].length - 1 - j);
			}
		}
	}

	public void swapMatrix(int[][] matrix, int i, int j, int r, int c) {
		int temp = matrix[i][j];
		matrix[i][j] = matrix[r][c];
		matrix[r][c] = temp;
	}
	
	/**
	 * first transpose the matrix, then reverse from left to right
	 * 1 2 3     1 4 7    7 4 1
	 * 4 5 6  => 2 5 8 => 8 5 2
	 * 7 8 9     3 6 9    9 6 3
	 * @param matrix
	 */
	public static void rotateV2(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return;
		}
		// first transpose
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix[i].length; j++) {
				AbstractSortHelper.swapMatrix(matrix, i, j, j, i);
			}
		}
		// then reverse from left to right by swapping (i, j ) with (i , matrix[i].length - i - j)
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length/2; j++) {
				AbstractSortHelper.swapMatrix(matrix, i, j, i, matrix[i].length - 1 - j);
			}
		}
	}
	
	
	
	/**
	 * clockwise rotate
	 * first reverse up to down, then swap the symmetry(transpose).
	 * But reverse by column is not friendly to Java
	 * 1 2 3     7 8 9     7 4 1
     * 4 5 6  => 4 5 6  => 8 5 2
     * 7 8 9     1 2 3     9 6 3
	 */
	public static void rotateV1(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return;
		}
		
		//Reverse up to down
		for (int col = 0; col < matrix[0].length; col++) {
			for (int row = 0; row < matrix.length/2; row++) {
				AbstractSortHelper.swapMatrix(matrix, row, col, matrix.length - 1 - row, col);
				/*
				 * int temp = 0; temp = matrix[row][col]; 
				 * matrix[row][col] =matrix[matrix.length - 1 - row][col]; 
				 * matrix[matrix.length - 1 - row][col] = temp;
				 */
			}
		}
		//Swap the symmetry(transpose)
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix[i].length; j++) {
				AbstractSortHelper.swapMatrix(matrix, i, j, j, i);
				/*
				 * int temp = 0; 
				 * temp = matrix[i][j]; 
				 * matrix[i][j] = matrix[j][i]; 
				 * matrix[j][i]= temp;
				 */
			}
		}
	}
	
	/**
	 * anti-clockwise rotate
	 * first reverse left to right, then swap the symmetry(transpose)
	 * 1 2 3     3 2 1     3 6 9
     * 4 5 6  => 6 5 4  => 2 5 8
     * 7 8 9     9 8 7     1 4 7
	 * @param matrix
	 */
	public static void antiRotateV1(int[][] matrix) {
		// Reverse left to right
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length/2; j++) {
				AbstractSortHelper.swapMatrix(matrix, i, j, i, matrix[i].length - 1 - j);
			}
		}
		// Swap the symmetry(transpose)
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix[i].length; j++) {
				AbstractSortHelper.swapMatrix(matrix, i, j, j, i);
			}
		}
	}
	
}
