package org.ict.algorithm.leetcode.backtrack;

import java.util.Arrays;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
 * such that no two queens attack each other.
 *
 * Given an integer n,
 * return the number of distinct solutions to the n-queens puzzle.
 *
 * Example 1:
 * Input: n = 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
 *
 * Example 2:
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 * 1 <= n <= 9
 * @author sniper
 * @date 19 Jun 2023
 * LC52, Hard
 */
public class NQueensII {

    public int totalNQueensV1(int n) {
        //todo
        return 0;
    }

    /**
     * Understanding the following solution
     * Time Cost 2ms
     * -------------------------------
     * class Solution {
     * public:
     *     int totalNQueens(int n) {
     *         int res = 0;
     *         vector<string> board(n, string(n, '.'));
     *         backtrack(0, board, res);
     *         return res;
     *     }
     *
     *     void backtrack(int curRow, vector<string>& board, int& res) {
     *         int n = board.size();
     *         if (curRow == n) {
     *             res++;
     *             return;
     *         }
     *
     *         for (int j = 0; j < n; j++) {
     *             if (isValid(board, curRow, j)) {
     *                 board[curRow][j] = 'Q';
     *                 backtrack(curRow + 1, board, res);
     *                 board[curRow][j] = '.';
     *             }
     *         }
     *     }
     *
     *     bool isValid(vector<string>& board, int row, int col) {
     *         for (int i = 0; i < row; ++i) {
     *             if (board[i][col] == 'Q') {
     *                 return false;
     *             }
     *         }
     *
     *         for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
     *             if (board[i][j] == 'Q') {
     *                 return false;
     *             }
     *         }
     *
     *         for (int i = row - 1, j = col + 1; i >= 0 && j < board.size(); i--, j++) {
     *             if (board[i][j] == 'Q') {
     *                 return false;
     *             }
     *         }
     *
     *         return true;
     *     }
     * };
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        int[] res = new int[1];
        backtrack(0, board, res);
        return res[0];
    }

    private void backtrack(int curRow, char[][] board, int[] res) {
        int n = board.length;
        if (curRow == n) {
            res[0]++;
            return;
        }
        for (int j = 0; j < n; j++) {
            if (isValid(board, curRow, j)) {
                board[curRow][j] = 'Q';
                backtrack(curRow + 1, board, res);
                board[curRow][j] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        /**
         * Check the column of col
         */
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        /**
         * Check the main diagonal line
         *  to
         *   \
         *    \
         *     \
         *      from(row - 1, col - 1)
         */
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        /**
         * Check another diagonal line
         *         to
         *        /
         *      /
         *    /
         *  from(row - 1, col + 1)
         *
         */
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }
}
