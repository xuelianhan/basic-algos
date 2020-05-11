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
				return o1.height = o2.height;
			}
			
		});
		
		int m = heightMap.length;
		int n = heightMap[0].length;
		boolean[][] visited = new boolean[m][n];
		
        return 0;
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
