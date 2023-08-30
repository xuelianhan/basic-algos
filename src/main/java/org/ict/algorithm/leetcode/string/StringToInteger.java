package org.ict.algorithm.leetcode.string;


/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 * 1.Read in and ignore any leading whitespace.
 *
 * 2.Check if the next character (if not already at the end of the string) is '-' or '+'.
 * Read this character in if it is either. This determines if the final result is negative or positive respectively.
 * Assume the result is positive if neither is present.
 *
 * 3.Read in next the characters until the next non-digit character or the end of the input is reached.
 * The rest of the string is ignored.
 *
 * 4.Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32).
 * If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
 * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range.
 * Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
 *
 * Return the integer as the final result.
 *
 * Note:
 *
 * Only the space character ' ' is considered a whitespace character.
 * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
 *
 *
 * Example 1:
 *
 * Input: s = "42"
 * Output: 42
 * Explanation: The underlined characters are what is read in, the caret is the current reader position.
 * Step 1: "42" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "42" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "42" ("42" is read in)
 *            ^
 * The parsed integer is 42.
 * Since 42 is in the range [-231, 231 - 1], the final result is 42.
 * Example 2:
 *
 * Input: s = "   -42"
 * Output: -42
 * Explanation:
 * Step 1: "   -42" (leading whitespace is read and ignored)
 *             ^
 * Step 2: "   -42" ('-' is read, so the result should be negative)
 *              ^
 * Step 3: "   -42" ("42" is read in)
 *                ^
 * The parsed integer is -42.
 * Since -42 is in the range [-231, 231 - 1], the final result is -42.
 * Example 3:
 *
 * Input: s = "4193 with words"
 * Output: 4193
 * Explanation:
 * Step 1: "4193 with words" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "4193 with words" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "4193 with words" ("4193" is read in; reading stops because the next character is a non-digit)
 *              ^
 * The parsed integer is 4193.
 * Since 4193 is in the range [-231, 231 - 1], the final result is 4193.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 200
 * s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 * @author sniper
 * @date 30 Mar, 2023
 * LC8, Medium, frequency=19
 * Amazon
 */
public class StringToInteger {

    public static void main(String[] args) {
        String s = " ++1";
        StringToInteger instance = new StringToInteger();
        int res = instance.wrong(s);
        System.out.println(res);
    }

    public int myAtoiV1(String s) {
        int res = 0;
        //todo
        return res;
    }

    /**
     * Understanding the following solution
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int sign = 1;
        int res = 0;
        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        if (i == s.length()) {
            return 0;
        }
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = s.charAt(i) == '+' ? 1 : -1;
            i++;
        }

        while (i < s.length()) {
            int digit = s.charAt(i) - '0';
            if (digit < 0 || digit > 9) {
                break;
            }
            if ((Integer.MAX_VALUE / 10 < res) || (Integer.MAX_VALUE / 10 == res && Integer.MAX_VALUE % 10 < digit)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = 10 * res + digit;
            i++;
        }
        return sign * res;
    }

    /**
     * e.g. s = "words and 987", expected 0
     * e.g. s = "478 and char", expected 478
     * e.g. s = "  -42", expected -42
     * e.g. s = "-91283472332", expected Integer.MIN_VALUE
     * e.g. s = "-", expected 0
     * e.g. s = "+1", expected 1
     * e.g. s = "+-12", expected 0
     * e.g. s = "00000-42a1234", expected 0
     * e.g. s = "   +0 123", expected 0
     * e.g. s = "20000000000000000000", expected 2147483647
     * e.g. s = "2147483646", expected 2147483646
     * e.g. s = "  +  413", expected 0
     * e.g. s = " ++1", expected 0
     * @param s
     * @return
     */
    public int wrong(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int sign = 1;
        boolean metNumBefore = false;
        boolean metCharBefore = false;
        boolean metPositive = false;
        boolean metNegative = false;
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                int digit = c - '0';
                if ((Integer.MAX_VALUE / 10 < res) || (Integer.MAX_VALUE / 10 == res && Integer.MAX_VALUE % 10 < digit)) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                res = 10 * res + digit;
                metNumBefore = true;
            } else {
                if (metNumBefore) {
                    break;
                }
                if (' ' == c) {
                    if (metPositive || metNegative) {
                        break;
                    } else {
                        continue;
                    }
                }
                if ('-' == c) {
                    sign = -1;
                    metNegative = true;
                    continue;
                }
                if ('+' == c) {
                    sign = 1;
                    metPositive = true;
                    continue;
                }
                metCharBefore = true;
            }
        }
        if (metCharBefore) {
            return 0;
        }
        if (metNegative && metPositive) {
            return 0;
        }
        return sign * res;
    }
}
