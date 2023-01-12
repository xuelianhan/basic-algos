package org.ict.algorithm.leetcode.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down.
 * You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[9,9,4],
 *                  [6,6,8],
 *                  [2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 *
 * Input: matrix = [[3,4,5],
 *                  [3,2,6],
 *                  [2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 *
 * Input: matrix = [[1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 2^31 - 1
 * @author sniper
 * @date 08 Jan, 2023
 * LC329, Hard
 */
public class LongestIncreasingPathInMatrix {

    public int longestIncreasingPathV2(int[][] matrix) {
        //todo
        //use dfs-topological-order.
        return 0;
    }

    /**
     * Topological Sort Solution.
     * Kahn Algorithm
     * The Longest Path in DAG
     * Time Cost 17 ms
     *
     * We regard
     * 1.a cell in the matrix as a node,
     * 2.a directed edge from node x to node y if x and y are adjacent and x's value < y's value
     * Then a graph is formed.
     *
     * No cycles can exist in the graph, i.e. a DAG is formed.
     *
     * The problem becomes to get the longest path in the DAG.
     *
     * Topological sort can iterate the vertices of a DAG in the linear ordering.
     *
     * Using Kahn's algorithm(BFS) to implement topological sort while counting the levels can give us the longest chain of nodes in the DAG.
     * @author GraceMeng.
     * @see <a href="https://leetcode.com/problems/longest-increasing-path-in-a-matrix/solutions/288520/longest-path-in-dag">Longest Path in DAG</a>
     * @param matrix
     * @return
     */
    public int longestIncreasingPathV1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] indegree = new int[m][n];
        int[][] dirs = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (matrix[x][y] > matrix[i][j]) {
                            /**
                             * Node(i, j) ---> Node(x, y)
                             */
                            indegree[x][y] += 1;
                        }
                    }
                }
            }
        }
        /**
         * Initialize the queue with all the nodes that their in-degree are zero.
         */
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (indegree[i][j] == 0) {
                    int[] node = {i, j};
                    queue.offer(node);
                }
            }
        }

        int length = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // Level traversal
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        /**
                         * Node(x, y) ---> Node(nx, ny)
                         */
                        if (matrix[nx][ny] > matrix[x][y]) {
                            indegree[nx][ny] -= 1;
                            if (indegree[nx][ny] == 0) {
                                queue.offer(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
            length++;
        }
        return length;
    }

    private static final int[][] dirs = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};

    /**
     * Understanding the following Solution.

     * Time Cost 9ms
     *
     * Traverse all points in matrix, use every point as starting point to do dfs traversal.
     * DFS function returns the max increasing path after comparing four distance from four directions.
     *
     * To get max length of increasing sequences:
     *
     * 1.Do DFS from every cell
     * 2.Compare every 4 direction and skip cells that are out of boundary or smaller
     * 3.Get matrix max from every cell's max
     * 4.Use matrix[x][y] <= matrix[i][j], so we don't need a visited[m][n] array.
     * 5.The key is to cache the distance because it's highly possible to revisit a cell
     * @author yavinci
     * @see <a href="https://leetcode.com/problems/longest-increasing-path-in-a-matrix/solutions/78308/15ms-concise-java-solution"></a>
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i , int j, int m, int n, int[][] cache) {
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        int max = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) {
                continue;
            }
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
}