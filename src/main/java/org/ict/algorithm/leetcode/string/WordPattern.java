package org.ict.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a pattern and a string s, find if s follows the same pattern.
 *
 * Here follow means a full match,
 * such that there is a bijection between a letter in pattern and a non-empty word in s.
 *
 *
 *
 * Example 1:
 *
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 * Example 2:
 *
 * Input: pattern = "abba", s = "dog cat cat fish"
 * Output: false
 * Example 3:
 *
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * Output: false
 * Example 4:
 *
 * Input: pattern = "abba", s = "dog dog dog dog"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= pattern.length <= 300
 * pattern contains only lower-case English letters.
 * 1 <= s.length <= 3000
 * s contains only lower-case English letters and spaces ' '.
 * s does not contain any leading or trailing spaces.
 * All the words in s are separated by a single space.
 * @author sniper
 * LC290
 */
public class WordPattern {

    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog dog dog dog";
        boolean flag = wordPatternV2(pattern, s);
        System.out.println(flag);
    }

    public static boolean wordPatternV2(String pattern, String s) {
        int pLength = pattern.length();
        String[] arr = s.split(" ");
        int sLength = arr.length;
        if (pLength != sLength) {
            return false;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < pLength; i++) {
            Character c = pattern.charAt(i);
            /**
             * put method return the previous value associated with key, or
             * null if there was no mapping for key
             */
            if (map.put(String.valueOf(c), i) != map.put(arr[i], i)) {
                return false;
            }
        }
        return true;
    }



    public static boolean wordPattern(String pattern, String s) {
        int pLength = pattern.length();
        String[] arr = s.split(" ");
        int sLength = arr.length;
        if (pLength != sLength) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pLength; i++) {
            Character c = pattern.charAt(i);
            String t = map.get(c);
            if (null == t) {
                if (map.size() != 0 && map.values().contains(arr[i])) {
                    return false;
                }
                map.put(c, arr[i]);
            } else {
                if (!t.equalsIgnoreCase(arr[i])) {
                    return false;
                }
            }
        }
        return true;
    }

}
