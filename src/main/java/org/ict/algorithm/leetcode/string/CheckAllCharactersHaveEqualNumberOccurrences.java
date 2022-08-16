package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given a string s, return true if s is a good string, or false otherwise.
 *
 * A string s is good if all the characters that
 * appear in s have the same number of occurrences (i.e., the same frequency).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abacbc"
 * Output: true
 * Explanation: The characters that appear in s are 'a', 'b', and 'c'. All characters occur 2 times in s.
 * Example 2:
 *
 * Input: s = "aaabb"
 * Output: false
 * Explanation: The characters that appear in s are 'a' and 'b'.
 * 'a' occurs 3 times while 'b' occurs 2 times, which is not the same number of times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 * @author sniper
 * @date 2022/8/16
 * LC1941
 */
public class CheckAllCharactersHaveEqualNumberOccurrences {

    public static void main(String[] args) {
        String s = "abacbc";
        boolean result = areOccurrencesEqual(s);
        System.out.println(result);
    }

    public static boolean areOccurrencesEqualV2(String s) {
        if (s.length() == 1) {
            return true;
        }
        int[] freq = new int[26];
        Arrays.fill(freq, 0);
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        /**
         * Instead of looping over the array to get the first frequency we encounter,
         * we get the frequency of the first letter in the string
         * (or any letter in the string, it doesn't matter)
         */
        int prev = freq[s.charAt(0) - 'a'];
        /**
         * Compare non-zero frequencies to the frequency of the first letter
         */
        for(int num : freq){
            /**
             * Return false if the current frequency is not equal to "prev"
             * Notice the condition (num != 0) here is essential
             */
            if(num != 0 && num != prev){
                return false;
            }
        }
        /**
         * If we reached here, then all the frequencies are the same
         */
        return true;
    }

    public static boolean areOccurrencesEqual(String s) {
        if (s.length() == 1) {
            return true;
        }
        int[] freq = new int[26];
        Arrays.fill(freq, 0);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            freq[c - 'a']++;
        }
        for (int i = 1; i < s.length(); i++) {
            if (freq[s.charAt(i - 1) - 'a'] != freq[s.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }
}
