package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * Given a string s. In one step you can insert any character at any index of the string.
 *
 * Return the minimum number of steps to make s palindrome.
 *
 * A Palindrome String is one that reads the same backward as well as forward.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we do not need any insertions.
 * Example 2:
 *
 * Input: s = "mbadm"
 * Output: 2
 * Explanation: String can be "mbdadbm" or "mdbabdm".
 * Example 3:
 *
 * Input: s = "leetcode"
 * Output: 5
 * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 * @author sniper
 * @date 23 Apr, 2023
 * LC1312, Hard
 * @see LongestPalindromicSubsequence
 */
public class MinimumInsertionStepsMakeStringPalindrome {

    /**
     * Since any character can be added at any position,
     * if the original string is reversed and added directly after it,
     * it must be a palindrome,
     * but this addition does not necessarily use the least number of characters.
     * In fact, we should try to make use of some subsequence palindromes in the original string as much as possible.
     * So the longer the subsequence, the fewer characters need to be added,
     * and the problem is converted to find the length of the longest subsequence,
     * which becomes the Longest Palindromic Subsequence.
     * Define a two-dimensional dp array,
     * where dp[i][j] denotes the length of the longest subsequence palindrome string
     * among the substrings in the range [i, j] of the original string
     *
     * e.g. s ="mbadm", the longest palindrome subsequence is "mam",
     * we only need to add two characters 'b' and 'd' into the original string,
     * so the answer is s.length - max(longest_palindrome_subsequence)
     *
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return n - dp[0][n - 1];
    }

}
