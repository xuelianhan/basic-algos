package org.ict.algorithm.leetcode.string;


import java.util.Arrays;

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
 * LC14, Easy, frequency=31
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        LongestCommonPrefix instance = new LongestCommonPrefix();
        String res = instance.longestCommonPrefix(strs);
        System.out.println(res);
    }

    /**
     * Understanding the following solution
     * Time Cost 1ms
     * class Solution:
     *     def longestCommonPrefix(self, strs: List[str]) -> str:
     *         if not strs:
     *             return ""
     *         shortest = min(strs,key=len)
     *         for i, ch in enumerate(shortest):
     *             for other in strs:
     *                 if other[i] != ch:
     *                     return shortest[:i]
     *         return shortest
     * @param strs
     * @return
     */
    public String longestCommonPrefixV5(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        int i = 0;
        while (i < first.length()) {
            if (first.charAt(i) == last.charAt(i)) {
                i++;
            } else {
                break;
            }
        }
        return first.substring(0, i);
    }

    /**
     * Understanding the following solution
     * Time Cost 1ms
     * @param strs
     * @return
     */
    public String longestCommonPrefixV4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        int i = 0;
        while (i < first.length() && i < last.length()) {
            if (first.charAt(i) == last.charAt(i)) {
                i++;
            } else {
                break;
            }
        }
        return first.substring(0, i);
    }

    /**
     * Understanding the following solution
     * Time Cost 1ms
     * @param strs
     * @return
     */
    public String longestCommonPrefixV3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        int i = 0;
        while (i < first.length()) {
            if (first.charAt(i) == last.charAt(i)) {
                i++;
            } else {
                break;
            }
        }
        return i == 0 ? "" : first.substring(0, i);
    }


    /**
     * Understanding the following solution.
     * Time Cost 1ms
     *
     * At first, sorting the input string array. What is the advantage of doing so?
     * Since it is sorted alphabetically,
     * the two strings that have more letters in common will be sorted together,
     * and the strings that have fewer letters in common with everyone else will be squeezed into the first and last ends,
     * so if there is a common prefix, it will definitely appear in the first and last strings,
     * so you only need to find the common prefix of the first and last strings.
     * For example, example 1 is sorted as ["flight", "flow", "flower"],
     * and example 2 is sorted as [" cat", "dog", "racecar"],
     * although example 2 does not have a common prefix,
     * it can be assumed that the common prefix is the empty string and appears in the first and last strings.
     * Since it is arranged in alphabetical order,
     * not by length, so the relationship between the length of the first and last letters is not known,
     * in order to prevent overflow errors,
     * only the length of the shorter one while such is traversed, finding the common prefix to return.
     * @param strs
     * @return
     */
    public String longestCommonPrefixV2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs);
        int i = 0;
        int len = Math.min(strs[0].length(), strs[strs.length - 1].length());
        while (i < len && strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) {
            i++;
        }
        return strs[0].substring(0, i);
    }

        /**
         * Understanding the following solution
         * Time Cost 1ms
         *
         * If you find that the current character is not equal to the character in the corresponding position of the first string,
         * it means that there will not be a longer common prefix,
         * so you can directly retrieve the substring of the common prefix by using the method of substring.
         * If the result is not returned before the end of the traversal,
         * the first word is the common prefix, return as the result.
         * @param strs
         * @return
         */
    public String longestCommonPrefixV1(String[] strs) {
        int n = strs.length;
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < n; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != strs[0].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public String longestCommonPrefix(String[] strs) {
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

    public char[] commonPrefixOfStr(String s1, String s2) {
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
