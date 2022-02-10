package org.ict.algorithm.leetcode.string;


/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 *
 * A subsequence of a string is a new string that
 * is formed from the original string by deleting some (can be none) of the characters
 * without disturbing the relative positions of the remaining characters.
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * Example 2:
 *
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 100
 * 0 <= t.length <= 104
 * s and t consist only of lowercase English letters.
 *
 *
 * Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk
 * where k >= 109, and you want to check one by one to see if t has its subsequence.
 * In this scenario, how would you change your code?
 * @author sniper
 * @date 2022/2/10
 * LC392
 */
public class IsSubsequence {

    public static void main(String[] args) {
        //String s = "axc";
        //String t = "ahbgdc";
        String s = "";
        String t = "aueeeu";
        boolean result = isSubsequence(s, t);
        System.out.println(result);
    }

    public static boolean isSubsequence(String s, String t)  {
        if (null == s && null == t) {
            return false;
        }
        if (null == s  && null != t) {
            return true;
        }
        if (s.length() == 0 && t.length() >= 0) {
            //s:"", t:"", expected true
            return true;
        }
        char[] tArr = t.toCharArray();
        /**
         * i scan original string t, j scan subsequence string s
         */
        int i = 0;
        int j = 0;
        for (;i < tArr.length;) {
            for(;j < s.length();) {
                /**
                 * if original t at the end, but subsequence s not finished
                 * s is not subsequence, return false directly.
                 */
                if (i >= tArr.length && j < s.length()) {
                    return false;
                }
                if (tArr[i] == s.charAt(j)) {
                    j++;
                }
                i++;
            }
            if ((j == s.length())) {
                return true;
            }
        }
        return false;
    }
}
