package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string of English letters s, return the greatest English letter which occurs as both a lowercase and uppercase letter in s.
 * The returned letter should be in uppercase.
 * If no such letter exists, return an empty string.
 *
 * An English letter b is greater than another letter a if b appears after a in the English alphabet.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "lEeTcOdE"
 * Output: "E"
 * Explanation:
 * The letter 'E' is the only letter to appear in both lower and upper case.
 * Example 2:
 *
 * Input: s = "arRAzFif"
 * Output: "R"
 * Explanation:
 * The letter 'R' is the greatest letter to appear in both lower and upper case.
 * Note that 'A' and 'F' also appear in both lower and upper case, but 'R' is greater than 'F' or 'A'.
 * Example 3:
 *
 * Input: s = "AbCdEfGhIjK"
 * Output: ""
 * Explanation:
 * There is no letter that appears in both lower and upper case.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of lowercase and uppercase English letters.
 *
 * @author sniper
 * @date 13 Aug, 2022
 * LC2309
 */
public class GreatestLetterInUpperLowerCase {

    /**
     * 90-65=25
     * 122-97=25
     * @param args
     */
    public static void main(String[] args) {
        String s = "AbCdEfGhIjK";
        String result = greatestLetter(s);
        System.out.println(result);
        char c = 'Z' - 'A';//ascii code is 25
        System.out.println(c);
        char add = (char)('a' + c);
        System.out.println(add);
    }

    public String greatestLetterV2(String s) {
        Set<Character> set = new HashSet<>();
        for(char ch : s.toCharArray()) {
            set.add(ch);
        }
        for(char ch = 'Z'; ch >= 'A'; ch--) {
            /**
             * The distance of Big A-Z equals the distance of a-z
             * we can use this character to operate chars.
             */
            if(set.contains(ch) && set.contains((char)('a'+(ch-'A')))) {
                return ""+ch;
            }
        }
        return "";
    }

    /**
     * a-z:97-122
     * A-Z:65-90
     *
     * @param s
     * @return
     */
    public static String greatestLetter(String s) {
        char[] upper = new char[26];
        char[] lower = new char[26];
        Arrays.fill(upper, '0');
        Arrays.fill(lower, '0');
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                upper[c - 'A']++;
            }
            if (Character.isLowerCase(c)) {
                lower[c - 'a']++;
            }
        }
        char res = '0';
        for(int i = 25; i >= 0; i--) {
            if (upper[i] > '0' && lower[i] > '0') {
                /**
                 * 90 - 25 = 65
                 */
                res = (char)(65 + i);
                break;
            }
        }
        return (res == '0' ? "" : Character.toString(res));
    }
}
