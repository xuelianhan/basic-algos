package org.ict.algorithm.leetcode.array;


import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9 x 9 Sudoku board is valid.
 * Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 *
 *
 * Example 1:
 *
 *
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 * Example 2:
 *
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8.
 * Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 *
 * Constraints:
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 * @author sniper
 * @date 23 Dec, 2022
 * LC36
 */
public class ValidSudoku {

    /**
     * rowFlag:[
     * [false, false, true, false, true, false, true, false, false],
     * [true, false, false, false, true, true, false, false, true],
     * [false, false, false, false, false, true, false, true, true],
     * [false, false, true, false, false, true, false, true, false],
     * [true, false, true, true, false, false, false, true, false],
     * [false, true, false, false, false, true, true, false, false],
     * [false, true, false, false, false, true, false, true, false],
     * [true, false, false, true, true, false, false, false, true],
     * [false, false, false, false, false, false, true, true, true]]
     *
     * colFlag:[
     * [false, false, false, true, true, false, false, false, true],
     * [false, false, false, false, true, false, true, false, false],
     * [false, true, false, false, false, true, false, false, true],
     * [true, false, false, true, false, false, false, false, false],
     * [true, false, false, false, false, true, false, false, true],
     * [true, true, false, false, true, false, false, true, true],
     * [true, false, false, false, true, false, false, true, false],
     * [true, false, true, true, true, false, false, true, false],
     * [false, true, false, false, true, true, false, false, true]]
     *
     * cellFlag:[
     * [false, false, true, false, true, true, false, true, true],
     * [true, false, false, false, true, false, true, false, true],
     * [false, false, false, false, false, true, false, false, false],
     * [false, false, false, true, false, false, true, true, false],
     * [false, true, true, false, false, true, false, true, false],
     * [true, false, true, false, false, true, false, false, false],
     * [false, false, false, false, false, true, false, false, false],
     * [true, false, false, true, false, false, false, true, true],
     * [false, true, false, false, true, false, true, true, true]]
     * @param args
     */
    public static void main(String[] args) {
        char[][] board =
                {
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                };
        ValidSudoku instance = new ValidSudoku();
        instance.isValidSudoku(board);
        instance.printSubBoxOfMatrix();
    }


    /**
     * Time Cost 34 ms
     * This solution is more readable.
     * @param board
     * @return
     */
    public boolean isValidSudokuV1(char[][] board) {
        int n = 9;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                char number = board[i][j];
                /**
                 * return true if this set did not already contain the specified element.
                 */
                boolean f1 = set.add(number + " in row " + i);
                boolean f2 = set.add(number + " in col " + j);
                boolean f3 = set.add(number + " in box " + i / 3 + "~" + j / 3);
                if (!f1 || !f2 || !f3) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Time Cost 4ms
     * Time Complexity O(N^2)
     * Space Complexity O(3*N^2)
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int n = 9;
        boolean[][] rowFlag = new boolean[n][n];
        boolean[][] colFlag = new boolean[n][n];
        boolean[][] boxFlag = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                /**
                 * Convert char '1'~'9' to int 0~8, which is the range of the array index.
                 */
                int c = board[i][j] - '1';
                /**
                 * Calculate 3 x 3 sub-boxes row coordinate.
                 */
                int r = 3 * (i / 3) + j / 3;
                /**
                 * If the number has been appeared before, return false.
                 */
                if (rowFlag[i][c] || colFlag[c][j] || boxFlag[r][c]) {
                    return false;
                }
                rowFlag[i][c] = true;
                colFlag[c][j] = true;
                boxFlag[r][c] = true;
            }
        }
        return true;
    }

    /**
     * the matrix of r = 3 * (i / 3) + j / 3 is following:
     * 0 0 0 1 1 1 2 2 2
     * 0 0 0 1 1 1 2 2 2
     * 0 0 0 1 1 1 2 2 2
     * 3 3 3 4 4 4 5 5 5
     * 3 3 3 4 4 4 5 5 5
     * 3 3 3 4 4 4 5 5 5
     * 6 6 6 7 7 7 8 8 8
     * 6 6 6 7 7 7 8 8 8
     * 6 6 6 7 7 7 8 8 8
     */
    private void printSubBoxOfMatrix() {
        int n = 9;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int r = 3 * (i / 3) + j / 3;
                System.out.println("i:" + i + ", j:" + j + ", r:" + r);
            }
        }
    }
}
