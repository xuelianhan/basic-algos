package org.ict.algorithm.leetcode.slidewindow;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 * @author sniper
 * @date 22 Oct, 2022
 * LC3
 */
public class LongestSubstringWithoutRepeatingCharacters {


    public int lengthOfLongestSubstringV5(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        //todo
        return res;
    }

    public int lengthOfLongestSubstringV4(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        //todo
        return res;
    }


    public int lengthOfLongestSubstringV3(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        //todo
        return res;
    }


    public int lengthOfLongestSubstringV2(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        //todo
        return res;
    }


    public int lengthOfLongestSubstringV1(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        //todo
        return res;
    }

    /**
     * Understand the following solution
     *
     * Two-Pointers + HashSet solution.
     * The idea is use a hashset to track the longest substring without repeating characters so far,
     * use a fast pointer j to see if character j is in the hashset or not, if not, add it to the hashset, move j forward and update the max length,
     * otherwise, delete from the head by using a slow pointer i until we can put character j to the hash set.
     * 
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        if (s == null || s.length() == 0) {
            return res;
        }
        int i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                res = Math.max(res, set.size());
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return res;
    }
}
