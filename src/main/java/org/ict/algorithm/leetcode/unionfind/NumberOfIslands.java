package org.ict.algorithm.leetcode.unionfind;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * Example 2:
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 *
 * Similar Question
 * @see NumberOfEnclaves
 * @see NumberOfClosedIslands
 *
 * LC200, Medium, frequency=306, High Frequency Interview Problem
 * Tag: Amazon
 */
public class NumberOfIslands {

	/**
	 * Depth-First-Search
	 * Iterate through each of the cell and if it is an island,
	 * do dfs to mark all adjacent islands,
	 * then increase the counter by 1.
	 * ---------------------------------
	 * class Solution:
	 *     def numIslands(self, grid: List[List[str]]) -> int:
	 *         if not grid:
	 *             return 0
	 *         res = 0
	 *         for i in range(len(grid)):
	 *             for j in range(len(grid[0])):
	 *                 if grid[i][j] == '1':
	 *                     self.dfs(grid, i, j)
	 *                     res += 1
	 *         return res
	 *
	 *     def dfs(self, grid, i, j):
	 *         if i < 0 or j < 0 or i >= len(grid) or j >= len(grid[0]) or grid[i][j] != '1':
	 *             return
	 *         grid[i][j] = '#'
	 *         self.dfs(grid, i - 1, j)
	 *         self.dfs(grid, i + 1, j)
	 *         self.dfs(grid, i, j - 1)
	 *         self.dfs(grid, i, j + 1)
	 * ------------------------------------------
	 * object Solution {
	 *     def numIslands(grid: Array[Array[Char]]): Int = {
	 *         if (grid == null) {
	 *             return 0
	 *         }
	 *         var res = 0
	 *         var m = grid.length
	 *         var n = grid(0).length
	 *         for (i <- 0 until m) {
	 *             for (j <- 0 until n) {
	 *                 if (grid(i)(j) == '1') {
	 *                     dfs(grid, i, j)
	 *                     res += 1
	 *                 }
	 *             }
	 *         }
	 *         res
	 *     }
	 *
	 *     def dfs(grid: Array[Array[Char]], i: Int, j: Int) {
	 *         if (i < 0 || j < 0 || i >= grid.length || j >= grid(0).length || grid(i)(j) != '1') {
	 *             return
	 *         }
	 *         grid(i)(j) = '0'
	 *         dfs(grid, i - 1, j)
	 *         dfs(grid, i + 1, j)
	 *         dfs(grid, i, j - 1)
	 *         dfs(grid, i, j + 1)
	 *     }
	 * }
	 * ---------------------------
	 * @param grid
	 * @return
	 */
	public int numIslandsV2(char[][] grid) {
		if (null == grid) {
			return 0;
		}
		int res = 0;
		int m = grid.length;
		int n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					dfs(grid, i, j);
					res += 1;
				}
			}
		}
		return res;
	}

	private void dfs(char[][] grid, int i, int j) {
		/**
		 * grid[i][j] != '1' means grid[i][j] has been visited.
		 */
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') {
			return;
		}
		/**
		 * Mark land grid[i][j] has been visited, here using '#'
		 * Actually it just needs to be not 1, e.g. using '0' here is OK.
		 */
		grid[i][j] = '#';
		dfs(grid, i - 1, j);
		dfs(grid, i + 1, j);
		dfs(grid, i, j - 1);
		dfs(grid, i, j + 1);
	}

	/**
	 * Breadth-First-Search Solution
	 * Time Cost 4ms
	 * Iterate through each of the cell and if it is an island,
	 * do BFS to mark all adjacent islands,
	 * then increase the counter by 1.
	 * At here, we use grid itself as visited array, so it doesn't need
	 * visited array anymore.
	 * @param grid
	 * @return
	 */
	public int numIslandsV1(char[][] grid) {
		int res = 0;
		int m = grid.length;
		int n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					bfs(grid, i, j);
					res += 1;
				}
			}
		}
		return res;
	}

	private void bfs(char[][] grid, int i, int j) {
		grid[i][j] = '0';
		Deque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {i, j});
		int[] dir = {-1, 0, 1, 0, -1};
		int m = grid.length;
		int n = grid[0].length;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int k = 0; k < 4; k++) {
				int x = cur[0] + dir[k];
				int y = cur[1] + dir[k + 1];
				/**
				 * grid[x][y] != '1' means grid[x][y] has been visited.
				 */
				if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != '1') {
					continue;
				}
				grid[x][y] = '0';
				queue.offer(new int[] {x, y});
			}
		}
	}

	private static final char CONNECT_SYMBOL = '1';
	
	private static final int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};

	/**
	 * Union-Find Solution
	 * @param grid
	 * @return
	 */
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}
		UnionFind uf = new UnionFind(grid);
		int rows = grid.length;
		int cols = grid[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] != CONNECT_SYMBOL) {
					continue;
				}
				for (int[] d : direction) {
					int x = i + d[0];
					int y = j + d[1];
					if (!inRegion(x, y, grid)) {
						continue;
					}
					if (grid[x][y] == CONNECT_SYMBOL) {
						int p = i * cols + j;
						int q = x * cols + y;
						uf.union(p, q);
					}
				}
			}
		}
		return uf.count;
	}
	
	private boolean inRegion(int x, int y, char[][] grid) {
		if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
			return true;
		}
		return false;
	}
	
	class UnionFind {
		int[] parent;
		int count = 0;
		
		public UnionFind(char[][] grid) {
			int m = grid.length;
			int n = grid[0].length;
			parent = new int[m * n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] != CONNECT_SYMBOL) {
						continue;
					}
					/**
					 * skills here: convert 2D-position to 1D-number.
					 */
					int id = i * n + j;
					parent[id] = id;
					count++;
				}
			}
		}

		/**
		 * path compression by halving
		 */
		public int find(int p) {
			while(p != parent[p]) {
				parent[p] = parent[parent[p]];
				p = parent[p];
			}
			return p;
		}
		
		public void union(int p, int q) {
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ) {
				return;
			}
			/**
			 * direct connect p's root to q's root, not consideration weight of each node.
			 */
			parent[rootP] = rootQ;
			count--;
		}
	}
	
}
