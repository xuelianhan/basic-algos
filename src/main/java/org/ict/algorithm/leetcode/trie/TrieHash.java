package org.ict.algorithm.leetcode.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/></a>
 */
public class TrieHash {

    private TrieNode root;

    public TrieHash() {
        root = new TrieNode();
    }

    public void insert(String word) {
        Map<Character, TrieNode> children = root.children;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode t;
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                children.put(c, t);
            }

            if (i == word.length() - 1) {
                t.isLeaf = true;
            }
        }
    }

    public boolean search(String word) {
        TrieNode t = searchNode(word);
        return (t != null && t.isLeaf);
    }

    public boolean startWith(String prefix) {
        return (searchNode(prefix) != null);
    }

    private TrieNode searchNode(String str) {
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (children.containsKey(c)) {
                t = children.get(c);
                children = t.children;
            } else {
                return null;
            }
        }
        return t;
    }

    static class TrieNode {
        char c;
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isLeaf;

        public TrieNode() {}

        public TrieNode(char c) {
            this.c = c;
        }
    }

}

