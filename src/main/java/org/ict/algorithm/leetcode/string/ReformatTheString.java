package org.ict.algorithm.leetcode.string;

/**
 * You are given an alphanumeric string s. (Alphanumeric string is a string consisting of lowercase English letters and digits).
 *
 * You have to find a permutation of the string where no letter is followed by another letter and no digit is followed by another digit. That is, no two adjacent characters have the same type.
 *
 * Return the reformatted string or return an empty string if it is impossible to reformat the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "a0b1c2"
 * Output: "0a1b2c"
 * Explanation: No two adjacent characters have the same type in "0a1b2c". "a0b1c2", "0a1b2c", "0c2a1b" are also valid permutations.
 * Example 2:
 *
 * Input: s = "leetcode"
 * Output: ""
 * Explanation: "leetcode" has only characters so we cannot separate them by digits.
 * Example 3:
 *
 * Input: s = "1229857369"
 * Output: ""
 * Explanation: "1229857369" has only digits so we cannot separate them by characters.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of only lowercase English letters and/or digits.
 * @author sniper
 * @date 2022/8/12
 * LC1417
 */
public class ReformatTheString {

    public static void main(String[] args) {
        String s = "ab123";
        String result = reformat(s);
        System.out.println(result);
    }

    public static String reformat(String s) {
        /**
         * s = "a"
         * s = "1"
         */
        if (s.length() == 1) {
            return s;
        }
        StringBuilder csb = new StringBuilder();
        StringBuilder nsb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                csb.append(c);
            }
            if (Character.isDigit(c)) {
                nsb.append(c);
            }
        }
        /**
         * s = "123"
         * s = "abc"
         * s = "ab1234"
         */
        if (csb.length() == 0 || nsb.length() == 0 || Math.abs(csb.length() - nsb.length()) > 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int min = Math.min(csb.length(), nsb.length());
        int i = 0;
        if (csb.length() <= nsb.length()) {
            for (; i < min; i++) {
                sb.append(nsb.charAt(i));
                sb.append(csb.charAt(i));
            }
        } else {
            for (; i < min; i++) {
                sb.append(csb.charAt(i));
                sb.append(nsb.charAt(i));
            }
        }
        if (i == csb.length() && i < nsb.length())  {
            sb.append(nsb.charAt(i));
        }
        if (i == nsb.length() && i < csb.length()) {
            sb.append(csb.charAt(i));
        }
        return sb.toString();
    }
    
}
