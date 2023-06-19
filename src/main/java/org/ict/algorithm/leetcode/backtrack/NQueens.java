package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * According to the rules of chess,
 * the queen may attack a piece in the same row or column or on the same diagonal as it.
 *
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
 * such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * You may return the answer in any order.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, w
 * here 'Q' and '.' both indicate a queen and an empty space, respectively.
 *
 * Example 1:
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 *
 * Example 2:
 * Input: n = 1
 * Output: [["Q"]]
 *
 * Constraints:
 * 1 <= n <= 9
 *
 * @author sniper
 * @date 19 Jun 2023
 * LC51, Hard, frequency=3
 */
public class NQueens {


    /**
     * todo
     * Time Cost 1ms
     * @param n
     * @return
     */
    public List<List<String>> solveNQueensV1(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        boolean[] cols = new boolean[n];
        boolean[] diagonalOne = new boolean[2 * n - 1];
        boolean[] diagonalTwo = new boolean[2 * n - 1];
        backtrackV1(0, cols, diagonalOne, diagonalTwo, board, res);

        return res;
    }

    private void backtrackV1(int curRow, boolean[] cols, boolean[] diagonalOne,  boolean[] diagonalTwo, char[][] board, List<List<String>> res) {
        int n = board.length;
        if (curRow == n) {
            res.add(build(board));
            return;
        }
        for (int j = 0; j < n; j++) {
            if (cols[j] || diagonalOne[curRow + j] || diagonalTwo[j - curRow + n - 1]) {
                continue;
            }
            cols[j] = diagonalOne[curRow + j] =  diagonalTwo[j - curRow + n - 1] = true;
            board[curRow][j] = 'Q';
            backtrackV1(curRow + 1, cols, diagonalOne,  diagonalTwo, board, res);
            cols[j] = diagonalOne[curRow + j] =  diagonalTwo[j - curRow + n - 1] = false;
            board[curRow][j] = '.';
        }
    }

    /**
     * Understanding the following solution
     * Time Cost 3ms
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        backtrack(0, board, res);
        return res;
    }


    private void backtrack(int curRow, char[][] board, List<List<String>> res) {
        int n = board.length;
        if (curRow == n) {
            res.add(build(board));
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

    private List<String> build(char[][] board) {
        List<String> rowList = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            rowList.add(String.valueOf(board[i]));
        }
        return rowList;
    }

}
