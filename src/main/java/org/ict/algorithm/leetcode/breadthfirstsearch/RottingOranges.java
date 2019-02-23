package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * In a given grid, each cell can have one of three values:
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * 
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 *  *Input: [[2,1,1],
 *           [1,1,0],
 *           [0,1,1]]
 * Output: 4
 * Example 2:
 * 
 * Input: [[2,1,1],
 *         [0,1,1],
 *         [1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 * 
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 *  
 * 
 * Note:
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] is only 0, 1, or 2.
 * 
 * LC
 * @see https://practice.geeksforgeeks.org/problems/rotten-oranges/0
 * @see https://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/
 */
public class RottingOranges {
	
	public static void main(String[] args) {
		//int[][] grid = {{2,1,1}, {1,1,0}, {0,1,1}};
		//int[][] grid = {{0,2}};
		//int[][] grid = {{2,1,1}, {0,1,1}, {1,0,1}};
		//int[][] grid = {{1}};
		int[][] grid = {{1,2}};
		int min = orangesRotting(grid);
		System.out.println(min);
	}
	
	// The inaccessible flag
	private static final int inaccessible = 0;
	private static final int fresh = 1;
	private static final int rotten = 2;
	
	public static int orangesRotting(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return -1;
		}
		int freshCount = 0;
		Queue<int[]> queue = new LinkedList<>();
		/* Collect all rotten oranges into queue */
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == rotten) {
					queue.offer(new int[] {i, j, grid[i][j]});
				}
				if (grid[i][j] == fresh) {
					freshCount++;
				}
			}
		}
		/* If has no fresh orange, return 0 */
		if (freshCount == 0) {
			return 0;
		}
		/* If has no rotten orange, return -1 */
		if (queue.size() == 0) {
			return -1;
		}
		
	    int minMinutes = bfs(grid, queue, freshCount);
	    return minMinutes;
	}
	
	private static int bfs(int[][] grid, Queue<int[]> queue, int freshCount) {
		int[][] dir = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		
		int minMinutes = -1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				visited[cur[0]][cur[1]] = true;
				for (int j = 0; j < dir.length; j++) {
					int x = cur[0] + dir[j][0];
					int y = cur[1] + dir[j][1];
					if (!isFree(x, y, grid)) {
						continue;
					}
					if (!visited[x][y]) {
						visited[x][y] = true;
						if (grid[x][y] == fresh) {
							grid[x][y] = rotten;
							freshCount--;
						}
						queue.add(new int[] {x, y, grid[x][y]});
					}
				}
			}
			minMinutes++;
		}
		return (freshCount == 0 ? minMinutes : -1);
	}
	
	private static boolean isFree(int x, int y, int[][] grid) {
		if ((x >=0 && x < grid.length) && 
		    (y >=0 && y < grid[x].length) &&
			(grid[x][y] != inaccessible)) {
			return true;
		}
		return false;
	}
}
