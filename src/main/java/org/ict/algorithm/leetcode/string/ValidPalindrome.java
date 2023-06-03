package org.ict.algorithm.leetcode.string;

/**
 * Given a string, determine if it is a palindrome,
 * considering only alphanumeric characters and ignoring cases.
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 *
 * Example 2:
 * Input: "race a car"
 * Output: false
 *
 *
 * Constraints:
 * s consists only of printable ASCII characters.
 *
 * LC125
 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (null == s || s.isEmpty()) {
            return true;
        }
        char[] a = s.toCharArray();
        int lo = 0, hi = s.length() - 1;
        char head, tail;
        while (lo <= hi) {
            head = a[lo];
            tail = a[hi];
            if (!Character.isLetterOrDigit(head)) {
                lo++;
            } else if (!Character.isLetterOrDigit(tail)) {
                hi--;
            } else {
                if (Character.toLowerCase(head) != Character.toLowerCase(tail)) {
                    return false;
                }
                lo++;
                hi--;
            }
        }
        return true;
    }
}
