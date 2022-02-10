package org.ict.algorithm.leetcode.string;

import java.util.Arrays;

/**
 * You are given two strings s and t.
 *
 * String t is generated by random shuffling string s and then add one more letter at a random position.
 *
 * Return the letter that was added to t.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcd", t = "abcde"
 * Input: s = "abcd", t = "aebcd"
 * Output: "e"
 * Explanation: 'e' is the letter that was added.
 * Example 2:
 *
 * Input: s = "", t = "y"
 * Output: "y"
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: "a"
 * Example 4:
 *
 * Input: s = "ae", t = "aea"
 * Output: "a"
 *
 * ae, ade
 * ae, aed
 * ae, dae
 * ae, aae
 *
 * Constraints:
 *
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s and t consist of lower-case English letters.
 *
 * @author sniper
 * LC389
 */
public class FindTheDifference {

    /**
     * the solution is like subtracting s from t
     * @param s
     * @param t
     * @return
     */
    public char findTheDifferenceV2(String s, String t) {
        int[] c = new int[26];
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        for (int i = 0; i < S.length; i++) {
            c[S[i] - 'a']++;
        }
        for (int i = 0; i < T.length; i++) {
            c[T[i] - 'a']--;
        }
        for(int i = 0; i < c.length; i++) {
            if (c[i] != 0) {
                return (char)(i + 'a');
            }
        }
        return '0';
    }

    /**
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        if (s.length() == 0 && t.length() == 1) {
            return t.charAt(0);
        }
        char[] sortedS = s.toCharArray();
        char[] sortedT = t.toCharArray();
        Arrays.sort(sortedS);
        Arrays.sort(sortedT);

        for(int i = 0; i < s.length(); i++) {
            if (sortedS[i] != sortedT[i]) {
                /**
                 * Notice here is from sorted T, not sorted S
                 */
                return sortedT[i];
            }
        }
        return sortedT[s.length()];
    }
}