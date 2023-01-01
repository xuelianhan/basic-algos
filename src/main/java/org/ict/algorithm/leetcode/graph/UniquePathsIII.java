package org.ict.algorithm.leetcode.graph;

/**
 * You are given an m x n integer array grid where grid[i][j] could be:
 *
 * 1 representing the starting square.
 * There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square,
 * that walk over every non-obstacle square exactly once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * Example 2:
 *
 *
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * Example 3:
 *
 *
 * Input: grid = [[0,1],[2,0]]
 * Output: 0
 * Explanation: There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * 1 <= m * n <= 20
 * -1 <= grid[i][j] <= 2
 * There is exactly one starting cell and one ending cell.
 * @author sniper
 * @date 01 Jan, 2023
 * LC980
 */
public class UniquePathsIII {

    public int uniquePathsIIIV3(int[][] grid) {
        int[] emptyCount = new int[1];
        /**
         * the source can be walked, so occupies one
         */
        emptyCount[0] = 1;
        int sx = 0;
        int sy = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    emptyCount[0]++;
                } else if (grid[i][j] == 1) {
                    /**
                     * select j as sx, and i as sy.
                     */
                    sx = j;
                    sy = i;
                }
            }
        }
        return dfsV3(grid, sx, sy, emptyCount);
    }

    private int dfsV3(int[][] grid, int x, int y, int[] emptyCount) {
        /**
         * Notice here, we check whether the index out of bound, and grid[y][x] equals -1.
         * grid[y][x] can be written as grid[y][x] == - 1, because we set grid[y][x] to -1
         * at the following codes.
         */
        if (x < 0 || x == grid[0].length || y < 0 || y == grid.length || grid[y][x] == -1) {
            return 0;
        }
        if (grid[y][x] == 2) {
            if (emptyCount[0] == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        grid[y][x] = -1;
        emptyCount[0]--;
        int res = 0;
        res += dfsV3(grid, x - 1, y, emptyCount);
        res += dfsV3(grid, x + 1, y, emptyCount);
        res += dfsV3(grid, x, y - 1, emptyCount);
        res += dfsV3(grid, x, y + 1, emptyCount);
        grid[y][x] = 0;
        emptyCount[0]++;
        return res;
    }


    public int uniquePathsIIIV2(int[][] grid) {
        int[] emptyCount = new int[1];
        /**
         * the source can be walked, so occupies one
         */
        emptyCount[0] = 1;
        int sx = 0;
        int sy = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    emptyCount[0]++;
                } else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                }
            }
        }
        return dfsV2(grid, sx, sy, emptyCount);
    }

    private int dfsV2(int[][] grid, int x, int y, int[] emptyCount) {
        /**
         * Notice here, we check whether the index out of bound, and grid[x][y] < 0.
         * grid[x][y] cannot be written as grid[x][y] == - 1, because we set grid[x][y] to -2
         * at the following codes.
         */
        if (x < 0 || x == grid.length || y < 0 || y == grid[0].length || grid[x][y] < 0) {
            return 0;
        }
        if (grid[x][y] == 2) {
            if (emptyCount[0] == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        grid[x][y] = -2;
        emptyCount[0]--;
        int res = 0;
        res += dfsV2(grid, x - 1, y, emptyCount);
        res += dfsV2(grid, x + 1, y, emptyCount);
        res += dfsV2(grid, x, y - 1, emptyCount);
        res += dfsV2(grid, x, y + 1, emptyCount);
        grid[x][y] = 0;
        emptyCount[0]++;
        return res;
    }


    /**
     * Depth-First-Search Solution without Global variable.
     * Notice this problem cannot be using BFS to solve.
     * @param grid
     * @return
     */
    public int uniquePathsIIIV1(int[][] grid) {
        int[] emptyCount = new int[1];
        /**
         * the source can be walked, so occupies one
         */
        emptyCount[0] = 1;
        int sx = 0;
        int sy = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    emptyCount[0]++;
                } else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                }
            }
        }
        return dfsV1(grid, sx, sy, emptyCount, new boolean[m][n]);
    }

    private int dfsV1(int[][] grid, int x, int y, int[] emptyCount, boolean[][] visited) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == -1 || visited[x][y]) {
            return 0;
        }
        if (grid[x][y] == 2) {
            if (emptyCount[0] == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int res = 0;
        /**
         * Use visit[x][y] to replace with grid[x][y] = -2
         */
        visited[x][y] = true;
        emptyCount[0]--;
        res += dfsV1(grid, x - 1, y, emptyCount, visited);
        res += dfsV1(grid, x + 1, y, emptyCount, visited);
        res += dfsV1(grid, x, y - 1, emptyCount, visited);
        res += dfsV1(grid, x, y + 1, emptyCount, visited);
        visited[x][y] = false;
        emptyCount[0]++;
        return res;
    }


    /**
     * Depth-First-Search Solution
     *
     * First find out where the start index, also We need to know the number of empty cells.
     *
     * we try to explore a cell,
     * it will change 0 to -2 and do a dfs in 4 direction.
     * If we hit the target and pass all empty cells, increment the result.
     *
     * @author lee215
     * @param grid
     * @return
     */
    int res = 0;
    /**
     * the start cell can be walked, so it occupies one
     */
    int emptyCount = 1;
    public int uniquePathsIII(int[][] grid) {
        /**
         * the start index(grid[i][j] == 1)
         */
        int sx = 0;
        int sy = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    emptyCount++;
                } else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                }
            }
        }
        dfs(grid, sx, sy);
        return res;
    }

    public void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] < 0) {
            return;
        }
        if (grid[x][y] == 2) {
            if (emptyCount == 0) {
                res++;
            }
            return;
        }
        /**
         * Set any value except (-1, 0, 1, 2)
         */
        grid[x][y] = -2;
        emptyCount--;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
        grid[x][y] = 0;
        emptyCount++;
    }


}
