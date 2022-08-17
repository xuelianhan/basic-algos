package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A string is good if there are no repeated characters.
 *
 * Given a string s​​​​​,
 * return the number of good substrings of length three in s​​​​​​.
 *
 * Note that if there are multiple occurrences of the same substring,
 * every occurrence should be counted.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "xyzzaz"
 * Output: 1
 * Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz".
 * The only good substring of length 3 is "xyz".
 * Example 2:
 *
 * Input: s = "aababcabc"
 * Output: 4
 * Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
 * The good substrings are "abc", "bca", "cab", and "abc".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s​​​​​​ consists of lowercase English letters.
 * @author sniper
 * @date 2022/8/17
 * LC1876
 */
public class SubstringsOfSizeThreeWithDistinctCharacters {

    public static void main(String[] args) {
        String s = "xyzzaz";
        //String s = "aababcabc";
        int result = countGoodSubstrings(s);
        System.out.println(result);
    }

    public int countGoodSubstringsV2(String s) {
        int result = 0;
        /**
         * Due to charAt(i-1), charAt(i+1)
         * So i start with 1, end with (i < s.length() - 1)
         */
        for (int i = 1;i < s.length() - 1; i++) {
            if(s.charAt(i-1) != s.charAt(i)
                    && s.charAt(i+1) != s.charAt(i)
                    && s.charAt(i+1) != s.charAt(i-1)) {
                result++;
            }
        }
        return result;
    }

    public static int countGoodSubstrings(String s) {
        if (s.length() < 3) {
            return 0;
        }
        int cnt = 0;
        Set<Character> set = new HashSet<>();
        int k = 3;
        /**
         * Due to substring(i - k, i)
         * So i start at k, end at s.length()
         */
        for (int i = k; i <= s.length(); i++) {
            String sub = s.substring(i - k, i);
            for (char c : sub.toCharArray()) {
                set.add(c);
            }
            if (set.size() == k) {
                cnt++;
            }
            set.clear();
        }
        return cnt;
    }
}
