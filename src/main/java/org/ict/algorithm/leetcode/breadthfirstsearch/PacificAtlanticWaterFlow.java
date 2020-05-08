package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, 
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * 
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * Note:
 * The order of returned grid coordinates does not matter.
 * 
 * Both m and n are less than 150.
 * Example:
 * 
 * Given the following 5x5 matrix:
 * 
 *   Pacific ~   ~   ~   ~   ~ 
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 * 
 * Return:
 * 
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 * 
 * LC417
 *
 */
public class PacificAtlanticWaterFlow {

	public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        return null;
    }
	
	/**
	 * Start dfs from each boundary, then find common visited nodes.That's the answer.
	 * @param matrix
	 * @return
	 */
	public List<List<Integer>> pacificAtlanticV1(int[][] matrix) {
		List<List<Integer>> result = new ArrayList<>();
		if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
			return result;
		}
		
		int m = matrix.length;
		int n = matrix[0].length;
		boolean[][] pac = new boolean[m][n];
		boolean[][] atl = new boolean[m][n];
		int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		
		// Vertical border
		for (int i = 0; i < m; i++) {
			dfsV1(matrix, dir, pac, i, 0);
			dfsV1(matrix, dir, atl, i, n-1);
		}
		
		// Horizontal border
		for (int i = 0; i < n; i++) {
			dfsV1(matrix, dir, pac, 0, i);
			dfsV1(matrix, dir, atl, m-1, i);
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (pac[i][j] && atl[i][j]) {
					List<Integer> coordinate = new ArrayList<>();
					coordinate.add(i);
					coordinate.add(j);
					result.add(coordinate);
				}
			}
		}
		return result;
	}
	
	public void dfsV1(int[][] matrix, int[][] dir, boolean[][] visited, int i, int j) {
		
	}
	
	
}
