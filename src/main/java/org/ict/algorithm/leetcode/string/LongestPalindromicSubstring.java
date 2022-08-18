package org.ict.algorithm.leetcode.string;

/**
 * p5
 * tag:string
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 100
 * Example:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example:
 * Input: "cbbd"
 * Output: "bb"
 * @see <a href="https://leetcode.com/GraceMeng/">Xinrong Meng, A Software Engineer In Databricks, Apache Committer</a>
 * LC5 TOP IQ
 * 
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        //String s = "aba";
        String s = "abcdefgfedcba";
        //String s = "aaaaaaaaaaaaaaaaaaaaaaaa";
        LongestPalindromicSubstring obj = new LongestPalindromicSubstring();
        //obj.longestPalindrome(s1);
        String result = obj.longestPalindrome(s);
        //obj.longestPalindrome(s3);
        System.out.println(result);
    }

    /**
     * Brute-Force Solution, Very Slow, cost 2014ms, Memory usage 567MB
     * aba
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        if (isPalindrome(s, 0, s.length() - 1)) {
            return s;
        }
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (isPalindrome(sub, 0, sub.length() - 1)) {
                    if (sub.length() > res.length()) {
                        res = sub;
                    }
                }
            }
        }
        return res;
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


    private int lo, maxLen;

    public String longestPalindromeV2(String s) {
        System.out.println("input:" + s);
        int len = s.length();
        if (len < 2) {
            return s;
        }
        for (int i = 0; i < len-1; i++) {
            /**
             * odd case, like "aba"
             * assume odd length, try to extend Palindrome as possible
             */
            extendPalindrome(s, i, i);
            /**
             * even case, like "abba"
             * assume even length.
             */
            extendPalindrome(s, i, i+1);
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }
    

}
