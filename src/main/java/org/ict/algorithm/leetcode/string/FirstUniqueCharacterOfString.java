package org.ict.algorithm.leetcode.string;

import java.util.*;

/**
 * Given a string s,
 * find the first non-repeating character in it and return its index.
 * If it does not exist, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode"
 * Output: 0
 * Example 2:
 *
 * Input: s = "loveleetcode"
 * Output: 2
 * Example 3:
 *
 * Input: s = "aabb"
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of only lowercase English letters.
 * @author sniper
 * @date 2022/2/9
 * LC387
 */
public class FirstUniqueCharacterOfString {
    
    public static void main(String[] args) {
        String s = "loveleetcode";
        int result = firstUniqueChar(s);
        System.out.println(result);
    }
    
    public static int firstUniqueChar(String s) {
        /**
         * record the frequency of each character of s
         */
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            arr[idx]++;
        }
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (arr[idx] == 1) {
                return i;
            }
        }
        return -1;
    }
    
}
