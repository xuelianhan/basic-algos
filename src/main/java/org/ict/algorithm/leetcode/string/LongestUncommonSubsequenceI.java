package org.ict.algorithm.leetcode.string;

/**
 * Given two strings a and b, return the length of the longest uncommon subsequence between a and b.
 * If the longest uncommon subsequence does not exist, return -1.
 *
 * An uncommon subsequence between two strings is a string that is a subsequence of one but not the other.
 *
 * A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
 *
 * For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc".
 * Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
 *
 *
 * Example 1:
 *
 * Input: a = "aba", b = "cdc"
 * Output: 3
 * Explanation: One longest uncommon subsequence is "aba" because "aba" is a subsequence of "aba" but not "cdc".
 * Note that "cdc" is also a longest uncommon subsequence.
 * Example 2:
 *
 * Input: a = "aaa", b = "bbb"
 * Output: 3
 * Explanation: The longest uncommon subsequences are "aaa" and "bbb".
 * Example 3:
 *
 * Input: a = "aaa", b = "aaa"
 * Output: -1
 * Explanation: Every subsequence of string a is also a subsequence of string b.
 * Similarly, every subsequence of string b is also a subsequence of string a.
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 100
 * a and b consist of lower-case English letters.
 * @author sniper
 * @date 01 Mar, 2022
 * LC521
 */

public class LongestUncommonSubsequenceI {

    /**
     * concise solution
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        /**
         * if a equals b and a.length == b.length
         * no uncommon subsequence between a and b.
         */
        if (a.equals(b)) {
            return -1;
        } else {
            if (a.length() != b.length()) {
                return Math.max(a.length(), b.length());
            } else {
                /**
                 *  or return b.length() is ok
                 */
                return a.length();
            }
        }
    }

    /**
     * In general, the first thought many people may have is to generate
     * all possible 2^n subsequences of both strings and store there
     * frequency in hashmap.
     * Longest subsequence whose frequency is equal to 1 will be the
     * required subsequence.
     * but the time complexity is O(2^x) + O(2^y), where x and y are the
     * lengths of two strings.
     *
     * @param a
     * @param b
     * @return
     */
    public int bruteForce(String a, String b) {
        return -1;
    }

}
