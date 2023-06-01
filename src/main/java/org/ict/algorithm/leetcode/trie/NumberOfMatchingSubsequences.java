package org.ict.algorithm.leetcode.trie;

/**
 * Given a string s and an array of strings words,
 * return the number of words[i] that is a subsequence of s.
 *
 * A subsequence of a string is a new string generated from the original string with some characters
 * (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 * Example 1:
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 *
 * Example 2:
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 *
 * Constraints:
 * 1 <= s.length <= 5 * 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * s and words[i] consist of only lowercase English letters.
 *
 * @author sniper
 * @date 31 May 2023
 * LC792, Medium, frequency=17
 */
public class NumberOfMatchingSubsequences {

    public static void main(String[] args) {
        String s = "abcde";
        String[] words = {"a","bb","acd","ace"};
        NumberOfMatchingSubsequences instance = new NumberOfMatchingSubsequences();
        instance.numMatchingSubseq(s, words);
    }

    public int numMatchingSubseqV1(String s, String[] words) {
        //todo
        return 0;
    }

    /**
     * Understanding the following solution
     * Trie + Depth-First-Search
     * Time Cost 123ms
     *
     * Time Complexity O(len(s) + sum(len(words[i])))
     * Space Complexity O(sum(len(words[i])))
     * @param s
     * @param words
     * @return
     */
    public int numMatchingSubseq(String s, String[] words) {
        for (String word: words) {
            insert(word);
        }
        return dfs(s, 0, root);
    }

    private TrieNode root = new TrieNode();

    /**
     * e.g. s = "abcde", words = ["a","bb","acd","ace"]
     *                       root
     *                        |
     *                       a(count:1)---b
     *                       /             \
     *                      c              b(count:1)
     *                     / \
     *                   /    \
     *                 /       \
     *               /          \
     *             d(count:1)   e(count:1)
     * ------------------------------------------------
     * dfs(s, 0, root), res:0, i:0, j:0, idx:0
     *    dfs(s, 1, root[a]), res:1, i:1, j:2, idx:2
     *       dfs(s, 2, root[c]), res:0, i:2, j:3, idx:3
     *       dfs(s, 2, root[c]), res:0, i:2, j:4, idx:4
     *
     * @param s
     * @param i
     * @param node
     * @return
     */
    private int dfs(String s, int i, TrieNode node) {
        /**
         * Notice here res is initialized to node.count, not zero.
         */
        int res = node.count;
        if (i >= s.length()) {
            return res;
        }
        for (int j = 0; j < 26; j++) {
            if (node.children[j] != null) {
                /**
                 * Notice the indexOf method and the i-index.
                 */
                int idx = s.indexOf('a' + j, i);
                if (idx != -1) {
                    res += dfs(s, idx + 1, node.children[j]);
                }
            }
        }
        return res;
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.count++;
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int count;
    }

}
