package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.*;

/**
 * You are given an n x n integer matrix board where the cells labeled from 1 to n^2
 * in a Boustrophedon style starting from the bottom left of the board (i.e. board[n - 1][0])
 * and alternating direction each row.
 *
 * You start on square 1 of the board.
 * In each move, starting from square curr, do the following:
 *
 * Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
 * This choice simulates the result of a standard 6-sided die roll: i.e.,
 * there are always at most 6 destinations, regardless of the size of the board.
 * If next has a snake or ladder, you must move to the destination of that snake or ladder.
 * Otherwise, you move to next.
 * The game ends when you reach the square n2.
 * A board square on row r and column c has a snake or ladder if board[r][c] != -1.
 * The destination of that snake or ladder is board[r][c].
 * Squares 1 and n2 do not have a snake or ladder.
 *
 * Note that you only take a snake or ladder at most once per move.
 * If the destination to a snake or ladder is the start of another snake or ladder,
 * you do not follow the subsequent snake or ladder.
 *
 * For example, suppose the board is [[-1,4],[-1,3]],
 * and on the first move, your destination square is 2.
 * You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
 * Return the least number of moves required to reach the square n2.
 * If it is not possible to reach the square, return -1.
 *
 *
 * Example 1:
 * Input: board = [[-1,-1,-1,-1,-1,-1],
 *                 [-1,-1,-1,-1,-1,-1],
 *                 [-1,-1,-1,-1,-1,-1],
 *                 [-1,35,-1,-1,13,-1],
 *                 [-1,-1,-1,-1,-1,-1],
 *                 [-1,15,-1,-1,-1,-1]]
 * Output: 4
 * Explanation:
 * In the beginning, you start at square 1 (at row 5, column 0).
 * You decide to move to square 2 and must take the ladder to square 15.
 * You then decide to move to square 17 and must take the snake to square 13.
 * You then decide to move to square 14 and must take the ladder to square 35.
 * You then decide to move to square 36, ending the game.
 * This is the lowest possible number of moves to reach the last square, so return 4.
 *
 * Example 2:
 * Input: board = [[-1,-1],
 *                 [-1,3]]
 * Output: 1
 *
 *
 * Constraints:
 * n == board.length == board[i].length
 * 2 <= n <= 20
 * grid[i][j] is either -1 or in the range [1, n^2].
 * The squares labeled 1 and n^2 do not have any ladders or snakes.
 *
 * @author sniper
 * @date 24 Jan, 2023
 * LC909, medium
 */
public class SnakesAndLadders {


    /**
     * Understanding the following solution
     * Time Cost 6ms
     * -------------------------------
     * class Solution:
     *     def snakesAndLadders(self, board: List[List[int]]) -> int:
     *         def getPosition(cur):
     *             x, y = (cur - 1) // n, (cur - 1) % n
     *             if x % 2 == 1:
     *                 y = n - 1 - y
     *             x = n - 1 - x
     *             return x, y
     *         n = len(board)
     *         queue = deque([1])
     *         visited = {1}
     *         res = 0
     *         while queue:
     *             for _ in range(len(queue)):
     *                 cur = queue.popleft()
     *                 if cur == n * n:
     *                     return res
     *                 for next in range(cur + 1, min(cur + 7, n * n + 1)):
     *                     x, y = getPosition(next)
     *                     if board[x][y] != -1:
     *                         next = board[x][y]
     *                     if next not in visited:
     *                         visited.add(next)
     *                         queue.append(next)
     *             res += 1
     *         return -1
     * --------------------------------------------
     * @param board
     * @return
     */
    public int snakesAndLaddersV2(int[][] board) {
        int n = board.length;
        int res = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        /**
         * index-next start from 1, index-0 not used, so visited size is n * n + 1
         */
        boolean[] visited = new boolean[n * n + 1];
        while (!queue.isEmpty()) {
            for (int k = queue.size(); k > 0; k--) {
                int cur = queue.poll();
                if (cur == n * n) {
                    return res;
                }
                for (int t = cur + 1; t <= Math.min(cur + 6, n * n); t++) {
                    int next = getPositionV1(board, t);
                    if (next == -1) {
                        next = t;
                    }
                    if (visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    queue.offer(next);
                }
            }
            res++;
        }
        return -1;
    }


    /**
     * Understanding the following solution
     * Time Cost 5ms
     * Time Complexity O(N^2)
     * Space Complexity O(N)
     * -------------------------------------
     * class Solution {
     * public:
     *     int snakesAndLadders(vector<vector<int>>& board) {
     *         int n = board.size();
     *         int res = 0;
     *         queue<int> q{{1}};
     *         vector<bool> visited(n * n + 1);
     *         while (!q.empty()) {
     *             for (int k = q.size(); k > 0; k--) {
     *                 int cur = q.front();
     *                 q.pop();
     *                 if (cur == n * n) {
     *                     return res;
     *                 }
     *                 for (int i = 1; i <= 6 && cur + i <= n * n; ++i) {
     *                     int next = getBoardValue(board, cur + i);
     *                     if (next == -1) {
     *                         next = cur + i;
     *                     }
     *                     if (visited[next]) {
     *                         continue;
     *                     }
     *                     visited[next] = true;
     *                     q.push(next);
     *                 }
     *             }
     *             ++res;
     *         }
     *         return -1;
     *     }
     *
     * private:
     *     int getBoardValue(vector<vector<int>>& board, int cur) {
     *         int n = board.size();
     *         int x = (cur - 1) / n;
     *         int y = (cur - 1) % n;
     *         if (x % 2 == 1) {
     *             y = n - 1 - y;
     *         }
     *         x = n - 1 - x;
     *         return board[x][y];
     *     }
     * };
     * @param board
     * @return
     */
    public int snakesAndLaddersV1(int[][] board) {
        int n = board.length;
        int res = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        boolean[] visited = new boolean[n * n  + 1];
        while (!queue.isEmpty()) {
            for (int k = queue.size(); k > 0; k--) {
                int cur = queue.poll();
                if (cur == n * n) {
                    return res;
                }
                for (int i = 1; i <= 6 && cur + i <= n * n; i++) {
                    int next = getPositionV1(board, cur + i);
                    /**
                     * Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n^2)]
                     * board[i][j] is either -1 or in the range [1, n^2].
                     * If next has a snake or ladder,
                     * you must move to the destination of that snake or ladder.
                     * Otherwise, you move to next.
                     * A board square on row r and column c has a snake or ladder if board[r][c] != -1.
                     * The destination of that snake or ladder is board[r][c].
                     */
                    if (next == -1) {
                        next = cur + i;
                    }
                    if (visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    queue.offer(next);
                }
            }
            res++;
        }
        return -1;
    }

    private int getPositionV1(int[][] board, int cur) {
        int n = board.length;
        int x = (cur - 1) / n;
        int y = (cur - 1) % n;
        if (x % 2 == 1) {
            /**
             * A trick here, alternating direction each row.
             */
            y = n - 1 - y;
        }
        x = n - 1 - x;
        return board[x][y];
    }


    /**
     * Time Cost 7ms
     * Time Complexity O(N^2)
     * Space Complexity O(N^2)
     * @param board
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int res = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        boolean[] visited = new boolean[n * n  + 1];
        while (!queue.isEmpty()) {
            for (int k = queue.size(); k > 0; k--) {
                int cur = queue.poll();
                if (cur == n * n) {
                    return res;
                }
                for (int i = 1; i <= 6 && cur + i <= n * n; i++) {
                    int[] pos = getPosition(cur + i, n);
                    /**
                     * Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n^2)]
                     * board[i][j] is either -1 or in the range [1, n^2].
                     * If next has a snake or ladder,
                     * you must move to the destination of that snake or ladder.
                     * Otherwise, you move to next.
                     * A board square on row r and column c has a snake or ladder if board[r][c] != -1.
                     * The destination of that snake or ladder is board[r][c].
                     */
                    int next = board[pos[0]][pos[1]] == -1 ? (cur + i) : board[pos[0]][pos[1]];
                    if (visited[next]) {
                        continue;
                    }
                    visited[next] = true;
                    queue.offer(next);
                }
            }
            res++;
        }
        return -1;
    }

    /**
     * Convert digit(starting from 1) to x-y coordinate(0-0 based)
     * starting from the bottom left of the board (i.e. board[n - 1][0]),
     * and alternating direction each row.
     * e.g. n = 4
     * 16 15 14 13
     * 9  10 11 12
     * 8   7  6  5
     * 1   2  3  4
     * -------------------------
     * cur:1, x:0, y:0, x = n - 1 - x = 4 - 1 = 3, so cur:1 --> (3, 0)
     * cur:2, x:0, y:1, x = n - 1 - x = 4 - 1 = 3, so cur:2 --> (3, 1)
     * cur:3, x:0, y:2, x = n - 1 - x = 4 - 1 = 3, so cur:3 --> (3, 2)
     * cur:4, x:0, y:3, x = n - 1 - x = 4 - 1 = 3, so cur:4 --> (0, 3)
     * cur:5, x:1, y:0, x % 2==1, y=n-1-y=4-1=3, x=n-1-x=2, so cur:5 --> (2, 3)
     * cur:6, x:1, y:1, x % 2==1, y=n-1-y=4-1-1=2, x=n-1-x=4-1-1=2, so cur:6 --> (2, 2)
     * ......
     *
     * @param cur
     * @param n
     * @return
     */
    private int[] getPosition(int cur, int n) {
        int x = (cur - 1) / n;
        int y = (cur - 1) % n;
        if (x % 2 == 1) {
            /**
             * A trick here, alternating direction each row.
             */
            y = n - 1 - y;
        }
        x = n - 1 - x;
        return new int[] {x, y};
    }
}
