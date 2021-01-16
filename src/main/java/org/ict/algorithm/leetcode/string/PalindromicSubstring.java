package org.ict.algorithm.leetcode.string;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 *
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 *
 * Example 1:
 *
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 *
 * Example 2:
 *
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 *
 * Note:
 *
 * The input string length won't exceed 1000.
 *
 * @see <a href="https://www.geeksforgeeks.org/count-palindrome-sub-strings-string/></a>
 *
 * LC647
 */
public class PalindromicSubstring {

    public int countSubstringsV1(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            // notice j start with i not 0.
            for (int j = i; j < s.length(); j++) {
                total += (isPalindrome(s, i, j) ? 1 : 0);
            }
        }
        return total;
    }

    private boolean isPalindrome(String s, int lo, int hi) {
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
