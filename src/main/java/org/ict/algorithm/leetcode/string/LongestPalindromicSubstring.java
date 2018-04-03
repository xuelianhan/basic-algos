package org.ict.algorithm.leetcode.string;

/**
 * p5
 * tag:string
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 100
 * Example:
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example:
 * Input: "cbbd"
 * Output: "bb"
 * 
 */
public class LongestPalindromicSubstring {

    private int lo, maxLen;

    public String longestPalindrome(String s) {
        System.out.println("input:" + s);
        int len = s.length();
        if (len < 2)
            return s;
        
        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        System.out.println("extendPalindrome between " + j + " and "+ k + ", charAt j:" + s.charAt(j) + ",charAt k:" + s.charAt(k));
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        System.out.println("extendPalindrome loop-end at " + j + " and "+ k);
        if (maxLen < k - j - 1) {
            System.out.println("maxLen:" + maxLen + " less than " + (k - j - 1));
            lo = j + 1;
            maxLen = k - j - 1;
        }
        System.out.println("lo:" + lo + ", maxLen:" + maxLen);
    }
    
    public static void main(String[] args) {
        String s1 = "aba";
        String s2 = "abcdefgfedcba";
        String s3 = "aaaaaaaaaaaaaaaaaaaaaaaa";
        LongestPalindromicSubstring obj = new LongestPalindromicSubstring();
        //obj.longestPalindrome(s1);
        obj.longestPalindrome(s2);
        //obj.longestPalindrome(s3);
    }
}
