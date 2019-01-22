package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Given a matrix of m x n elements (m rows, n columns), 
 * return all elements of the matrix in spiral order.
 * Example 1:
 * Input:
 * [
 *   [ 1, 2, 3 ],
 *   [ 4, 5, 6 ],
 *   [ 7, 8, 9 ]
 * ]
 * 
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 * 
 * Input:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 */

public class SpiralMatrix {
	
	public static void main(String[] args) {
		int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9,10,11,12}};
		List<Integer> result = spiralOrderV1(matrix);
		System.out.println(result);
	}
	

	public static List<Integer> spiralOrderV1(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		if (matrix.length == 0) {
			return result;
		}
		int rowBegin = 0, rowEnd = matrix.length - 1;
		int colBegin = 0, colEnd = matrix[0].length - 1;
		while (rowBegin <= rowEnd && colBegin <= colEnd) {
			// Traverse Right
			for (int i = colBegin; i <= colEnd; i++) {
				result.add(matrix[rowBegin][i]);
			}
			rowBegin++;
			
			// Traverse Down
			for (int i = rowBegin; i <= rowEnd; i++) {
				result.add(matrix[i][colEnd]);
			}
			colEnd--;
			
			// Traverse left
			if (rowBegin <= rowEnd) {
				for (int i = colEnd; i>= colBegin; i--) {
					result.add(matrix[rowEnd][i]);
				}
			}
			rowEnd--;
			
			// Traverse Up
			if (colBegin <= colEnd) {
				for (int i = rowEnd; i >= rowBegin; i--) {
					result.add(matrix[i][colBegin]);
				}
			}
			colBegin++;
		}
		return result;
	}
			
}
