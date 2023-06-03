package org.ict.algorithm.leetcode.string;

/**
 * Given a string s,
 * return true if the s can be palindrome after deleting at most one character from it.
 *
 * Example 1:
 * Input: s = "aba"
 * Output: true
 *
 * Example 2:
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 *
 * Example 3:
 * Input: s = "abc"
 * Output: false
 *
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 *
 * @author sniper
 * @date 2022/4/26
 * LC680, Easy, frequency=252
 */
public class ValidPalindromeII {

    public static void main(String[] args) {
        //String s = "aba";
        //String s = "abca";
        String s = "abc";
        ValidPalindromeII instance = new ValidPalindromeII();
        boolean result = instance.validPalindrome(s);
        System.out.println(result);
    }

    /**
     * Understanding the following solution
     *
     * Two-Pointer Solution
     * ----------------------
     * class Solution:
     *     def isValid(self, arr, lo, hi) -> bool:
     *         while lo < hi:
     *             if arr[lo] != arr[hi]:
     *                 return False
     *             lo += 1
     *             hi -= 1
     *         return True
     *
     *     def validPalindrome(self, s: str) -> bool:
     *         lo, hi = 0, len(s) - 1
     *         arr = list(s)
     *         while lo < hi:
     *             if arr[lo] != arr[hi]:
     *                 return self.isValid(arr, lo + 1, hi) or self.isValid(arr, lo, hi - 1)
     *             lo += 1
     *             hi -= 1
     *         return True
     * ------------------------------------------
     * @param s
     * @return
     */
    public boolean validPalindromeV1(String s) {
        int lo = 0;
        int hi = s.length() - 1;
        char[] arr = s.toCharArray();
        while (lo < hi) {
            if (arr[lo] != arr[hi]) {
                return isValid(arr, lo + 1, hi) || isValid(arr, lo, hi - 1);
            }
            lo++;
            hi--;
        }
        return true;
    }

    private boolean isValid(char[] arr, int lo, int hi) {
        while (lo < hi) {
            if (arr[lo] != arr[hi]) {
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }

    /**
     * Two-Pointer Solution
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
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

    /**
     * Check if substring from low to high is palindrome or not.
     *
     * @param str
     * @param low
     * @param high
     * @return
     */
    public boolean isPalindrome(String str, int low, int high) {
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
