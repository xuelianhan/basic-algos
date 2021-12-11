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

    public static void main(String[] args) {
        /**
         * expected 4, but output -1
         */
        String haystack = "mississippi";
        String needle = "issip";
        System.out.println(strStr(haystack, needle));
    }

    /**
     * 0 1 2 3 4
     * h e l l o
     * l l<--
     *   l l<--
     *     l l<--
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        if ( null == needle || needle.length() == 0) {
            //corner case 1: ("a", "") or ("", "")
            return 0;
        }
        if (needle.length() > 0 && (haystack == null || haystack.length() == 0)) {
            // corner case 2: ("", "a")
            return -1;
        }
        char[] a = haystack.toCharArray();
        char[] b = needle.toCharArray();
        int index = -1;
        int matched = 0;
        int j = 0;
        // start compare one by one from left to right
        for (int i = 0; i < a.length;) {
            if (b[j] == a[i]) {
                matched++;
                if (j == b.length - 1) {
                    index = i - b.length + 1;
                    break;
                }
                j++;
                i++;
            } else {
                // if not match, j back to start of needle
                // i back to the next round
                i = i - matched + 1;
                j = 0;
                matched = 0;
            }
        }
        return index;
    }
}
