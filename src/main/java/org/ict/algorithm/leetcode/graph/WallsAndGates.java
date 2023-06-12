package org.ict.algorithm.leetcode.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * You are given an m x n grid rooms initialized with these three possible values.
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647
 * to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate.
 * If it is impossible to reach a gate, it should be filled with INF.
 *
 *
 *
 * Example 1:
 * Input: rooms = [[2147483647,-1,0,2147483647],
 *                 [2147483647,2147483647,2147483647,-1],
 *                 [2147483647,-1,2147483647,-1],
 *                 [0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1], [2,2,1,-1], [1,-1,2,-1], [0,-1,3,4]]
 * Example 2:
 *
 * Input: rooms = [[-1]]
 * Output: [[-1]]
 *
 * Constraints:
 * m == rooms.length
 * n == rooms[i].length
 * 1 <= m, n <= 250
 * rooms[i][j] is -1, 0, or 2^31 - 1.
 * @author sniper
 * @date 12 Jun 2023
 * LC286, Medium, frequency=16
 */
public class WallsAndGates {

    public static void main(String[] args) {
        int[][] rooms = {
                {2147483647,-1,0,2147483647},
                {2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},
                {0,-1,2147483647,2147483647}
        };
        WallsAndGates instance = new WallsAndGates();
        instance.wallsAndGatesV3(rooms);
        System.out.println(Arrays.deepToString(rooms));
    }

    /**
     * Breadth-First-Search
     * @param rooms
     */
    public void wallsAndGatesV3(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                }
            }
        }

        int[] dirs = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            for (int k = 0; k < 4; k++) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x < 0 || x >= m || y < 0 || y >= n || rooms[x][y] != Integer.MAX_VALUE) {
                    continue;
                }
                rooms[x][y] = rooms[i][j] + 1;
                queue.offer(new int[] {x, y});
            }
        }
    }

    /**
     * Breadth-First-Search
     * @param rooms
     */
    public void wallsAndGatesV2(int[][] rooms) {
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                }
            }
        }

        int[] dirs = {-1, 0, 1, 0, -1};
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            for (int i = queue.size(); i > 0; i--) {
                int[] cur = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int x = cur[0] + dirs[k];
                    int y = cur[1] + dirs[k + 1];
                    if (x >= 0 && x < rooms.length && y >= 0 && y < rooms[0].length && rooms[x][y] == Integer.MAX_VALUE) {
                        rooms[x][y] = level;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
    }

    /**
     * Breadth-First-Search
     * @param rooms
     */
    public void wallsAndGatesV1(int[][] rooms) {
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                }
            }
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            for (int k = 0; k < dirs.length; k++) {
                int x = i + dirs[k][0];
                int y = j + dirs[k][1];
                if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] < rooms[i][j] + 1) {
                    continue;
                }
                rooms[x][y] = rooms[i][j] + 1;
                queue.offer(new int[]{x, y});
            }
        }
    }

    /**
     * Depth-First-Search
     * @param rooms
     */
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int i, int j, int val) {
        /**
         * Notice room[i][j] < val instead of < 0 here
         */
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < val) {
            return;
        }
        rooms[i][j] = val;
        dfs(rooms, i + 1, j, val + 1);
        dfs(rooms, i - 1, j, val + 1);
        dfs(rooms, i, j + 1, val + 1);
        dfs(rooms, i, j - 1, val + 1);
    }
}
