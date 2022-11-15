package org.ict.algorithm.leetcode.trie;

import sun.text.normalizer.Trie;

import java.util.*;

/**
 * A valid encoding of an array of words is any reference string s and array of indices indices such that:
 *
 * words.length == indices.length
 * The reference string s ends with the '#' character.
 * For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
 * Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["time", "me", "bell"]
 * Output: 10
 * Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
 * words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
 * words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
 * words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
 * Example 2:
 *
 * Input: words = ["t"]
 * Output: 2
 * Explanation: A valid encoding would be s = "t#" and indices = [0].
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * words[i] consists of only lowercase letters.
 * @author sniper
 * @date 14 Nov, 2022
 * LC820
 */
public class ShortEncodingOfWords {

    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        //String[] words = {"feipyxx","e"};
        //String[] words = {"me","time"};
        //String[] words = {"p","grah","qwosp"};//expected: qwosp#grah#
        //String[] words = {"ctxdic","c"};
        ShortEncodingOfWords instance = new ShortEncodingOfWords();
        int result = instance.minimumLengthEncodingV1(words);
        System.out.println(result);
    }

    /**
     * Improvement of Trie-DFS Solution.
     *
     * @param words
     * @return
     */
    public int minimumLengthEncodingV4(String[] words) {
        if (words.length == 1) {
            return words[0].length() + 1;
        }

        int result = 0;
        return result;
    }

    /**
     * Trie with Depth First Search Solution
     * e.g.1 words = ["bat", "cat", "at"]
     *              root depth==1
     *               |
     *               t   depth==2
     *               |
     *               a   depth==3
     *              / \
     *              b c  depth==4
     * e.g.2 words = ["time", "me", "bell"]
     *              root
     *              /  \
     *             e    l
     *             |    |
     *             m    l
     *             |    |
     *             i    e
     *             |    |
     *             t    b
     *
     * @param words
     * @return
     */
    public int minimumLengthEncodingV3(String[] words) {
        if (words.length == 1) {
            return words[0].length() + 1;
        }
        Set<String> dict = new HashSet<>(Arrays.asList(words));
        /**
         * Build Trie-Tree in word's reverse order.
         */
        TrieNode root = buildTrie(dict);
        int[] result = new int[] {0};
        /**
         * Because we reverse the words and add # at the first place of the reversed words,
         * so the length starts from 1(root node has only one character #).
         */
        dfsTrie(root, 1, result);
        return result[0];
    }

    /**
     *
     * e.g.1 words = ["bat", "cat", "at"]
     *              root length==1(#)
     *               |
     *               t   length==2(#t)
     *               |
     *               a   length==3(#ta)
     *              / \
     *              b c  length==4(#tab or #tac, both of them's length is 4.
     * dfsTrie(root, 1, result), isLeaf: false, length: 1
     *   dfsTrie(node-t, 2, result), isLeaf: false, length: 2
     *     dfsTrie(node-a, 3, result), isLeaf: false, length: 3
     *       dfsTrie(node-b, 4, result), isLeaf: true, length: 4
     *       result: 0 + 4 = 4
     *       dfsTrie(node-c, 4, result), isLeaf: true, length: 4
     *       result: 4 + 4 = 4
     *
     * @param parent
     * @param length
     * @param result
     */
    public void dfsTrie(TrieNode parent, int length, int[] result) {
        /**
         * isLeaf is used to mark the parent node whether is a leaf or not.
         */
        boolean isLeaf = true;
        for (TrieNode child : parent.children) {
            if (null != child) {
                isLeaf = false;
                dfsTrie(child, length + 1, result);
            }
        }
        if (isLeaf) {
            result[0] += length;
        }
    }


    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int wordLength;
    }

    public TrieNode buildTrie(Set<String> dict) {
        TrieNode root = new TrieNode();
        for (String word : dict) {
            TrieNode p = root;
            char[] arr = word.toCharArray();
            /**
             * Build Trie-Tree in word's reverse order.
             */
            for (int i = arr.length - 1; i >= 0; i--) {
                char ch = arr[i];
                int idx = ch - 'a';
                if (p.children[idx] == null) {
                    p.children[idx] = new TrieNode();
                }
                p = p.children[idx];
            }
        }
        return root;
    }

    /**
     * Use HashSet to remove all suffix-matched words.
     * @param words
     * @return
     */
    public int minimumLengthEncodingV2(String[] words) {
        if (words.length == 1) {
            return words[0].length() + 1;
        }
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                String suffix = word.substring(i);
                if (set.contains(suffix)) {
                    set.remove(suffix);
                }
            }
        }
        int result = 0;
        for (String word : set) {
            result += word.length() + 1;
        }
        return result;
    }

    /**
     * words = ["time", "me", "bell"];
     * 1.Reverse each word in words array and sort by lexicographical order.
     * words = ["em", "emit", "lleb" ]
     * 2.Check current word is the prefix of the step-on word.
     * If current word is prefix: result-length add 0
     * If current word is not prefix: result-length add the length of current word + 1( plus one is the #'s length)
     * @param words
     * @return
     */
    public int minimumLengthEncodingV1(String[] words) {
        if (words.length == 1) {
            return words[0].length() + 1;
        }

        int n = words.length;
        for (int i = 0; i < n; i++) {
            String reversed = reverseString(words[i]);
            words[i] = reversed;
        }
        /**
         * Sort words in dictionary order.
         */
        Arrays.sort(words);
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (words[i + 1].startsWith(words[i])) {
                result += 0;
            } else {
                result += (words[i].length() + 1);
            }
        }
        /**
         * Above index i only goes to n - 2, the end word at index n - 1 needs to be added too.
         */
        result += words[n - 1].length() + 1;
        return result;
    }

    public String reverseString(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while ( i < j) {
            char temp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = temp;
        }
        return new String(arr);
    }



    /**
     * e.g.words = {"ctxdic", "dictionary", "d", "c"}
     * 1.we sort words by length with descendant order
     *  words = {"dictionary", "ctxdic", "d" "c",};
     * String result = "";
     * i:0, "dictionary" is not in result, so we append "dictionary#" in the result, result:dictionary#
     * i:1, "ctxdic" is not in the result, so we append "ctxdic" in the result, result:dictionary#ctxdic#
     * i:2, "d" is not in the result, so we append "d" in the result, result: dictionary#ctxdic#d#
     * i:3, "c" is in right side of the result:"c#", and it steps after with "#", so we skip it.
     * The final result:dictionary#ctxdic#d#
     *
     * Notice here use lastIndexOf instead other than indexOf
     * For each word, we search it in the result string and check
     * current word exists in it or not.
     * If we don't find the word, we can append it directly.
     * If we have found the word, we check whether it has string "#" step after it.
     * If there is no # step after the word, we also append this word.
     * plus 1 means the #
     *
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        if (words.length == 1) {
            return words[0].length() + 1;
        }
        /**
         * Sort words in descend order by their length.
         */
        Arrays.sort(words, (s1, s2) -> {
            if (s1.length() < s2.length()) {
                return 1;
            } else if (s1.length() > s2.length()) {
                return -1;
            } else {
                return 0;
            }
        });
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            int found = result.lastIndexOf(word);
            if (found < 0 || !"#".equals(result.substring((found + word.length()), (found + word.length() + 1)))) {
                result.append(word);
                result.append("#");
            }
        }
        return result.length();
    }
}
