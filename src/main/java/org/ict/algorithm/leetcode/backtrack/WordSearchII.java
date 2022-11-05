package org.ict.algorithm.leetcode.backtrack;

import org.ict.algorithm.leetcode.trie.Trie;

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

    public List<String> findWordsV1(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        return result;
    }

    /**
     * Trie-Tree Solution.
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
        Trie tree = new Trie();
        for (String word : words) {
            tree.insert(word);
        }
        /**
         * Build visit marking array.
         */
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        /**
         * Search board with Trie Tree.
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Trie.TrieNode[] children = tree.getRoot().getChildren();
                int idx = board[i][j] - 'a';
                if (children[idx] != null) {
                    search(board, children[idx], i, j, visited, result);
                }
            }
        }

        return result;
    }


    public void search(char[][] board, Trie.TrieNode p, int i, int j, boolean[][] visited, List<String> result) {
        if (p.isWord()) {
            result.add(p.getWord());
            p.word(false);
            p.setWord(null);
            return;
        }
        int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        visited[i][j] = true;
        for (int[] a : dir) {
            int nx = a[0] +  i, ny = a[1] + j;
            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length
                    && !visited[nx][ny]) {
                Trie.TrieNode[] children = p.getChildren();
                int idx = board[i][j] - 'a';
                if (children[idx] != null) {
                    search(board, children[idx], nx, ny, visited, result);
                }
            }
        }
        visited[i][j] = false;
    }



}
