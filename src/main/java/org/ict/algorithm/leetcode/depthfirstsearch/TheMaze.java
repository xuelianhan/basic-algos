package org.ict.algorithm.leetcode.depthfirstsearch;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
 * The ball can go through the empty spaces by rolling up, down, left or right,
 * but it won't stop rolling until hitting a wall.
 * When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination,
 * where start = [start-row, start-col] and destination = [destination-row, destination-col],
 * return true if the ball can stop at the destination, otherwise return false.
 *
 * You may assume that the borders of the maze are all walls (see examples).
 *
 *
 *
 * Example 1:
 * Input: maze = [[0,0,1,0,0],
 *                [0,0,0,0,0],
 *                [0,0,0,1,0],
 *                [1,1,0,1,1],
 *                [0,0,0,0,0]],
 * start = [0,4], destination = [4,4]
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 * Example 2:
 *
 *
 *
 * Input: maze = [[0,0,1,0,0],
 *                [0,0,0,0,0],
 *                [0,0,0,1,0],
 *                [1,1,0,1,1],
 *                [0,0,0,0,0]],
 * start = [0,4], destination = [3,2]
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination.
 * Notice that you can pass through the destination, but you cannot stop there.
 * Example 3:
 *
 * Input: maze = [[0,0,0,0,0],
 *                [1,1,0,0,1],
 *                [0,0,0,0,0],
 *                [0,1,0,0,1],
 *                [0,1,0,0,0]],
 * start = [4,3], destination = [0,1]
 * Output: false
 *
 *
 * Constraints:
 * m == maze.length
 * n == maze[i].length
 * 1 <= m, n <= 100
 * maze[i][j] is 0 or 1.
 * start.length == 2
 * destination.length == 2
 * 0 <= start-row, destination-row <= m
 * 0 <= start-col, destination-col <= n
 * Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
 * The maze contains at least 2 empty spaces.
 * @author sniper
 * @date 20 Jun 2023
 * LC490, Medium, frequency=9
 */
public class TheMaze {

    public static void main(String[] args) {
        int[][] maze = {{0,0,1,0,0}, {0,0,0,0,0}, {0,0,0,1,0}, {1,1,0,1,1}, {0,0,0,0,0}};
        int[] start = {0, 4};
        int[] destination = {4, 4};

        //int[][] maze = {{0,0,1,0,0}, {0,0,0,0,0}, {0,0,0,1,0}, {1,1,0,1,1}, {0,0,0,0,0}};
        //int[] start = {0, 4};
        //int[] destination = {3, 2};

        TheMaze instance = new TheMaze();
        boolean res = instance.hasPathV3(maze, start, destination);
        System.out.println(res);
    }

    /**
     * Breadth-First-Search solution
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    public boolean hasPathV3(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;

        boolean[][] visited = new boolean[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {start[0], start[1]});
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            if (p[0] == destination[0] && p[1] == destination[1]) {
                return true;
            }

            for (int k = 0; k < 4; k++) {
                int x = p[0];
                int y = p[1];

                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += dirs[k];
                    y += dirs[k + 1];
                }

                x -= dirs[k];
                y -= dirs[k + 1];
                if (visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[] {x, y});
            }
        }
        return false;
    }


    /**
     * Breadth-First-Search solution
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    public boolean hasPathV2(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;

        boolean[][] visited = new boolean[m][n];
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(start[0], start[1]));
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (p.left == destination[0] && p.right == destination[1]) {
                return true;
            }

            for (int k = 0; k < 4; k++) {
                int x = p.left;
                int y = p.right;

                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += dirs[k];
                    y += dirs[k + 1];
                }

                x -= dirs[k];
                y -= dirs[k + 1];

                if (!visited[x][y]) {
                    visited[x][y] = true;
                    queue.offer(new Pair(x, y));
                }
            }
        }

        return false;
    }

    static class Pair {
        private int left;
        private int right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Understanding the following solution.
     * Depth-First-Search Solution with extra spaces.
     *
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    public boolean hasPathV1(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        return dfsV1(maze, visited, start[0], start[1], destination);
    }

    private boolean dfsV1(int[][] maze, boolean[][] visited, int i, int j, int[] destination) {
        if (i == destination[0] && j == destination[1]) {
            return true;
        }
        if (visited[i][j]) {
            return false;
        }

        visited[i][j] = true;
        int m = maze.length;
        int n = maze[0].length;
        for (int k = 0; k < 4; k++) {
            int x = i;
            int y = j;
            while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                x += dirs[k];
                y += dirs[k + 1];
            }

            x -= dirs[k];
            y -= dirs[k + 1];

            if (dfsV1(maze, visited, x, y, destination)) {
                return true;
            }
        }
        return false;
    }

    private int[] dirs = {-1, 0, 1, 0, -1};

    /**
     * Understanding the following solution.
     * Depth-First-Search solution without extra spaces.
     * ------------------------------------------------------
     * class Solution {
     * public:
     *     vector<vector<int>> dirs{{0,-1},{-1,0},{0,1},{1,0}};
     *
     *     bool hasPath(vector<vector<int>>& maze, vector<int>& start, vector<int>& destination) {
     *         return dfs(maze, start[0], start[1], destination[0], destination[1]);
     *     }
     *
     *     bool dfs(vector<vector<int>>& maze, int i, int j, int di, int dj) {
     *         if (i == di && j == dj) return true;
     *         bool res = false;
     *         int m = maze.size(), n = maze[0].size();
     *         maze[i][j] = -1;
     *         for (auto dir : dirs) {
     *             int x = i, y = j;
     *             while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] != 1) {
     *                 x += dir[0]; y += dir[1];
     *             }
     *             x -= dir[0]; y -= dir[1];
     *             if (maze[x][y] != -1) {
     *                 res |= helper(maze, x, y, di, dj);
     *             }
     *         }
     *         return res;
     *     }
     * };
     * ------------------------------------------------------
     *
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        return dfs(maze, start[0], start[1], destination[0], destination[1]);
    }

    private boolean dfs(int[][] maze, int i, int j, int destX, int destY) {
        if (i == destX && j == destY) {
            return true;
        }
        boolean res = false;
        int m = maze.length;
        int n = maze[0].length;
        /**
         * Marking (i, j) has been visited(using -1 here),
         * because the original array maze[i][j] is 0 or 1.
         */
        maze[i][j] = -1;
        for (int k = 0; k < 4; k++) {
            int x = i;
            int y = j;
            /**
             * Rolling the ball until hit the wall(1 means the wall)
             */
            while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] != 1) {
                x += dirs[k];
                y += dirs[k + 1];
            }
            /**
             * Go back on step after above while-loop exit.
             */
            x -= dirs[k];
            y -= dirs[k + 1];

            /**
             * If current (x, y) cell has not been visited, recursively access again at new position.
             */
            if (maze[x][y] != -1) {
                res |= dfs(maze, x, y, destX, destY);
            }
        }
        return res;
    }

}
