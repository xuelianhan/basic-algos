package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given a string s. Reorder the string using the following algorithm:
 *
 * Pick the smallest character from s and append it to the result.
 * Pick the smallest character from s which is greater than the last appended character to the result and append it.
 * Repeat step 2 until you cannot pick more characters.
 * Pick the largest character from s and append it to the result.
 * Pick the largest character from s which is smaller than the last appended character to the result and append it.
 * Repeat step 5 until you cannot pick more characters.
 * Repeat the steps from 1 to 6 until you pick all characters from s.
 * In each step, If the smallest or the largest character appears more than once you can choose any occurrence and append it to the result.
 *
 * Return the result string after sorting s with this algorithm.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaaabbbbcccc"
 * Output: "abccbaabccba"
 * Explanation: After steps 1, 2 and 3 of the first iteration, result = "abc"
 * After steps 4, 5 and 6 of the first iteration, result = "abccba"
 * First iteration is done. Now s = "aabbcc" and we go back to step 1
 * After steps 1, 2 and 3 of the second iteration, result = "abccbaabc"
 * After steps 4, 5 and 6 of the second iteration, result = "abccbaabccba"
 * Example 2:
 *
 * Input: s = "rat"
 * Output: "art"
 * Explanation: The word "rat" becomes "art" after re-ordering it with the mentioned algorithm.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of only lowercase English letters.
 * @author sniper
 * @date 2022/8/12
 * LC1370, Easy, frequency=8
 */
public class IncreasingDecreasingString {

    public static void main(String[] args) {
        String s = "rat";
        String result = sortStringV2(s);
        System.out.println(result);
    }

    public static String sortStringV2(String s) {
        StringBuilder ans = new StringBuilder();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            ++count[c - 'a'];
        }
        while (ans.length() < s.length()) {
            add(count, ans, true);
            add(count, ans, false);
        }
        return ans.toString();
    }

    private static void add(int[] cnt, StringBuilder ans, boolean asc) {
        for (int i = 0; i < 26; ++i) {
            int j = (asc ? i : 25 - i);
            if (cnt[j]-- > 0) {
                ans.append((char)(j + 'a'));
            }
        }
    }

    /**
     *  char[] arr = s.toCharArray();
     *  Arrays.sort(arr);
     *  return new String(arr);
     * @param s
     * @return
     */
    public static String sortString(String s) {
        Map<Character, Integer> frequency = new HashMap<>(26);
        int max = 0;
        for(char c : s.toCharArray()) {
            int cur = frequency.getOrDefault(c, 0);
            frequency.put(c, cur + 1);
            if (cur + 1 > max) {
                max = cur + 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < max; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (frequency.getOrDefault(c, 0) > 0) {
                    sb.append(c);
                    frequency.put(c, frequency.get(c)-1);
                }
            }
            for (char c = 'z'; c >= 'a'; c--) {
                if (frequency.getOrDefault(c, 0) > 0) {
                    sb.append(c);
                    frequency.put(c, frequency.get(c)-1);
                }
            }
        }
        return sb.toString();
    }

}
