package org.ict.algorithm.leetcode.simulation;

import java.util.Arrays;

/**
 * According to Wikipedia's article: "The Game of Life, also known simply as Life,
 * is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
 * using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state is created by applying the above rules simultaneously to every cell in the current state,
 * where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * Example 2:
 *
 *
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] is 0 or 1.
 *
 *
 * Follow up:
 *
 * Could you solve it in-place? Remember that the board needs to be updated simultaneously:
 * You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array.
 * In principle, the board is infinite,
 * which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border).
 * How would you address these problems?
 * @author sniper
 * @date 23 Nov, 2022
 * LC289
 */
public class GameOfLife {

    public static void main(String[] args) {
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        GameOfLife instance = new GameOfLife();
        instance.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
    }

    /**
     * In memory of John Horton Conway,
     * an English mathematician active in the theory of finite groups,
     * knot theory, number theory, combinatorial game theory and coding theory.
     * He also made contributions to many branches of recreational mathematics,
     * most notably the invention of the cellular automaton called the Game of Life.
     *
     * Let's name neighbor-live-count as NLC.
     * The rules as following:
     * live -> NLC < 2 -> dead.
     * live -> NLC == 2 || NLC == 3 -> live.
     * live -> NLC > 3 -> dead.
     * dead -> NLC == 3 -> live.
     *
     * Firstly, 0 indicates dead status, 1 indicates live status.
     * We can create a state machine and define status-transformation like the following:
     * 0: dead to dead, 0 mod 2 -> 0, 0 is dead
     * 1: live to live, 1 mod 2 -> 1, 1 is live
     * 2: live to dead, 2 mod 2 -> 0, 0 is dead
     * 3: dead to live, 3 mod 2 -> 1, 1 is live.
     * We can observe the fact that the mod result matches the transformed status.
     * So we transform original status from 0, 1 to 0,1,2,3, then we apply modular on these transformed status,
     * we can get the final result without copying any auxiliary array.
     * 0->0, 1->1, these two cases don't change status, we can skip them.
     * 1->0, we need to change the first 1 to 2
     * 0->1, we need to change the first 0 to 3
     *
     * We collect all neighbors live status before transforming(status 1 and status 2) and count them,
     * and then we apply the rules.
     * if board[i][j] is live(1) and (NLC < 2 || NLC > 3), then we change board[i][j] from live(1) to dead(accord with status 2)
     * if board[i][j] is dead(0) and NLC == 3, then we change board[i][j] from dead(0) to live(accord with status 3)
     *
     * @param board
     */
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = ( m > 0 ? board[0].length : 0);
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                /**
                 * Count the neighbors with live status.
                 */
                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    /**
                     * Notice using board[x][y] here, not board[i][j]
                     * The next state is created by applying the above rules simultaneously to every cell in the current state,
                     * so we concern state==1 and state==2(both states indicate live)
                     */
                    if (x >= 0 && x < m && y >= 0 && y < n && (board[x][y] == 1 || board[x][y] == 2)) {
                        cnt++;
                    }
                }
                if (board[i][j] == 1 && (cnt < 2 ||  cnt > 3)) {
                    /**
                     * From live to dead.
                     */
                    board[i][j] = 2;
                }
                if (board[i][j] == 0 && cnt == 3) {
                    /**
                     * From dead to live
                     */
                    board[i][j] = 3;
                }
            }
        }
        /**
         * Restore state back to 0 and 1.
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] %= 2;
            }
        }
    }
}
