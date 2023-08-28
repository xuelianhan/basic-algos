package org.ict.algorithm.leetcode.depthfirstsearch;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n binary matrix grid.
 * An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 * The area of an island is the number of cells with a value 1 in the island.
 * Return the maximum area of an island in grid.
 * If there is no island, return 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *                [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *                [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *                [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *                [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *                [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *                [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *                [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 * @author sniper
 * @date 28 Aug 2023
 * LC695, Medium
 */
public class MaxAreaOfIsland {


    private int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static final int land = 1;

    /**
     * Union Find Solution
     * @param grid
     * @return
     */
    public int maxAreaOfIslandV6(int[][] grid) {
        //todo
        return 0;
    }

    /**
     * BFS Solution
     * Time Cost 3ms
     * @param grid
     * @return
     */
    public int maxAreaOfIslandV5(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] != land) {
                    continue;
                }
                res = Math.max(res, bfs(grid, i, j));
            }
        }
        return res;
    }

    private int bfs(int[][] grid, int i, int j) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        grid[i][j] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            res++;
            for (int[] dir : dirs) {
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                    continue;
                }
                grid[x][y] = 0;
                queue.offer(new int[] {x, y});
            }
        }
        return res;
    }

    /**
     * BFS Solution
     * Time Cost 3ms
     * @param grid
     * @return
     */
    public int maxAreaOfIslandV4(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] != land) {
                    continue;
                }
                int cnt = 0;
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[] {i, j});
                /**
                 * Marking (i, j) has been visited
                 */
                grid[i][j] *= -1;
                while (!q.isEmpty()) {
                    int[] t = q.poll();
                    res = Math.max(res, ++cnt);
                    for (int[] dir : dirs) {
                        int x = t[0] + dir[0];
                        int y = t[1] + dir[1];
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] <= 0) {
                            continue;
                        }
                        /**
                         * Marking (x, y) has been visited
                         */
                        grid[x][y] *= -1;
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }
        return res;
    }

    /**
     * BFS Solution
     * Time Cost 3ms
     * @param grid
     * @return
     */
    public int maxAreaOfIslandV3(int[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != land) {
                    continue;
                }

                int cnt = 0;
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] {i, j});
                /**
                 * Marking (i, j) has been visited
                 */
                grid[i][j] = 2;
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    cnt++;
                    res = Math.max(res, cnt);
                    for (int[] dir : dirs) {
                        int x = cur[0] + dir[0];
                        int y = cur[1] + dir[1];
                        /**
                         * Notice here is grid[x][y] != land, not grid[i][j] != land
                         */
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != land) {
                            continue;
                        }
                        /**
                         * Marking (x, y) has been visited
                         */
                        grid[x][y] = 2;
                        queue.offer(new int[] {x, y});
                    }
                }
            }
        }

        return res;
    }

    /**
     * DFS Solution
     * Time Cost 2ms
     * @param grid
     * @return
     */
    public int maxAreaOfIslandV2(int[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfsV2(grid, i, j));
            }
        }
        return res;
    }

    private int dfsV2(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if ( i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }
        if (grid[i][j] != land) {
            return 0;
        }
        /**
         * Marking (i, j) has been visited
         */
        grid[i][j] = 2;
        int sum = 1;
        for (int[] dir : dirs) {
            sum += dfsV2(grid, i + dir[0], j + dir[1]);
        }
        return sum;
    }


    /**
     * DFS Solution
     * Time Cost 2ms
     * @param grid
     * @return
     */
    public int maxAreaOfIslandV1(int[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfsV1(grid, i, j));
            }
        }
        return res;
    }

    private int dfsV1(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if ( i < 0 || i >= m || j < 0 || j >= n) {
            return 0;
        }
        if (grid[i][j] != land) {
            return 0;
        }
        /**
         * Marking (i, j) has been visited
         */
        grid[i][j] = 2;
        return 1 + dfsV1(grid, i + 1, j) + dfsV1(grid, i - 1, j) + dfsV1(grid, i, j + 1) + dfsV1(grid, i, j - 1);
    }

    /**
     * DFS Solution
     * Time Cost 2ms
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] res = new int[1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != land) {
                    continue;
                }
                int[] cnt = new int[1];
                dfs(grid, i, j, cnt, res);
            }
        }
        return res[0];
    }

    private void dfs(int[][] grid, int i, int j, int[] cnt, int[] res) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != land) {
            return;
        }
        cnt[0]++;
        res[0] = Math.max(res[0], cnt[0]);
        /**
         * Marking(i, j) has been visited(using -1 here)
         * You can use other value except 1 or 0
         */
        grid[i][j] = -1;
        for (int[] dir : dirs) {
            dfs(grid, i + dir[0], j + dir[1], cnt, res);
        }
    }
}
