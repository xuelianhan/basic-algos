package org.ict.algorithm.leetcode.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a 0-indexed array of unique strings words.
 *
 * A palindrome pair is a pair of integers (i, j) such that:
 *
 * 0 <= i, j < word.length,
 * i != j, and
 * words[i] + words[j] (the concatenation of the two strings) is a
 * palindrome
 * .
 * Return an array of all the palindrome pairs of words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["abcddcba","dcbaabcd","slls","llssssll"]
 * Example 2:
 *
 * Input: words = ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 * Example 3:
 *
 * Input: words = ["a",""]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["a","a"]
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 5000
 * 0 <= words[i].length <= 300
 * words[i] consists of lowercase English letters.
 * @author sniper
 * @date 16 Nov, 2022
 * LC336
 */
public class PalindromePairs {

    /**
     * The following is the thought from GraceMeng, you can see the original post by the href-link below.
     * I add some descriptions with decomposed cases to help understand her thought.
     * -----------------------------------------------------------------
     * We want to concatenate string B to string A to make AB palindrome.
     * How could AB be palindrome?
     * If B ends with x, then A must starts with x.
     * If the second character of B is y, then the second last character of A is y...
     * That is,
     *   Case 1. A must be prefix of reversed B, and the rest of reversed B should be palindrome.
     *   For example, (B:oooabc - cbaooo,    A:cba       AB:cba|oooabc), the rest of reversed B:(ooo)
     *
     *   Case 2. Or, reversed B must be prefix of A, and the rest of A should be palindrome.
     *   For example, (B:abc - cba           A:cbaooo,   AB:cbaooo|abc), the rest of A:(ooo)
     *
     * Each word in words can be B. We put all reversed words in a trie.
     *   (cba, oooabc) ---> reversed: (abc, cbaooo)
     *   (cbaooo, abc) ---> reversed: (oooabc, cba)
     *
     * Each word in words can be A, so we search A in trie, In this way,
     *   Case 1. if we found A in trie, and the branch under the end node is a palindrome, we found it!
     *   e.g. (A, B)=(cba, oooabc), we found A:cba in trie, the branch under the end node(a) is "ooo", "ooo" is a palindrome.
     *   Case 2. if we reach a leaf of trie, and the rest of A is palindrome, we found it!
     *   e.g. A:"cbaooo", when we reach the leaf node 'c' in the path "oooabc"(reversed-A), the rest of A:(ooo) is a palindrome.
     *
     *   For Case 1., we modify TrieNode data structure by adding belowPalindromeWordIds.
     *   the belowPalindromeWordIds is a list of word indices such that nodes below can construct a palindrome.
     *   For Case 2., we create a method isPalindrome(str, start, end).
     *
     * Please take care of corner cases of empty string.
     * the constraint words[i].length == 0 indicates it may be empty string.
     * Both ("", self-palindrome) and (self-palindrome, "") are still palindrome.
     * -------------------------------------------------------------------
     * Let's take some examples to understand the above thoughts of GraceMeng.
     * case forms:(A, B), the first element in case pair is A, the second one is B:
     * case 1. ("abcd", "dcba"), ("bat", "tab"), A is the prefix of reversed-B, B is the prefix of reversed-A.
     * case 2. ("s", "lls"), A is the prefix of reversed-B.
     * case 3. ("sssll", "lls"), B is the prefix of reversed-A.
     * case 4. ("a", ""), ("", "ccc"), empty string combined with palindrome string already, this is the corner case.
     *
     * case-1 can be combined into case-2 or case-3.
     * So there are three cases in total need to be considered.
     *
     * We build the trie-tree with all the words reversed from the input.
     *           -----root----
     *          / | \  \   \  \
     *         a  l  s  d   o  c
     *        /   |   \  \   \  \
     *       b    l    l  c   o  b
     *      /     |     \  \   \  \
     *     c      s      l  b   o  a
     *    /       |          \   \  \
     *   d        s           a   a  o
     *            |                \  \
     *            s                 b  o
     *                               \  \
     *                                c  o
     * @see <a href="https://leetcode.com/problems/palindrome-pairs/solutions/176205/beats-80-trie-java-with-explanations"></a>
     * @author GraceMeng
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        if (null == words || words.length == 0) {
            return new ArrayList<>();
        }
        

        return null;
    }

    public TrieNode buildTrie(String word, int index) {
        TrieNode root = new TrieNode();
        TrieNode p = root;
        int n = word.length();
        char[] arr = word.toCharArray();
        for (int i = 0; i < n; i++) {
            int idx = arr[i] - 'a';
            if (p.children[idx] == null) {
                p.children[idx] = new TrieNode();
            }
            p = p.children[idx];
            if (isPalindrome(word, i + 1, n - 1)) {
                p.belowPalindromeWordIds.add(index);
            }
        }
        p.endIndex = index;
        return root;
    }



    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        /**
         * Equals to -1 in default.
         * If it is a word's end, it is the index of the word.
         */
        int endIndex = -1;
        /**
         *  List of word indices such that nodes below can construct a palindrome
         *  e.g.
         */
        List<Integer> belowPalindromeWordIds = new ArrayList<>();
    }

    public List<Integer> getSelfPalindrome(String[] words) {
        List<Integer> wordIndices = new ArrayList<>();
        int i = 0;
        for (String word : words) {
            if (isPalindrome(word, 0, word.length() - 1)) {
                wordIndices.add(i);
            }
            i++;
        }
        return wordIndices;
    }

    public String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public boolean isPalindrome(String s, int lo, int hi) {
        if (lo > hi) {
            return false;
        }
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) {
                return false;
            } else {
                lo++;
                hi--;
            }
        }
        return true;
    }


}
