package org.ict.algorithm.leetcode.trie;

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
     * I have added some descriptions with decomposed cases to help understand her thought.
     * -----------------------------------------------------------------
     * We want to concatenate string B to string A to make AB palindrome.
     * How could AB be palindrome?
     * If B ends with x, then A must starts with x.
     * If the second character of B is y, then the second last character of A is y...
     * That is,
     *   Case 1. A must be prefix of reversed B, and the rest of reversed B should be palindrome. For example,
     * 	(B:oooabc - cbaooo,    A:cba       AB:cba|oooabc), the rest of reversed B:(ooo)
     *   Case 2. Or, reversed B must be prefix of A, and the rest of A should be palindrome. For example,
     * 	(B:abc - cba           A:cbaooo,   AB:cbaooo|abc), the rest of A:(ooo)
     *
     * Each word in words can be B. We put all reversed words in a trie.
     * (cba, oooabc) ---> reversed: (abc, cbaooo)
     * (cbaooo, abc) ---> reversed: (oooabc, cba)
     * Each word in words can be A, so we search A in trie, In this way,
     *   Case 1. if we found A in trie, and the branch under the end node is a palindrome, we found it!
     *   Case 2. if we reach a leaf of trie, and the rest of A is palindrome, we found it!
     *
     *   For Case 1., we modify TrieNode data structure by adding belowPalindromeWordIds.
     *   the belowPalindromeWordIds is a list of word indices such that nodes below can construct a palindrome.
     *   For Case 2., we create a method isPalindrome(str, start, end) .
     *
     * Please take care of corner cases of empty string.
     * Both ("", self-palindrome) and (self-palindrome, "") are still palindrome.
     * -------------------------------------------------------------------
     * Let's take some examples to understand the above thoughts of GraceMeng.
     * case forms:(A, B), the first element in case pair is A, the second one is B:
     * case 1. ("abcd","dcba"), ("bat","tab"), A is the prefix of reversed-B, B is the prefix of reversed-A.
     * case 2. ("s", "lls"), A is the prefix of reversed-B.
     * case 3. ("sssll", "lls"), B is the prefix of reversed-A.
     * case 4. ("a",""), ("", "ccc"), empty string combined with palindrome string already, this is the corner case.
     *
     * case1 can be combined into case2 or case3.
     * So there are three cases in total need to be considered.
     *
     * We build the trie-tree with all the words reversed from the input.
     *              root
     *           ----------------
     *          / | \  \   \  \  \
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
        Map<String, Integer> wordIdxMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordIdxMap.put(words[i], i);
        }

        return null;
    }

    public boolean isPalindrome(String s, int lo, int hi) {
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
