package org.ict.algorithm.leetcode.matrix;

import java.util.ArrayList;
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
 *
 * Example 2:
 * Input:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * LC54, Medium, frequency=42
 *
 */

public class SpiralMatrix {
	
	public static void main(String[] args) {
		int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9,10,11,12}};

		SpiralMatrix instance = new SpiralMatrix();
		List<Integer> result = instance.spiralOrder(matrix);
		System.out.println(result);
	}

	/**
	 * Understanding the following solution.
	 * ------------------------------------------------------------
	 * class Solution:
	 *     def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
	 *         if not matrix or not matrix[0]:
	 *             return []
	 *         m = len(matrix)
	 *         n = len(matrix[0])
	 *         i, j, idx = 0, 0, 0
	 *         dir = [[0, 1], [1, 0], [0, -1], [-1, 0]]
	 *         res = []
	 *         visited = set()
	 *         for k in range(0, m * n):
	 *             res.append(matrix[i][j])
	 *             visited.add((i, j))
	 *             x, y = i + dir[idx][0], j + dir[idx][1]
	 *             if x < 0 or x >= m or y < 0 or y >= n or (x, y) in visited:
	 *                 idx = (idx + 1) % 4
	 *                 x, y = i + dir[idx][0], j + dir[idx][1]
	 *             i, j = x, y
	 *         return res
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrderV3(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return new ArrayList<>();
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int i = 0, j = 0, idx = 0;
		/**
		 * The order of elements in the direction array can't be messed.
		 * right-->down-->left-->up
		 */
		int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		List<Integer> res = new ArrayList<>();
		boolean[][] visited = new boolean[m][n];
		for (int k = 0; k < m * n; k++) {
			res.add(matrix[i][j]);
			/**
			 * Marking the matrix[i][j] has been visited.
			 */
			visited[i][j] = true;
			int x = i + direction[idx][0];
			int y = j + direction[idx][1];
			/**
			 * The matrix[x][y] must be put after range-checking.
			 * If x, y is out of bound, or the element of (x, y) has been visited,
			 * we need to change the direction.
			 */
			if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
				idx = (idx + 1) % 4;
				x = i + direction[idx][0];
				y = j + direction[idx][1];
			}
			i = x;
			j = y;
		}
		return res;
	}

	/**
	 * Understanding the following solution
	 *
	 * class Solution:
	 *     def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
	 *         if not matrix or not matrix[0]:
	 *             return []
	 *         m = len(matrix)
	 *         n = len(matrix[0])
	 *         i, j, idx = 0, 0, 0
	 *         dir = [[0, 1], [1, 0], [0, -1], [-1, 0]]
	 *         res = []
	 *         for k in range(0, m * n):
	 *             res.append(matrix[i][j])
	 *             matrix[i][j] = 0
	 *             x, y = i + dir[idx][0], j + dir[idx][1]
	 *             if x < 0 or x >= m or y < 0 or y >= n or matrix[x][y] == 0:
	 *                 idx = (idx + 1) % 4
	 *                 x, y = i + dir[idx][0], j + dir[idx][1]
	 *             i, j = x, y
	 *         return res
	 *
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrderV2(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return new ArrayList<>();
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int i = 0, j = 0, idx = 0;
		/**
		 * The order of elements in the direction array can't be messed.
		 * right-->down-->left-->up
		 */
		int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		List<Integer> res = new ArrayList<>();
		for (int k = 0; k < m * n; k++) {
			res.add(matrix[i][j]);
			/**
			 * Marking the matrix[i][j] has been visited.
			 */
			matrix[i][j] = 0;
			int x = i + direction[idx][0];
			int y = j + direction[idx][1];
			/**
			 * The matrix[x][y] must be put after range-checking.
			 * If x, y is out of bound, or the element of (x, y) has been visited,
			 * we need to change the direction.
			 */
			if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] == 0) {
				idx = (idx + 1) % 4;
				x = i + direction[idx][0];
				y = j + direction[idx][1];
			}
			i = x;
			j = y;
		}

		return res;
	}

	/**
	 * Understanding the following solution
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrderV1(int[][] matrix) {
		if (matrix.length == 0) {
			return new ArrayList<>();
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int rs = 0, re = m - 1;
		int cs = 0, ce = n - 1;
		List<Integer> res = new ArrayList<>();
		while (rs <= re && cs <= ce) {
			/**
			 * Go right
			 */
			for (int j = cs; j <= ce; j++) {
				res.add(matrix[rs][j]);
			}
			rs++;
			/**
			 * Go down
			 */
			for (int i = rs; i <= re; i++) {
				res.add(matrix[i][ce]);
			}
			ce--;
			/**
			 * Go left
			 */
			if (rs <= re) {
				for (int j = ce; j >= cs; j--) {
					res.add(matrix[re][j]);
				}
			}

			re--;
			/**
			 * Go Up
			 */
			if (cs <= ce) {
				for (int i = re; i >= rs; i--) {
					res.add(matrix[i][cs]);
				}
			}
			cs++;
		}
		return res;
	}


	/**
	 * Input:
	 * [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
	 *
	 * Expected:
	 * [1,2,3,4,8,12,11,10,9,5,6,7]
	 *
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		if (matrix.length == 0) {
			return result;
		}
		int rowBegin = 0, rowEnd = matrix.length - 1;
		int colBegin = 0, colEnd = matrix[0].length - 1;
		while (rowBegin <= rowEnd && colBegin <= colEnd) {
			/**
			 * Traverse Right
			 */
			for (int i = colBegin; i <= colEnd; i++) {
				result.add(matrix[rowBegin][i]);
			}
			rowBegin++;

			/**
			 * Traverse Down
			 */
			for (int i = rowBegin; i <= rowEnd; i++) {
				result.add(matrix[i][colEnd]);
			}
			colEnd--;

			/**
			 * Traverse left, this if condition cannot be commented here
			 */
			if (rowBegin <= rowEnd) {
				for (int i = colEnd; i>= colBegin; i--) {
					result.add(matrix[rowEnd][i]);
				}
			}
			rowEnd--;

			/**
			 * Traverse Up, this if condition cannot be commented here
			 */
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
