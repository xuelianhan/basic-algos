package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's,
 * and all the 0's and all the 1's in these substrings are grouped consecutively.
 *
 * Substrings that occur multiple times are counted the number of times they occur.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's:
 * "0011", "01", "1100", "10", "0011", and "01".
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 * Example 2:
 *
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are 4 substrings: "10", "01", "10", "01"
 * that have equal number of consecutive 1's and 0's.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either '0' or '1'.
 * @author sniper
 * @date 2022/4/26
 * LC696
 */
public class CountBinarySubstrings {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String s = "00110011";
        subString(s.toCharArray(), s.length(), list);
        System.out.println(list);
    }

    public int countBinarySubstrings(String s) {
        if (s.length() == 1) {
            return 0;
        }
        if (s.length() == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                return 0;
            } else {
                return 1;
            }
        }
        //length of string greater than 2
        int size = s.length() / 2;

        return 0;
    }

    /**
     * Raw implementation of all substring for str array with length of n
     * @param str
     * @param n
     * @param list
     */
    public static void subString(char str[], int n, List<String> list) {
        // Pick starting point
        for (int len = 1; len <= n; len++) {
            // Pick ending point
            for (int i = 0; i <= n - len; i++) {
                //  Print characters from current
                // starting point to current ending
                // point.
                int j = i + len - 1;
                StringBuffer sb = new StringBuffer();
                for (int k = i; k <= j; k++) {
                   sb.append(str[k]);
                }
                list.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }
    }
}
