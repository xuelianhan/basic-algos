package org.ict.algorithm.leetcode.string;

/**
 * You are given a 0-indexed string s that has lowercase English letters
 * in its even indices and digits in its odd indices.
 *
 * There is a function shift(c, x), where c is a character and x is a digit, that returns the xth character after c.
 *
 * For example, shift('a', 5) = 'f' and shift('x', 0) = 'x'.
 * For every odd index i, you want to replace the digit s[i] with shift(s[i-1], s[i]).
 *
 * Return s after replacing all digits. It is guaranteed that shift(s[i-1], s[i]) will never exceed 'z'.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "a1c1e1"
 * Output: "abcdef"
 * Explanation: The digits are replaced as follows:
 * - s[1] -> shift('a',1) = 'b'
 * - s[3] -> shift('c',1) = 'd'
 * - s[5] -> shift('e',1) = 'f'
 * Example 2:
 *
 * Input: s = "a1b2c3d4e"
 * Output: "abbdcfdhe"
 * Explanation: The digits are replaced as follows:
 * - s[1] -> shift('a',1) = 'b'
 * - s[3] -> shift('b',2) = 'd'
 * - s[5] -> shift('c',3) = 'f'
 * - s[7] -> shift('d',4) = 'h'
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists only of lowercase English letters and digits.
 * shift(s[i-1], s[i]) <= 'z' for all odd indices i.
 * @author sniper
 * @date 2022/8/16
 * LC1844
 */
public class ReplaceAllDigitsWithCharacters {

    public static void main(String[] args) {
        char base = 'b';
        int offset = 24;
        char result = shift(base, offset);
        System.out.println(result);
        String s = "a1b2c3d4e21";
        String res = replaceDigits(s);
        System.out.println(res);
    }

    /**
     * Solution provided by lee215
     * s[i] - '0' change a character to integer.
     * shift(c, x) is (char)(c + x) in java.
     * @param s
     * @return
     */
    public String replaceDigitsV2(String s) {
        char[] res = s.toCharArray();
        for (int i = 1; i < s.length(); i += 2) {
            res[i] = (char)(res[i - 1] + res[i] - '0');
        }
        return String.valueOf(res);
    }

    public static String replaceDigits(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        for (int i = 1; i < arr.length; i += 2) {
            char temp = shift(arr[i-1], arr[i] - '0');
            arr[i] = temp;
        }
        return new String(arr);
    }

    public static char shift(char base, int offset) {
        int x = base;
        /**
         * Note that you cannot use x=x+1
         * because it causes an implicit narrowing conversion.
         * You need to use either x++ or x+=1 instead.
         * a~z: 97~122
         */

        x += offset;
        if (x > 122) {
            return 'z';
        }
        return (char)x;
    }
}
