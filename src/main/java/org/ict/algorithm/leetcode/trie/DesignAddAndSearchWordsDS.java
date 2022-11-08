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
 * Notice the tree level high == word.length
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

        /**
         * Consider storing length of the current longest word.
         * In search method you can return False if word is longer than current longest word
         */
        int maxDepth;

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
            maxDepth = Math.max(maxDepth, word.length());
        }

        /**
         *
         * @param word
         * @return
         */
        public boolean search(String word) {
            /**
             * Time cost 411 ms, when add length check.
             */
            if (word.length() > maxDepth) {
                return false;
            }
            return searchV2(word.toCharArray(), root, 0);
            //return searchV1(word.toCharArray(), root, 0);
            //return search(word.toCharArray(), root, 0);
        }


        /**
         * Time Cost 975 ms, very slow
         * @param arr
         * @param p
         * @param k
         * @return
         */
        private boolean searchV2(char[] arr, TrieNode p, int k) {
            /**
             * Because trie-tree start from root, high of each tree-branch
             * equals the length of word.
             */
            if (k == arr.length) {
                return p.end;
            }
            if (arr[k] != '.') {
                int i = arr[k] - 'a';
                if (p.children[i] == null) {
                    return false;
                }
                return searchV2(arr, p.children[i], k + 1);
            }
            /**
             * When we meet '.', we traverse the no-null nodes at the same level, if one branch of them is matched, then we find the
             * word. This process is similar with DFS. At here, using DFS is better than BFS, we don't need to check all the branches of current node.
             */
            for (TrieNode node : p.children) {
                if (node != null) {
                    if (searchV2(arr, node, k + 1)) {
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * Time Cost 1275 ms, very slow.
         * @param arr
         * @param p
         * @param k
         * @return
         */
        private boolean searchV1(char[] arr, TrieNode p, int k) {
            /**
             * Because trie-tree start from root, high of each tree-branch
             * equals the length of word.
             */
            if (k == arr.length) {
                return p.end;
            }
            if (arr[k] != '.') {
                int i = arr[k] - 'a';
                if (p.children[i] == null) {
                    return false;
                }
                return searchV1(arr, p.children[i], k + 1);
            }
            /**
             * When we meet '.', we traverse the no-null nodes at the same level, if one branch of them is matched, then we find the
             * word. This process is similar with DFS. At here, using DFS is better than BFS, we don't need to check all the branches of current node.
             */
            for (TrieNode node : p.children) {
                if (node != null && searchV1(arr, node, k + 1)) {
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
            /**
             * Because trie-tree start from root, high of each tree-branch
             * equals the length of word.
             */
            if (k == arr.length) {
                return p.end;
            }
            if (arr[k] == '.') {
                /**
                 * When we meet '.', we traverse the no-null nodes at the same level, if one branch of them is matched, then we find the
                 * word. This process is similar with DFS. At here, using DFS is better than BFS, we don't need to check all the branches of current node.
                 */
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
