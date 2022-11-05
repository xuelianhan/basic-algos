package org.ict.algorithm.leetcode.trie;

/**
 * @author sniper
 * @date 06 Nov, 2022
 */
public class Trie {

    public static class TrieNode {

        private boolean isWord;

        private String word;

        private TrieNode[] children = new TrieNode[26];

        public boolean isWord() {
            return this.isWord;
        }

        public void word(boolean isWord)  {
            this.isWord = isWord;
        }

        public String getWord() {
            return this.word;
        }

        public void setWord(String word) {
            this.word = word;
        }


        public TrieNode[] getChildren() {
            return this.children;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (null == node.children[idx]) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isWord = true;
        node.word = word;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (null == node.children[idx]) {
                return false;
            }
            node = node.children[idx];
        }
        return node.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (null == node.children[idx]) {
                return false;
            }
            node = node.children[idx];
        }
        return true;
    }

    public TrieNode getRoot() {
        return this.root;
    }
}
