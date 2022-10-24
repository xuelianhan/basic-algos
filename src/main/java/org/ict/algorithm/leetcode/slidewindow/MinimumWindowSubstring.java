package org.ict.algorithm.leetcode.slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t of lengths m and n respectively,
 * return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 *
 * Constraints:
 *
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 10^5
 * s and t consist of uppercase and lowercase English letters.
 *
 *
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 *
 * @author sniper
 * @date 23 Oct, 2022
 * LC76
 */
public class MinimumWindowSubstring {

    /**
     * Slide-Window with HashMap
     * Cost 21 ms
     * @param s
     * @param t
     * @return
     */
    public String minWindowV1(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int left = 0, right = 0, minStart = 0;
        int counter = t.length();
        int minLength = Integer.MAX_VALUE;
        while (right < s.length()) {
            char rc = s.charAt(right);
            if (map.getOrDefault(rc, 0) > 0) {
                counter--;
            }
            map.put(rc, map.getOrDefault(rc, 0) - 1);
            right++;
            while (counter == 0) {
                if (minLength > right - left) {
                    minLength = right - left;
                    minStart = left;
                }
                char lc = s.charAt(left);
                map.put(lc, map.getOrDefault(lc, 0) + 1);
                if (map.getOrDefault(lc, 0) > 0) {
                    counter++;
                }
                left++;
            }
        }
        return (minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength));
    }

    /**
     * Slide-Window with Array
     * Cost 4ms
     * 1. Use two pointers: left and right to represent a window.
     * 2. Move right to find a valid window.
     * 3. When a valid window found, move left to find a smaller window.
     *
     * e.g.
     * s = "abcdef", t = "bd", expected "bcd"
     * map[97]=0, map[98]=1, map[99]=0, map[100]=1, map[101]=0, map[102]=0, counter=2
     * right:0, rc:a, map[97]=0, counter:2, map[97]=-1, right:1, left:0
     * right:1, rc:b, map[98]=1, counter:1, map[98]=0, right:2, left:0
     * right:2, rc:c, map[99]=0, counter:1, map[99]=-1, right:3, left:0
     * right:3, rc:d, map[100]=1, counter:0, map[100]=0, right:4, minLength:4-0=4, minStart=0, lc:a, map[97]=-1+1=0, left:1
     *                            counter:0, right:4, minLength:4-1=3, minStart:1, lc:b, map[98]=0+1=1, counter:1, left:2
     * right:4, rc:e, map[101]=0, counter:1, map[101]=-1, right:5
     * right:5, rc:f, map[102]=0, counter:1, map[102]=-1, right:6
     * outer-while-loop-end
     * minLength:3, minStart:1, subString(1, 1+3)=bcd
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        /**
         * Count char in string t
         */
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int left = 0, right = 0, minStart = 0;
        /**
         * counter represents the number of t's chars found in s.
         */
        int counter = t.length();
        int minLength = Integer.MAX_VALUE;
        while (right < s.length()) {
            char rc = s.charAt(right);
            /**
             * If a char in s exist in t, then decrease the counter.
             */
            if (map[rc] > 0) {
                counter--;
            }
            /**
             * Decrease map[rc]. If char does not exist in t, map[rc] will be negative.
             */
            map[rc]--;
            right++;
            /**
             * When a valid window found, move left to find smaller window.
             */
            while (counter == 0) {
                if (minLength > right - left) {
                    minLength = right - left;
                    minStart = left;
                }
                char lc = s.charAt(left);
                map[lc]++;
                /**
                 * When char exists in t, increase counter.
                 */
                if (map[lc] > 0) {
                    counter++;
                }
                left++;
            }
        }
        return (minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength));
    }
}
