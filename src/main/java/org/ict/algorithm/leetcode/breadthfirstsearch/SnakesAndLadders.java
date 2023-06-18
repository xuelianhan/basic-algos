package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayDeque;
import java.util.Deque;

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
 * The destination of that snake or ladder is board[r][c]. Squares 1 and n2 do not have a snake or ladder.
 *
 * Note that you only take a snake or ladder at most once per move.
 * If the destination to a snake or ladder is the start of another snake or ladder,
 * you do not follow the subsequent snake or ladder.
 *
 * For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2.
 * You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
 * Return the least number of moves required to reach the square n2.
 * If it is not possible to reach the square, return -1.
 *
 *
 *
 * Example 1:
 *
 *
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
 * Example 2:
 *
 * Input: board = [[-1,-1],
 *                 [-1,3]]
 * Output: 1
 *
 *
 * Constraints:
 *
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


    public int snakesAndLaddersV2(int[][] board) {
        return 0;
    }


    public int snakesAndLaddersV1(int[][] board) {
        return 0;
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
                     * board[i][j] is either -1 or in the range [1, n^2].
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
     * e.g. n = 4
     * 16 15 14 13
     * 9  10 11 12
     * 8   7  6  5
     * 1   2  3  4
     * -------------------------
     * @param cur
     * @param n
     * @return
     */
    private int[] getPosition(int cur, int n) {
        int x = (cur - 1) / n;
        int y = (cur - 1) % n;
        if (x % 2 == 1) {
            y = n - 1 - y;
        }
        x = n - 1 - x;
        return new int[] {x, y};
    }
}
