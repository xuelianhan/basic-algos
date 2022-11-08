package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["o","a","a","n"],
 *                 ["e","t","a","e"],
 *                 ["i","h","k","r"],
 *                 ["i","f","l","v"]],
 *       words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * Example 2:
 *
 *
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 10^4
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 * @author sniper
 * @date 02 Nov, 2022
 * LC212
 */
public class WordSearchII {

    public static void main(String[] args) {
        WordSearchII instance = new WordSearchII();
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        List<String> result = instance.findWords(board, words);
        System.out.println(result);
    }

    public List<String> findWordsV2(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                /**
                 * Notice here, we always start to search from root at each char in board.
                 */
                searchV2(board, i, j, root, res);
            }
        }
        return res;
    }

    /**
     * Intuitively, start from every cell and try to build a word in the dictionary.
     * Backtracking (dfs) is the powerful way to exhaust every possible ways.
     * Apparently, we need to do pruning when current character is not in any word.
     *
     * How do we instantly know the current character is invalid? HashMap?
     * How do we instantly know what's the next valid character? LinkedList?
     * But the next character can be chosen from a list of characters. "Mutil-LinkedList"?
     * Combing them, Trie is the natural choice. Notice that:
     *
     * TrieNode is all we need. search and startsWith are useless.
     * No need to store character at TrieNode. c.next[i] != null is enough.
     * Never use c1 + c2 + c3. Use StringBuilder.
     * No need to use O(n^2) extra space visited[m][n].
     * No need to use StringBuilder. Storing word itself at leaf node is enough.
     * No need to use HashSet to de-duplicate. Use "one time search" trie.
     * For more explanations, check out dietpepsi's blog.
     *
     * @author yavinci
     * @param board
     * @param i
     * @param j
     * @param p
     * @param res
     */
    public void searchV2(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.children[c - 'a'] == null) {
            return;
        }
        p = p.children[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';
        if (i > 0) searchV2(board, i - 1, j ,p, res);
        if (j > 0) searchV2(board, i, j - 1, p, res);
        if (i < board.length - 1) searchV2(board, i + 1, j, p, res);
        if (j < board[0].length - 1) searchV2(board, i, j + 1, p, res);
        board[i][j] = c;
    }

    /**
     * Backtracking + Trie-Tree Solution.
     * When the size of words.length is very big, brute force solution will run timeout.
     * So using trie-tree here.
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if (null == words || words.length == 0 || null == board || board[0].length == 0) {
            return result;
        }

        /**
         * Build Trie Tree with words
         */
        TrieNode root = buildTrie(words);

        /**
         * Build visit marking array.
         */
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        /**
         * Search board with Trie Tree.
         * Notice here, we must search trie from root every time.
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                /**
                 * two version of implements is similar except some tiny difference on the traversal of directions.
                 * Notice here, we always start to search from root at each char in board.
                 */
                search(board, root, i, j, visited, result);
                //searchV1(board, root, i, j, visited, result);
            }
        }

        return result;
    }

    public void searchV1(char[][] board, TrieNode p, int i, int j, boolean[][] visited, List<String> result) {
        /**
         * Notice the boarder conditions of i and j.
         */
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        char c = board[i][j];
        if (p.children[c - 'a'] == null) {
            return;
        }
        /**
         *
         */
        p = p.children[c - 'a'];
        if (p.word != null) {
            result.add(p.word);
            /**
             * deduplicate.
             */
            p.word = null;
            /**
             * don't put return here, otherwise you will get a wrong answer.
             */
        }
        /**
         * Use dir to store the 4 moving directions
         */
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        visited[i][j] = true;
        for (int[] a : dir) {
            int nx = a[0] +  i, ny = a[1] + j;
            search(board, p, nx, ny, visited, result);
        }
        visited[i][j] = false;
    }

    public void search(char[][] board, TrieNode p, int i, int j, boolean[][] visited, List<String> result) {
        /**
         * Notice the boarder conditions of i and j.
         */
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        char c = board[i][j];
        if (p.children[c - 'a'] == null) {
            return;
        }
        /**
         *
         */
        p = p.children[c - 'a'];
        if (p.word != null) {
            result.add(p.word);
            /**
             * deduplicate.
             */
            p.word = null;
            /**
             * don't put return here, otherwise you will get a wrong answer.
             */
        }
        visited[i][j] = true;
        search(board, p, i - 1, j, visited, result);
        search(board, p, i + 1, j, visited, result);
        search(board, p, i, j - 1, visited, result);
        search(board, p, i, j + 1, visited, result);
        visited[i][j] = false;
    }

    /**
     * "oath","pea","eat","rain"
     *          root
     *          /|\ \
     *         o p e r
     *        /  |  \ \
     *       a   e   a a
     *      /    |    \ \
     *     t     a     t i
     *    /               \
     *   h                n
     * @param words
     * @return
     */
    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        /**
         * All the words start from the same root.
         */
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.children[i] == null) {
                    p.children[i] = new TrieNode();
                }
                p = p.children[i];
            }
            p.word = w;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        /**
         * Usually, we use a marked field here to mark
         * the end of the string, but we store the word directly here to
         * prevent searching the whole trie-tree to combine the word string.
         */
        String word;
    }


}
