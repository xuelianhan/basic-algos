package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix consists of 0 and 1, 
 * find the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 * Example 1:
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 * 
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 * Example 2:
 * 
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,1,1]]
 * 
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,2,1]]
 * 
 * Note:
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 * 
 * LC542
 */
public class ZeroOneMatrix {
	
	public int[][] updateMatrixV2(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (matrix[i][j] == 0) {
        			queue.offer(new int[]{i, j});
        			visited[i][j] = true;
        		}
        	}
        }
        
        int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        while(!queue.isEmpty()) {
        	int[] cell = queue.poll();
        	for (int[] dir : dirs) {
        		int r = cell[0] + dir[0];
        		int c = cell[1] + dir[1];
        		if (r < 0 || r >= m || c < 0 || c >= n) {
        			continue;
        		}
        		if (visited[r][c]) {
        			continue;
        		}
        		
        		visited[r][c] = true;
        		matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
        		queue.add(new int[]{r, c});
        	}
        }
        return matrix;
	}

	/**
	 * 1.At beginning, set cell value to Integer.MAX_VALUE(to mark unvisited cells) if it is not 0;
	 * 2.If newly calculated distance >= current distance,
	 * then we don't need to explore that cell again.
	 * Because we need to find the nearest distance to 0-cell
	 * 
	 * This question is very similar with 286 Walls and Gates, same logic.
	 * @param matrix
	 * @return
	 */
	public int[][] updateMatrixV1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (matrix[i][j] == 0) {
        			queue.offer(new int[]{i, j});
        		} else {
        			matrix[i][j] = Integer.MAX_VALUE;
        		}
        	}
        }
        
        int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        while(!queue.isEmpty()) {
        	int[] cell = queue.poll();
        	for (int[] dir : dirs) {
        		int r = cell[0] + dir[0];
        		int c = cell[1] + dir[1];
        		if (r < 0 || r >= m || c < 0 || c >= n) {
        			continue;
        		}
        		if (matrix[r][c] <= matrix[cell[0]][cell[1]] + 1) {
        			continue;
        		}
        		queue.add(new int[]{r, c});
        		matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
        	}
        }
        return matrix;
    }

	/**
	 * Multiple-Source BFS
	 * ---------------------------------------
	 * class Solution:
	 *     def updateMatrix(self, mat: List[List[int]]) -> List[List[int]]:
	 *         m, n = len(mat), len(mat[0])
	 *         res = [[-1] * n for _ in range(m)]
	 *         queue = deque()
	 *         for i, row in enumerate(mat):
	 *             for j, v in enumerate(row):
	 * 				  if v == 0:
	 * 				    res[i][j] = 0
	 * 					queue.append((i, j))
	 * 		  dirs = [(0, 1), (0, -1), (-1, 0), (1, 0)]
	 * 		  while queue:
	 * 		      i, j = queue.popleft()
	 * 		      for a, b in in dirs:
	 * 		          x, y = i + a, j + b
	 * 		          if 0 <= x < m and 0 <= y < n and res[x][y] = -1:
	 * 		              res[x][y] = res[i][j] + 1
	 * 		              queue.append((x, y))
	 * 		  return res
	 *
	 * @param matrix
	 * @return
	 */
	public int[][] updateMatrix(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		int[][] res = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(res[i], -1);
		}
		Deque<int[]> queue = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					res[i][j] = 0;
					queue.offer(new int[]{i, j});
				}
			}
		}
		int[] dirs = new int[]{-1, 0, 1, 0, -1};
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int i = cur[0];
			int j = cur[1];
			for (int k = 0; k < 4; k++) {
				int x = i + dirs[k];
				int y = j + dirs[k + 1];
				if (x >= 0 && x < m && y >= 0 && y < n && res[x][y] == -1) {
					res[x][y] = res[i][j] + 1;
					queue.offer(new int[] {x, y});
				}
			}
		}
		return res;
	}
}
