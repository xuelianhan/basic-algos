package org.ict.algorithm.leetcode.backtrack;

import org.ict.algorithm.leetcode.trie.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * 目前在用 Mac mini 作为主力办公电脑，无奈跟 Windows 不一样的是，在 macOS 下通过数据线链接安卓平板后无法作为MTP 设备看到，传输文件数据不方便，所以才想要安装 adb，可以通过 adb install 来安装apk 应用。首先安装 homebrew：/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"让后通过 homebrew 来安装 adb：brew install --cask android-platform-toolsHomebrew的使用可以去官网详细了解：Homebrew​brew.sh之后就可以使用了：adb devices -l
 * List of devices attached
 * 99418373459118  authorizing usb:338690048X transport_id:44
 *
 * 作者：默契
 * 链接：https://zhuanlan.zhihu.com/p/445554966
 * 来源：知乎
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs (board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
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
        if (i > 0) dfs(board, i - 1, j ,p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
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
                search(board, root, i, j, visited, result);
            }
        }

        return result;
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
        System.out.println("p.word:" + p.word);
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
        }
        visited[i][j] = true;
        search(board, p, i - 1, j, visited, result);
        search(board, p, i + 1, j, visited, result);
        search(board, p, i, j - 1, visited, result);
        search(board, p, i, j + 1, visited, result);
        visited[i][j] = false;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
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
        String word;
    }


}
