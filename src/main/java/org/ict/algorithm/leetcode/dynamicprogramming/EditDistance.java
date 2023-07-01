package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 *
 * Constraints:
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 * @author sniper
 * @date 01 Jul 2023
 * LC72, Medium,
 */
public class EditDistance {


    /**
     * Dynamic Programming Solution
     * @param word1
     * @param word2
     * @return
     */
    public int minDistanceV1(String word1, String word2) {
        //todo
        return 0;
    }

    /**
     * Dynamic Programming Solution
     * Time Cost 6ms
     * -----------------------------
     * dp[i][j] represents the steps needed to convert from the first i characters of word1 to the first j characters of word2.
     * First, assign a value to the first row and column of this two-dimensional array dp,
     * which is simple because the first row and column always correspond to a string that is empty,
     * so the conversion step is exactly the length of another string
     * e.g. word1:"bbc", word2:"abcd"
     *     null a b c d
     * null  0  1 2 3 4
     *    b  1  1 1 2 3
     *    b  2  2 1 2 3
     *    c  3  3 2 1 2
     * By observation, we can find that when word1[i] == word2[j],
     * dp[i][j] = dp[i - 1][j - 1], and in other cases,
     * dp[i][j] is the smallest of its three values of left, upper left, and upper left plus 1.
     * In fact, the left, upper, and upper left here
     * correspond to the add, delete, and modify operations, respectively.
     *            ------- dp[i-1][j-1], if word1[i-1] == word2[j-1]
     * dp[i][j] = |
     *            |______ min(dp[i-1][j-1], min(dp[i-1][j], dp[i][j-1])) + 1
     * -----------------------------------------------------------
     * class Solution:
     *     def minDistance(self, word1: str, word2: str) -> int:
     *         m = len(word1)
     *         n = len(word2)
     *         dp = [[0] * (n + 1) for _ in range(m + 1)]
     *
     *         for i in range(1, m + 1):
     *             dp[i][0] = i
     *
     *         for j in range(1, n + 1):
     *             dp[0][j] = j
     *
     *         for i in range(1, m + 1):
     *             for j in range(1, n + 1):
     *                 if word1[i - 1] == word2[j - 1]:
     *                     dp[i][j] = dp[i - 1][j - 1]
     *                 else:
     *                     dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
     *         return dp[m][n]
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
