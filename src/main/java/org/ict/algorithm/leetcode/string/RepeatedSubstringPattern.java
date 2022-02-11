package org.ict.algorithm.leetcode.string;

/**
 *
 * Given a string s, check if it can be constructed by taking
 * a substring of it and appending multiple copies of the substring together.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abab"
 * Output: true
 * Explanation: It is the substring "ab" twice.
 * Example 2:
 *
 * Input: s = "aba"
 * Output: false
 * Example 3:
 *
 * Input: s = "abcabcabcabc"
 * Output: true
 * Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of lowercase English letters.
 * @author sniper
 * @date 12 Feb, 2022
 * LC459
 */
public class RepeatedSubstringPattern {

    public static void main(String[] args) {
        // Expected false, but returns true, fix it.
        String s = "ababba";
        boolean result = repeatedSubstringPattern(s);
        System.out.println(result);
    }

    public static boolean repeatedSubstringPattern(String s) {
        if (s.length() == 1) {
            return true;
        }
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[c - 'a']++;
        }
        int last = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                if (last == 0) {
                    last = arr[i];
                } else {
                    if (last == arr[i]) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        }
        if (last == 1) {
            return false;
        }
        return true;
    }

}
