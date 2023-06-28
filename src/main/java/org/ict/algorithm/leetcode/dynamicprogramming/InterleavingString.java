package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.Arrays;

/**
 * Given strings s1, s2, and s3,
 * find whether s3 is formed by an interleaving of s1 and s2.
 * An interleaving of two strings s and t is a configuration where s and t are divided into n and m
 * substrings respectively, such that:
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 *
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Explanation: One way to obtain s3 is:
 * Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
 * Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
 * Since s3 can be obtained by interleaving s1 and s2, we return true.
 *
 * Example 2:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
 *
 * Example 3:
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 *
 *
 * Constraints:
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lowercase English letters.
 *
 * Follow up: Could you solve it using only O(s2.length) additional memory space?
 * @author sniper
 * @date 27 Jun 2023
 * LC97, Medium, High Frequency, Top-150
 */
public class InterleavingString {

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        InterleavingString instance = new InterleavingString();
        instance.isInterleave(s1, s2, s3);
    }

    public boolean isInterleaveV3(String s1, String s2, String s3) {
        return false;
    }

    public boolean isInterleaveV2(String s1, String s2, String s3) {
        return false;
    }

    /**
     * Understanding the following solution
     * Time Cost 4ms
     * ---------------------------------------
     * 2-Dimension Dynamic Programming.
     * Similar with isInterleave
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleaveV1(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        /**
         * e.g. s1 = "a", s2 = "b", s3 = "a"
         * != means > or <
         */
        if (m + n != s3.length()) {
            return false;
        }
        /**
         * dp[i][j] is true if s3[0..i + j) can be formed
         * by the interleaving of s1[0..i) and s2[0..j)
         */
        boolean[][] dp = new boolean[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    boolean f1 = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    boolean f2 = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    dp[i][j] = (f1 || f2);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Understanding the following solution
     * 2-Dimension Dynamic Programming.
     * Time Cost 4ms
     * -----------------------------------
     * DP table represents if s3 is interleaving at (i+j)-th position when s1 is at i-th position,
     * and s2 is at j-th position. 0-th position means empty string.
     *
     * So if both s1 and s2 is currently empty, s3 is empty too,
     * and it is considered interleaving.
     *
     * If only s1 is empty,
     * then if previous s2 position is interleaving and current s2 position char is equal to s3 current position char,
     * it is considered interleaving.
     * Similar idea applies to when s2 is empty.
     *
     * When both s1 and s2 is not empty,
     * then if we arrive i, j from i-1, j,
     * then if i-1,j is already interleaving and i and current s3 position equal, it s interleaving.
     *
     * If we arrive i,j from i, j-1,
     * then if i, j-1 is already interleaving and j and current s3 position equal, it is interleaving.
     * ----------------------------------
     * s3: "aadbbcbcac"
     * s1: "aabcc"
     * s2: "dbbca"
     * dp:
     *       d  b  b  c  a
     *   [T, F, F, F, F, F],
     * a [T, F, F, F, F, F],
     * a [T, T, T, T, T, F],
     * b [F, T, T, F, T, F],
     * c [F, F, T, T, T, T],
     * c [F, F, F, T, F, T]
     *
     * i:1, j:1
     * f1 = dp[0][1] && s1.charAt(0) == s3.charAt(1)
     *    = False && 'a' == 'a'
     *    = False
     * f2 = dp[1][0] && s2.charAt(0) == s3.charAt(1)
     *    = True && 'd' == 'a'
     *    = False
     * dp[1][1] = f1 || f2 = False || False = False
     *
     * @author sherryxmhe
     * @see <a href="https://leetcode.com/problems/interleaving-string/solutions/31879/my-dp-solution-in-c/"></a>
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        /**
         * e.g. s1 = "a", s2 = "b", s3 = "a"
         * != means > or <
         */
        if (m + n != s3.length()) {
            return false;
        }
        /**
         * dp[i][j] is true if s3[0..i + j) can be formed
         * by the interleaving of s1[0..i) and s2[0..j)
         */
        boolean[][] dp = new boolean[m + 1][n + 1];
        /**
         * Empty string can form an interleaving string.
         */
        dp[0][0] = true;

        /**
         * If s2 is empty string, then compare s1 with s3.
         */
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        /**
         * If s1 is empty string, then compare s2 with s3.
         */
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        /**
         * Elements not on border, Two options:
         * 1. We arrive at (i, j) from (i - 1, j)
         * 2. We arrive at (i, j) from (i, j - 1)
         */
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                boolean f1 = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                boolean f2 = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                dp[i][j] = (f1 || f2);
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[m][n];
    }
}
