package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 *
 * Constraints:
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 *
 * @author sniper
 * @date 15 Oct, 2022
 * LC1143
 */
public class LongestCommonSubsequence {


    /**
     * Fixed length dp
     * Time Cost 34ms
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequenceV2(String text1, String text2) {
        int[][] dp = new int[1001][1001];
        int m = text1.length();
        int n = text2.length();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i + 1][j + 1] = text1.charAt(i) == text2.charAt(j) ? dp[i][j] + 1 : Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[m][n];
    }


    /**
     * Solution provided by votrubac
     * Time Cost 21ms
     *
     * LCS is a well-known problem, and there are similar problems here:
     *
     * 1092. Shortest Common Supersequence
     * 1062. Longest Repeating Substring
     * 516. Longest Palindromic Subsequence
     * Bottom-up DP utilizes a matrix dp where we track LCS sizes for each combination of i and j.
     *
     * If a[i] == b[j], LCS for i and j would be 1 plus LCS till the i-1 and j-1 indexes.
     * Otherwise, we will take the largest LCS if we skip a charracter from one of the string (max(dp[i - 1][j], dp[i][j - 1])).
     *
     * Complexity Analysis
     *
     * Time: O(nm), where n and m are the string sizes.
     * Memory: O(nm).
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequenceV1(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c1 = text1.charAt(i);
                char c2 = text2.charAt(j);
                dp[i + 1][j + 1] = (c1 == c2 ? dp[i][j] + 1 : Math.max(dp[i + 1][j], dp[i][j + 1]));
            }
        }

        return dp[m][n];
    }

    /**
     * Bottom-up DP
     *
     * Time Cost 32ms
     *
     * dp[i][j] definition:
     * for string text1[0...i-1] and string text2[0...j-1],
     * dp[i][j] is the length of the longest common subsequence.
     * dp[i][0]=0
     * dp[0][j]=0
     * e.g.text1 = "xabccde", text2 = "ace".
     *      x a b c c d e
     *    0 0 0 0 0 0 0 0
     *  a 0 0 1 1 1 1 1 1
     *  c 0 0 1 1 2 2 2 2
     *  e 0 0 1 1 2 2 2 3
     * int[][] dp = new dp[3+1][7+1];
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        /**
         * Due to i=0 or j=0, both dp[i][0] and dp[0][j] are zero
         * So both i and j start at one.
         */
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                /**
                 * Every char in text has two status, the char is in LCS, the char is not in LCS
                 * when text1.charAt(i-1) == text2.charAt(j-1), this char is in LCS.
                 * Otherwise, the length of LCS is the max one of dp[i-1][j] and dp[i][j-1]
                 */
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
