package org.ict.algorithm.leetcode.unionfind;


import java.util.ArrayDeque;
import java.util.Queue;

/**
 * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
 * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
 * Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
 * Example 1:
 * Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
 *
 * Example 2:
 * Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation: All 1s are either on the boundary or can reach the boundary.
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] is either 0 or 1.
 * @author sniper
 * @date 07 Apr, 2023
 * LC1020, Medium
 */
public class NumberOfEnclaves {

    public static void main(String[] args) {
        int[][] grid = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        NumberOfEnclaves instance = new NumberOfEnclaves();
        int res = instance.numEnclavesV1(grid);
        System.out.println(res);
    }

    /**
     * Breadth-First-Search Solution
     * Time Cost 12ms
     * @param grid
     * @return
     */
    public int numEnclavesV4(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res += grid[i][j];
                /**
                 * Put boundary (i, j) into the queue.
                 */
                if (i * j == 0 || i == m - 1 || j == n - 1) {
                    queue.offer(new int[] {i, j});
                }
            }
        }
        /**
         * Start from the land of boundary(cell value 1 represents land).
         * Change grid[x][y] from 1 to 0, and decrease the total count of 1 variable res.
         */
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) {
                continue;
            }
            grid[x][y] = 0;
            res--;
            for (int[] d : direction) {
                queue.offer(new int[] {x + d[0], y + d[1]});
            }
        }
        return res;
    }

    /**
     * Depth-First-Search Solution
     * @param grid
     * @return
     */
    public int numEnclavesV3(int[][] grid) {
        return 0;
    }

    public int numEnclavesV2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    uf.union(i * n + j, m * n);
                } else {
                    for (int[] d : direction) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (grid[x][y] == 1) {
                            int p = i * n + j;
                            int q = x * n + y;
                            uf.union(p, q);
                        }
                    }
                }
            }
        }
        int res = 0;
        int reachableLoc = uf.find(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && uf.find(i * n + j) != reachableLoc) {
                    res++;
                }
            }
        }
        return res;
    }


    /**
     * Union-Find Solution
     * Time Cost 26ms
     *
     * In this problem, we don't need to count the connected component, what we need is how many cells in the component.
     * We can use a formula to flatten the two-dimensions array into one-dimension array in the union-find class:
     * index = i * n + j
     * We don't care these cells with value not equals 1(because 0 is sea, 1 is land, we can walk on land while we can't walk directly on sea)
     * We need to return isolate cells with 1, this cells cannot reach the boundary.
     * This means the cells with 1 on the boundary and connected cells with these cell-1 on boundary need to be marked alone(At here we marked them as m * n).
     * The normal cells with 1, we mark them to x*n + y.
     * At last, we count the cells with 1, and it's parent array value not equals to m * n, that's the result we need.
     * m * n may be used as index of array, so the parent array in UnionFind class
     * need m * n + 1 spaces to prevent index of bound.
     * [0, 1, 2, 3, 16, 5, 10, 7, 8, 10, 10, 11, 12, 13, 14, 15, 16]
     * @param grid
     * @return
     */
    public int numEnclavesV1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    uf.union(i * n + j, m * n);
                } else {
                    for (int[] d : direction) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (grid[x][y] == 1) {
                            int p = i * n + j;
                            int q = x * n + y;
                            uf.union(p, q);
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && uf.find(i * n + j) != uf.find(m * n)) {
                    res++;
                }
            }
        }
        return res;
    }


    class UnionFind {
        int[] parent;
        int count;//no used in this problem

        public UnionFind(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            /**
             * Set as m * n + 1 to prevent index out of bound.
             */
            parent = new int[m * n + 1];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
                count++;
            }
        }

        /**
         *  path compression by halving
         */
        public int find(int p) {
            while(p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        /**
         * direct connect p's root to q's root, not consideration weight of each node.
         */
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            parent[rootP] = rootQ;
            count--;
        }
    }

    /**
     * Union-Find Solution
     * [0, 1, 2, 3, 16, 5, 10, 7, 8, 10, 10, 11, 12, 13, 14, 15, 16]
     * Union-Find Solution
     * Time Cost 18ms
     * @param grid
     * @return
     */
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        /**
         * union-find use m*n as array access index,
         * so it needs to plus one more space.
         */
        int[] parent = new int[m * n + 1];
        for (int i = 0; i < m * n + 1; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                /**
                 * skills here: change 2D-position to 1D-number.
                 * int id = i * n + j;
                 * 0  1  2  3
                 * 4  5  6  7
                 */
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    union(parent, i * n + j, m * n);
                } else {
                    if (i > 0 && grid[i - 1][j] == 1) {
                        union(parent, i * n + j, (i - 1) * n + j);
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        union(parent, i * n + j, i * n + j - 1);
                    }
                    if (i < m - 1 && grid[i + 1][j] == 1) {
                        union(parent, i * n + j, (i + 1) * n + j);
                    }
                    if (j < n - 1 && grid[i][j + 1] == 1) {
                        union(parent, i * n + j, i * n + j + 1);
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && find(parent, i * n + j) != find(parent, m * n)) {
                    count++;
                }
            }
        }
        return count;
    }

    private void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
