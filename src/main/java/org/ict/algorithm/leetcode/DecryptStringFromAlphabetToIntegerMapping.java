package org.ict.algorithm.leetcode;


import java.util.Arrays;

/**
 * You are given a string s formed by digits and '#'.
 * We want to map s to English lowercase characters as follows:
 *
 * Characters ('a' to 'i') are represented by ('1' to '9') respectively.
 * Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
 * Return the string formed after mapping.
 *
 * The test cases are generated so that a unique mapping will always exist.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "10#11#12"
 * Output: "jkab"
 * Explanation: "j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
 * Example 2:
 *
 * Input: s = "1326#"
 * Output: "acz"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of digits and the '#' letter.
 * s will be a valid string such that mapping is always possible.
 * @author sniper
 * @date 2022/8/10
 * LC1309
 */
public class DecryptStringFromAlphabetToIntegerMapping {

    public static void main(String[] args) {
        String s = "10#11#12";
        //String s = "1326#";
        String result = freqAlphabets(s);
        System.out.println(result);
    }

    public static String freqAlphabets(String s) {
        char[] chars = new char[26];
        for(int i = 0; i < 26; i++) {
            chars[i] = (char)(97 + i);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '#') {
                String x = s.substring(i - 2, i);
                sb.append(chars[Integer.valueOf(x) - 1]);
                i-= 2;
            } else {
                int y = Integer.valueOf(Character.toString(c)) - 1;
                sb.append(chars[y]);
            }
        }
        return sb.reverse().toString();
    }
}
