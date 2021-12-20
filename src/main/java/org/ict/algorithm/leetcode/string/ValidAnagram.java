package org.ict.algorithm.leetcode.string;

import java.util.Arrays;

/**
 * Given two strings s and t,
 * return true if t is an anagram of s, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 5 * 104
 * s and t consist of lowercase English letters.
 *
 *
 * Follow up: What if the inputs contain Unicode characters?
 * How would you adapt your solution to such a case?
 * @author sniper
 * @date 2021/12/17 12:13 AM
 * LC242
 */
public class ValidAnagram {

    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        boolean result = isAnagram(s, t);
        System.out.println(result);
        //System.out.println("m1:" + Arrays.toString(m1));
        //System.out.println("m2:" + Arrays.toString(m2));
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        for(int i = 0; i < 256; i++) {
            m1[i] = 0;
            m2[i] = 0;
        }
        for(int i = 0; i < s.length(); ++i) {
            m1[s.charAt(i)]++;
            m2[t.charAt(i)]++;
        }
        for(int i = 0; i < 256; ++i) {
            if (m1[i] != m2[i])  {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagramV2(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            alphabet[t.charAt(i) - 'a']--;
        }
        for (int i : alphabet) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }


}
