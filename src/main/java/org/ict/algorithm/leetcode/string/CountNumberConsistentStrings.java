package org.ict.algorithm.leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.
 *
 * Return the number of consistent strings in the array words.
 *
 *
 *
 * Example 1:
 *
 * Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
 * Output: 2
 * Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
 * Example 2:
 *
 * Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
 * Output: 7
 * Explanation: All strings are consistent.
 * Example 3:
 *
 * Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
 * Output: 4
 * Explanation: Strings "cc", "acd", "ac", and "d" are consistent.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 10^4
 * 1 <= allowed.length <= 26
 * 1 <= words[i].length <= 10
 * The characters in allowed are distinct.
 * words[i] and allowed contain only lowercase English letters.
 * @author sniper
 * @date 14 Aug, 2022
 * LC1684
 */
public class CountNumberConsistentStrings {

    public int countConsistentStringsV2(String allowed, String[] words) {
        // support variable
        int res = words.length;
        boolean[] alpha = new boolean[26];
        // populating alpha
        for (char c: allowed.toCharArray()) {
            alpha[c - 'a'] = true;
        }
        // parsing all the words to see if each character is in alpha
        for (String word: words) {
            // in case we find an unallowed character, we decrease res and break
            for (char c: word.toCharArray()) {
                if (!alpha[c - 'a']) {
                    res--;
                    break;
                }
            }
        }
        return res;
    }

    public int countConsistentStrings(String allowed, String[] words) {
        int cnt = 0;
        Set<Character> allowedSet = new HashSet<>();
        for (char c : allowed.toCharArray()) {
            allowedSet.add(c);
        }
        Set<Character> wordSet = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                wordSet.add(c);
            }
            if (allowedSet.containsAll(wordSet)) {
                cnt++;
            }
            wordSet.clear();
        }
        return cnt;
    }
}
