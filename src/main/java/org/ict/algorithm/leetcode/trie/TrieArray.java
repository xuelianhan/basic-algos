package org.ict.algorithm.leetcode.trie;

/**
 * @see https://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
 */
public class TrieArray {
    private TrieNode root;

    public TrieArray() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (p.arr[index] == null) {
                TrieNode temp = new TrieNode();
                p.arr[index] = temp;
                p = temp;
            } else {
                p = p.arr[index];
            }
        }// end-for-loop
        p.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode p = searchNode(word);
        if (p == null) {
            return false;
        }
        if (p.isEnd) {
            return true;
        }
        return false;
    }

    public boolean startWith(String prefix) {
        TrieNode p = searchNode(prefix);
        return (p == null ? false : true);
    }

    private TrieNode searchNode(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            if (p.arr[index] != null) {
                p = p.arr[index];
            } else {
                return null;
            }
        }
        if (p == root) {
            return null;
        }
        return p;
    }

    static class TrieNode {
        TrieNode[] arr;
        boolean isEnd;

        public TrieNode() {
            // Initialize with 26 alphabet size
            this.arr = new TrieNode[26];
        }
    }
}
