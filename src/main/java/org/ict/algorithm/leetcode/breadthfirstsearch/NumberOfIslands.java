package org.ict.algorithm.leetcode.breadthfirstsearch;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.
 * Example 1:
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * Output: 1(all '1' as a whole consist the island)
 * 
 * Example 2:
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * Output: 3(three connect component)
 * LC200
 */
public class NumberOfIslands {
	
	private static final char CONNECT_SYMBOL = '1';
	
	private static final int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
			
	public int numIslandsV1(char[][] grid) {
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
		if (x >=0 && x < grid.length && y >=0 && y < grid[0].length) {
			return true;
		}
		return false;
	}
	
	class UnionFind {
		int[] parent;
		int m, n, count = 0;
		
		public UnionFind(char[][] grid) {
			m = grid.length;
			n = grid[0].length;
			parent = new int[m * n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] != CONNECT_SYMBOL) {
						continue;
					}
					int id = i * n + j;// skills here: change 2D-position to 1D-number.
					parent[id] = id;
					count++;
				}
			}
		}
		
		public int find(int p) {
			while(p != parent[p]) {
				parent[p] = parent[parent[p]];// path compression by halving
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
			// direct connect p's root to q's root, not consideration weight of each node.
			parent[rootP] = rootQ;
			count--;
		}
	}
	
}
