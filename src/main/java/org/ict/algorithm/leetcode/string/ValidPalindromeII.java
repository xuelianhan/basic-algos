package org.ict.algorithm.leetcode.string;

/**
 * Given a string s,
 * return true if the s can be palindrome after deleting at most one character from it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aba"
 * Output: true
 * Example 2:
 *
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * Example 3:
 *
 * Input: s = "abc"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 * @author sniper
 * @date 2022/4/26
 * LC680
 */
public class ValidPalindromeII {

    public static void main(String[] args) {
        //String s = "aba";
        //String s = "abca";
        String s = "abc";
        boolean result = validPalindrome(s);
        System.out.println(result);
    }


    public static boolean validPalindrome(String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            if (s.charAt(low) == s.charAt(high)) {
                low++;
                high--;
            } else {
                /**
                 * If removing str[low] makes the whole string
                 * palindrome. we basically check if substring
                 * str[low+1..high] is palindrome or not.
                 */
                if (isPalindrome(s, low + 1, high)) {
                    return true;
                }
                /**
                 * If removing str[high] makes the whole string
                 * palindrome. we basically check if substring
                 * str[low..high-1] is palindrome or not.
                 */
                if (isPalindrome(s, low, high - 1)) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(String str, int low, int high) {
        while (low < high) {
            if (str.charAt(low) != str.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}
