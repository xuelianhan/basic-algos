package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 *
 *
 * Constraints:
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 *
 * @author sniper
 * @date 2022/8/18
 * LC5, Medium, frequency=42, TOP IQ
 */
public class LongestPalindromicSubstring {

    /**
     * Solution provided by xiaoaojianghu
     *
     * Cost: 170ms, 45.7MB
     * @param s
     * @return
     */
    public String longestPalindromeDPV3(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        int ml = 0;
        int mr = 0;
        for (int r = 0; r < len; r++) {
            dp[r][r] = true;
            for (int l = 0; l < r; l++) {
                // l  r
                // abca ...  //a = a, ok
                if (s.charAt(l) != s.charAt(r)) {
                    continue;
                }
                // l  r
                // abca ...   //b != c continue
                if (l + 1 < r - 1 && !dp[l + 1][r - 1]) {
                    continue;
                }
                dp[l][r] = true;
                if (r - l > mr - ml) {
                    ml = l;
                    mr = r;
                }
            }
        }
        return s.substring(ml, mr + 1);
    }

    /**
     * Solution provided by Venkatesh M(venkim)
     * If you do the brute force way you would generate a lot more strings than this method looks at.
     * which is set of all subsets ( rather substrings) -
     * E(sigma) (n-i) as i runs from 1 to n-1 = n-squared + n(n+1)/2 - O(n-squared) complexity.
     * This problem can be done using DP with n-squared complexity as shown above by [@GraceMeng](https://leetcode.com/GraceMeng)
     * with a few comments below by me based on that
     *
     * Cost: 256ms, 45.9MB
     **/
    public String longestPalindromeDPV2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int len = s.length();
        // dp[i][j] stands for status of a substring starting at i and ending at j incl j
        boolean[][] dp = new boolean[len][len];

        // initialize all the 1 character palins
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        // to compute dp[i][j] we need dp[i+1][j-1]
        // so the i - outer loop needs to go from higher to lower
        // and the j - inner loop needs to go from lower to higher
        int maxLen = 0, maxSta = 0, maxEnd = 0;
        for (int i = len; i >= 0; i--) {
            // dist of 0 - already covered by initialization
            for (int dist = 1; dist < len - i; dist++) {
                int j = i + dist;
                // we are ready to compute dp [i] [j]
                if (dist == 1 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                } else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1];
                }
                // if true
                if (dp[i][j] && maxLen < (j-i+1)) {
                    maxLen = j - i + 1;
                    maxSta = i;
                    maxEnd = j;
                }
            }
        }
        return s.substring(maxSta, maxEnd + 1);
    }

    /**
     * Solution provided by GraceMeng(Xinrong Meng), an engineer in Databricks
     * Intuitively,
     * we list all the substrings, pick those palindromic, and get the longest one.
     * This approach takes O(n^3) time complexity, where n is the length of s.
     *
     * Dynamic Programming
     * The problem can be broken down into subproblems which are reused several times.
     * Overlapping subproblems lead us to Dynamic Programming.
     *
     * We decompose the problem as follows:
     *
     * 1.State variable
     * The start index and end index of a substring can identify a state, which influences our decision.
     * So the state variable is state(start, end) indicates whether s[start, end] inclusive is palindromic
     *
     * 2.Goal state
     * max(end - start + 1) for all state(start, end) = true
     *
     * 3.State transition
     * for start = end (e.g. 'a'), state(start, end) is True
     * for start + 1 = end (e.g. 'aa'), state(start, end) is True if s[start] = s[end]
     * for start + 2 = end (e.g. 'aba'),  state(start, end) is True if s[start] = s[end] and state(start + 1, end - 1)
     * for start + 3 = end (e.g. 'abba'),  state(start, end) is True if s[start] = s[end] and state(start + 1, end - 1)
     * ...
     * This approach takes O(n^2) time complexity, O(n^2) space complexity, where n is the length of s.
     *
     * Cost: 249ms, 72.3MB
     *
     * @param s
     * @return
     */
    public String longestPalindromeDP(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int n = s.length();
        /**
         * dp[i][j] means the state of substring from i to j
         */
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int longestPalindromeStart = 0, longestPalindromeLen = 1;
        for (int end = 0; end < n; end++) {
            for (int start = end - 1; start > -1; start--) {
                if (s.charAt(start) == s.charAt(end)) {
                    if (end - start == 1 || dp[start+1][end-1]) {
                        dp[start][end] = true;
                        int palindromeLen = end - start + 1;
                        if (longestPalindromeLen < palindromeLen) {
                            longestPalindromeStart = start;
                            longestPalindromeLen = palindromeLen;
                        }
                    }
                }
            }
        }
        return s.substring(longestPalindromeStart, longestPalindromeStart + longestPalindromeLen);
    }
}
