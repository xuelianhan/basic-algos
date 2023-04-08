package org.ict.algorithm.leetcode.unionfind;

import java.util.Arrays;

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
        int res = instance.numEnclaves(grid);
        System.out.println(res);
    }


    private static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int numEnclavesV3(int[][] grid) {
        return 0;
    }

    public int numEnclavesV2(int[][] grid) {
        return 0;
    }

    /**
     * Union-Find Solution
     * @param grid
     * @return
     */
    public int numEnclavesV1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(grid, 1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                for (int[] d : direction) {
                    int x = i + d[0];
                    int y = j + d[1];
                    if (!inRegion(x, y, grid)) {
                        continue;
                    }
                    if (grid[x][y] == 1) {
                        int p = i * n + j;
                        int q = x * n + y;
                        uf.union(p, q);
                    }
                }
            }
        }
        System.out.println(Arrays.toString(uf.parent));
        return 0;
    }

    private boolean inRegion(int x, int y, int[][] grid) {
        if (x >=0 && x < grid.length && y >=0 && y < grid[0].length) {
            return true;
        }
        return false;
    }

    class UnionFind {
        int[] parent;
        int count;

        public UnionFind(int[][] grid, int connectSymbol) {
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] != connectSymbol) {
                        continue;
                    }
                    /**
                     * skills here: change 2D-position to 1D-number.
                     * int id = i * n + j;
                     * parent[id] = id;
                     * count++;
                     */
                }
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
     * [0, 1, 2, 3, 16, 5, 10, 7, 8, 10, 10, 11, 12, 13, 14, 15, 16]
     * Union-Find Solution
     * Time Cost 18ms
     * @param grid
     * @return
     */
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] parent = new int[m * n + 1];
        for (int i = 0; i < m * n + 1; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
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
        //System.out.println(Arrays.toString(parent));
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
