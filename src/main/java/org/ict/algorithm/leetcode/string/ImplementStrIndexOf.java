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

    /**
     * "mississippi"
     * "issip"
     * expected 4
     *
     * ""
     * "a"
     * expected -1
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (null == needle || needle.length() == 0) {
            //needle = ""
            return 0;
        }
        if (needle.length() > 0 &&  (haystack == null || haystack.length() == 0)) {
            //haystack = "", needle = "a"
            return -1;
        }
        char[] a = haystack.toCharArray();
        char[] b = needle.toCharArray();
        int index = -1;
        int step = 0;
        int j = 0;
        // start compare one by one from left to right
        for (int i = 0; i < a.length; i++) {
            step++;
            if (b[j] == a[i]) {
                if (j == b.length - 1) {
                    index = step - b.length;
                    break;
                }
                j++;
            } else {
                // if not match, back to start of needle
                j = 0;
            }
        }
        return index;
    }
}
