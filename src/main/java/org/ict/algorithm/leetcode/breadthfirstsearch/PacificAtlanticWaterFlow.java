package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	
	private int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

	/**
	 * Two Queue and add all the Pacific border to one queue; Atlantic border to another queue.
	 * Keep a visited matrix for each queue. In the end, add the cell visited by two queue to the result.
	 * BFS: Water flood from ocean to the cell. 
	 * Since water can only flow from high/equal cell to low cell, 
	 * add the neighboor cell with height larger or equal to current cell to the queue and mark as visited.
	 * 
	 * @param matrix
	 * @return
	 */
	public List<List<Integer>> pacificAtlanticV2(int[][] matrix) {
		List<List<Integer>> result = new ArrayList<>();
		if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
			return result;
		}
		int m = matrix.length;// rows
		int n = matrix[0].length;// columns
		boolean[][] pac = new boolean[m][n];
		boolean[][] atl = new boolean[m][n];
		Queue<int[]> pQueue = new LinkedList<>();
		Queue<int[]> aQueue = new LinkedList<>();
		
		// Vertical border(row changes but column not change)
		for (int i = 0; i < m; i++) {
			pQueue.offer(new int[] {i, 0});
			aQueue.offer(new int[] {i, n-1});
			pac[i][0] = true;
			atl[i][n-1] = true;
		}
		
		// Horizontal border(column changes but row not change)
		for (int i = 0; i < n; i++) {
			pQueue.offer(new int[] {0, i});
			aQueue.offer(new int[] {m-1, i});
			pac[0][i] = true;
			atl[m-1][i] = true;
		}
		
		//bfs
		bfs(matrix, pQueue, pac);
		bfs(matrix, aQueue, atl);
		
		// Add the cell visited by two queue to the result
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (pac[i][j] && atl[i][j]) {
					List<Integer> coordinate = new ArrayList<>(2);
					coordinate.add(i);
					coordinate.add(j);
					result.add(coordinate);
				}
			}
		}
		return result;
    }
	
	/**
	 * Water flood from ocean to the cell.
	 * Since water can only flow from high/equal cell to low cell, 
	 * add the neighboor cell with height larger or equal to current cell to the queue and mark as visited.
	 * @param matrix
	 * @param queue
	 * @param visited
	 */
	public void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
		int m = matrix.length;// rows
		int n = matrix[0].length;// columns
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int[] d : dir) {
				int x = cur[0] + d[0];
				int y = cur[1] + d[1];
				if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]) {
					continue;
				}
				visited[x][y] = true;
				
				queue.offer(new int[]{x, y});
			}
		}
	}
	
	
	
	/**
	 * Start dfs from each boundary, then find common visited nodes.That's the answer.
	 * This method will cause stackoverflow error, Reason is not found.
	 * @param matrix
	 * @return
	 */
	public List<List<Integer>> pacificAtlanticV1(int[][] matrix) {
		List<List<Integer>> result = new ArrayList<>();
		if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
			return result;
		}
		
		int m = matrix.length;// rows
		int n = matrix[0].length;// columns
		boolean[][] pac = new boolean[m][n];
		boolean[][] atl = new boolean[m][n];
		
		
		// Vertical border(row changes but column not change)
		for (int i = 0; i < m; i++) {
			dfsV1(matrix, pac, i, 0, Integer.MIN_VALUE);
			dfsV1(matrix, atl, i, n-1, Integer.MIN_VALUE);
		}
		
		// Horizontal border(column changes but row not change)
		for (int i = 0; i < n; i++) {
			dfsV1(matrix, pac, 0, i, Integer.MIN_VALUE);
			dfsV1(matrix, atl, m-1, i, Integer.MIN_VALUE);
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
	
	public void dfsV1(int[][] matrix, boolean[][] visited, int x, int y, int height) {
		int m = matrix.length;
		int n = matrix[0].length;
		if (x < 0 || y < 0 || x >= m || x >= n || matrix[x][y] < height) {
			return;
		}
		visited[x][y] = true;
		for (int[] d : dir) {
			dfsV1(matrix, visited, x + d[0], y + d[1], matrix[x][y]);
		}
	}
	
	
}
