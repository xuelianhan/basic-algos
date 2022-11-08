package org.ict.algorithm.leetcode.trie;

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 * word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 *         root
 *         /|\
 *        b d m
 *       /  |  \
 *      a   a   a
 *     /    |    \
 *    d     d     d
 *
 * Constraints:
 *
 * 1 <= word.length <= 25
 * word in addWord consists of lowercase English letters.
 * word in search consist of '.' or lowercase English letters.
 * There will be at most 3 dots in word for search queries.
 * At most 10^4 calls will be made to addWord and search.
 * @author sniper
 * @date 08 Nov, 2022
 * LC211
 */
public class DesignAddAndSearchWordsDS {

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
    static class WordDictionary {

        static class TrieNode {
            boolean end;
            TrieNode[] children = new TrieNode[26];
        }

        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        /**
         * Same as Trie
         * @param word
         */
        public void addWord(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int i = ch - 'a';
                if (node.children[i] == null) {
                    node.children[i] = new TrieNode();
                }
                node = node.children[i];
            }
            node.end = true;
        }

        /**
         *
         * @param word
         * @return
         */
        public boolean search(String word) {
            return search(word.toCharArray(), root, 0);
        }

        /**
         * Time Cost 1039 ms, very slow.
         * @param arr
         * @param p
         * @param k
         * @return
         */
        private boolean searchV1(char[] arr, TrieNode p, int k) {
            if (k == arr.length) {
                return p.end;
            }
            if (arr[k] != '.') {
                int i = arr[k] - 'a';
                if (p.children[i] == null) {
                    return false;
                }
                return search(arr, p.children[i], k + 1);
            }
            for (TrieNode node : p.children) {
                if (node != null && search(arr, node, k + 1)) {
                    return true;
                }
            }
            return false;
        }


        /**
         * Time Cost 1195 ms, very slow.
         * @param arr
         * @param p
         * @param k
         * @return
         */
        private boolean search(char[] arr, TrieNode p, int k) {
            if (k == arr.length) {
                return p.end;
            }
            if (arr[k] == '.') {
                for (TrieNode node : p.children) {
                    if (node != null && search(arr, node, k + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                int i = arr[k] - 'a';
                if (p.children[i] == null) {
                    return false;
                }
                return search(arr, p.children[i], k + 1);
            }
        }


    }
}
