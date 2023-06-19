package org.ict.algorithm.leetcode.depthfirstsearch;

/**
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.',
 * return the number of the battleships on board.
 *
 * Battleships can only be placed horizontally or vertically on board.
 * In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column),
 * where k can be of any size.
 * At least one horizontal or vertical cell separates between two battleships
 * (i.e., there are no adjacent battleships).
 *
 *
 *
 * Example 1:
 * Input: board = [["X",".",".","X"],
 *                 [".",".",".","X"],
 *                 [".",".",".","X"]]
 * Output: 2
 *
 * Example 2:
 * Input: board = [["."]]
 * Output: 0
 *
 *
 * Constraints:
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is either '.' or 'X'.
 *
 *
 * Follow up: Could you do it in one-pass,
 * using only O(1) extra memory and without modifying the values board?
 * @author sniper
 * @date 19 Jun 2023
 * LC419, Medium, frequency=9
 */
public class BattleshipsInABoard {

    /**
     * Understanding the following solution
     * Brute-Force Solution
     * ---------------------------------
     * class Solution {
     * public:
     *     int countBattleships(vector<vector<char>>& board) {
     *         if (board.empty() || board[0].empty()) {
     *             return 0;
     *         }
     *         int res = 0;
     *         int m = board.size();
     *         int n = board[0].size();
     *         char bigX = 'X';
     *         for (int i = 0; i < m; ++i) {
     *             for (int j = 0; j < n; ++j) {
     *                 if (board[i][j] == '.' || (i > 0 && board[i - 1][j] == bigX) || (j > 0 && board[i][j - 1] == bigX)) {
     *                     continue;
     *                 }
     *                 res++;
     *             }
     *         }
     *         return res;
     *     }
     * };
     * -------------------------------
     * class Solution:
     *     def countBattleships(self, board: List[List[str]]) -> int:
     *         m = len(board)
     *         n = len(board[0])
     *         bigX = 'X'
     *         res = 0
     *         for i in range(m):
     *             for j in range(n):
     *                 if board[i][j] == '.' or (i > 0 and board[i - 1][j] == bigX) or (j > 0 and board[i][j - 1] == bigX):
     *                     continue
     *                 res += 1
     *         return res
     * @param board
     * @return
     */
    public int countBattleshipsV3(char[][] board) {
        int res = 0;
        int m = board.length;
        int n = board[0].length;
        char bigX = 'X';
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.' || (i > 0 && board[i - 1][j] == bigX) || (j > 0 && board[i][j - 1] == bigX)) {
                    continue;
                }
                res++;
            }
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Brute-Force Solution
     * @param board
     * @return
     */
    public int countBattleshipsV2(char[][] board) {
        int res = 0;
        int m = board.length;
        int n = board[0].length;
        char bigX = 'X';
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (i > 0 && board[i - 1][j] == bigX) {
                    continue;
                }
                if (j > 0 && board[i][j - 1] == bigX) {
                    continue;
                }
                res++;
            }
        }
        return res;
    }

    /**
     * Breadth-First-Search Solution
     * @param board
     * @return
     */
    public int countBattleshipsV1(char[][] board) {
        int res = 0;
        //todo
        return res;
    }


    /**
     * Depth-First-Search Solution
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        int res = 0;
        //todo
        return res;
    }

}
