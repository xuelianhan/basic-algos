package org.ict.algorithm.leetcode.twopointer;

/**
 * @author sniper
 * @date 2022/8/19
 */
public class LongestPalindromicSubstring {

    /**
     * More intuitive solution provided by solkowy
     * For each mid point i,
     * use two points (left, right) to check the values on i's left and right sides respectively.
     *
     * Cost 21ms, 42.1MS
     * @param s
     * @return
     */
    public String longestPalindromeDPV3(String s) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            /**
             * Every time, we expand substring from i only when substring is palindromic
             * e.g. s = "dabcccbae"
             * e.g. s = "aba"
             * e.g. s = "abba"
             */
            char cur = s.charAt(i);
            int left = i;
            int right = i;

            while (left >= 0 && s.charAt(left) == cur) {
                left--;
            }
            while (right < s.length() && s.charAt(right) == cur) {
                right++;
            }

            while (left >=0 && right < s.length()) {
                if (s.charAt(left) != s.charAt(right)) {
                    break;
                }
                left--;
                right++;
            }
            /**
             * the palindromic substring is from[left+1, right-1]
             * This is dependent on substring method
             */
            left = left + 1;
            if (end - start < right - left) {
                end = right;
                start = left;
            }
        }
        return s.substring(start, end);
    }
}
