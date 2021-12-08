package org.ict.algorithm.leetcode.string;


/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lower-case English letters.
 *
 * @author sniper
 *
 * LC14
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        //String[] strs = {"dog","racecar","car"};
        System.out.println(longestCommonPrefix(strs));
        //char[] a = commonPrefixOfStr("flower", "flow");
        //System.out.println(new String(a));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        char[] commonPrefix = commonPrefixOfStr(strs[0], strs[1]);
        if (null == commonPrefix || commonPrefix.length == 0) {
            return "";
        }
        for (int i = 2; i < strs.length; i++) {
            String s1 = new String(commonPrefix);
            String s2 = strs[i];
            commonPrefix = commonPrefixOfStr(s1, s2);
            if (null == commonPrefix || commonPrefix.length == 0) {
                return "";
            }
        }
        return new String(commonPrefix).trim();
    }

    public static char[] commonPrefixOfStr(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return null;
        }
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        int min = Math.min(a1.length, a2.length);
        char[] prefix = new char[min];
        int k = 0;
        for (; k < min; k++) {
            if (a1[k] == a2[k]) {
                prefix[k] = a1[k];
            } else {
                break;
            }
        }
       return prefix;
    }

}
