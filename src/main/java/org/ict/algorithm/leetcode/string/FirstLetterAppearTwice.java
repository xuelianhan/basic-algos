package org.ict.algorithm.leetcode.string;

import java.util.Arrays;

/**
 * Given a string s consisting of lowercase English letters, return the first letter to appear twice.
 *
 * Note:
 *
 * A letter a appears twice before another letter b if the second occurrence of a is before the second occurrence of b.
 * s will contain at least one letter that appears twice.
 *
 *
 * Example 1:
 *
 * Input: s = "abccbaacz"
 * Output: "c"
 * Explanation:
 * The letter 'a' appears on the indexes 0, 5 and 6.
 * The letter 'b' appears on the indexes 1 and 4.
 * The letter 'c' appears on the indexes 2, 3 and 7.
 * The letter 'z' appears on the index 8.
 * The letter 'c' is the first letter to appear twice,
 * because out of all the letters the index of its second occurrence is the smallest.
 * Example 2:
 *
 * Input: s = "abcdd"
 * Output: "d"
 * Explanation:
 * The only letter that appears twice is 'd' so we return 'd'.
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 100
 * s consists of lowercase English letters.
 * s has at least one repeated letter.
 * @author sniper
 * @date 13 Aug, 2022
 * LC2351
 */
public class FirstLetterAppearTwice {

    public static void main(String[] args) {
        String s = "abccbaacz";
        char res = repeatedCharacter(s);
        System.out.println(res);
    }

    public static char repeatedCharacter(String s) {
        char[] frequency = new char[26];
        Arrays.fill(frequency, '0');
        char res = '0';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = (int)(c - 'a');
            frequency[idx]++;
            if (frequency[idx] == '2') {
                res = c;
                break;
            }
        }
        return res;
    }

}
