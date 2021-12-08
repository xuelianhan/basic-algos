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
 * @author sniper
 * LC647
 */
public class PalindromicSubstring {

    public int countSubStrings(String s) {
        int total = 0;
        if (null == s || s.length() == 0) {
            return total;
        }

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        /**
         * one letter
         */
        for (int i = 0; i < n; i++, total++) {
            dp[i][i] = true;
        }

        /**
         * two letters
         */
        for (int i = 0; i < n - 1; i++) {
            dp[i][i+1] = (s.charAt(i) == s.charAt(i+1) ? true : false);
            total += (dp[i][i+1] ? 1 : 0);
        }

        /**
         * three letters and above
         * notice: gap can be up to n
         */
        for (int gap = 3; gap <= n; gap++) {
            for (int i = 0, j = i + gap - 1; j < n; i++, j++) {
                dp[i][j] = ((dp[i+1][j-1]) && (s.charAt(i) == s.charAt(j)));
                total += (dp[i][j] ? 1 : 0);
            }
        }
        return total;
    }

    public int countSubstringsV2(String s) {
        if(s.length() == 0) {
            return 0;
        }
        int total = 0;
        for(int i = 0; i < s.length(); i++){
            /**
             * To check the palindrome of odd length palindromic sub-string
             */
            total += checkPalindrome(s, i, i);
            /**
             * To check the palindrome of even length palindromic sub-string
             */
            total += checkPalindrome(s, i, i+1);
        }
        return total;
    }

    /**
     * Check for the palindrome string
     */
    private int checkPalindrome(String s, int i, int j) {
        int total = 0;
        while( i>= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            /**
             * Increment the count if palindromin substring found
             */
            total++;
            /**
             * To trace string in left direction
             */
            i--;
            /**
             * To trace string in right direction
             */
            j++;
        }
        return total;
    }

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

    /**
     * Double-Pointer to check s from lo to hi is palindrome or not.
     * @param s
     * @param lo
     * @param hi
     * @return
     */
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
