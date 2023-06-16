package org.ict.algorithm.leetcode.backtrack;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Notice the adjacent sub-boxes doesn't cross with each other.
 * The '.' character indicates empty cells.
 *
 * Example 1:
 * Input: board = [["5","3",".",".","7",".",".",".","."],
 *                 ["6",".",".","1","9","5",".",".","."],
 *                 [".","9","8",".",".",".",".","6","."],
 *                 ["8",".",".",".","6",".",".",".","3"],
 *                 ["4",".",".","8",".","3",".",".","1"],
 *                 ["7",".",".",".","2",".",".",".","6"],
 *                 [".","6",".",".",".",".","2","8","."],
 *                 [".",".",".","4","1","9",".",".","5"],
 *                 [".",".",".",".","8",".",".","7","9"]]
 * Output: [["5","3","4","6","7","8","9","1","2"],
 *          ["6","7","2","1","9","5","3","4","8"],
 *          ["1","9","8","3","4","2","5","6","7"],
 *          ["8","5","9","7","6","1","4","2","3"],
 *          ["4","2","6","8","5","3","7","9","1"],
 *          ["7","1","3","9","2","4","8","5","6"],
 *          ["9","6","1","5","3","7","2","8","4"],
 *          ["2","8","7","4","1","9","6","3","5"],
 *          ["3","4","5","2","8","6","1","7","9"]]
 * Explanation: The input board is shown above and the only valid solution is shown below:

 *
 * Constraints:
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 * It is guaranteed that the input board has only one solution.
 * @author sniper
 * @date 15 Jun 2023
 * LC37, Hard, frequency=10
 */
public class SudokuSolver {

    /**
     * Time Cost 21ms
     * @param board
     */
    public void solveSudokuV2(char[][] board) {
        backtrackV2(board);
    }

    private boolean backtrackV2(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (char c = '1'; c <= '9'; c++) {
                    if (!isValidV2(board, i, j, c)) {
                        continue;
                    }
                    board[i][j] = c;
                    if (backtrackV2(board)) {
                        return true;
                    }
                    board[i][j] = '.';
                }
                return false;
            }
        }
        return true;
    }

    private boolean isValidV2(char[][] board, int row, int col, char c) {
        for (int k = 0; k < 9; k++) {
            if (board[k][col] != '.' && board[k][col] == c) {
                return false;
            }
            if (board[row][k] != '.' && board[row][k] == c) {
                return false;
            }
            int newRow = 3 * (row / 3) + k / 3;
            int newCol = 3 * (col / 3) + k % 3;
            if (board[newRow][newCol] != '.' && board[newRow][newCol] == c) {
                return false;
            }
        }
        return true;
    }

    /**
     * Understanding the following solution
     * Time Cost 7ms
     * @param board
     */
    public void solveSudokuV1(char[][] board) {
        backtrackV1(board, 0, 0);
    }

    private boolean backtrackV1(char[][] board, int i, int j) {
        if (i == 9) {
            /**
             * Last line + 1
             */
            return true;
        }
        if (j >= 9) {
            /**
             * Last column + 1, so move to the next line
             */
            return backtrackV1(board, i + 1, 0);
        }
        /**
         * The current char is not dot, so fill the right-side space of current slot.
         */
        if (board[i][j] != '.') {
            return backtrackV1(board, i, j + 1);
        }
        for (char c = '1'; c <= '9'; c++) {
            if (!isValidV1(board, i, j, c)) {
                continue;
            }
            board[i][j] = c;
            if (backtrackV1(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }

    private boolean isValidV1(char[][] board, int row, int col, char c) {
        for (int x = 0; x < 9; x++) {
            if (board[x][col] == c) {
                return false;
            }
        }

        for (int y = 0; y < 9; y++) {
            if (board[row][y] == c) {
                return false;
            }
        }

        int newRow = row - row % 3;
        int newCol = col - col % 3;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (board[x + newRow][y + newCol] == c) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Understanding the following solution
     * Time Cost 21ms
     * @param board
     */
    public void solveSudoku(char[][] board) {
        backtrack(board, 0);
    }

    private boolean backtrack(char[][] board, int val) {
        if (val == 81) {
            return true;
        }
        int i = val / 9;
        int j = val % 9;
        if (board[i][j] != '.') {
            return backtrack(board, val + 1);
        }
        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, i, j, c)) {
                board[i][j] = c;
                if (backtrack(board, val + 1)) {
                    return true;
                }
                board[i][j] = '.';
            }
        }
        return false;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int k = 0; k < 9; k++) {
            /**
             * Notice here:
             * 3 * (row / 3) + k / 3
             * 3 * (col / 3) + k % 3
             */
            int newRow = 3 * (row / 3) + k / 3;
            int newCol = 3 * (col / 3) + k % 3;
            if (board[k][col] == c || board[row][k] == c || board[newRow][newCol] == c) {
                return false;
            }
        }
        return true;
    }
}
