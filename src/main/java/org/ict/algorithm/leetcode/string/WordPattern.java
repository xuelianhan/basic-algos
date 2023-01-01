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
        /*String pattern = "a";
        String s = "a";*/

        String pattern = "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdd";
        String s = "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s t t";
        boolean flag = wordPatternV3(pattern, s);
        System.out.println(flag);
    }

    public boolean wordPatternV4(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        String[] animals = s.split(" ");
        if (pattern.length() != animals.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String animal = animals[i];
            if (map.containsKey(c)) {
                if (!map.get(c).equals(animal)) return false;
            } else {
                if (map.containsValue(animal)) return false;
                map.put(c, animal);
            }
        }
        return true;
    }

    public static boolean wordPatternV3(String pattern, String s) {
        String[] arr = s.split(" ");
        int sLength = arr.length;
        if (sLength != pattern.length()) {
            return false;
        }
        /**
         * Notice here using Object as Key and Value
         */
        Map<Object, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            Character c = pattern.charAt(i);
            /**
             * We use putIfAbsent other than put method, due to the auto-unboxing for Integer from -128~127.
             * Integer -128 ~ 127 already cached.
             * Using putIfAbsent() won't exceed that range but put() will.
             * putIfAbsent method return the previous value associated with key,
             * or null if there was no mapping for the key.
             */
            Integer x= map.putIfAbsent(c, i);
            Integer y = map.putIfAbsent(arr[i], i);
            /**
             * x:128, y:128,
             * x != y is true due to the Integer cache range internal is from -128 to 127,
             * In here 128 has already out of that range.
             */
            System.out.println("c:" + c + ", arr[i]:" + arr[i] + ", x:" + x + ", y:" + y + " x == y:" + (x == y));
            if (x == null && y != null) {
                return false;
            }
            if (x != null && y == null) {
                return false;
            }
            if (x == null && y == null) {
                continue;
            }
            int x1 = x;
            int y1 = y;
            if (x1 != y1) {
                return false;
            }
        }
        return true;
    }

    public boolean wordPatternV2(String pattern, String s) {
        String[] arr = s.split(" ");
        int sLength = arr.length;
        if (sLength != pattern.length()) {
            return false;
        }
        /**
         * Notice here using Object as Key and Value
         */
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            Character c = pattern.charAt(i);
            /**
             * We use putIfAbsent other than put method, due to the auto-unboxing for Integer from -127-128.
             * Using putIfAbsent() won't exceed that range but put() will.
             * putIfAbsent method return the previous value associated with key, or
             * null if there was no mapping for key
             */
            Object x = map.putIfAbsent(c, i);
            Object y = map.putIfAbsent(arr[i], i);
            if (x == null && y != null) {
                return false;
            }
            if (x != null && y == null) {
                return false;
            }
            if (x == null && y == null) {
                continue;
            }
            int x1 = (int)x;
            int y1 = (int)y;
            if (x1 != y1) {
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
