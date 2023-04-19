package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.Arrays;

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

    /**
     * Understanding the following solution
     * Time Cost 42ms
     * @author tankztc
     * @see <a href="https://leetcode.com/problems/longest-palindromic-subsequence/solutions/99101/straight-forward-java-dp-solution"></a>
     * @param s
     * @return
     */
    public int longestPalindromeSubseqV2(String s) {
        int n = s.length();
        Integer[][] memo = new Integer[n][n];
        return helper(s, memo, 0, n - 1);
    }

    private int helper(String s, Integer[][] memo, int i, int j) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, memo, i + 1, j - 1) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, memo, i + 1, j), helper(s, memo, i, j - 1));
        }
        return memo[i][j];
    }


    public int longestPalindromeSubseqV1(String s) {
        int res = 0;
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = n - 1; i >= 0; i--) {
            int len = 0;
            for (int j = i + 1; j < n; j++) {
                int t = dp[j];
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = len + 2;
                }
                len = Math.max(len, t);
            }
        }

        for (int x : dp) {
            res = Math.max(res, x);
        }
        return res;
    }

    /**
     * Understanding the following solution
     *
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
     * @see <a href="https://stackoverflow.com/questions/13157961/2d-array-of-zeros"></a>
     * -------------------------------------------------------------
     * def longestPalindromeSubseq(self, s: str) -> int:
     *         if not s: return 0
     *         n = len(s)
     *         dp = [[0] * n for _ in range(n)]
     *         for i in range(n - 1, -1, -1):
     *             dp[i][i] = 1
     *             for j in range(i + 1, n):
     *                 if s[i] == s[j]:
     *                     dp[i][j] = dp[i + 1][j - 1] + 2
     *                 else:
     *                     dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
     *         return dp[0][n - 1]
     * ---------------------------------------------------------------
     * def longestPalindromeSubseq(s: String): Int = {
     *         if (s.isEmpty) return 0
     *         val n = s.length
     *         val dp = Array.ofDim[Int](n, n)
     *         for (i <- n - 1 to 0 by -1) {
     *             dp(i)(i) = 1
     *             for (j <- i + 1 until n) {
     *                 if (s(i) == s(j)) {
     *                     dp(i)(j) = dp(i + 1)(j - 1) + 2
     *                 } else {
     *                     dp(i)(j) = math.max(dp(i + 1)(j), dp(i)(j - 1))
     *                 }
     *             }
     *         }
     *         dp(0)(n - 1)
     *     }
     * ----------------------------------------------------------------
     * e.g. s = "bbbab"
     *   i
     * j 0 1 2 3 4
     * 0 1
     * 1   1
     * 2     1
     * 3       1
     * 4       1 1
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
