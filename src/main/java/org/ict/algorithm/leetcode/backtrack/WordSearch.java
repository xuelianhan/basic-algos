package org.ict.algorithm.leetcode.backtrack;

/**
 *
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["A","B","C","E"],
 *                 ["S","F","C","S"],
 *                 ["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 *
 *
 * Input: board = [["A","B","C","E"],
 *                 ["S","F","C","S"],
 *                 ["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 *
 *
 * Input: board = [["A","B","C","E"],
 *                 ["S","F","C","S"],
 *                 ["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 *
 * Constraints:
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 *
 *
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 * @author sniper
 * @date 02 Nov, 2022
 * LC79
 */
public class WordSearch {


    /**
     * We all have played Word Search but what if we build a code which would actually search a word and return whether it exists or not?
     * This can be achieved by one very simple and elegant algorithmic technique known as the backtracking algorithm.
     * We choose the backtracking algorithm because it's deterministic and goes in a depth-first order,
     * at each level we can edit information, which keeps the state of our system the way we need it to for the next level's recursive calls,
     * and then we can undo the change we made for whenever we go back up to the previous level.
     * In short, we can have shared variable without having to create a new one for each recursive call which in turn,
     * saves time as well as memory.
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtrack(board, word, m, n, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Time Cost 147ms
     * @param board
     * @param word
     * @param m
     * @param n
     * @param i
     * @param j
     * @param pos
     * @return
     */
    public boolean backtrackV1(char[][] board, String word, int m, int n, int i, int j, int pos) {
        /**
         * out of the boarder or not matched for current pos character
         */
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(pos)) {
            return false;
        }

        /**
         * when completely matched
         * Notice this line must put after the checking of boarder above.
         */
        if (pos == word.length() - 1) {
            return true;
        }

        /**
         * mark the current block, because we can't reuse the block.
         * It's similar with visited array boolean[] in DFS.
         */
        char cur = board[i][j];
        board[i][j] = '#';

        /**
         * check on up, down, left, right all 4 directions
         */
        boolean found = backtrack(board, word, m, n, i + 1, j, pos + 1)
                || backtrack(board, word, m, n, i - 1, j, pos + 1)
                || backtrack(board, word, m, n, i, j + 1, pos + 1)
                || backtrack(board, word, m, n, i, j - 1, pos + 1);

        /**
         * unmark the current block
         */
        board[i][j] = cur;

        return found;
    }

    /**
     *
     * Time Cost 181ms
     *
     *
     *
     * We are allowed to start from any block in the board,
     * can only travel to adjacent (i.e., up, down, left & right) blocks and cannot reuse any block.
     * Considering these three conditions as our golden rules,
     * Now we will construct our algorithm by iterating over every block in the board and in each iteration we will perform the following,
     * 1.Check if the block is valid, as in whether it's out of bounds or whether the searching word has the character or not.
     *
     * 2.Mark the current block with any other character which manages the 3rd golden rule of the problem (cannot reuse any block), we have used '#'.
     *
     * 3.Now we will recursively check the left, right, up and down blocks to match the next character of the searching word.
     *
     * 4.Unmark the block which we had marked in step 2.
     *
     * 5.Return true if this block was the end of the word or else any of the recursive calls returned true.
     *
     * @see <a href="https://www.c-sharpcorner.com/article/word-search-using-backtracking-algorithm/"></a>
     * @param board
     * @param word
     * @param m total number of rows
     * @param n total number of columns
     * @param i
     * @param j
     * @param pos the index of character of word
     * @return
     */
    public boolean backtrack(char[][] board, String word, int m, int n, int i, int j, int pos) {
        /**
         * out of the boarder.
         */
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }

        /**
         * not matched for current pos character
         */
        if (board[i][j] != word.charAt(pos)) {
            return false;
        }

        /**
         * when completely matched
         */
        if (pos == word.length() - 1) {
            return true;
        }

        /**
         * mark the current block, because we can't reuse the block.
         * It's similar with visited array boolean[] in DFS.
         */
        char cur = board[i][j];
        board[i][j] = '#';

        /**
         * check on up, down, left, right all 4 directions
         */
        boolean found = backtrack(board, word, m, n, i + 1, j, pos + 1)
                || backtrack(board, word, m, n, i - 1, j, pos + 1)
                || backtrack(board, word, m, n, i, j + 1, pos + 1)
                || backtrack(board, word, m, n, i, j - 1, pos + 1);

        /**
         * unmark the current block
         */
        board[i][j] = cur;

        return found;
    }
}
