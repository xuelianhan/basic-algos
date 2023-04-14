package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 * A subsequence is a sequence that can be derived from another sequence
 * by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 *
 *
 * Constraints:
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 * Notice that:
 * Subsequences, unlike substrings, do not need to be contiguous
 *
 * @author sniper
 * @date 14 Apr, 2023
 * LC516, Medium
 */
public class LongestPalindromicSubsequence {

    public int longestPalindromeSubseqV2(String s) {
        int res = 0;
        //todo
        return res;
    }


    public int longestPalindromeSubseqV1(String s) {
        int res = 0;
        //todo
        return res;
    }

    /**
     * Time Cost 55ms.
     * Create a two-dimensional DP array,
     * where dp[i][j] denotes the longest palindromic subsequence of the string in the interval [i,j].
     * Recursive formula analysis, if s[i] == s[j], then i and j can increase the length of the echo string by 2.
     * We know the value of the middle dp[i + 1][j - 1], then its plus 2 is the value of dp[i][j].
     * If s[i] ! = s[j], one of the characters i or j can be removed,
     * and then the remaining string in both cases is assigned to dp[i][j] if the dp value is larger,
     * then the recursive formula is as follows
     * 1.If s[i] == s[j], then dp[i][j] = dp[i + 1][j - 1] + 2;
     * 2.If s[i] != s[j], then dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
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

        return dp[0][n - 1];
    }
}
