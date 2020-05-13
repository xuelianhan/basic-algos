package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, 
 * compute the volume of water it is able to trap after raining.
 * Example:
 * 
 * Given the following 3x6 height map:
 * [
 *   [1,4,3,1,3,2],
 *   [3,2,1,3,2,4],
 *   [2,3,3,2,3,1]
 * ]
 * 
 * Return 4.
 * 
 * Constraints:
 * 1 <= m, n <= 110
 * 0 <= heightMap[i][j] <= 20000
 *
 */
public class TrappingRainWaterTwo {

	public int trapRainWater(int[][] heightMap) {
		if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
			return 0;
		}
		PriorityQueue<Cell> queue = new PriorityQueue<>(1, new Comparator<Cell>() {
			@Override
			public int compare(Cell o1, Cell o2) {
				return o1.height - o2.height;// min-heap
			}
			
		});
		
		int m = heightMap.length;
		int n = heightMap[0].length;
		boolean[][] visited = new boolean[m][n];
		
		// Initially, add all the Cells which are on borders to the queue.
		// Vertical border(row changes but column not change)
		for (int i = 0; i < m; i++) {
			visited[i][0] = true;
			visited[i][n-1] = true;
			queue.offer(new Cell(i, 0, heightMap[i][0]));
			queue.offer(new Cell(i, n-1, heightMap[i][n-1]));
		}
		
		// Horizontal border(column changes but row not change)
		for (int i = 0; i < n; i++) {
			visited[0][i] = true;
			visited[m-1][i] = true;
			queue.offer(new Cell(0, i, heightMap[0][i]));
			queue.offer(new Cell(m-1, i, heightMap[m-1][i]));
		}
		
		// Pick the shortest cell visited from the borders and check its neighbors,
		// if the neighbor is shorter than current cell, then we need to collect the water
		// it can trap, and update with its height as current height plus with water trapped.
		int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		int res = 0;
		while (!queue.isEmpty()) {
			Cell cell = queue.poll();
			for (int[] dir : dirs) {
				int row = cell.row + dir[0];
				int col = cell.col + dir[1];
				if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col]) {
					continue;
				}
				// cell not visited, we collect water
				visited[row][col] = true;
				res += Math.max(0, cell.height - heightMap[row][col]);
				queue.offer(new Cell(row, col, Math.max(cell.height, heightMap[row][col])));
			}
		}
        return res;
    }
	
	public class Cell {
		int row; 
		int col;
		int height;
		
		public Cell(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}
	}
}
