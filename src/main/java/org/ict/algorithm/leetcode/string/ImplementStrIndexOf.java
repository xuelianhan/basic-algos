package org.ict.algorithm.leetcode.string;

/**
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 *
 *
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Example 3:
 *
 * Input: haystack = "", needle = ""
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= haystack.length, needle.length <= 5 * 104
 * haystack and needle consist of only lower-case English characters.
 *
 * @author sniper
 * LC28
 *
 */
public class ImplementStrIndexOf {

    public int strStr(String haystack, String needle) {
        if (null == haystack || null == needle || haystack.length() == 0 || needle.length() == 0) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        char[] a = haystack.toCharArray();
        char[] b = needle.toCharArray();
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < b.length; j++) {
                if (a[i] == a[j]) {

                } else {

                }
            }
        }
        return -1;
    }
}
